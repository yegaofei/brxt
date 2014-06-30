package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.ProjectInfo;
import com.brxt.model.report.RiskControlReport;

public interface ReportManager extends GenericManager<RiskControlReport, Long>  {

	public RiskControlReport findRiskControlReport(ProjectInfo projectInfo, String reportSeason);
	
	public List<RiskControlReport> findByReport(RiskControlReport report);
}
