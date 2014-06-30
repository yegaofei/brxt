package com.brxt.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.RiskControlReportDao;
import com.brxt.model.ProjectInfo;
import com.brxt.model.report.RiskControlReport;
import com.brxt.service.ReportManager;

@Service("reportManager")
public class ReportManagerImpl extends
		GenericManagerImpl<RiskControlReport, Long> implements ReportManager {

	RiskControlReportDao riskControlReportDao;
	
	@Autowired
	public void setRiskControlReportDao(RiskControlReportDao riskControlReportDao) {
		this.dao = riskControlReportDao;
		this.riskControlReportDao = riskControlReportDao;
	}
	
	public RiskControlReport findRiskControlReport(ProjectInfo projectInfo, String reportSeason)
	{
		return riskControlReportDao.find(projectInfo, reportSeason);
	}
	
	public List<RiskControlReport> findByReport(RiskControlReport report){
		return riskControlReportDao.findByReport(report);
	}
}
