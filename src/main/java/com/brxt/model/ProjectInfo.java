package com.brxt.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.brxt.model.report.RiskControlReport;

@Entity
@Table(name = "project_info")
public class ProjectInfo extends BaseObject {

	private static final long serialVersionUID = -217461395372350336L;
	private Long id; //项目id
	private String projectName; //项目名称
	private String expectedReturn; //预期收益率
	private String fundUsage; //资金运用方式
	private String riskManager; //风险经理
	private String delegateManager; //托管经理
	private String trustManager; //信托经理
	private String guaranteeMode; //保障措施
	private String projectType; //是否事务管理类信托项目
	private List<ProjectSize> projectSizes = new ArrayList<ProjectSize>(); //信托规模
	private String createUser; //创建人
	private Date createTime; //创建时间
	private String updateUser; //最后更新人
	private Date updateTime; //最后更新时间
	private Integer version;
	private Set<InvestmentStatus> investments = new HashSet<InvestmentStatus>(); //投资项目
	private Set<Counterparty> counterparties = new HashSet<Counterparty>(); //交易对手
	private Set<Counterparty> guarantors = new HashSet<Counterparty>(); //担保人
	private ProjectInfoStatus projectInfoStatus = new ProjectInfoStatus();
	private List<RiskControlReport> riskControlReports = new ArrayList<RiskControlReport>(); //风险排查报告
	
	private Date searchTimeStart;
	private Date searchTimeEnd;
		
	@Column(name = "project_name", length = 50)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(columnDefinition = "longtext")
	public String getExpectedReturn() {
		return expectedReturn;
	}

	public void setExpectedReturn(String expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

	@Column(columnDefinition = "longtext")
	public String getFundUsage() {
		return fundUsage;
	}

	public void setFundUsage(String fundUsage) {
		this.fundUsage = fundUsage;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "guaranteeMode", columnDefinition = "longtext")
	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "projectInfo", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT) 
	public List<ProjectSize> getProjectSizes() {
		return projectSizes;
	}

	public void setProjectSizes(List<ProjectSize> projectSizes) {
		this.projectSizes = projectSizes;
	}
	
	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getRiskManager() {
		return riskManager;
	}

	public void setRiskManager(String riskManager) {
		this.riskManager = riskManager;
	}

	public String getDelegateManager() {
		return delegateManager;
	}

	public void setDelegateManager(String delegateManager) {
		this.delegateManager = delegateManager;
	}

	public String getTrustManager() {
		return trustManager;
	}

	public void setTrustManager(String trustManager) {
		this.trustManager = trustManager;
	}

	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "project_info_investments",
            joinColumns = { @JoinColumn(name = "project_info_id") },
            inverseJoinColumns = @JoinColumn(name = "investment_status_id")
    )
	public Set<InvestmentStatus> getInvestments() {
		return investments;
	}

	public void setInvestments(Set<InvestmentStatus> investments) {
		this.investments = investments;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String create) {
		this.createUser = create;
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

	public void setUpdateUser(String update) {
		this.updateUser = update;
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

	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "project_info_counterparties",
            joinColumns = { @JoinColumn(name = "project_info_id") },
            inverseJoinColumns = @JoinColumn(name = "counterparty_id")
    )
	public Set<Counterparty> getCounterparties() {
		return counterparties;
	}

	public void setCounterparties(Set<Counterparty> counterparties) {
		this.counterparties = counterparties;
	}

	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "project_info_guarantors",
            joinColumns = { @JoinColumn(name = "project_info_id") },
            inverseJoinColumns = @JoinColumn(name = "guarantor_id")
    )
	public Set<Counterparty> getGuarantors() {
		return guarantors;
	}

	public void setGuarantors(Set<Counterparty> guarantors) {
		this.guarantors = guarantors;
	}

	@OneToOne(cascade = CascadeType.ALL)
	public ProjectInfoStatus getProjectInfoStatus() {
		return projectInfoStatus;
	}

	public void setProjectInfoStatus(ProjectInfoStatus projectInfoStatus) {
		this.projectInfoStatus = projectInfoStatus;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "projectInfo", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT)
	public List<RiskControlReport> getRiskControlReports() {
		return riskControlReports;
	}

	public void setRiskControlReports(List<RiskControlReport> riskControlReports) {
		this.riskControlReports = riskControlReports;
	}

	@Transient
	public Date getSearchTimeStart() {
		return searchTimeStart;
	}

	public void setSearchTimeStart(Date searchTimeStart) {
		this.searchTimeStart = searchTimeStart;
	}

	@Transient
	public Date getSearchTimeEnd() {
		return searchTimeEnd;
	}

	public void setSearchTimeEnd(Date searchTimeEnd) {
		this.searchTimeEnd = searchTimeEnd;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.projectName).toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof ProjectInfo)) {
			return false;
		}

		final ProjectInfo projectInfo = (ProjectInfo) o;

		return !(projectName != null ? !projectName
				.equals(projectInfo.projectName)
				: projectInfo.projectName != null);
	}

	@Override
	public int hashCode() {
		return (projectName != null ? projectName.hashCode() : 0);
	}

}
