package com.brxt.model.finance;

import java.util.Date;

import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.TradingRelationship;

public class BaseStatementModel {

	protected Long projectId;
	protected String projectName;
	protected Long counterpartyId;
	protected String counterpartyType;
	protected String counterpartyName;
	protected Date reportTime;
	protected String reportName;
	protected TradingRelationship tradingRelationship;
	
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
	public Date getReportTime() {
		return reportTime;
	}
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public TradingRelationship getTradingRelationship() {
		return tradingRelationship;
	}
	public void setTradingRelationship(TradingRelationship tradingRelationship) {
		this.tradingRelationship = tradingRelationship;
	}
	public String getCounterpartyType() {
		return counterpartyType;
	}
	public void setCounterpartyType(String counterpartyType) {
		this.counterpartyType = counterpartyType;
	}
	
	
}
