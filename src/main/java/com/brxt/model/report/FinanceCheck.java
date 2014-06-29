package com.brxt.model.report;

import com.brxt.model.Counterparty;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;

public class FinanceCheck {
	
	private Long id;
	private Counterparty counterparty;
	private ProfitStatement prevProfitStatement;
	private CorporateBalanceSheet prevCorpBalanceSheet;
	private InstituteBalanceSheet prevInstituteBalanceSheet;
	private FinanceRatio prevFinanceRatio;
	
	private ProfitStatement currProfitStatement;
	private CorporateBalanceSheet currCorpBalanceSheet;
	private InstituteBalanceSheet currInstituteBalanceSheet;
	private FinanceRatio currFinanceRatio;
	
	private ProfitStatement profitStatementChanges;
	private CorporateBalanceSheet corpBalanceSheetChanges;
	private InstituteBalanceSheet instituteBalanceSheetChanges;
	private FinanceRatio financeRatioChanges;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Counterparty getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}
	public ProfitStatement getPrevProfitStatement() {
		return prevProfitStatement;
	}
	public void setPrevProfitStatement(ProfitStatement prevProfitStatement) {
		this.prevProfitStatement = prevProfitStatement;
	}
	public CorporateBalanceSheet getPrevCorpBalanceSheet() {
		return prevCorpBalanceSheet;
	}
	public void setPrevCorpBalanceSheet(CorporateBalanceSheet prevCorpBalanceSheet) {
		this.prevCorpBalanceSheet = prevCorpBalanceSheet;
	}
	public ProfitStatement getCurrProfitStatement() {
		return currProfitStatement;
	}
	public void setCurrProfitStatement(ProfitStatement currProfitStatement) {
		this.currProfitStatement = currProfitStatement;
	}
	public CorporateBalanceSheet getCurrCorpBalanceSheet() {
		return currCorpBalanceSheet;
	}
	public void setCurrCorpBalanceSheet(CorporateBalanceSheet currCorpBalanceSheet) {
		this.currCorpBalanceSheet = currCorpBalanceSheet;
	}
	public ProfitStatement getProfitStatementChanges() {
		return profitStatementChanges;
	}
	public void setProfitStatementChanges(ProfitStatement profitStatementChanges) {
		this.profitStatementChanges = profitStatementChanges;
	}
	public CorporateBalanceSheet getCorpBalanceSheetChanges() {
		return corpBalanceSheetChanges;
	}
	public void setCorpBalanceSheetChanges(
			CorporateBalanceSheet corpBalanceSheetChanges) {
		this.corpBalanceSheetChanges = corpBalanceSheetChanges;
	}
	public FinanceRatio getPrevFinanceRatio() {
		return prevFinanceRatio;
	}
	public void setPrevFinanceRatio(FinanceRatio prevFinanceRatio) {
		this.prevFinanceRatio = prevFinanceRatio;
	}
	public FinanceRatio getCurrFinanceRatio() {
		return currFinanceRatio;
	}
	public void setCurrFinanceRatio(FinanceRatio currFinanceRatio) {
		this.currFinanceRatio = currFinanceRatio;
	}
	public FinanceRatio getFinanceRatioChanges() {
		return financeRatioChanges;
	}
	public void setFinanceRatioChanges(FinanceRatio financeRatioChanges) {
		this.financeRatioChanges = financeRatioChanges;
	}
	public InstituteBalanceSheet getPrevInstituteBalanceSheet() {
		return prevInstituteBalanceSheet;
	}
	public void setPrevInstituteBalanceSheet(
			InstituteBalanceSheet prevInstituteBalanceSheet) {
		this.prevInstituteBalanceSheet = prevInstituteBalanceSheet;
	}
	public InstituteBalanceSheet getCurrInstituteBalanceSheet() {
		return currInstituteBalanceSheet;
	}
	public void setCurrInstituteBalanceSheet(
			InstituteBalanceSheet currInstituteBalanceSheet) {
		this.currInstituteBalanceSheet = currInstituteBalanceSheet;
	}
	public InstituteBalanceSheet getInstituteBalanceSheetChanges() {
		return instituteBalanceSheetChanges;
	}
	public void setInstituteBalanceSheetChanges(
			InstituteBalanceSheet instituteBalanceSheetChanges) {
		this.instituteBalanceSheetChanges = instituteBalanceSheetChanges;
	}
	
}
