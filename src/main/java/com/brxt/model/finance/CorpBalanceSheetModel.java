package com.brxt.model.finance;

import com.brxt.model.enums.StatementType;

public class CorpBalanceSheetModel extends BaseStatementModel {

	private CorporateBalanceSheet beginBalSheet;
	private CorporateBalanceSheet endBalSheet;
	private String statementType = StatementType.BALANCE_SHEET.getTitle();
		
	public CorporateBalanceSheet getBeginBalSheet() {
		return beginBalSheet;
	}
	public void setBeginBalSheet(CorporateBalanceSheet beginBalSheet) {
		this.beginBalSheet = beginBalSheet;
	}
	
	public CorporateBalanceSheet getEndBalSheet() {
		return endBalSheet;
	}
	public void setEndBalSheet(CorporateBalanceSheet endBalSheet) {
		this.endBalSheet = endBalSheet;
	}
	public String getStatementType() {
		return statementType;
	}
	public void setStatementType(String statementType) {
		this.statementType = statementType;
	}
	
	
	
	
	
}
