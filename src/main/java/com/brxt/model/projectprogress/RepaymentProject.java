package com.brxt.model.projectprogress;

import java.util.Date;

import javax.persistence.CascadeType;
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

import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.CapitalInvestmentType;

@NamedNativeQueries({
	@NamedNativeQuery(
			name = "searchRPByProjectInfoId",
			query = "select * from repayment_project s where s.projectInfo_id = :projectInfo_id",
		        resultClass = RepaymentProject.class
			)
})

@Entity
@Table(name = "repayment_project")
public class RepaymentProject extends BaseObject  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1401605168965563698L;
	private Long id;
	private ProjectInfo projectInfo;
	private String name;  //投资项目名称
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
	
	private User createUser; // 创建人
	private Date createTime; // 创建时间
	private User updateUser; // 最后更新人
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
	@JoinColumn(name="projectInfo_id")
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Date getProjectEndTime() {
		return projectEndTime;
	}
	public void setProjectEndTime(Date projectEndTime) {
		this.projectEndTime = projectEndTime;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
		.append(this.name).toString();
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

		return !(name != null ? !name
				.equals(investmentProject.name)
				: investmentProject.name != null)
				&& !(projectInfo != null ? !projectInfo
						.equals(investmentProject.projectInfo)
						: investmentProject.projectInfo != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (name != null ? name.hashCode() : 0);
        result = 29 * result + (projectInfo != null ? projectInfo.hashCode() : 0);
        return result;
	}
	

}
