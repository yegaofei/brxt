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
import com.brxt.model.finance.InstBalanceSheetModel;
import com.brxt.model.finance.InstituteBalanceSheet;

@Controller
@RequestMapping("/finance/instBalanceSheet*")
public class InstBalanceSheetController extends BaseSheetController {

	public InstBalanceSheetController() {
		setCancelView("/finance/instBalanceSheet");
		setSuccessView("/finance/instBalanceSheet");
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String showForm() {
		return getSuccessView();
	}

	@ModelAttribute("instBalanceSheetModel")
	public InstBalanceSheetModel getInstBalanceSheetModel(final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		InstituteBalanceSheet endSheet = null;
		ProjectInfo projectInfo = null;
		Counterparty counterparty = null;
		InstBalanceSheetModel ibs = new InstBalanceSheetModel();
		String statementId = request.getParameter("id");
		String reportTime = request.getParameter("reportTime");
		if (!StringUtils.isBlank(statementId)) {
			endSheet = financeSheetManager.getInstituteBalanceSheet(Long.valueOf(statementId));
			counterparty = endSheet.getCounterparty();
			ibs.setCounterpartyId(counterparty.getId());
			ibs.setCounterpartyType(counterparty.getCounterpartyType());
			projectInfo = endSheet.getProjectInfo();
			ibs.setProjectId(projectInfo.getId());
			if (findCounterparty(projectInfo, counterparty.getId()) != null) {
				ibs.setTradingRelationship(TradingRelationship.COUNTERPARTY);
			} else if (findGuarantor(projectInfo, counterparty.getId()) != null) {
				ibs.setTradingRelationship(TradingRelationship.GUARANTOR);
			}
			ibs.setCounterpartyName(counterparty.getName());
			ibs.setReportTime(getDateObj(endSheet.getReportYear(), endSheet.getReportMonth()));
		} else {
			String counterpartyIdStr = request.getParameter("counterpartyId");
			String trStr = request.getParameter("type");
			if (StringUtils.isBlank(projectInfoIdStr) || StringUtils.isBlank(counterpartyIdStr) || StringUtils.isBlank(trStr)) {
				// Error out;
				saveError(request, "request parameters are not enough");
				log.error("request parameters are not enough");
				return null;
			}
			TradingRelationship tradingRelationship = TradingRelationship.valueOf(trStr.toUpperCase());
			Long projectInfoId = Long.valueOf(projectInfoIdStr);
			Long counterpartyId = Long.valueOf(counterpartyIdStr);

			ibs.setCounterpartyId(counterpartyId);
			ibs.setProjectId(projectInfoId);
			ibs.setTradingRelationship(tradingRelationship);

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

			ibs.setCounterpartyName(counterparty.getName());
			ibs.setCounterpartyType(counterparty.getCounterpartyType());
			
			if(StringUtils.isBlank(reportTime))
			{
				ibs.setReportTime(new Date());				
				endSheet = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty, getCurrentYear(), getCurrentMonth());
				if(endSheet != null)
				{
					saveMessage(request, getText("finance.balSheet.existed", new String[]{ getCurrentYear().toString(), getCurrentMonth().toString()}, request.getLocale()));
					endSheet = null;
				} 
			}	
		}

		int year = getCurrentYear();
		if (endSheet != null && endSheet.getReportYear() != null) {
			year = endSheet.getReportYear();
		}

		InstituteBalanceSheet beginSheet = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty, year, 0);
		if (beginSheet == null) {
			beginSheet = new InstituteBalanceSheet();
			beginSheet.setProjectInfo(projectInfo);
			beginSheet.setCounterparty(counterparty);
		}
		ibs.setBeginBalSheet(beginSheet);

		if (endSheet == null) {
			endSheet = new InstituteBalanceSheet();
			endSheet.setProjectInfo(projectInfo);
			endSheet.setCounterparty(counterparty);
		}
		ibs.setEndBalSheet(endSheet);

		return ibs;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("instBalanceSheetModel") InstBalanceSheetModel instBalanceSheetModel, BindingResult errors,
			final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if (validator != null) { // validator is null during testing
			validator.validate(instBalanceSheetModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..." + errors.toString());
				saveMessage(request, errors.toString());
				return getCancelView();
			}
		}
		if (method != null) {
			switch (method) {
			case "Save":
				saveInstBalanceSheet(instBalanceSheetModel, request);
				break;
			default:
				// Error
			}
		} else {
			// error
			log.error("The method is empty");
			return getCancelView();
		}
		return getSuccessView();
	}

	private void saveInstBalanceSheet(InstBalanceSheetModel instBalanceSheetModel, HttpServletRequest request) throws Exception {
		final Locale locale = request.getLocale();
		Long projectInfoId = instBalanceSheetModel.getProjectId();
		Long counterpartyId = instBalanceSheetModel.getCounterpartyId();
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

		InstituteBalanceSheet beginBalSheet = instBalanceSheetModel.getBeginBalSheet();
		beginBalSheet.setProjectInfo(projectInfo);
		beginBalSheet.setCounterparty(cp);

		InstituteBalanceSheet endBalSheet = instBalanceSheetModel.getEndBalSheet();
		endBalSheet.setProjectInfo(projectInfo);
		endBalSheet.setCounterparty(cp);
		
		Date reportTime = instBalanceSheetModel.getReportTime();
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

		financeSheetManager.saveInstituteBalanceSheets(beginBalSheet, endBalSheet);
		updateProjectInfoStatus(projectInfo, true);
		saveMessage(request, getText("instBalanceSheet.updated", locale));
	}

}
