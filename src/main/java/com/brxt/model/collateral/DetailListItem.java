package com.brxt.model.collateral;

import java.util.Date;

public class DetailListItem {

	private Long id;
	private Long realId;
	private CollateralType type;
	private String displayId;
	private Date evaluatedTime; //评估时间
	private String collateralValue;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRealId() {
		return realId;
	}
	public void setRealId(Long realId) {
		this.realId = realId;
	}
	public CollateralType getType() {
		return type;
	}
	public void setType(CollateralType type) {
		this.type = type;
	}
	public String getDisplayId() {
		return displayId;
	}
	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}
	public Date getEvaluatedTime() {
		return evaluatedTime;
	}
	public void setEvaluatedTime(Date evaluatedTime) {
		this.evaluatedTime = evaluatedTime;
	}
	public String getCollateralValue() {
		return collateralValue;
	}
	public void setCollateralValue(String collateralValue) {
		this.collateralValue = collateralValue;
	}
	
	
	
}
