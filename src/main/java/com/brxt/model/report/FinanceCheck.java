package com.brxt.model.report;

import java.math.BigDecimal;

import com.brxt.model.Counterparty;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.ProfitStatement;

public class FinanceCheck {
	
	private Long id;
	private Counterparty counterparty;
	private ProfitStatement prevProfitStatement;
	private CorporateBalanceSheet prevCorpBalanceSheet;
	
	private ProfitStatement currProfitStatement;
	private CorporateBalanceSheet currCorpBalanceSheet;
	
	private ProfitStatement profitStatementChanges;
	private CorporateBalanceSheet corpBalanceSheetChanges;
	
	private BigDecimal assetLiabilityRatio;
	private BigDecimal liquidityRatio;
	private BigDecimal quickRatio;
	private BigDecimal assetRoR;  //净资产收益率
	private BigDecimal salesIncrementRatio; //销售收入年度增长率
	
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
	public BigDecimal getAssetLiabilityRatio() {
		return assetLiabilityRatio;
	}
	public void setAssetLiabilityRatio(BigDecimal assetLiabilityRatio) {
		this.assetLiabilityRatio = assetLiabilityRatio;
	}
	public BigDecimal getLiquidityRatio() {
		return liquidityRatio;
	}
	public void setLiquidityRatio(BigDecimal liquidityRatio) {
		this.liquidityRatio = liquidityRatio;
	}
	public BigDecimal getQuickRatio() {
		return quickRatio;
	}
	public void setQuickRatio(BigDecimal quickRatio) {
		this.quickRatio = quickRatio;
	}
	public BigDecimal getAssetRoR() {
		return assetRoR;
	}
	public void setAssetRoR(BigDecimal assetRoR) {
		this.assetRoR = assetRoR;
	}
	public BigDecimal getSalesIncrementRatio() {
		return salesIncrementRatio;
	}
	public void setSalesIncrementRatio(BigDecimal salesIncrementRatio) {
		this.salesIncrementRatio = salesIncrementRatio;
	}
	
}
