package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.dao.UserDao;
import org.appfuse.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.model.enums.CounterpartyType;


public class ProjectInfoDaoTest extends BaseDaoTestCase {

	@Autowired
    private ProjectInfoDao projectInfoDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CounterpartyDao counterpartyDao;
		
	
	@Test //(expected = DataAccessException.class)
	public void testAddAndRemoveProjectInfo() throws Exception {
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setProjectName("TestProject2");
		ProjectSize projectSize = new ProjectSize();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date startTime = sf.parse("2014-02-02");
		Date endTime = sf.parse("2015-02-01");
		projectSize.setStartTime(startTime);
		projectSize.setEndTime(endTime);
		projectSize.setProjectSize(new BigDecimal("5000000"));
		projectInfo.getProjectSizes().add(projectSize);
		
		Counterparty cp = new Counterparty();
		String cpName = "CP1";
		cp.setName(cpName);
		cp.setCounterpartyType(CounterpartyType.REAL_ESTATE_FIRM.toString());
		counterpartyDao.save(cp);
		
		cp = counterpartyDao.findByCounterpartyName(cpName);
		projectInfo.getCounterparties().add(cp);
		User user = new User();
		user.setUsername("Philip");
		user.setLastName("gaofei");
		user.setFirstName("Ye");
		user.setAccountExpired(false);
		user.setAccountLocked(false);
		user.setCredentialsExpired(false);
		user.setEmail("yegaofei@gmail.com");
		user.setPassword("123");
		userDao.save(user);
		projectInfo.setCreateUser(user.getUsername());
		projectInfo.setUpdateUser(user.getUsername());
		projectInfo.setCreateTime(new Date());
		
		projectInfo = projectInfoDao.save(projectInfo);
	    flush();
	 
	    projectInfo = projectInfoDao.get(projectInfo.getId());
	 
	    assertEquals("TestProject2", projectInfo.getProjectName());
	    assertNotNull(projectInfo.getId());
	    assertEquals(startTime, projectInfo.getProjectSizes().get(0).getStartTime());
	    assertNotNull(projectInfo.getCounterparties());
	    assertTrue(projectInfo.getCounterparties().size() > 0);
	    assertNotNull(projectInfo.getUpdateUser());
	    /*
	    log.debug("removing projectInfoDao...");	 
	    projectInfoDao.remove(projectInfo.getId());
	    projectInfoDao.remove(1L);
	    flush();
	 
	    // should throw DataAccessException
	    projectInfoDao.get(projectInfo.getId());
	    */
	}
}
