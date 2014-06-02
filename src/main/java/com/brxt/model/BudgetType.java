package com.brxt.model;

public enum BudgetType {
	BUDGET_MONTH("budget_month"), 
	THIS_MONTH("this_month");
	
	private String title;
	
	private BudgetType(String s) {
		this.title = s;
	}

	public String toString() {
		return title;
	}
}
