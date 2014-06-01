package com.brxt.model;

public enum ProjectType {

	TRANSACTION_MANAGEMENT("transaction_management"),
	NON_TRANSACTION_MANAGEMENT("non_transaction_management");
	
	private String projectTypeName;
	
	private ProjectType(String s)
	{
		this.projectTypeName = s; 
	}
	
	public String toString()
	{
		return this.projectTypeName;
	}
}
