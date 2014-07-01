package com.brxt.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.projectprogress.InvestmentProject;

public class InvestmentProjectDaoTest extends BaseDaoTestCase {

	@Autowired
	private InvestmentProjectDao investmentProjectDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;

	@Test
	public void testGet() throws Exception {
		InvestmentProject investment = investmentProjectDao.get(1L);
		assertNotNull(investment);
		assertNotNull(investment.getProjectInfo());
	}
	
	@Test 
	public void testFindUniqueProjects() {
		List<InvestmentProject> investments = investmentProjectDao.findUniqueProjects(1L);
		assertNotNull(investments);
	}

}
