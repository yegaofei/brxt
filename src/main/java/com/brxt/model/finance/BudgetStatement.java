package com.brxt.model.finance;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;

import com.brxt.model.Counterparty;
import com.brxt.model.enums.BudgetType;
import com.brxt.model.enums.StatementType;

@Entity
@Table(name = "budget_statement")
public class BudgetStatement extends BaseObject {
	
	private static final long serialVersionUID = 1743254494015304403L;
	protected Long id; 
	protected Counterparty counterparty;	
	private BigDecimal incomeTotal;
	private BigDecimal budgetIncomeTotal;
	private BigDecimal taxIncome;
	private BigDecimal nonTaxIncome;
	private BigDecimal govFundIncome;
	private BigDecimal paymentTotal;
	private BigDecimal budgetPayTotal;
	private BigDecimal govFundPayment;	
	protected Integer reportYear;
	protected Short reportMonth;
	protected String createUser; //创建人
	protected Date createTime; //创建时间
	protected String updateUser; //最后更新人
	protected Date updateTime; //最后更新时间	
	private Integer version;
	
	private BudgetType budgetType;
	protected String reportName;
	protected StatementType statementType;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="counterparty_id") 
	public Counterparty getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}
	
	@Transient
	public BudgetType getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(BudgetType budgetType) {
		this.budgetType = budgetType;
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
	public BigDecimal getGovFundPayment() {
		return govFundPayment;
	}
	public void setGovFundPayment(BigDecimal govFundPayment) {
		this.govFundPayment = govFundPayment;
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
	@Transient
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@Transient
	public StatementType getStatementType() {
		return statementType;
	}
	public void setStatementType(StatementType statementType) {
		this.statementType = statementType;
	}
	public Integer getReportYear() {
		return reportYear;
	}
	public void setReportYear(Integer reportYear) {
		this.reportYear = reportYear;
	}
	public Short getReportMonth() {
		return reportMonth;
	}
	public void setReportMonth(Short reportMonth) {
		this.reportMonth = reportMonth;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.reportName).toString();
	}

	@Override
	public int hashCode() {
		int result;
        result = (counterparty != null ? counterparty.hashCode() : 0);
        result = 29 * result + (reportYear != null ? reportYear.hashCode() : 0);
        result = 29 * result + (reportMonth != null ? reportMonth.hashCode() : 0);
        return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BudgetStatement)) {
			return false;
		}

		final BudgetStatement statement = (BudgetStatement) o;

		return !(counterparty != null ? !counterparty
				.equals(statement.counterparty)
				: statement.counterparty != null)
				&& !(statementType != null ? !statementType
						.equals(statement.statementType)
						: statement.statementType != null)
				&& !(reportYear != null ? !reportYear
						.equals(statement.reportYear)
						: statement.reportYear != null)		
				&& !(reportMonth != null ? !reportMonth
						.equals(statement.reportMonth)
						: statement.reportMonth != null);
	}	
}
