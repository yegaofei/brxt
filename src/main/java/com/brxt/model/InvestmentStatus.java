package com.brxt.model;

import com.brxt.model.enums.CapitalInvestmentType;

public class InvestmentStatus {

	private Long id;
	private String projectName;
	private CapitalInvestmentType capitalInvestmentType;
	
	public InvestmentStatus() {
		super();
	}
	
	public InvestmentStatus(Long id, String projectName, CapitalInvestmentType capitalInvestmentType) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.capitalInvestmentType = capitalInvestmentType;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
