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
		String projectInfoIdStr = (String) session.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		CorporateBalanceSheet endBalSheet = null;
		ProjectInfo projectInfo = null;
		Counterparty counterparty = null;
		CorpBalanceSheetModel csm = new CorpBalanceSheetModel();
		String statementId = request.getParameter("id");
		String reportTime = request.getParameter("reportTime");
		if(!StringUtils.isBlank(statementId))
		{
			endBalSheet = financeSheetManager.getCorpBalanceSheet(Long.valueOf(statementId));
			counterparty = endBalSheet.getCounterparty();
			csm.setCounterpartyId(counterparty.getId());
			csm.setCounterpartyType(counterparty.getCounterpartyType());
			projectInfo = endBalSheet.getProjectInfo();
			csm.setProjectId(projectInfo.getId());
			if(findCounterparty(projectInfo, counterparty.getId()) != null)
			{
				csm.setTradingRelationship(TradingRelationship.COUNTERPARTY);
			}
			else if(findGuarantor(projectInfo, counterparty.getId()) != null)
			{
				csm.setTradingRelationship(TradingRelationship.GUARANTOR);
			}
			csm.setCounterpartyName(counterparty.getName());
			csm.setReportTime(getDateObj(endBalSheet.getReportYear(), endBalSheet.getReportMonth()));
		}
		else
		{
			//Add
			String counterpartyIdStr = request.getParameter("counterpartyId");
			String trStr = request.getParameter("type");
			if (StringUtils.isBlank(projectInfoIdStr)
					|| StringUtils.isBlank(counterpartyIdStr)
					|| StringUtils.isBlank(trStr)) {
				// Error out;
				saveError(request, "request parameters are not enough"); 
				log.error("request parameters are not enough");
				return null;
			}
			TradingRelationship tradingRelationship = TradingRelationship
					.valueOf(trStr.toUpperCase());
			Long projectInfoId = Long.valueOf(projectInfoIdStr);
			Long counterpartyId = Long.valueOf(counterpartyIdStr);
			
			csm.setCounterpartyId(counterpartyId);
			
			csm.setProjectId(projectInfoId);
			csm.setTradingRelationship(tradingRelationship);
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
			csm.setCounterpartyName(counterparty.getName());
			csm.setCounterpartyType(counterparty.getCounterpartyType());
			if(StringUtils.isBlank(reportTime))
			{
				csm.setReportTime(new Date());				
				endBalSheet = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty, getYear(csm.getReportTime()), getMonth(csm.getReportTime()).intValue());
				if(endBalSheet != null)
				{
					saveMessage(request, getText("finance.balSheet.existed", new String[]{ getCurrentYear().toString(), getCurrentMonth().toString()}, request.getLocale()));
					endBalSheet = null;
				} 
			}					
		}
		
		int year = getCurrentYear();
		if(endBalSheet != null && endBalSheet.getReportYear() != null)
		{
			year = endBalSheet.getReportYear();
		}
		
		CorporateBalanceSheet beginBalSheet = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty, year, 0);
		if(beginBalSheet == null)
		{
			beginBalSheet = new CorporateBalanceSheet();
			beginBalSheet.setProjectInfo(projectInfo);
			beginBalSheet.setCounterparty(counterparty);
		}
		csm.setBeginBalSheet(beginBalSheet);
		
		if(endBalSheet == null) {
			endBalSheet = new CorporateBalanceSheet();
			endBalSheet.setProjectInfo(projectInfo);
			endBalSheet.setCounterparty(counterparty);
		}
		csm.setEndBalSheet(endBalSheet);
		
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

		Date reportTime = corpBalanceSheetModel.getReportTime();
		if(beginBalSheet.getReportYear() != null && beginBalSheet.getReportYear().intValue() != getYear(reportTime).intValue() )
		{
			beginBalSheet.setId(null);
		}
		beginBalSheet.setReportYear(getYear(reportTime));			
		beginBalSheet.setReportMonth((short) 0);
		endBalSheet.setReportYear(getYear(reportTime));
		endBalSheet.setReportMonth(getMonth(reportTime));
		
		boolean isNewBeginBalSheet = (beginBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		if (isNewBeginBalSheet) {
			// Add
			beginBalSheet.setCreateUser(currentUser.getUsername());
			beginBalSheet.setCreateTime(new Date());
		} else {
			beginBalSheet.setUpdateUser(currentUser.getUsername());
			beginBalSheet.setUpdateTime(new Date());
		}

		boolean isNewEndBalSheet = (endBalSheet.getId() == null);
		if (isNewEndBalSheet) {
			// Add
			endBalSheet.setCreateUser(currentUser.getUsername());
			endBalSheet.setCreateTime(new Date());
		} else {
			endBalSheet.setUpdateUser(currentUser.getUsername());
			endBalSheet.setUpdateTime(new Date());
		}

		financeSheetManager.saveCorpBalanceSheets(beginBalSheet, endBalSheet);
		updateProjectInfoStatus(projectInfo, true);
		if (isNewEndBalSheet) {
			saveMessage(request, getText("corpBalanceSheet.added", new String[]{endBalSheet.getReportYear().toString(), endBalSheet.getReportMonth().toString()}, locale));
		}
		else
		{
			saveMessage(request, getText("corpBalanceSheet.updated", new String[]{endBalSheet.getReportYear().toString(), endBalSheet.getReportMonth().toString()}, locale));
		}
	}

}
