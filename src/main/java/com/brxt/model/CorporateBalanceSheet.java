package com.brxt.model;

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
	private BeginEnd beginEnd;
	private Double inventory;
	private Double cash;
	private Double receivableNote;
	private Double receivables;
	private Double receivableOther;
	private Double liquidAsset;
	private Double totalAsset;
	private Double prepayment;
	private Double shortLoan;
	private Double payableNote;
	private Double payable;
	private Double prereceive;
	private Double liquidDebt;
	private Double longLoan;
	private Double totalDebt;
	private Double actualCapital;
	private Double unAssignedProfit;
	private Double netAsset;
	
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

	public BeginEnd getBeginEnd() {
		return beginEnd;
	}


	public void setBeginEnd(BeginEnd beginEnd) {
		this.beginEnd = beginEnd;
	}


	public Double getInventory() {
		return inventory;
	}


	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}


	public Double getCash() {
		return cash;
	}


	public void setCash(Double cash) {
		this.cash = cash;
	}


	public Double getReceivableNote() {
		return receivableNote;
	}


	public void setReceivableNote(Double receivableNote) {
		this.receivableNote = receivableNote;
	}


	public Double getReceivables() {
		return receivables;
	}


	public void setReceivables(Double receivables) {
		this.receivables = receivables;
	}


	public Double getReceivableOther() {
		return receivableOther;
	}


	public void setReceivableOther(Double receivableOther) {
		this.receivableOther = receivableOther;
	}


	public Double getLiquidAsset() {
		return liquidAsset;
	}


	public void setLiquidAsset(Double liquidAsset) {
		this.liquidAsset = liquidAsset;
	}


	public Double getTotalAsset() {
		return totalAsset;
	}


	public void setTotalAsset(Double totalAsset) {
		this.totalAsset = totalAsset;
	}


	public Double getPrepayment() {
		return prepayment;
	}


	public void setPrepayment(Double prepayment) {
		this.prepayment = prepayment;
	}


	public Double getShortLoan() {
		return shortLoan;
	}


	public void setShortLoan(Double shortLoan) {
		this.shortLoan = shortLoan;
	}


	public Double getPayableNote() {
		return payableNote;
	}


	public void setPayableNote(Double payableNote) {
		this.payableNote = payableNote;
	}


	public Double getPayable() {
		return payable;
	}


	public void setPayable(Double payable) {
		this.payable = payable;
	}


	public Double getPrereceive() {
		return prereceive;
	}


	public void setPrereceive(Double prereceive) {
		this.prereceive = prereceive;
	}


	public Double getLiquidDebt() {
		return liquidDebt;
	}


	public void setLiquidDebt(Double liquidDebt) {
		this.liquidDebt = liquidDebt;
	}


	public Double getLongLoan() {
		return longLoan;
	}


	public void setLongLoan(Double longLoan) {
		this.longLoan = longLoan;
	}


	public Double getTotalDebt() {
		return totalDebt;
	}


	public void setTotalDebt(Double totalDebt) {
		this.totalDebt = totalDebt;
	}


	public Double getActualCapital() {
		return actualCapital;
	}


	public void setActualCapital(Double actualCapital) {
		this.actualCapital = actualCapital;
	}


	public Double getUnAssignedProfit() {
		return unAssignedProfit;
	}


	public void setUnAssignedProfit(Double unAssignedProfit) {
		this.unAssignedProfit = unAssignedProfit;
	}


	public Double getNetAsset() {
		return netAsset;
	}


	public void setNetAsset(Double netAsset) {
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
