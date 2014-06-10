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

		BudgetStatementModel bsm = new BudgetStatementModel();
		bsm.setCounterpartyId(counterpartyId);
		bsm.setProjectId(projectInfoId);

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

		bsm.setCounterpartyName(cpObj.getName());
		BudgetStatement thisYearBudget = financeSheetManager.findBudgetStatement(projectInfo, cpObj, getCurrentYear(), 0);
		bsm.setThisYearBudget(thisYearBudget);
		BudgetStatement thisYear = financeSheetManager.findBudgetStatement(projectInfo, cpObj, getCurrentYear(), getCurrentMonth());
		bsm.setThisYear(thisYear);
		BudgetStatement lastYear = financeSheetManager.findBudgetStatement(projectInfo, cpObj, getCurrentYear() - 1, getCurrentMonth());
		bsm.setLastYear(lastYear);
		BudgetStatement budgetRatio = calculateBudgetRatio(thisYearBudget, thisYear);
		bsm.setBudgetRatio(budgetRatio);
		BudgetStatement growthRate = calculateGrowthRate(lastYear, thisYear);
		bsm.setGrowthRate(growthRate);
		bsm.setReportTime(new Date());
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
			return number.subtract(baseNumber).divide(baseNumber);
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
		
		BudgetStatement thisYearBudget = budgetStatementModel.getThisYearBudget();
		thisYearBudget.setProjectInfo(projectInfo);
		thisYearBudget.setCounterparty(cp);
		
		boolean isNewThisYearBudget = (thisYearBudget.getId() == null);
		User currentUser = getCurrentUser();
		if (isNewThisYearBudget) {
			// Add
			thisYearBudget.setReportYear(getCurrentYear());
			thisYearBudget.setReportMonth((short) 0);
			thisYearBudget.setCreateUser(currentUser.getUsername());
			thisYearBudget.setCreateTime(new Date());
		} else {
			thisYearBudget.setUpdateUser(currentUser.getUsername());
			thisYearBudget.setUpdateTime(new Date());
		}

		BudgetStatement thisYear = budgetStatementModel.getThisYear();
		thisYear.setProjectInfo(projectInfo);
		thisYear.setCounterparty(cp);
		boolean isNewThisYear = (thisYear.getId() == null);
		if (isNewThisYear) {
			// Add
			thisYear.setReportYear(getCurrentYear());
			thisYear.setReportMonth(getCurrentMonth().shortValue());
			thisYear.setCreateUser(currentUser.getUsername());
			thisYear.setCreateTime(new Date());
		} else {
			thisYear.setUpdateUser(currentUser.getUsername());
			thisYear.setUpdateTime(new Date());
		}
		
		financeSheetManager.saveBudgetStatements(thisYearBudget, thisYear);
		BudgetStatement budgetRatio = calculateBudgetRatio(thisYearBudget, thisYear);
		budgetStatementModel.setBudgetRatio(budgetRatio);
		BudgetStatement lastYear = budgetStatementModel.getLastYear();
		BudgetStatement growthRate = calculateGrowthRate(lastYear, thisYear);
		budgetStatementModel.setGrowthRate(growthRate);
		saveMessage(request, getText("budgetStatementForm.updated", locale));
	}

}
