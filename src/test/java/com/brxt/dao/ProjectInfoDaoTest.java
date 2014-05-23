package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.appfuse.dao.UserDao;
import org.appfuse.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;


public class ProjectInfoDaoTest extends BaseDaoTestCase {

	@Autowired
    private ProjectInfoDao projectInfoDao;
	
	@Autowired
	private UserDao userDao;
		
	@Test
	public void testFindProjectInfoByProjectName() throws Exception {
	    List<ProjectInfo> projectInfo = projectInfoDao.findByProjectName("TestProject1");
	    assertTrue(projectInfo.size() > 0);
	    assertTrue(projectInfo.get(0).getProjectSizes().size() > 0);
	}
	
	@Test(expected = DataAccessException.class)
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
		cp.setRelationship("Unknown relationship");
		projectInfo.getCounterpartyList().add(cp);
		
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
		projectInfo.setCreateUser(user);
		projectInfo.setUpdateUser(user);
		projectInfo.setCreateTime(new Date());
		
		projectInfo = projectInfoDao.save(projectInfo);
	    flush();
	 
	    projectInfo = projectInfoDao.get(projectInfo.getId());
	 
	    assertEquals("TestProject2", projectInfo.getProjectName());
	    assertNotNull(projectInfo.getId());
	    assertEquals(startTime, projectInfo.getProjectSizes().get(0).getStartTime());
	    assertNotNull(projectInfo.getCounterpartyList());
	    assertTrue(projectInfo.getCounterpartyList().size() > 0);
	    assertEquals(cpName, projectInfo.getCounterpartyList().get(0).getName());
	    assertNotNull(projectInfo.getUpdateUser());
	    
	    log.debug("removing projectInfoDao...");	 
	    projectInfoDao.remove(projectInfo.getId());
	    projectInfoDao.remove(1L);
	    flush();
	 
	    // should throw DataAccessException
	    projectInfoDao.get(projectInfo.getId());
	}
}
