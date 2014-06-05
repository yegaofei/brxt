package com.brxt.model.enums;

public enum CounterpartyType {
	
	REAL_ESTATE_FIRM("real_estate_firm"),
	COMMERCE_COMPANY("commerce_company"),
	INSTITUTION("institution");
	
	
	private String title;
	
	private CounterpartyType(String s)
	{
		this.title = s;
	}
	
	public String toString()
	{
		return this.title;
	}
	
	public String getTitle()
	{
		return title;
	}
	

}
