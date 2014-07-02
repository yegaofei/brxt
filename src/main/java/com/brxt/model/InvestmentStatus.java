package com.brxt.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;

@Entity
@Table(name = "investment_status")
public class InvestmentStatus extends BaseObject{

	private static final long serialVersionUID = 2315172272788889932L;
	private Long id;
	private String projectName;
	private CapitalInvestmentType capitalInvestmentType;
	private String projectType;
	
	private List<InvestmentProject> investmentProjects = new  ArrayList<InvestmentProject>();
	private List<RepaymentProject> repaymentProjects = new  ArrayList<RepaymentProject>();
	private List<SupplyLiquidProject> supplyLiquidProjects = new  ArrayList<SupplyLiquidProject>();
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable = false)
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Column(nullable = false)
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

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "investmentStatus", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT)
	public List<InvestmentProject> getInvestmentProjects() {
		return investmentProjects;
	}

	public void setInvestmentProjects(List<InvestmentProject> investmentProjects) {
		this.investmentProjects = investmentProjects;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "investmentStatus", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT)
	public List<RepaymentProject> getRepaymentProjects() {
		return repaymentProjects;
	}

	public void setRepaymentProjects(List<RepaymentProject> repaymentProjects) {
		this.repaymentProjects = repaymentProjects;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "investmentStatus", cascade = { CascadeType.ALL })
	@Fetch(FetchMode.SELECT)
	public List<SupplyLiquidProject> getSupplyLiquidProjects() {
		return supplyLiquidProjects;
	}

	public void setSupplyLiquidProjects(
			List<SupplyLiquidProject> supplyLiquidProjects) {
		this.supplyLiquidProjects = supplyLiquidProjects;
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
