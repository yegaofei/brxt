package com.brxt.model.enums;

public enum TradingRelationship {

	COUNTERPARTY("counterparty"),
	GUARANTOR("guarantor");
	
	private String title;
	
	private TradingRelationship(String s)
	{
		this.title = s;
	}
	
	public String toString()
	{
		return this.title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
}
