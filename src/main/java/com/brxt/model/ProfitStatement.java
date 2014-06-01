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
@Table(name = "profit_statement")
public class ProfitStatement extends Statement {
	
	private static final long serialVersionUID = 1743254494015304403L;
	private String beginEnd;
	private BigDecimal operatingIncome;
	private BigDecimal operatingCost;
	private BigDecimal operatingProfit;
	private BigDecimal interestCost;
	private BigDecimal operatingTax;
	private BigDecimal netProfit;	
	
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
	
	public BigDecimal getOperatingIncome() {
		return operatingIncome;
	}
	public void setOperatingIncome(BigDecimal operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
	public BigDecimal getOperatingCost() {
		return operatingCost;
	}
	public void setOperatingCost(BigDecimal operatingCost) {
		this.operatingCost = operatingCost;
	}
	public BigDecimal getOperatingProfit() {
		return operatingProfit;
	}
	public void setOperatingProfit(BigDecimal operatingProfit) {
		this.operatingProfit = operatingProfit;
	}
	public BigDecimal getInterestCost() {
		return interestCost;
	}
	public void setInterestCost(BigDecimal interestCost) {
		this.interestCost = interestCost;
	}
	public BigDecimal getOperatingTax() {
		return operatingTax;
	}
	public void setOperatingTax(BigDecimal operatingTax) {
		this.operatingTax = operatingTax;
	}
	public BigDecimal getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
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
		ProfitStatement other = (ProfitStatement) obj;
		if (beginEnd != other.beginEnd)
			return false;
		return true;
	}

	
	

}
