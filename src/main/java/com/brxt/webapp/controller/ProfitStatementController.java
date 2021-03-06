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
	public ProfitStatementModel getProfitStatementModel(final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		ProfitStatement latestPSheet = null;
		ProjectInfo projectInfo = null;
		Counterparty counterparty = null;
		ProfitStatementModel psm = new ProfitStatementModel();
		String statementId = request.getParameter("id");
		String reportTime = request.getParameter("reportTime");
		if (!StringUtils.isBlank(statementId)) {
			latestPSheet = financeSheetManager.getProfitStatement(Long.valueOf(statementId));
			counterparty = latestPSheet.getCounterparty();
			psm.setCounterpartyId(counterparty.getId());
			psm.setCounterpartyType(counterparty.getCounterpartyType());
			projectInfo = latestPSheet.getProjectInfo();
			psm.setProjectId(projectInfo.getId());
			if (findCounterparty(projectInfo, counterparty.getId()) != null) {
				psm.setTradingRelationship(TradingRelationship.COUNTERPARTY);
			} else if (findGuarantor(projectInfo, counterparty.getId()) != null) {
				psm.setTradingRelationship(TradingRelationship.GUARANTOR);
			}
			psm.setCounterpartyName(counterparty.getName());
			psm.setReportTime(getDateObj(latestPSheet.getReportYear(), latestPSheet.getReportMonth()));
		} else {
			String counterpartyIdStr = request.getParameter("counterpartyId");
			String trStr = request.getParameter("type");
			String ctype = request.getParameter("ctype");
			final Locale locale = request.getLocale();
			if (StringUtils.isBlank(projectInfoIdStr) || StringUtils.isBlank(counterpartyIdStr) || StringUtils.isBlank(trStr)
					|| StringUtils.isBlank(ctype)) {
				// Error out;
				saveError(request, "request parameters are not enough");
				log.error("request parameters are not enough");
				return null;
			}

			if (ctype.equalsIgnoreCase(CounterpartyType.INSTITUTION.toString())) {
				saveError(request, getText("budgetStatementForm.error.type", locale));
			}

			TradingRelationship tradingRelationship = TradingRelationship.valueOf(trStr.toUpperCase());
			Long projectInfoId = Long.valueOf(projectInfoIdStr);
			Long counterpartyId = Long.valueOf(counterpartyIdStr);

			psm.setCounterpartyId(counterpartyId);
			psm.setProjectId(projectInfoId);
			psm.setTradingRelationship(tradingRelationship);

			projectInfo = projectInfoManager.get(projectInfoId);
			switch (tradingRelationship) {
			case COUNTERPARTY:
				counterparty = findCounterparty(projectInfo, counterpartyId);
				break;
			case GUARANTOR:
				counterparty = findGuarantor(projectInfo, counterpartyId);
				break;
			default:
			}
			psm.setCounterpartyName(counterparty.getName());
			psm.setCounterpartyType(counterparty.getCounterpartyType());
			
			if(StringUtils.isBlank(reportTime))
			{
				psm.setReportTime(new Date());		
				latestPSheet = financeSheetManager.findProfitStatement(projectInfo, counterparty, getYear(psm.getReportTime()), getMonth(psm.getReportTime()).intValue());
				if(latestPSheet != null)
				{
					saveMessage(request, getText("finance.profitSheet.existed", new String[]{getCurrentYear().toString(), getCurrentMonth().toString()}, request.getLocale()));
					latestPSheet = null;
				} 
			}
		}

		if (latestPSheet == null) {
			latestPSheet = new ProfitStatement();
			latestPSheet.setProjectInfo(projectInfo);
			latestPSheet.setCounterparty(counterparty);
		}
		psm.setEndBalSheet(latestPSheet);
		
		return psm;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("profitStatementModel") ProfitStatementModel profitStatementModel, BindingResult errors,
			final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if (validator != null) {
			validator.validate(profitStatementModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..." + errors.toString());
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

	private void saveProfitStatement(ProfitStatementModel profitStatementModel, final HttpServletRequest request) throws Exception {
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
		endBalSheet.setCounterparty(cp);
		Date reportTime = profitStatementModel.getReportTime();
		endBalSheet.setReportYear(getYear(reportTime));
		endBalSheet.setReportMonth(getMonth(reportTime));
		
		boolean isNew = (endBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		if (isNew) {
			// Add
		    ProfitStatement ps = super.financeSheetManager.findProfitStatement(null, cp, getYear(reportTime), getMonth(reportTime).intValue());
		    if (ps != null) {
		        saveError(request, getText("finance.profitSheet.existed", new String[]{getYear(reportTime).toString(), getMonth(reportTime).toString()}, request.getLocale()));
		        return;
		    }
		    
			endBalSheet.setCreateUser(currentUser.getUsername());
			endBalSheet.setCreateTime(new Date());
		} else {
			endBalSheet.setUpdateUser(currentUser.getUsername());
			endBalSheet.setUpdateTime(new Date());
		}
		financeSheetManager.saveProfitStatement(endBalSheet);
		updateProjectInfoStatus(projectInfo, true);
		saveMessage(request, getText("profitStatement.updated", locale));
	}

}
