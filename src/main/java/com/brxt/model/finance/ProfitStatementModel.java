package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;

public class ProfitStatementModel extends BaseStatementModel{

	private ProfitStatement beginBalSheet;
	private ProfitStatement endBalSheet;
	private String statementType = StatementType.PROFIT_SHEET.getTitle();
	
	
	public ProfitStatement getBeginBalSheet() {
		return beginBalSheet;
	}
	public void setBeginBalSheet(ProfitStatement beginBalSheet) {
		this.beginBalSheet = beginBalSheet;
	}
	public ProfitStatement getEndBalSheet() {
		return endBalSheet;
	}
	public void setEndBalSheet(ProfitStatement endBalSheet) {
		this.endBalSheet = endBalSheet;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
		
}
