package com.brxt.model.enums;

public enum RepaymentType {

	PRINCIPLE("principle"),
	PREMIUM("premium"),
	TRUST_INCOME("trust_income"),
	REPURCHASE("repurchase"),
	INTEREST("interest"),
	OTHER_REPAYMENT_TYPES("other_repayment_types");
	
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
