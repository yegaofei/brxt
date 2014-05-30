package com.brxt.model;

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
}
