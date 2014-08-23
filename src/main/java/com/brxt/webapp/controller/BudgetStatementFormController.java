package com.brxt.webapp.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
import com.brxt.model.finance.BudgetStatement;
import com.brxt.model.finance.BudgetStatementModel;

@Controller
@RequestMapping("/finance/budgetStatementForm*")
public class BudgetStatementFormController extends BaseSheetController {

	private static final MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
	
	public BudgetStatementFormController() {
		setCancelView("/finance/budgetStatementForm");
		setSuccessView("/finance/budgetStatementForm");
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String showForm() {
		return getSuccessView();
	}

	@ModelAttribute("budgetStatementModel")
	public BudgetStatementModel getBudgetStatementModel(
			final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session
				.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		BudgetStatement thisYear = null;
		ProjectInfo projectInfo = null;
		Counterparty counterparty = null;
		BudgetStatementModel bsm = new BudgetStatementModel();
		String statementId = request.getParameter("id");
		String reportTime = request.getParameter("reportTime");
		if(!StringUtils.isBlank(statementId))
		{
			thisYear = financeSheetManager.getBudgetStatement(Long.valueOf(statementId));
			counterparty = thisYear.getCounterparty();
			bsm.setCounterpartyId(counterparty.getId());
			bsm.setCounterpartyType(counterparty.getCounterpartyType());
			projectInfo = projectInfoManager.get(Long.valueOf(projectInfoIdStr));
			bsm.setProjectId(projectInfo.getId());
			if(findCounterparty(projectInfo, counterparty.getId()) != null)
			{
				bsm.setTradingRelationship(TradingRelationship.COUNTERPARTY);
			}
			else if(findGuarantor(projectInfo, counterparty.getId()) != null)
			{
				bsm.setTradingRelationship(TradingRelationship.GUARANTOR);
			}
			bsm.setCounterpartyName(counterparty.getName());
			bsm.setReportTime(getDateObj(thisYear.getReportYear(), thisYear.getReportMonth()));
		}
		else
		{
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

			if(ctype.equalsIgnoreCase(CounterpartyType.COMMERCE_COMPANY.toString()) || ctype.equalsIgnoreCase(CounterpartyType.REAL_ESTATE_FIRM.toString()))
			{
				saveError(request, getText("budgetStatementForm.error.type", locale));
			}
			
			TradingRelationship tradingRelationship = TradingRelationship
					.valueOf(trStr.toUpperCase());
			Long projectInfoId = Long.valueOf(projectInfoIdStr);
			Long counterpartyId = Long.valueOf(counterpartyIdStr);
			bsm.setCounterpartyId(counterpartyId);
			bsm.setProjectId(projectInfoId);
			bsm.setTradingRelationship(tradingRelationship);
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

			bsm.setCounterpartyName(counterparty.getName());
			bsm.setCounterpartyType(counterparty.getCounterpartyType());
			
			if(StringUtils.isBlank(reportTime))
			{
				bsm.setReportTime(new Date());				
				thisYear = financeSheetManager.findBudgetStatement(projectInfo, counterparty, getCurrentYear(), getCurrentMonth());
				if(thisYear != null)
				{
					saveMessage(request, getText("finance.budgetSheet.existed", new String[]{ getCurrentYear().toString(), getCurrentMonth().toString()}, request.getLocale()));
					thisYear = null;
				} 
			}		
		}
		
		bsm.setThisYear(thisYear);
		
		int year = getCurrentYear();
		if(thisYear != null && thisYear.getReportYear() != null)
		{
			year = thisYear.getReportYear();
		}
		
		int month = getCurrentMonth();
		if(thisYear != null && thisYear.getReportMonth() != null)
		{
			month = thisYear.getReportMonth();
		}
		
		BudgetStatement thisYearBudget = financeSheetManager.findBudgetStatement(projectInfo, counterparty, year, 0);
		bsm.setThisYearBudget(thisYearBudget);
		BudgetStatement lastYear = financeSheetManager.findBudgetStatement(projectInfo, counterparty, year - 1, month);
		bsm.setLastYear(lastYear);
		BudgetStatement budgetRatio = calculateBudgetRatio(thisYearBudget, thisYear);
		bsm.setBudgetRatio(budgetRatio);
		BudgetStatement growthRate = calculateGrowthRate(lastYear, thisYear);
		bsm.setGrowthRate(growthRate);
		return bsm;
	}
	
	private BudgetStatement calculateBudgetRatio(BudgetStatement thisYearBudget, BudgetStatement thisYear)
	{
		BudgetStatement budgetRatio = new BudgetStatement();
		if(thisYearBudget == null || thisYear == null)
		{
			return budgetRatio;
		}
		
		budgetRatio.setBudgetIncomeTotal(calculateRatio(thisYear.getBudgetIncomeTotal(), thisYearBudget.getBudgetIncomeTotal()));
		budgetRatio.setBudgetPayTotal(calculateRatio(thisYear.getBudgetPayTotal(), thisYearBudget.getBudgetPayTotal()));
		budgetRatio.setGovFundIncome(calculateRatio(thisYear.getGovFundIncome(), thisYearBudget.getGovFundIncome()));
		budgetRatio.setGovFundPayment(calculateRatio(thisYear.getGovFundPayment(), thisYearBudget.getGovFundPayment()));
		budgetRatio.setIncomeTotal(calculateRatio(thisYear.getIncomeTotal(), thisYearBudget.getIncomeTotal()));
		budgetRatio.setNonTaxIncome(calculateRatio(thisYear.getNonTaxIncome(), thisYearBudget.getNonTaxIncome()));
		budgetRatio.setPaymentTotal(calculateRatio(thisYear.getPaymentTotal(), thisYearBudget.getPaymentTotal()));
		budgetRatio.setTaxIncome(calculateRatio(thisYear.getTaxIncome(), thisYearBudget.getTaxIncome()));
		return budgetRatio;
	}
	
	private final BigDecimal calculateRatio(BigDecimal number, BigDecimal baseNumber)
	{
		if(number == null || baseNumber == null)
		{
			return null;
		}
		
		if(!baseNumber.equals(BigDecimal.ZERO))
		{
			return number.divide(baseNumber, mc);
		}
		
		return null;
	}
	
	private BudgetStatement calculateGrowthRate(BudgetStatement lastYear, BudgetStatement thisYear)
	{
		BudgetStatement growthRate = new BudgetStatement();
		if(lastYear == null || thisYear == null)
		{
			return growthRate;
		}
		
		growthRate.setBudgetIncomeTotal(calculateRate(thisYear.getBudgetIncomeTotal(), lastYear.getBudgetIncomeTotal()));
		growthRate.setBudgetPayTotal(calculateRate(thisYear.getBudgetPayTotal(), lastYear.getBudgetPayTotal()));
		growthRate.setGovFundIncome(calculateRate(thisYear.getGovFundIncome(), lastYear.getGovFundIncome()));
		growthRate.setGovFundPayment(calculateRate(thisYear.getGovFundPayment(), lastYear.getGovFundPayment()));
		growthRate.setIncomeTotal(calculateRate(thisYear.getIncomeTotal(), lastYear.getIncomeTotal()));
		growthRate.setNonTaxIncome(calculateRate(thisYear.getNonTaxIncome(), lastYear.getNonTaxIncome()));
		growthRate.setPaymentTotal(calculateRate(thisYear.getPaymentTotal(), lastYear.getPaymentTotal()));
		growthRate.setTaxIncome(calculateRate(thisYear.getTaxIncome(), lastYear.getTaxIncome()));
		return growthRate;
	}
	
	private final BigDecimal calculateRate(BigDecimal number, BigDecimal baseNumber)
	{
		if(number == null || baseNumber == null)
		{
			return null;
		}
		
		if(!baseNumber.equals(BigDecimal.ZERO))
		{
			return number.subtract(baseNumber).divide(baseNumber, mc);
		}
		
		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("budgetStatementModel") BudgetStatementModel budgetStatementModel,
			BindingResult errors, final HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		if (validator != null) { 
			validator.validate(budgetStatementModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..." + errors.toString());
				saveMessage(request, errors.toString());
				return getCancelView();
			}
		}

		if (method != null) {
			switch (method) {
			case "Save":
				saveBudgetStatement(budgetStatementModel, request);
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

	private void saveBudgetStatement(BudgetStatementModel budgetStatementModel, HttpServletRequest request)
			throws Exception {
		final Locale locale = request.getLocale();
		Long projectInfoId = budgetStatementModel.getProjectId();
		Long counterpartyId = budgetStatementModel.getCounterpartyId();
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
		Date reportTime = budgetStatementModel.getReportTime();
		User currentUser = getCurrentUser();
		
		BudgetStatement thisYear = budgetStatementModel.getThisYear();
        thisYear.setCounterparty(cp);
        thisYear.setReportYear(getYear(reportTime));
        thisYear.setReportMonth(getMonth(reportTime));
        
        boolean isNewThisYear = (thisYear.getId() == null);
        if (isNewThisYear) {
            // Add
            BudgetStatement bs = financeSheetManager.findBudgetStatement(null, cp, getYear(reportTime), getMonth(reportTime).intValue());
            if(bs != null) {
                saveError(request, getText("finance.budgetSheet.existed", new String[]{ getYear(reportTime).toString(), getMonth(reportTime).toString()}, request.getLocale()));
                return;
            }
            thisYear.setCreateUser(currentUser.getUsername());
            thisYear.setCreateTime(new Date());
        } else {
            thisYear.setUpdateUser(currentUser.getUsername());
            thisYear.setUpdateTime(new Date());
        }
		
		BudgetStatement thisYearBudget = budgetStatementModel.getThisYearBudget();
		thisYearBudget.setCounterparty(cp);
		if(thisYearBudget.getReportYear() != null && thisYearBudget.getReportYear().intValue() != getYear(reportTime).intValue())
		{
			thisYearBudget.setId(null);
		}
		thisYearBudget.setReportYear(getYear(reportTime));			
		thisYearBudget.setReportMonth((short) 0);
		
		boolean isNewThisYearBudget = (thisYearBudget.getId() == null);
		if (isNewThisYearBudget) {
			// Add
			thisYearBudget.setCreateUser(currentUser.getUsername());
			thisYearBudget.setCreateTime(new Date());
		} else {
			thisYearBudget.setUpdateUser(currentUser.getUsername());
			thisYearBudget.setUpdateTime(new Date());
		}

		financeSheetManager.saveBudgetStatements(thisYearBudget, thisYear);
		updateProjectInfoStatus(projectInfo, true);
		BudgetStatement budgetRatio = calculateBudgetRatio(thisYearBudget, thisYear);
		budgetStatementModel.setBudgetRatio(budgetRatio);
		BudgetStatement lastYear = budgetStatementModel.getLastYear();
		BudgetStatement growthRate = calculateGrowthRate(lastYear, thisYear);
		budgetStatementModel.setGrowthRate(growthRate);
		saveMessage(request, getText("budgetStatementForm.updated", locale));
	}

}
