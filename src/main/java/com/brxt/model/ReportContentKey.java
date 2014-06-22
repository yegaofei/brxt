package com.brxt.model;

import java.util.Date;

public class ReportContentKey {

	private ProjectInfo projectInfo;
	private Counterparty counterparty;
	private Counterparty guarantor;
	private Date startTime;
	private Date endTime;
	private String activeTab;
	
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public Counterparty getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Counterparty getGuarantor() {
		return guarantor;
	}
	public void setGuarantor(Counterparty guarantor) {
		this.guarantor = guarantor;
	}
	public String getActiveTab() {
		return activeTab;
	}
	public void setActiveTab(String activeTab) {
		this.activeTab = activeTab;
	}
	
}
