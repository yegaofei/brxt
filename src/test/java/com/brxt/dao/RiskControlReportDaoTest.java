package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.ProjectInfo;
import com.brxt.model.report.RiskControlReport;

public class RiskControlReportDaoTest extends BaseDaoTestCase {

	@Autowired
    private ProjectInfoDao projectInfoDao;
	
	@Autowired
    private RiskControlReportDao riskControlReportDao;
	
	@Test
	public void testSave()
	{
		String testComment = "Test comments in dao test";
		ProjectInfo projectInfo = projectInfoDao.get(1L);
		RiskControlReport riskControlReport = new RiskControlReport();
		riskControlReport.setProjectInfo(projectInfo);
		riskControlReport.setReportSeason("2014Q3");
		riskControlReport.setComments(testComment);
		riskControlReportDao.save(riskControlReport);
		flush();
		
		projectInfo = projectInfoDao.get(1L);
		List<RiskControlReport> riskControlReports = riskControlReportDao.findByProjectInfo(projectInfo);
		assertNotNull(riskControlReports);
		assertNotNull(riskControlReports.get(0));
		riskControlReport = riskControlReports.get(0);
		assertEquals(testComment, riskControlReport.getComments());
	}
	
	@Test
	public void testFind()
	{
		String testComment = "Test comments in dao test";
		String reportSeason = "2014Q3";
		ProjectInfo projectInfo = projectInfoDao.get(1L);
		RiskControlReport riskControlReport = new RiskControlReport();
		riskControlReport.setProjectInfo(projectInfo);
		riskControlReport.setReportSeason(reportSeason);
		riskControlReport.setComments(testComment);
		riskControlReportDao.save(riskControlReport);
		flush();
		
		projectInfo = projectInfoDao.get(1L);
		riskControlReport = riskControlReportDao.find(projectInfo, reportSeason);
		assertNotNull(riskControlReport);
		assertEquals(testComment, riskControlReport.getComments());
	}

}
