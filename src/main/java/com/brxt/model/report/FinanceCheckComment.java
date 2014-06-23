package com.brxt.model.report;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.brxt.model.Counterparty;

@Entity
@Table(name = "finance_check_comment")
public class FinanceCheckComment {

	private Long id;
	private Counterparty counterparty;
	private RiskControlReport riskControlReport;
	private String comments;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)  
	@JoinColumn(name="counterparty_id") 
	public Counterparty getCounterparty() {
		return counterparty;
	}
	public void setCounterparty(Counterparty counterparty) {
		this.counterparty = counterparty;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)  
	@JoinColumn(name="risk_control_report_id") 
	public RiskControlReport getRiskControlReport() {
		return riskControlReport;
	}
	public void setRiskControlReport(RiskControlReport riskControlReport) {
		this.riskControlReport = riskControlReport;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
