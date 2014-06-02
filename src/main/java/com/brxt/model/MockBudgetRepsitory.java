package com.brxt.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MockBudgetRepsitory {
	private Map<String, Map<String,BudgetStatement>> savedBudgetStatement = new TreeMap<String, Map<String,BudgetStatement>>();
	private static MockBudgetRepsitory mockBudgetRepsitory = new MockBudgetRepsitory();
	
	private MockBudgetRepsitory(){
		
	}
	
	public static MockBudgetRepsitory getInstance(){
		return mockBudgetRepsitory;
	}
	
	public synchronized void addOrUpdateBudget(BudgetStatement budgetStatement){
		if (budgetStatement.getReportMonth() == null || budgetStatement.getReportMonth().length() !=6)
			return;
		String reportYear = budgetStatement.getReportMonth().substring(0, 4);
		Map<String,BudgetStatement> oneYear = savedBudgetStatement.get(reportYear);
		if (oneYear == null){
			oneYear = new TreeMap<String,BudgetStatement>();
			oneYear.put(budgetStatement.getReportMonth(), budgetStatement);
			savedBudgetStatement.put(reportYear, oneYear);
		}
		else {
			oneYear.put(budgetStatement.getReportMonth(), budgetStatement);
		}
	}
	
	public synchronized void deleteBudget(BudgetStatement budgetStatement){
		if (budgetStatement.getReportMonth() == null || budgetStatement.getReportMonth().length() !=6)
			return;
		String reportYear = budgetStatement.getReportMonth().substring(0, 4);		
		Map<String,BudgetStatement> oneYear = savedBudgetStatement.get(reportYear);
		if (oneYear != null){
			oneYear.remove(budgetStatement.getReportMonth());
		}		
	}
	
	public synchronized List<BudgetStatement> listAllBudget(){
		List<BudgetStatement> allYear = new ArrayList<BudgetStatement>();
		for (Map<String,BudgetStatement> oneYear : savedBudgetStatement.values()){
			allYear.addAll(oneYear.values());
		}
		return allYear;
	}
	
	public synchronized BudgetStatementModel getBudgetInfo(String reportYear){
		if (reportYear.length() !=4)
			return new BudgetStatementModel();		
		
		BudgetStatement fullYearBudget = getFullYearBudget(reportYear);
		BudgetStatement thisYear = getThisYear(reportYear);
		BudgetStatement lastYear = getLastYear(reportYear);
		BudgetStatement budgetRatio = getBudgetRatio(thisYear,fullYearBudget);
		BudgetStatement growthRate = getGrowthRate(thisYear, lastYear);
		
		BudgetStatementModel budgetStatementModel= new BudgetStatementModel();
		budgetStatementModel.setFullYearBudget(fullYearBudget);
		budgetStatementModel.setThisYear(thisYear);
		budgetStatementModel.setLastYear(lastYear);
		budgetStatementModel.setBudgetRatio(budgetRatio);
		budgetStatementModel.setGrowthRate(growthRate);			
		
		budgetStatementModel.setReportName(thisYear.getReportName());
		budgetStatementModel.setProjectId(thisYear.getProjectId());
		budgetStatementModel.setCounterpartyId(thisYear.getCounterpartyId());
		budgetStatementModel.setCounterpartyName("testCounterParty");
		budgetStatementModel.setProjectName("testProjectName");
		budgetStatementModel.setReportYear(reportYear);		
		
		return budgetStatementModel;
		
	}
	
	
	private synchronized BudgetStatement getBudgetRatio(BudgetStatement thisYear, BudgetStatement fullYearBudget){
		BudgetStatement budgetRatio = new BudgetStatement();
		
		if (fullYearBudget == null)
			return budgetRatio;
		
		if (!BigDecimal.ZERO.equals(fullYearBudget.getIncomeTotal())){	
			budgetRatio.setIncomeTotal(thisYear.getIncomeTotal().divide(fullYearBudget.getIncomeTotal()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getBudgetIncomeTotal())){
		budgetRatio.setBudgetIncomeTotal(thisYear.getBudgetIncomeTotal().divide(fullYearBudget.getBudgetIncomeTotal()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getTaxIncome())){
		budgetRatio.setTaxIncome(thisYear.getTaxIncome().divide(fullYearBudget.getTaxIncome()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getNonTaxIncome())){
		budgetRatio.setNonTaxIncome(thisYear.getNonTaxIncome().divide(fullYearBudget.getNonTaxIncome()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getGovFundIncome())){
		budgetRatio.setGovFundIncome(thisYear.getGovFundIncome().divide(fullYearBudget.getGovFundIncome()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getPaymentTotal())){
		budgetRatio.setPaymentTotal(thisYear.getPaymentTotal().divide(fullYearBudget.getPaymentTotal()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getBudgetPayTotal())){
		budgetRatio.setBudgetPayTotal(thisYear.getBudgetPayTotal().divide(fullYearBudget.getBudgetPayTotal()));
		}
		if (!BigDecimal.ZERO.equals(fullYearBudget.getGovFundPayment())){
		budgetRatio.setGovFundPayment(thisYear.getGovFundPayment().divide(fullYearBudget.getGovFundPayment()));
		}		
		
		return budgetRatio;
	}
	
	private synchronized BudgetStatement getGrowthRate(BudgetStatement thisYear, BudgetStatement lastYear){
		BudgetStatement budgetRatio = new BudgetStatement();
				
		budgetRatio.setIncomeTotal(thisYear.getIncomeTotal().subtract(lastYear.getIncomeTotal())); 
		budgetRatio.setBudgetIncomeTotal(thisYear.getBudgetIncomeTotal().subtract(lastYear.getBudgetIncomeTotal()));
		budgetRatio.setTaxIncome(thisYear.getTaxIncome().subtract(lastYear.getTaxIncome()));
		budgetRatio.setNonTaxIncome(thisYear.getNonTaxIncome().subtract(lastYear.getNonTaxIncome()));
		budgetRatio.setGovFundIncome(thisYear.getGovFundIncome().subtract(lastYear.getGovFundIncome()));
		budgetRatio.setPaymentTotal(thisYear.getPaymentTotal().subtract(lastYear.getPaymentTotal()));
		budgetRatio.setBudgetPayTotal(thisYear.getBudgetPayTotal().subtract(lastYear.getBudgetPayTotal()));
		budgetRatio.setGovFundPayment(thisYear.getGovFundPayment().subtract(lastYear.getGovFundPayment()));		
		
		return budgetRatio;
	}
	
	private synchronized BudgetStatement getFullYearBudget(String reportYear){
		BudgetStatement fullYearBudget = new BudgetStatement();
		String defaultMonth = reportYear + "00";
		Map<String,BudgetStatement> oneYear = savedBudgetStatement.get(reportYear);
		if (oneYear != null){
			fullYearBudget = oneYear.get(defaultMonth);
		}
		
		return fullYearBudget;
	}
	
	private synchronized BudgetStatement getThisYear(String reportYear){		
		BudgetStatement thisYear = new BudgetStatement();		
		Map<String,BudgetStatement> oneYear = savedBudgetStatement.get(reportYear);
		if (oneYear != null){
			for (BudgetStatement month : oneYear.values()){
				thisYear.setIncomeTotal(thisYear.getIncomeTotal().add(month.getIncomeTotal())); 
				thisYear.setBudgetIncomeTotal(thisYear.getBudgetIncomeTotal().add(month.getBudgetIncomeTotal()));
				thisYear.setTaxIncome(thisYear.getTaxIncome().add(month.getTaxIncome()));
				thisYear.setNonTaxIncome(thisYear.getNonTaxIncome().add(month.getNonTaxIncome()));
				thisYear.setGovFundIncome(thisYear.getGovFundIncome().add(month.getGovFundIncome()));
				thisYear.setPaymentTotal(thisYear.getPaymentTotal().add(month.getPaymentTotal()));
				thisYear.setBudgetPayTotal(thisYear.getBudgetPayTotal().add(month.getBudgetPayTotal()));
				thisYear.setGovFundPayment(thisYear.getGovFundPayment().add(month.getGovFundPayment()));
			}
		}
		
		return thisYear;
	}
	
	private synchronized BudgetStatement getLastYear(String reportYear){		
		String lastReportYear = String.valueOf(Integer.parseInt(reportYear)-1);
		BudgetStatement thisYear = new BudgetStatement();		
		Map<String,BudgetStatement> oneYear = savedBudgetStatement.get(lastReportYear);
		if (oneYear != null){
			for (BudgetStatement month : oneYear.values()){
				thisYear.setIncomeTotal(thisYear.getIncomeTotal().add(month.getIncomeTotal())); 
				thisYear.setBudgetIncomeTotal(thisYear.getBudgetIncomeTotal().add(month.getBudgetIncomeTotal()));
				thisYear.setTaxIncome(thisYear.getTaxIncome().add(month.getTaxIncome()));
				thisYear.setNonTaxIncome(thisYear.getNonTaxIncome().add(month.getNonTaxIncome()));
				thisYear.setGovFundIncome(thisYear.getGovFundIncome().add(month.getGovFundIncome()));
				thisYear.setPaymentTotal(thisYear.getPaymentTotal().add(month.getPaymentTotal()));
				thisYear.setBudgetPayTotal(thisYear.getBudgetPayTotal().add(month.getBudgetPayTotal()));
				thisYear.setGovFundPayment(thisYear.getGovFundPayment().add(month.getGovFundPayment()));
			}
		}
		
		return thisYear;		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
