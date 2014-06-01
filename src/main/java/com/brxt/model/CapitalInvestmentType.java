package com.brxt.model;

public enum CapitalInvestmentType {

	REAL_ESTATE("real_estate"), 
	INFRASTRUCTURE("infrastructure"), 
	SUPPLEMENTAL_LIQUIDITY("supplemental_liquidity");

	private String title;

	private CapitalInvestmentType(String s) {
		this.title = s;
	}

	public String toString() {
		return title;
	}

}
