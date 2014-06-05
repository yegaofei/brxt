package com.brxt.model.projectprogress;

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

import org.appfuse.model.User;
import org.hibernate.annotations.Type;

import com.brxt.model.ProjectInfo;

@NamedNativeQueries({
	@NamedNativeQuery(
			name = "searchSLPByProjectInfoId",
			query = "select * from supply_liquid_project s where s.projectInfo_id = :projectInfo_id",
		        resultClass = SupplyLiquidProject.class
			)
})

@Entity
@Table(name = "supply_liquid_project")
public class SupplyLiquidProject {

	private Long id;
	private ProjectInfo projectInfo;
	private String name;
	private Date projectEndTime; //项目进展截止时间
	private String industryVista;
	private String firmSize;
	private String description;
	private String saleSituation;
	private Boolean bigChanges;
	private String investmentProgress;
	private String comments;
	private String evaluation;
	
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="projectInfo_id")
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	public String getIndustryVista() {
		return industryVista;
	}
	public void setIndustryVista(String industryVista) {
		this.industryVista = industryVista;
	}
	public String getFirmSize() {
		return firmSize;
	}
	public void setFirmSize(String firmSize) {
		this.firmSize = firmSize;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSaleSituation() {
		return saleSituation;
	}
	public void setSaleSituation(String saleSituation) {
		this.saleSituation = saleSituation;
	}
	
	@Column(name = "bigChanges")
	@Type(type="yes_no")
	public Boolean getBigChanges() {
		return bigChanges;
	}
	public void setBigChanges(Boolean bigChanges) {
		this.bigChanges = bigChanges;
	}
	public String getInvestmentProgress() {
		return investmentProgress;
	}
	public void setInvestmentProgress(String investmentProgress) {
		this.investmentProgress = investmentProgress;
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
}
