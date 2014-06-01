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
@Table(name = "inst_balancesheet")
public class InstituteBalanceSheet extends Statement {
	
	private static final long serialVersionUID = 1743254494015304403L;
	private String beginEnd;
	private BigDecimal assetGroupTotal;
	private BigDecimal assetTotal;
	private BigDecimal expenseTotal;
	private BigDecimal debtGroupTotal;
	private BigDecimal debtTotal;
	private BigDecimal netAssetTotal;
	private BigDecimal incomeTotal;
	
	
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
	
	public BigDecimal getAssetGroupTotal() {
		return assetGroupTotal;
	}
	public void setAssetGroupTotal(BigDecimal assetGroupTotal) {
		this.assetGroupTotal = assetGroupTotal;
	}
	public BigDecimal getAssetTotal() {
		return assetTotal;
	}
	public void setAssetTotal(BigDecimal assetTotal) {
		this.assetTotal = assetTotal;
	}
	public BigDecimal getExpenseTotal() {
		return expenseTotal;
	}
	public void setExpenseTotal(BigDecimal expenseTotal) {
		this.expenseTotal = expenseTotal;
	}
	public BigDecimal getDebtGroupTotal() {
		return debtGroupTotal;
	}
	public void setDebtGroupTotal(BigDecimal debtGroupTotal) {
		this.debtGroupTotal = debtGroupTotal;
	}
	public BigDecimal getDebtTotal() {
		return debtTotal;
	}
	public void setDebtTotal(BigDecimal debtTotal) {
		this.debtTotal = debtTotal;
	}
	public BigDecimal getNetAssetTotal() {
		return netAssetTotal;
	}
	public void setNetAssetTotal(BigDecimal netAssetTotal) {
		this.netAssetTotal = netAssetTotal;
	}
	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
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
		InstituteBalanceSheet other = (InstituteBalanceSheet) obj;
		if (beginEnd != other.beginEnd)
			return false;
		return true;
	}	

}
