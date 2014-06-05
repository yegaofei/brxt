package com.brxt.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;

public class RepaymentDaoTest extends BaseDaoTestCase {

	@Autowired
	private RepaymentDao repaymentDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testSave() throws Exception {
		ProjectInfo project = projectInfoDao.get(1L);
		Repayment repayment = new Repayment();
		repayment.setProjectInfo(project);
		repayment.setAmount(new BigDecimal("1000000"));
		repaymentDao.save(repayment);
		flush();

		List<Repayment> scList = repaymentDao.findByProjId(1L);
		assertNotNull(scList);
		assertTrue(scList.size() >= 2);

	}
}
