package com.brxt.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;

import com.brxt.model.enums.CapitalInvestmentType;

@Entity
@Table(name = "investment_status")
public class InvestmentStatus extends BaseObject{

	private Long id;
	private String projectName;
	private CapitalInvestmentType capitalInvestmentType;
	private String projectType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	@Transient
	public CapitalInvestmentType getCapitalInvestmentType() {
		return capitalInvestmentType;
	}

	public void setCapitalInvestmentType(CapitalInvestmentType capitalInvestmentType) {
		this.capitalInvestmentType = capitalInvestmentType;
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
		if (!(o instanceof InvestmentStatus)) {
			return false;
		}

		final InvestmentStatus investmentStatus = (InvestmentStatus) o;

		return !(projectName != null ? !projectName
				.equals(investmentStatus.projectName)
				: investmentStatus.projectName != null)
				&&
				!(projectType != null ? !projectType
						.equals(investmentStatus.projectType)
						: investmentStatus.projectType != null);
	}

	@Override
	public int hashCode() {
		int result;
        result = (projectName != null ? projectName.hashCode() : 0);
        result = 29 * result + (projectType != null ? projectType.hashCode() : 0);
        return result;
	}
	
}
