package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;
import com.brxt.model.enums.TradingRelationship;

public class CorpBalanceSheetModel {
	private Long projectId;
	private String projectName;
	private Long counterpartyId;
	private String counterpartyName;
	private String reportYear;
	private String reportName;	
	private TradingRelationship tradingRelationship;
	private CorporateBalanceSheet beginBalSheet;
	private CorporateBalanceSheet endBalSheet;
	private String statementType = StatementType.BALANCE_SHEET.getTitle();
	
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
	public CorporateBalanceSheet getBeginBalSheet() {
		return beginBalSheet;
	}
	public void setBeginBalSheet(CorporateBalanceSheet beginBalSheet) {
		this.beginBalSheet = beginBalSheet;
	}
	public TradingRelationship getTradingRelationship() {
		return tradingRelationship;
	}
	public void setTradingRelationship(TradingRelationship tradingRelationship) {
		this.tradingRelationship = tradingRelationship;
	}
	public CorporateBalanceSheet getEndBalSheet() {
		return endBalSheet;
	}
	public void setEndBalSheet(CorporateBalanceSheet endBalSheet) {
		this.endBalSheet = endBalSheet;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
	
	
	
	
	
}
