package com.brxt.model.collateral;

public enum CollateralType {
	
	LAND("land"),
	PROPERTY("property"),
	CONSTRUCTING_PROJECT("constructing_project");
	
	private String type;
	
	private CollateralType(String s)
	{
		this.type = s;
	}
	
	public String getType()
	{
		return type;
	}
	
	public String toString()
	{
		return type;
	}

}
