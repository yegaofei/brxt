package com.brxt.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.appfuse.model.User;
import org.hibernate.annotations.Type;

import com.brxt.model.enums.TradingRelationship;

@NamedNativeQueries({
	@NamedNativeQuery(
			name = "searchCIByProjectInfoId",
			query = "select * from credit_information s where s.projectInfo_id = :projectInfo_id",
		        resultClass = CreditInformation.class
			)
})

@Entity
@Table(name = "credit_information")
public class CreditInformation extends BaseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3447667612952483922L;
	private Long id;
	private Counterparty counterparty; 
	private ProjectInfo projectInfo; // 项目id 
	private Date queryTime; //征信查询时间
	private String debt; //征信系统显示的信托到期前须偿还的金融负债
	private String outstanding; //征信系统查询贷款余额
	private String balance; //征信系统查询对外担保余额
	private Boolean overdue; //有无逾期或不良贷款
	private String comment; 
	private User createUser; // 创建人
	private Date createTime; // 创建时间
	private User updateUser; // 最后更新人
	private Date updateTime; // 最后更新时间
	private Integer version;
	
	private BigDecimal debtBalance; //截至本期末的金融机构负债余额	
	private TradingRelationship tradeRelationship;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="projectInfo_id") 
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="counterparty_id") 
	public Counterparty getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}
	public Date getQueryTime() {
		return queryTime;
	}
	public void setQueryTime(Date queryTime) {
		this.queryTime = queryTime;
	}
	public String getDebt() {
		return debt;
	}
	public void setDebt(String debt) {
		this.debt = debt;
	}
	public String getOutstanding() {
		return outstanding;
	}
	public void setOutstanding(String outstanding) {
		this.outstanding = outstanding;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	@Column(name = "overdue")
	@Type(type="yes_no")
	public Boolean getOverdue() {
		return overdue;
	}
	public void setOverdue(Boolean overdue) {
		this.overdue = overdue;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "createUser", nullable = true)
	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "updateUser", nullable = true)
	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
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
	
	@Transient 
	public BigDecimal getDebtBalance() {
		return debtBalance;
	}
	public void setDebtBalance(BigDecimal debtBalance) {
		this.debtBalance = debtBalance;
	}
	@Transient  
	public TradingRelationship getTradeRelationship() {
		return tradeRelationship;
	}
	public void setTradeRelationship(TradingRelationship tradeRelationship) {
		this.tradeRelationship = tradeRelationship;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
		.append(this.counterparty).append(this.projectInfo)
		.append(this.queryTime).toString();
	}
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof CreditInformation)) {
			return false;
		}

		final CreditInformation creditInformation = (CreditInformation) o;

		return !(counterparty != null ? !counterparty
				.equals(creditInformation.counterparty)
				: creditInformation.counterparty != null)
				&& !(projectInfo != null ? !projectInfo
						.equals(creditInformation.projectInfo)
						: creditInformation.projectInfo != null)
				&& !(queryTime != null ? !queryTime
						.equals(creditInformation.queryTime)
						: creditInformation.queryTime != null);
	}
	@Override
	public int hashCode() {
		int result;
        result = (counterparty != null ? counterparty.hashCode() : 0);
        result = 29 * result + (projectInfo != null ? projectInfo.hashCode() : 0);
        result = 29 * result + (queryTime != null ? queryTime.hashCode() : 0);
        return result;
	}
	
	
	

}
