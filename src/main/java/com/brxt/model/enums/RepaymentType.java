package com.brxt.model.enums;

public enum RepaymentType {

	PRINCIPLE("principle"),
	INTEREST("interest");
	
	private String title;
	
	private RepaymentType(String s)
	{
		title = s;
	}
	
	public String getTitle()
	{
		return title;
	}
}
