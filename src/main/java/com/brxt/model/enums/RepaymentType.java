package com.brxt.model.enums;

public enum RepaymentType {

	PRINCIPLE("principle"),
	PREMIUM("premium"),
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
