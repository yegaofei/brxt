package com.brxt.model.enums;

public enum BeginEnd {
	BEGIN("begin"), 
	END("end");
	
	private String title;

	private BeginEnd(String s) {
		this.title = s;
	}

	public String toString() {
		return title;
	}
}
