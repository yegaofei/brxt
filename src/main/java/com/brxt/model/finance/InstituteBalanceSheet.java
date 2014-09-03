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
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.BeginEnd;
import com.brxt.model.enums.StatementType;

@Entity
@Table(name = "inst_balancesheet")
public class InstituteBalanceSheet extends BaseObject {
	
	private static final long serialVersionUID = 1743254494015304403L;
	protected Long id; 
	protected Counterparty counterparty;	
	private BigDecimal assetGroupTotal;
	private BigDecimal assetTotal;
	private BigDecimal expenseTotal;
	private BigDecimal debtGroupTotal;
	private BigDecimal debtTotal;
	private BigDecimal netAssetTotal;
	private BigDecimal incomeTotal;
	protected Integer reportYear;
	protected Short reportMonth;
	protected String createUser; //创建人
	protected Date createTime; //创建时间
	protected String updateUser; //最后更新人
	protected Date updateTime; //最后更新时间	
	private Integer version;
	
	protected String reportName;
	protected StatementType statementType;
	private BeginEnd beginEnd;
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
	public BeginEnd getBeginEnd() {
		return beginEnd;
	}
	public void setBeginEnd(BeginEnd beginEnd) {
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
				this.counterparty).append(this.reportYear).append(this.reportMonth).toString();
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
		if (!(o instanceof InstituteBalanceSheet)) {
			return false;
		}

		final InstituteBalanceSheet statement = (InstituteBalanceSheet) o;

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
