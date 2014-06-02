package com.brxt.model;

import java.io.Serializable;
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
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.appfuse.model.User;
import org.hibernate.annotations.Type;

@NamedNativeQueries({
	@NamedNativeQuery(
	name = "searchByProjectInfoIdCounterpartyId",
	query = "select * from subject_capacity s where s.projectInfo_id = :projectInfo_id and s.counterparty_id = :counterparty_id",
        resultClass = SubjectCapacity.class
	),
	@NamedNativeQuery(
			name = "searchByProjectInfoId",
			query = "select * from subject_capacity s where s.projectInfo_id = :projectInfo_id",
		        resultClass = SubjectCapacity.class
			)
})
@Entity
@Table(name = "subject_capacity")
public class SubjectCapacity extends BaseObject implements Serializable {

	private Long id;
	private Counterparty counterparty; // 交易对手id
	private ProjectInfo projectInfo; // 项目id
	private Integer reportSeason; // 报表季度
	private Date checkTime; // 排查时间
	private String licenseVerificationDate; // 营业执照最新年检日期
	private String orgCodeVerificationDate; // 组织机构代码证最新年检日期
	private Boolean loanCardValid; // 贷款卡是否有效
	private Boolean nameChanged; // 企业/单位名称是否变更
	private Boolean ownerChanged; // 企业/单位法人代表是否变化
	private Boolean ownershipChanged; // 企业股权结构是否变化
	private Boolean capitalChanged; // 企业注册资本或实收资本是否变化
	private Boolean devCapacityChanged; // 开发资质是否变化
	private Boolean bizScopeChanged; // 经营范围是否有变化
	private Boolean otherBigChanges; // 是否有其他重大变更事项
	private String verifyResults; // 事业单位法人证年检情况
	private String comments; // 备注
	private User createUser; // 创建人
	private Date createTime; // 创建时间
	private User updateUser; // 最后更新人
	private Date updateTime; // 最后更新时间
	private Integer version;

	/**
	 * 
	 */
	private static final long serialVersionUID = 5275631710808978254L;
	
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

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="projectInfo_id") 
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	public Integer getReportSeason() {
		return reportSeason;
	}

	public void setReportSeason(Integer reportSeason) {
		this.reportSeason = reportSeason;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getLicenseVerificationDate() {
		return licenseVerificationDate;
	}

	public void setLicenseVerificationDate(String licenseVerificationDate) {
		this.licenseVerificationDate = licenseVerificationDate;
	}

	public String getOrgCodeVerificationDate() {
		return orgCodeVerificationDate;
	}

	public void setOrgCodeVerificationDate(String orgCodeVerificationDate) {
		this.orgCodeVerificationDate = orgCodeVerificationDate;
	}

	@Column(name = "loanCardValid")
	@Type(type="yes_no")
	public Boolean getLoanCardValid() {
		return loanCardValid;
	}

	public void setLoanCardValid(Boolean loanCardValid) {
		this.loanCardValid = loanCardValid;
	}

	@Column(name = "nameChanged")
	@Type(type="yes_no")
	public Boolean getNameChanged() {
		return nameChanged;
	}

	public void setNameChanged(Boolean nameChanged) {
		this.nameChanged = nameChanged;
	}

	@Column(name = "ownerChanged")
	@Type(type="yes_no")
	public Boolean getOwnerChanged() {
		return ownerChanged;
	}

	public void setOwnerChanged(Boolean ownerChanged) {
		this.ownerChanged = ownerChanged;
	}

	@Column(name = "ownershipChanged")
	@Type(type="yes_no")
	public Boolean getOwnershipChanged() {
		return ownershipChanged;
	}

	public void setOwnershipChanged(Boolean ownershipChanged) {
		this.ownershipChanged = ownershipChanged;
	}

	@Column(name = "capitalChanged")
	@Type(type="yes_no")
	public Boolean getCapitalChanged() {
		return capitalChanged;
	}

	public void setCapitalChanged(Boolean capitalChanged) {
		this.capitalChanged = capitalChanged;
	}

	@Column(name = "devCapacityChanged")
	@Type(type="yes_no")
	public Boolean getDevCapacityChanged() {
		return devCapacityChanged;
	}

	public void setDevCapacityChanged(Boolean devCapacityChanged) {
		this.devCapacityChanged = devCapacityChanged;
	}

	@Column(name = "bizScopeChanged")
	@Type(type="yes_no")
	public Boolean getBizScopeChanged() {
		return bizScopeChanged;
	}

	public void setBizScopeChanged(Boolean bizScopeChanged) {
		this.bizScopeChanged = bizScopeChanged;
	}

	@Column(name = "otherBigChanges")
	@Type(type="yes_no")
	public Boolean getOtherBigChanges() {
		return otherBigChanges;
	}

	public void setOtherBigChanges(Boolean otherBigChanges) {
		this.otherBigChanges = otherBigChanges;
	}

	public String getVerifyResults() {
		return verifyResults;
	}

	public void setVerifyResults(String verifyResults) {
		this.verifyResults = verifyResults;
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

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
				.append(this.counterparty).append(this.projectInfo)
				.append(this.checkTime).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SubjectCapacity)) {
			return false;
		}

		final SubjectCapacity subjectCapacity = (SubjectCapacity) o;

		return !(counterparty != null ? !counterparty
				.equals(subjectCapacity.counterparty)
				: subjectCapacity.counterparty != null)
				&& !(projectInfo != null ? !projectInfo
						.equals(subjectCapacity.projectInfo)
						: subjectCapacity.projectInfo != null)
				&& !(checkTime != null ? !checkTime
						.equals(subjectCapacity.checkTime)
						: subjectCapacity.checkTime != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (counterparty != null ? counterparty.hashCode() : 0);
        result = 29 * result + (projectInfo != null ? projectInfo.hashCode() : 0);
        result = 29 * result + (checkTime != null ? checkTime.hashCode() : 0);
        return result;
	}

}
