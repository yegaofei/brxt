package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;

public class BudgetStatementModel extends BaseStatementModel{

	private BudgetStatement thisYearBudget; //全年预算数值
	private BudgetStatement thisYear; //本年数值
	private BudgetStatement lastYear; //上年数值
	private BudgetStatement budgetRatio; //占预算比例
	private BudgetStatement growthRate; //比上年同期增长
	private String statementType = StatementType.BUDGET_SHEET.getTitle();
		
	public BudgetStatement getThisYearBudget() {
		return thisYearBudget;
	}
	public void setThisYearBudget(BudgetStatement thisYearBudget) {
		this.thisYearBudget = thisYearBudget;
	}
	public BudgetStatement getThisYear() {
		return thisYear;
	}
	public void setThisYear(BudgetStatement thisYear) {
		this.thisYear = thisYear;
	}
	public BudgetStatement getLastYear() {
		return lastYear;
	}
	public void setLastYear(BudgetStatement lastYear) {
		this.lastYear = lastYear;
	}
	public BudgetStatement getBudgetRatio() {
		return budgetRatio;
	}
	public void setBudgetRatio(BudgetStatement budgetRatio) {
		this.budgetRatio = budgetRatio;
	}
	public BudgetStatement getGrowthRate() {
		return growthRate;
	}
	public void setGrowthRate(BudgetStatement growthRate) {
		this.growthRate = growthRate;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}	
	
}
