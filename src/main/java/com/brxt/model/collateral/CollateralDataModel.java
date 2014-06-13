package com.brxt.model.collateral;

import java.util.ArrayList;
import java.util.List;

public class CollateralDataModel {

	protected Long id;
	protected String projectName;
	protected String description;
	protected String evaluatedValue; //抵押物评估价值
	protected String rate;	//抵押率
	protected List<DetailListItem> detailList = new ArrayList<DetailListItem>();
	protected CollateralType addType = CollateralType.LAND;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEvaluatedValue() {
		return evaluatedValue;
	}
	public void setEvaluatedValue(String evaluatedValue) {
		this.evaluatedValue = evaluatedValue;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public List<DetailListItem> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<DetailListItem> detailList) {
		this.detailList = detailList;
	}
	public CollateralType getAddType() {
		return addType;
	}
	public void setAddType(CollateralType addType) {
		this.addType = addType;
	}
}
