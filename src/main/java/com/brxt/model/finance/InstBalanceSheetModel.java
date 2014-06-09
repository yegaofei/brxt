package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;

public class InstBalanceSheetModel {
	private Long projectId;
	private String projectName;
	private Long counterpartyId;
	private String counterpartyName;
	private String reportYear;
	private String reportName;	
	private InstituteBalanceSheet beginBalSheet = new InstituteBalanceSheet();
	private InstituteBalanceSheet endBalSheet = new InstituteBalanceSheet();
	private StatementType statementType = StatementType.BALANCE_SHEET;
	
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
	public InstituteBalanceSheet getBeginBalSheet() {
		return beginBalSheet;
	}
	public void setBeginBalSheet(InstituteBalanceSheet beginBalSheet) {
		this.beginBalSheet = beginBalSheet;
	}
	public InstituteBalanceSheet getEndBalSheet() {
		return endBalSheet;
	}
	public void setEndBalSheet(InstituteBalanceSheet endBalSheet) {
		this.endBalSheet = endBalSheet;
	}
	public StatementType getStatementType() {
		return statementType;
	}
	public void setStatementType(StatementType statementType) {
		this.statementType = statementType;
	}	
	
}
