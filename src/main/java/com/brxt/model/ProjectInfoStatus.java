package com.brxt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "project_info_status")
public class ProjectInfoStatus {
	
	private Long id;
	private ProjectInfo projectInfo;
	private Boolean subjectCapacity;
	private Boolean financeStatement;
	private Boolean creditInformation;
	private Boolean repayment;
	private Boolean projectProgress;
	private Boolean committed;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(mappedBy = "projectInfoStatus")
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	@Column(name = "subjectCapacity", nullable=false, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getSubjectCapacity() {
		return subjectCapacity;
	}
	public void setSubjectCapacity(Boolean subjectCapacity) {
		this.subjectCapacity = subjectCapacity;
	}
	
	@Column(name = "financeStatement", nullable=false, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getFinanceStatement() {
		return financeStatement;
	}
	public void setFinanceStatement(Boolean financeStatement) {
		this.financeStatement = financeStatement;
	}
	
	@Column(name = "creditInformation", nullable=false, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getCreditInformation() {
		return creditInformation;
	}
	public void setCreditInformation(Boolean creditInformation) {
		this.creditInformation = creditInformation;
	}
	
	@Column(name = "repayment", nullable=false, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getRepayment() {
		return repayment;
	}
	public void setRepayment(Boolean repayment) {
		this.repayment = repayment;
	}
	
	@Column(name = "projectProgress", nullable=false, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getProjectProgress() {
		return projectProgress;
	}
	public void setProjectProgress(Boolean projectProgress) {
		this.projectProgress = projectProgress;
	}
	
	@Column(name = "committed", nullable=false, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getCommitted() {
		return committed;
	}
	public void setCommitted(Boolean commit) {
		this.committed = commit;
	}
	
}
