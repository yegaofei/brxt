package com.brxt.model;

import com.brxt.model.enums.CapitalInvestmentType;

public class InvestmentStatus {

	private String projectName;
	private CapitalInvestmentType capitalInvestmentType;
	
	public InvestmentStatus() {
		super();
	}
	
	public InvestmentStatus(String projectName, CapitalInvestmentType capitalInvestmentType) {
		super();
		this.projectName = projectName;
		this.capitalInvestmentType = capitalInvestmentType;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public CapitalInvestmentType getCapitalInvestmentType() {
		return capitalInvestmentType;
	}

	public void setCapitalInvestmentType(CapitalInvestmentType capitalInvestmentType) {
		this.capitalInvestmentType = capitalInvestmentType;
	}
	
}
