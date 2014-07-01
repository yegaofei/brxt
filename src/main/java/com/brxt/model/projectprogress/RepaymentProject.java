package com.brxt.model.projectprogress;

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

import com.brxt.model.InvestmentStatus;
import com.brxt.model.enums.CapitalInvestmentType;

@Entity
@Table(name = "repayment_project")
public class RepaymentProject extends BaseObject  {
	
	private static final long serialVersionUID = -1401605168965563698L;
	private Long id;
	private InvestmentStatus investmentStatus;
	private Date projectEndTime; //项目进展截止时间
	private CapitalInvestmentType type = CapitalInvestmentType.REAL_ESTATE;
	private Double totalSize;
	private Double totalSaleSize;
	private Double permitSaleSize;
	private Date openDate;
	private Double preSaleSize;
	private Double salePercentRate;
	private Double preAvgPrice;
	private Double currAvgPrice;
	private String comments;
	
	private String evaluation; //还款来源评价
	
	private String createUser; // 创建人
	private Date createTime; // 创建时间
	private String updateUser; // 最后更新人
	private Date updateTime; // 最后更新时间
	private Integer version;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="investment_status_id")
	public InvestmentStatus getInvestmentStatus() {
		return investmentStatus;
	}
	public void setInvestmentStatus(InvestmentStatus investmentStatus) {
		this.investmentStatus = investmentStatus;
	}
	@Transient  
	public CapitalInvestmentType getType() {
		return type;
	}
	public void setType(CapitalInvestmentType type) {
		this.type = type;
	}
	public Double getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(Double totalSize) {
		this.totalSize = totalSize;
	}
	public Double getTotalSaleSize() {
		return totalSaleSize;
	}
	public void setTotalSaleSize(Double totalSaleSize) {
		this.totalSaleSize = totalSaleSize;
	}
	public Double getPermitSaleSize() {
		return permitSaleSize;
	}
	public void setPermitSaleSize(Double permitSaleSize) {
		this.permitSaleSize = permitSaleSize;
	}
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	public Double getPreSaleSize() {
		return preSaleSize;
	}
	public void setPreSaleSize(Double preSaleSize) {
		this.preSaleSize = preSaleSize;
	}
	public Double getSalePercentRate() {
		return salePercentRate;
	}
	public void setSalePercentRate(Double salePercentRate) {
		this.salePercentRate = salePercentRate;
	}
	public Double getPreAvgPrice() {
		return preAvgPrice;
	}
	public void setPreAvgPrice(Double preAvgPrice) {
		this.preAvgPrice = preAvgPrice;
	}
	public Double getCurrAvgPrice() {
		return currAvgPrice;
	}
	public void setCurrAvgPrice(Double currAvgPrice) {
		this.currAvgPrice = currAvgPrice;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
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
	
	public Date getProjectEndTime() {
		return projectEndTime;
	}
	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}
	
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
		.append(this.investmentStatus).append(this.projectEndTime).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RepaymentProject)) {
			return false;
		}

		final RepaymentProject investmentProject = (RepaymentProject) o;

		return !(investmentStatus != null ? !investmentStatus
				.equals(investmentProject.investmentStatus)
				: investmentProject.investmentStatus != null)
				&& !(projectEndTime != null ? !projectEndTime
						.equals(investmentProject.projectEndTime)
						: investmentProject.projectEndTime != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (investmentStatus != null ? investmentStatus.hashCode() : 0);
        result = 29 * result + (projectEndTime != null ? projectEndTime.hashCode() : 0);
        return result;
	}
	

}
