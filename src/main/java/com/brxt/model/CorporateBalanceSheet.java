package com.brxt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.User;

@Entity
@Table(name = "corp_balancesheet")
public class CorporateBalanceSheet extends Statement {
	
	private static final long serialVersionUID = 1743254494015304403L;
	private String beginEnd;
	private BigDecimal inventory;
	private BigDecimal cash;
	private BigDecimal receivableNote;
	private BigDecimal receivables;
	private BigDecimal receivableOther;
	private BigDecimal liquidAsset;
	private BigDecimal totalAsset;
	private BigDecimal prepayment;
	private BigDecimal shortLoan;
	private BigDecimal payableNote;
	private BigDecimal payable;
	private BigDecimal prereceive;
	private BigDecimal liquidDebt;
	private BigDecimal longLoan;
	private BigDecimal totalDebt;
	private BigDecimal actualCapital;
	private BigDecimal unAssignedProfit;
	private BigDecimal netAsset;
	
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {		
		return super.getId();
	}
	@Override
	public String getReportYear() {
		return reportYear;
	}
	@Override
	public void setReportYear(String reportYear) {
		this.reportYear = reportYear;
	}
	@Override
	public User getCreateUser() {
		return createUser;
	}
	@Override
	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
	@Override
	public Date getCreateTime() {
		return createTime;
	}
	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public User getUpdateUser() {
		return updateUser;
	}
	@Override
	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}
	@Override
	public Date getUpdateTime() {
		return updateTime;
	}
	@Override
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public Long getProjectId() {
		return projectId;
	}
	@Override
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	@Override
	public Long getCounterpartyId() {
		return counterpartyId;
	}
	@Override
	public void setCounterpartyId(Long counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getBeginEnd() {
		return beginEnd;
	}
	public void setBeginEnd(String beginEnd) {
		this.beginEnd = beginEnd;
	}
	public BigDecimal getInventory() {
		return inventory;
	}
	public void setInventory(BigDecimal inventory) {
		this.inventory = inventory;
	}
	public BigDecimal getCash() {
		return cash;
	}
	public void setCash(BigDecimal cash) {
		this.cash = cash;
	}
	public BigDecimal getReceivableNote() {
		return receivableNote;
	}
	public void setReceivableNote(BigDecimal receivableNote) {
		this.receivableNote = receivableNote;
	}
	public BigDecimal getReceivables() {
		return receivables;
	}
	public void setReceivables(BigDecimal receivables) {
		this.receivables = receivables;
	}
	public BigDecimal getReceivableOther() {
		return receivableOther;
	}
	public void setReceivableOther(BigDecimal receivableOther) {
		this.receivableOther = receivableOther;
	}
	public BigDecimal getLiquidAsset() {
		return liquidAsset;
	}
	public void setLiquidAsset(BigDecimal liquidAsset) {
		this.liquidAsset = liquidAsset;
	}
	public BigDecimal getTotalAsset() {
		return totalAsset;
	}
	public void setTotalAsset(BigDecimal totalAsset) {
		this.totalAsset = totalAsset;
	}
	public BigDecimal getPrepayment() {
		return prepayment;
	}
	public void setPrepayment(BigDecimal prepayment) {
		this.prepayment = prepayment;
	}
	public BigDecimal getShortLoan() {
		return shortLoan;
	}
	public void setShortLoan(BigDecimal shortLoan) {
		this.shortLoan = shortLoan;
	}
	public BigDecimal getPayableNote() {
		return payableNote;
	}
	public void setPayableNote(BigDecimal payableNote) {
		this.payableNote = payableNote;
	}
	public BigDecimal getPayable() {
		return payable;
	}
	public void setPayable(BigDecimal payable) {
		this.payable = payable;
	}
	public BigDecimal getPrereceive() {
		return prereceive;
	}
	public void setPrereceive(BigDecimal prereceive) {
		this.prereceive = prereceive;
	}
	public BigDecimal getLiquidDebt() {
		return liquidDebt;
	}
	public void setLiquidDebt(BigDecimal liquidDebt) {
		this.liquidDebt = liquidDebt;
	}
	public BigDecimal getLongLoan() {
		return longLoan;
	}
	public void setLongLoan(BigDecimal longLoan) {
		this.longLoan = longLoan;
	}
	public BigDecimal getTotalDebt() {
		return totalDebt;
	}
	public void setTotalDebt(BigDecimal totalDebt) {
		this.totalDebt = totalDebt;
	}
	public BigDecimal getActualCapital() {
		return actualCapital;
	}
	public void setActualCapital(BigDecimal actualCapital) {
		this.actualCapital = actualCapital;
	}
	public BigDecimal getUnAssignedProfit() {
		return unAssignedProfit;
	}
	public void setUnAssignedProfit(BigDecimal unAssignedProfit) {
		this.unAssignedProfit = unAssignedProfit;
	}
	public BigDecimal getNetAsset() {
		return netAsset;
	}
	public void setNetAsset(BigDecimal netAsset) {
		this.netAsset = netAsset;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.reportName).toString();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((beginEnd == null) ? 0 : beginEnd.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		CorporateBalanceSheet other = (CorporateBalanceSheet) obj;
		if (beginEnd != other.beginEnd)
			return false;
		return true;
	}

	
	

}
