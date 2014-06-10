package com.brxt.webapp.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.appfuse.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.finance.ProfitStatementModel;

@Controller
@RequestMapping("/finance/profitStatement*")
public class ProfitStatementController extends BaseSheetController {

	public ProfitStatementController() {
		setCancelView("/finance/profitStatement");
		setSuccessView("/finance/profitStatement");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return getSuccessView();
	}

	@ModelAttribute("profitStatementModel")
	public ProfitStatementModel getProfitStatementModel(
			final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session
				.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		String counterpartyIdStr = request.getParameter("counterpartyId");
		String trStr = request.getParameter("type");
		String ctype = request.getParameter("ctype");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoIdStr)
				|| StringUtils.isBlank(counterpartyIdStr)
				|| StringUtils.isBlank(trStr) || StringUtils.isBlank(ctype)) {
			// Error out;
			saveError(request, "request parameters are not enough");
			log.error("request parameters are not enough");
			return null;
		}

		if (ctype.equalsIgnoreCase(CounterpartyType.INSTITUTION.toString())) {
			saveError(request,
					getText("budgetStatementForm.error.type", locale));
		}

		TradingRelationship tradingRelationship = TradingRelationship
				.valueOf(trStr.toUpperCase());
		Long projectInfoId = Long.valueOf(projectInfoIdStr);
		Long counterpartyId = Long.valueOf(counterpartyIdStr);
		ProfitStatementModel psm = new ProfitStatementModel();
		psm.setCounterpartyId(counterpartyId);
		psm.setProjectId(projectInfoId);
		psm.setTradingRelationship(tradingRelationship);

		ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
		Counterparty cpObj = null;
		switch (tradingRelationship) {
		case COUNTERPARTY:
			cpObj = findCounterparty(projectInfo, counterpartyId);
			break;
		case GUARANTOR:
			cpObj = findGuarantor(projectInfo, counterpartyId);
			break;
		default:
		}
		psm.setCounterpartyName(cpObj.getName());
		ProfitStatement latestPSheet = financeSheetManager.findProfitStatement(
				projectInfo, cpObj, getCurrentYear(), getCurrentMonth());
		if (latestPSheet == null) {
			latestPSheet = new ProfitStatement();
			latestPSheet.setProjectInfo(projectInfo);
			latestPSheet.setCounterparty(cpObj);
		}
		psm.setEndBalSheet(latestPSheet);
		psm.setReportTime(new Date());
		return psm;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("profitStatementModel") ProfitStatementModel profitStatementModel,
			BindingResult errors, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if (validator != null) {
			validator.validate(profitStatementModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				return getCancelView();
			}
		}

		if (method != null) {
			switch (method) {
			case "Save":
				saveProfitStatement(profitStatementModel, request);
				break;
			default:
			}
		} else {
			log.error("The method is empty");
			return getCancelView();
		}
		return getSuccessView();
	}

	private void saveProfitStatement(ProfitStatementModel profitStatementModel,
			final HttpServletRequest request) throws Exception {
		final Locale locale = request.getLocale();
		Long projectInfoId = profitStatementModel.getProjectId();
		Long counterpartyId = profitStatementModel.getCounterpartyId();
		if (projectInfoId == null || counterpartyId == null) {
			// Error out
			saveError(request, "request parameters are not enough");
			log.error("request parameters are not enough");
			return;
		}
		ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
		Counterparty cp = findCounterparty(projectInfo, counterpartyId);
		if (cp == null) {
			cp = findGuarantor(projectInfo, counterpartyId);
		}

		ProfitStatement endBalSheet = profitStatementModel.getEndBalSheet();

		boolean isNew = (endBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		if (isNew) {
			// Add
			endBalSheet.setReportYear(getCurrentYear());
			endBalSheet.setReportMonth(getCurrentMonth().shortValue());
			endBalSheet.setCreateUser(currentUser.getUsername());
			endBalSheet.setCreateTime(new Date());
		} else {
			endBalSheet.setUpdateUser(currentUser.getUsername());
			endBalSheet.setUpdateTime(new Date());
		}
		financeSheetManager.saveProfitStatement(endBalSheet);
		saveMessage(request, getText("profitStatement.updated", locale));
	}

}
