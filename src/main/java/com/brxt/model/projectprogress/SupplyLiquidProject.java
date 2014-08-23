package com.brxt.model.projectprogress;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Type;

import com.brxt.model.InvestmentStatus;

@Entity
@Table(name = "supply_liquid_project")
public class SupplyLiquidProject extends BaseObject {
	private static final long serialVersionUID = 6426397213432166534L;
	private Long id;
	private InvestmentStatus investmentStatus;
	private Date projectEndTime; //项目进展截止时间
	private String industryVista;
	private String firmSize;
	private String description;
	private String saleSituation;
	private Boolean bigChanges = Boolean.FALSE;
	private String investmentProgress;
	private String comments;
	private String evaluation;
	
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
	@ManyToOne(cascade={CascadeType.REFRESH}, optional=true)  
	@JoinColumn(name="investment_status_id")
	public InvestmentStatus getInvestmentStatus() {
		return investmentStatus;
	}
	public void setInvestmentStatus(InvestmentStatus investmentStatus) {
		this.investmentStatus = investmentStatus;
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
		if (!(o instanceof SupplyLiquidProject)) {
			return false;
		}

		final SupplyLiquidProject investmentProject = (SupplyLiquidProject) o;

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
