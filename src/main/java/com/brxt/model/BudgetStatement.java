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
@Table(name = "budget_statement")
public class BudgetStatement extends Statement {
	
	private static final long serialVersionUID = 1743254494015304403L;	
	private BigDecimal incomeTotal;
	private BigDecimal budgetIncomeTotal;
	private BigDecimal taxIncome;
	private BigDecimal nonTaxIncome;
	private BigDecimal govFundIncome;
	private BigDecimal paymentTotal;
	private BigDecimal budgetPayTotal;
	private BigDecimal govFundPayment;
	
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
	@Override
	public String getReportMonth() {
		return reportMonth;
	}	
	@Override
	public void setReportMonth(String reportMonth) {
		this.reportMonth = reportMonth;
	}	
	
	public BigDecimal getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(BigDecimal incomeTotal) {
		this.incomeTotal = incomeTotal;
	}
	public BigDecimal getBudgetIncomeTotal() {
		return budgetIncomeTotal;
	}
	public void setBudgetIncomeTotal(BigDecimal budgetIncomeTotal) {
		this.budgetIncomeTotal = budgetIncomeTotal;
	}
	public BigDecimal getTaxIncome() {
		return taxIncome;
	}
	public void setTaxIncome(BigDecimal taxIncome) {
		this.taxIncome = taxIncome;
	}
	public BigDecimal getNonTaxIncome() {
		return nonTaxIncome;
	}
	public void setNonTaxIncome(BigDecimal nonTaxIncome) {
		this.nonTaxIncome = nonTaxIncome;
	}
	public BigDecimal getGovFundIncome() {
		return govFundIncome;
	}
	public void setGovFundIncome(BigDecimal govFundIncome) {
		this.govFundIncome = govFundIncome;
	}
	public BigDecimal getPaymentTotal() {
		return paymentTotal;
	}
	public void setPaymentTotal(BigDecimal paymentTotal) {
		this.paymentTotal = paymentTotal;
	}
	public BigDecimal getBudgetPayTotal() {
		return budgetPayTotal;
	}
	public void setBudgetPayTotal(BigDecimal budgetPayTotal) {
		this.budgetPayTotal = budgetPayTotal;
	}
	public BigDecimal getGovFundPayment() {
		return govFundPayment;
	}
	public void setGovFundPayment(BigDecimal govFundPayment) {
		this.govFundPayment = govFundPayment;
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
				+ ((reportMonth == null) ? 0 : reportMonth.hashCode());
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
		BudgetStatement other = (BudgetStatement) obj;
		if (reportMonth != other.reportMonth)
			return false;
		return true;
	}	

}
