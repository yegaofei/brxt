package com.brxt.model.projectprogress;

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

import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.CapitalInvestmentType;

@NamedNativeQueries({
	@NamedNativeQuery(
			name = "searchIPByProjectInfoId",
			query = "select * from investment_project s where s.projectInfo_id = :projectInfo_id",
		        resultClass = InvestmentProject.class
			)
})

@Entity
@Table(name = "investment_project")
public class InvestmentProject extends BaseObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1948840623486569978L;
	private Long id;
	private ProjectInfo projectInfo;
	private String name;  //投资项目名称
	private Date projectEndTime; //项目进展截止时间
	private CapitalInvestmentType type;
	private String investmentProjectType;
	private Date startTime;
	private Date endTime; //预计何时竣工
	private BigDecimal investmentAmount; //截至本期已投资额
	private BigDecimal totalAmout; //预计总投资额
	private String description; //项目进度描述
	private Boolean delayed;
	private String comments;
	
	private String evaluation; //资金投向综合评价
	private String policyChanges; //房地产市场调控政策有无变化
	private String priceChanges; //项目所在区域商品房价格走势情况；如难以取得上述数据，可结合周边楼盘定价及销售情况
	
	private String createUser; // 创建人
	private Date createTime; // 创建时间
	private String updateUser; // 最后更新人
	private Date updateTime; // 最后更新时间
	private Integer version;
	
	private Boolean sameAsRepayment = true;

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

	public String getInvestmentProjectType() {
		return investmentProjectType;
	}

	public void setInvestmentProjectType(String investmentProjectType) {
		this.investmentProjectType = investmentProjectType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public BigDecimal getTotalAmout() {
		return totalAmout;
	}

	public void setTotalAmout(BigDecimal totalAmout) {
		this.totalAmout = totalAmout;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "project_delayed")
	@Type(type="yes_no")
	public Boolean getDelayed() {
		return delayed;
	}

	public void setDelayed(Boolean delayed) {
		this.delayed = delayed;
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

	public String getPolicyChanges() {
		return policyChanges;
	}

	public void setPolicyChanges(String policyChanges) {
		this.policyChanges = policyChanges;
	}

	public String getPriceChanges() {
		return priceChanges;
	}

	public void setPriceChanges(String priceChanges) {
		this.priceChanges = priceChanges;
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

	@Transient  
	public Boolean getSameAsRepayment() {
		return sameAsRepayment;
	}

	public void setSameAsRepayment(Boolean sameAsRepayment) {
		this.sameAsRepayment = sameAsRepayment;
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
		if (!(o instanceof InvestmentProject)) {
			return false;
		}

		final InvestmentProject investmentProject = (InvestmentProject) o;

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
