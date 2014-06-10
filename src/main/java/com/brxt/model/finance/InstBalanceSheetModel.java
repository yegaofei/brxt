package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;

public class InstBalanceSheetModel extends BaseStatementModel  {

	private InstituteBalanceSheet beginBalSheet;
	private InstituteBalanceSheet endBalSheet;
	private String statementType = StatementType.BALANCE_SHEET.getTitle();
	
	public InstituteBalanceSheet getBeginBalSheet() {
		return beginBalSheet;
	}
	public void setBeginBalSheet(InstituteBalanceSheet beginBalSheet) {
		this.beginBalSheet = beginBalSheet;
	}
	public InstituteBalanceSheet getEndBalSheet() {
		return endBalSheet;
	}
	public void setEndBalSheet(InstituteBalanceSheet endBalSheet) {
		this.endBalSheet = endBalSheet;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}	
	
}
