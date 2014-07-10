package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.CorporateBalanceSheet;

public class CorpBalanceSheetDaoTest  extends BaseDaoTestCase{
	
	@Autowired
	private FinancialSheetDao<CorporateBalanceSheet, Long> corpBalanceSheetDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;
	
	@Test
	public void testSave()
	{ 
		ProjectInfo project = projectInfoDao.get(1L);
		Set<Counterparty> cpSet = project.getCounterparties();
		Iterator<Counterparty> it = cpSet.iterator();
		if(it.hasNext())
		{
			Counterparty cp = it.next();
			CorporateBalanceSheet cbs = new CorporateBalanceSheet();
			cbs.setProjectInfo(project);
			cbs.setCounterparty(cp);
			cbs.setReportYear(2014);
			cbs.setReportMonth((short)5);
			corpBalanceSheetDao.save(cbs);
		}
		else
		{
			fail();
		}
	}
	
	@Test
	public void testSaveAndFind()
	{
		ProjectInfo project = projectInfoDao.get(1L);
		Set<Counterparty> cpSet = project.getCounterparties();
		Iterator<Counterparty> it = cpSet.iterator();
		if(it.hasNext())
		{
			Counterparty cp = it.next();
			CorporateBalanceSheet cbs = new CorporateBalanceSheet();
			cbs.setProjectInfo(project);
			cbs.setCounterparty(cp);
			cbs.setReportYear(2014);
			cbs.setReportMonth((short)5);
			cbs.setInventory(new BigDecimal("2003"));
			corpBalanceSheetDao.save(cbs);
			flush();
			
			CorporateBalanceSheet corpBalanceSheet = corpBalanceSheetDao.find(project, cp, 2014, (short)5);
			assertNotNull(corpBalanceSheet);
			assertEquals(new BigDecimal("2003"), corpBalanceSheet.getInventory());
		}
		else
		{
			fail();
		}
	}
	
	@Test
	public void testFindLatest()
	{
		ProjectInfo project = projectInfoDao.get(1L);
		Set<Counterparty> cpSet = project.getCounterparties();
		Iterator<Counterparty> it = cpSet.iterator();
		if(it.hasNext())
		{
			Counterparty cp = it.next();
			CorporateBalanceSheet cbs = new CorporateBalanceSheet();
			cbs.setProjectInfo(project);
			cbs.setCounterparty(cp);
			cbs.setReportYear(2014);
			cbs.setReportMonth((short)5);
			cbs.setInventory(new BigDecimal("2003"));
			corpBalanceSheetDao.save(cbs);
			
			CorporateBalanceSheet cbsOld = new CorporateBalanceSheet();
			cbsOld.setProjectInfo(project);
			cbsOld.setCounterparty(cp);
			cbsOld.setReportYear(2015);
			cbsOld.setReportMonth((short)7);
			cbsOld.setInventory(new BigDecimal("2004"));
			corpBalanceSheetDao.save(cbsOld);
			flush();
			
			CorporateBalanceSheet corpBalanceSheet = corpBalanceSheetDao.findLatest(project, cp);
			assertNotNull(corpBalanceSheet);
			assertEquals(new BigDecimal("2004"), corpBalanceSheet.getInventory());
		}
		else
		{
			fail();
		}
	}

}
