package com.brxt.model.report;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "risk_control_report_status")
public class ReportStatus {
	
	private Long id;
	private Boolean offsiteDataInput;
	private Boolean commitReport;
	private Boolean reportAudit;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "offsiteDataInput", nullable=true, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getOffsiteDataInput() {
		return offsiteDataInput;
	}
	public void setOffsiteDataInput(Boolean offsiteDataInput) {
		this.offsiteDataInput = offsiteDataInput;
	}
	
	@Column(name = "commitReport", nullable=true, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getCommitReport() {
		return commitReport;
	}
	public void setCommitReport(Boolean commitReport) {
		this.commitReport = commitReport;
	}
	
	@Column(name = "reportAudit", nullable=true, columnDefinition="char(1) default 'N'" )
	@Type(type="yes_no")
	public Boolean getReportAudit() {
		return reportAudit;
	}
	public void setReportAudit(Boolean reportAudit) {
		this.reportAudit = reportAudit;
	}
	

}
