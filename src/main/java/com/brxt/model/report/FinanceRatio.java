package com.brxt.model.report;

import java.math.BigDecimal;

public class FinanceRatio {

	private BigDecimal assetLiabilityRatio; //资产负债率
	private BigDecimal liquidityRatio; //流动比率
	private BigDecimal quickRatio; //速动比率
	private BigDecimal assetRoR;  //净资产收益率
	private BigDecimal salesIncrementRatio; //销售收入年度增长率
	
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
