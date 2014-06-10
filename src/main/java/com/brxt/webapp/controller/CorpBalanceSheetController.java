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
import com.brxt.model.enums.TradingRelationship;
import com.brxt.model.finance.CorpBalanceSheetModel;
import com.brxt.model.finance.CorporateBalanceSheet;

@Controller
@RequestMapping("/finance/corpBalanceSheet*")
public class CorpBalanceSheetController extends BaseSheetController {

	public CorpBalanceSheetController() {
		setCancelView("/finace/corpBalanceSheet");
		setSuccessView("/finance/corpBalanceSheet");
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return getSuccessView();
	}
	
	@ModelAttribute("corpBalanceSheetModel")
	public CorpBalanceSheetModel getCorpBalanceSheetModel(
			final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session
				.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		String counterpartyIdStr = request.getParameter("counterpartyId");
		String trStr = request.getParameter("type");
		String ctypeStr = request.getParameter("ctype");
		if (StringUtils.isBlank(projectInfoIdStr)
				|| StringUtils.isBlank(counterpartyIdStr)
				|| StringUtils.isBlank(trStr) || StringUtils.isBlank(ctypeStr)) {
			// Error out;
			saveError(request, "request parameters are not enough"); 
			log.error("request parameters are not enough");
			return null;
		}

		TradingRelationship tradingRelationship = TradingRelationship
				.valueOf(trStr.toUpperCase());
		Long projectInfoId = Long.valueOf(projectInfoIdStr);
		Long counterpartyId = Long.valueOf(counterpartyIdStr);

		CorpBalanceSheetModel csm = new CorpBalanceSheetModel();
		csm.setCounterpartyId(counterpartyId);
		csm.setProjectId(projectInfoId);
		csm.setTradingRelationship(tradingRelationship);

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
		csm.setCounterpartyName(cpObj.getName());
		CorporateBalanceSheet beginBalSheet = financeSheetManager.findCorporateBalanceSheet(projectInfo, cpObj, getCurrentYear(), 0);
		if(beginBalSheet == null)
		{
			beginBalSheet = new CorporateBalanceSheet();
			beginBalSheet.setProjectInfo(projectInfo);
			beginBalSheet.setCounterparty(cpObj);
		}
		
		CorporateBalanceSheet endBalSheet =  financeSheetManager.findCorporateBalanceSheet(projectInfo, cpObj, getCurrentYear(), getCurrentMonth());
		if(endBalSheet == null) {
			endBalSheet = new CorporateBalanceSheet();
			endBalSheet.setProjectInfo(projectInfo);
			endBalSheet.setCounterparty(cpObj);
		}
		
		csm.setBeginBalSheet(beginBalSheet);
		csm.setEndBalSheet(endBalSheet);
		csm.setReportTime(new Date());
		return csm;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("corpBalanceSheetModel") CorpBalanceSheetModel corpBalanceSheetModel,
			BindingResult errors, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");		
		if (validator != null) {
			validator.validate(corpBalanceSheetModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..." + errors.toString());
				saveMessage(request, errors.toString());
				return getCancelView();
			}
		}

		if (method != null) {
			switch (method) {
			case "Save":
				saveCorpBalanceSheet(corpBalanceSheetModel, request);
				break;
			default:
			}
		} else {
			// error
			log.error("The method is empty");
			return getCancelView();
		}
		return getSuccessView();
	}

	private void saveCorpBalanceSheet(
			CorpBalanceSheetModel corpBalanceSheetModel,
			final HttpServletRequest request) throws Exception {
		final Locale locale = request.getLocale();
		Long projectInfoId = corpBalanceSheetModel.getProjectId();
		Long counterpartyId = corpBalanceSheetModel.getCounterpartyId();
		if(projectInfoId == null || counterpartyId == null)
		{
			//Error out
			saveError(request, "request parameters are not enough");
			log.error("request parameters are not enough");
			return;
		}
		
		ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
		Counterparty cp = findCounterparty(projectInfo, counterpartyId);
		if(cp == null)
		{
			cp = findGuarantor(projectInfo, counterpartyId);
		}
		
		CorporateBalanceSheet beginBalSheet = corpBalanceSheetModel
				.getBeginBalSheet();
		beginBalSheet.setProjectInfo(projectInfo);
		beginBalSheet.setCounterparty(cp);
		CorporateBalanceSheet endBalSheet = corpBalanceSheetModel
				.getEndBalSheet();
		endBalSheet.setProjectInfo(projectInfo);
		endBalSheet.setCounterparty(cp);

		boolean isNewBeginBalSheet = (beginBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		if (isNewBeginBalSheet) {
			// Add
			beginBalSheet.setReportYear(getCurrentYear());
			beginBalSheet.setReportMonth((short) 0);
			beginBalSheet.setCreateUser(currentUser.getUsername());
			beginBalSheet.setCreateTime(new Date());
		} else {
			beginBalSheet.setUpdateUser(currentUser.getUsername());
			beginBalSheet.setUpdateTime(new Date());
		}

		boolean isNewEndBalSheet = (endBalSheet.getId() == null);
		if (isNewEndBalSheet) {
			// Add
			endBalSheet.setReportYear(getCurrentYear());
			endBalSheet.setReportMonth(getCurrentMonth().shortValue());
			endBalSheet.setCreateUser(currentUser.getUsername());
			endBalSheet.setCreateTime(new Date());
		} else {
			endBalSheet.setUpdateUser(currentUser.getUsername());
			endBalSheet.setUpdateTime(new Date());
		}

		financeSheetManager.saveCorpBalanceSheets(beginBalSheet, endBalSheet);
		saveMessage(request, getText("corpBalanceSheet.updated", locale));
	}

}
