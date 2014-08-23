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
@Table(name = "corp_balancesheet")
public class CorporateBalanceSheet extends BaseObject {
	private static final long serialVersionUID = 4392602832383157079L;
	protected Long id; 
	protected Counterparty counterparty;	
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

	private BigDecimal nonLiquid;
	private BigDecimal longInvestment;
	private BigDecimal fixedAsset;
	private BigDecimal intangibleAsset;
	private BigDecimal capitalReserve;
	
	protected Integer reportYear;
	protected Short reportMonth;
	protected String createUser; //创建人
	protected Date createTime; //创建时间
	protected String updateUser; //最后更新人
	protected Date updateTime; //最后更新时间	
	private Integer version;
	
	private BeginEnd beginEnd;
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
	public BeginEnd getBeginEnd() {
		return beginEnd;
	}
	public void setBeginEnd(BeginEnd beginEnd) {
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
	public BigDecimal getNonLiquid() {
        return nonLiquid;
    }
    public void setNonLiquid(BigDecimal nonLiquid) {
        this.nonLiquid = nonLiquid;
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
	public BigDecimal getLongInvestment() {
        return longInvestment;
    }
    public void setLongInvestment(BigDecimal longInvestment) {
        this.longInvestment = longInvestment;
    }
    public BigDecimal getFixedAsset() {
        return fixedAsset;
    }
    public void setFixedAsset(BigDecimal fixedAsset) {
        this.fixedAsset = fixedAsset;
    }
    public BigDecimal getIntangibleAsset() {
        return intangibleAsset;
    }
    public void setIntangibleAsset(BigDecimal intangibleAsset) {
        this.intangibleAsset = intangibleAsset;
    }
    public BigDecimal getCapitalReserve() {
        return capitalReserve;
    }
    public void setCapitalReserve(BigDecimal capitalReserve) {
        this.capitalReserve = capitalReserve;
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
		if (!(o instanceof CorporateBalanceSheet)) {
			return false;
		}

		final CorporateBalanceSheet statement = (CorporateBalanceSheet) o;

		return !(counterparty != null ? !counterparty
				.equals(statement.counterparty)
				: statement.counterparty != null)
				&& !(reportYear != null ? !reportYear
						.equals(statement.reportYear)
						: statement.reportYear != null)		
				&& !(reportMonth != null ? !reportMonth
						.equals(statement.reportMonth)
						: statement.reportMonth != null);
	}	
}
