package com.brxt.model.collateral;

public class CollateralListItem {
	
	private Long id;
	private String projectName;
	private CollateralType collateralType;
	private String executor;
	
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
	public CollateralType getCollateralType() {
		return collateralType;
	}
	public void setCollateralType(CollateralType collateralType) {
		this.collateralType = collateralType;
	}
	public String getExecutor() {
		return executor;
	}
	public void setExecutor(String executor) {
		this.executor = executor;
	}
	
	

}
