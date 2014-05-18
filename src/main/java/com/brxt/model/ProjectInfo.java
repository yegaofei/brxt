package com.brxt.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "project_info")
public class ProjectInfo extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -217461395372350336L;
	private Long id;
	private String projectName;
	private Double expectedReturn;
	private String fundUsage;
	private String manager;
	private String guaranteeMode;
	private Boolean officeManagement;
	private String capitalInvestment;
	private List<ProjectSize> projectSizes = new ArrayList<ProjectSize>();

	@Column(name = "project_name", length = 50)
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Double getExpectedReturn() {
		return expectedReturn;
	}

	public void setExpectedReturn(Double expectedReturn) {
		this.expectedReturn = expectedReturn;
	}

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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode;
	}

	@Column(name = "officeManagement")
	@Type(type="yes_no")
	public Boolean getOfficeManagement() {
		return officeManagement;
	}

	public void setOfficeManagement(Boolean officeManagement) {
		this.officeManagement = officeManagement;
	}

	public String getCapitalInvestment() {
		return capitalInvestment;
	}

	public void setCapitalInvestment(String capitalInvestment) {
		this.capitalInvestment = capitalInvestment;
	}

	@OneToMany(mappedBy = "projectInfo", cascade = { CascadeType.ALL })
	public List<ProjectSize> getProjectSizes() {
		return projectSizes;
	}

	public void setProjectSizes(List<ProjectSize> projectSizes) {
		this.projectSizes = projectSizes;
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
