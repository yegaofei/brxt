package com.brxt.model.enums;

public enum StatementType {

	BALANCE_SHEET("balance_sheet"), 
	PROFIT_SHEET("profit_sheet"), 
	BUDGET_SHEET("budget_sheet");

	private String title;

	private StatementType(String s) {
		this.title = s;
	}

	public String toString() {
		return title;
	}

}
