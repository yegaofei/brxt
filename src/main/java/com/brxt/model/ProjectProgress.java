package com.brxt.model;

import java.util.Date;

import com.brxt.model.enums.CapitalInvestmentType;

public class ProjectProgress {
	
	private Long id;
	private Date deadline; //项目进展截止时间
	private CapitalInvestmentType capitalInvestmentType; //资金投向类型  
	private Boolean investment; //投资项目进展情况/还款来源项目销售进展情况
	private Boolean supplyLiquid; 
	private String projectName; //项目名称
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
