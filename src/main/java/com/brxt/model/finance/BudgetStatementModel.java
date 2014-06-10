package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;

public class BudgetStatementModel {
	private Long projectId;
	private String projectName;
	private Long counterpartyId;
	private String counterpartyName;
	private String reportYear;
	private String reportName;	
	private BudgetStatement fullYearBudget = new BudgetStatement();
	private BudgetStatement thisYear = new BudgetStatement();
	private BudgetStatement lastYear = new BudgetStatement();
	private BudgetStatement budgetRatio = new BudgetStatement();
	private BudgetStatement growthRate = new BudgetStatement();
	private String statementType = StatementType.BUDGET_SHEET.getTitle();
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public Long getCounterpartyId() {
		return counterpartyId;
	}
	public void setCounterpartyId(Long counterpartyId) {
		this.counterpartyId = counterpartyId;
	}
	public String getCounterpartyName() {
		return counterpartyName;
	}
	public void setCounterpartyName(String counterpartyName) {
		this.counterpartyName = counterpartyName;
	}
	public String getReportYear() {
		return reportYear;
	}
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public BudgetStatement getFullYearBudget() {
		return fullYearBudget;
	}
	public void setFullYearBudget(BudgetStatement fullYearBudget) {
		this.fullYearBudget = fullYearBudget;
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
