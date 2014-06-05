package com.brxt.model;

import java.util.Date;

import com.brxt.model.enums.CapitalInvestmentType;

public class ProjectProgress {
	
	private Date deadline;
	private CapitalInvestmentType capitalInvestmentType;
	private Boolean investment;
	private Boolean supplyLiquid;
	private String projectName;

	
	
	public Boolean getSupplyLiquid() {
		return supplyLiquid;
	}
	public void setSupplyLiquid(Boolean supplyLiquid) {
		this.supplyLiquid = supplyLiquid;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public CapitalInvestmentType getCapitalInvestmentType() {
		return capitalInvestmentType;
	}
	public void setCapitalInvestmentType(CapitalInvestmentType capitalInvestmentType) {
		this.capitalInvestmentType = capitalInvestmentType;
	}
	public Boolean getInvestment() {
		return investment;
	}
	public void setInvestment(Boolean investment) {
		this.investment = investment;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	

}
