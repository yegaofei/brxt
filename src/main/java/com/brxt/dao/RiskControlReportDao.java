package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.ProjectInfo;
import com.brxt.model.report.RiskControlReport;

public interface RiskControlReportDao extends GenericDao<RiskControlReport, Long>{

	public List<RiskControlReport> findByProjectInfo(ProjectInfo projectInfo);
	
	public RiskControlReport find(ProjectInfo projectInfo, String reportSeason);
	
	public List<RiskControlReport> findByReport(RiskControlReport report);
}
