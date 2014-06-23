package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.report.RiskControlReport;

public class RiskControlReportDaoTest extends BaseDaoTestCase {

	@Autowired
    private ProjectInfoDao projectInfoDao;
	
	@Autowired
	private FinancialSheetDao profitStatementDao;
	
	@Autowired
    private RiskControlReportDao riskControlReportDao;
	
	@Test
	public void testSave()
	{
		ProjectInfo projectInfo = projectInfoDao.get(1L);
		RiskControlReport riskControlReport = projectInfo.getRiskControlReport();
		if(riskControlReport == null || riskControlReport.getId() == null)
		{
			riskControlReport = new RiskControlReport();
			riskControlReport.setProjectInfo(projectInfo);
			riskControlReport = riskControlReportDao.save(riskControlReport);
		}
		
		riskControlReport.setComments("Test comments in dao test");
		projectInfo.setRiskControlReport(riskControlReport);
		projectInfoDao.save(projectInfo);
		flush();
		
		projectInfo = projectInfoDao.get(1L);
		riskControlReport = projectInfo.getRiskControlReport();
		assertNotNull(riskControlReport);
		assertEquals("Test comments in dao test", riskControlReport.getComments());
	}
	
	@Test
	public void testCascadeSave()
	{
		ProjectInfo projectInfo = projectInfoDao.get(1L);
		RiskControlReport riskControlReport = projectInfo.getRiskControlReport();
		if(riskControlReport == null || riskControlReport.getId() == null)
		{
			riskControlReport = new RiskControlReport();
			riskControlReport.setProjectInfo(projectInfo);
			riskControlReport = riskControlReportDao.save(riskControlReport);
		}
		
		Set<Counterparty> counterparties = projectInfo.getCounterparties();
		Iterator<Counterparty> itcp = counterparties.iterator();
		ProfitStatement bs = null;
		while (itcp.hasNext())
		{
			Counterparty cp = itcp.next();
			bs = (ProfitStatement)profitStatementDao.findLatest(projectInfo, cp);
			if(bs != null)
			{
				break;
			}
		}
		
		if(bs == null)
		{
			itcp = counterparties.iterator();
			Counterparty cp = itcp.next();
			bs = new ProfitStatement();
			bs.setProjectInfo(projectInfo);
			bs.setCounterparty(cp);
			bs.setReportYear(2014);
			bs.setReportMonth((short)5);
			bs.setOperatingIncome(new BigDecimal("50000"));
			bs = (ProfitStatement)profitStatementDao.save(bs);
		}
		
		riskControlReport.getProfitStatements().add(bs);
		//riskControlReport = riskControlReportDao.save(riskControlReport);
		projectInfo.setRiskControlReport(riskControlReport);
		projectInfoDao.save(projectInfo);
		flush();
		
		projectInfo = projectInfoDao.get(1L);
		riskControlReport = projectInfo.getRiskControlReport();
		assertNotNull(riskControlReport);
		assertNotNull(riskControlReport.getProfitStatements());
		assertTrue(riskControlReport.getProfitStatements().size() > 0);
		assertTrue(riskControlReport.getProfitStatements().contains(bs));
		
	}
}
