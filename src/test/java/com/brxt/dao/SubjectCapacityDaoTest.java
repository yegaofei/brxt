package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;

public class SubjectCapacityDaoTest extends BaseDaoTestCase {

	@Autowired
	private SubjectCapacityDao subjectCapacityDao;

	@Autowired
	private ProjectInfoDao projectInfoDao;

	@Autowired
	private ProjectSizeDao projectSizeDao;

	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	@Test
	public void testGet() {
		SubjectCapacity subjectCapacity = subjectCapacityDao.get(1L);
		assertNotNull(subjectCapacity);

		Counterparty cp = subjectCapacity.getCounterparty();
		assertNotNull(cp);
	}

	@Test
	public void testSave() throws Exception {
		ProjectInfo project = projectInfoDao.get(1L);
		Iterator<Counterparty> it = project.getCounterparties().iterator();
		if (it.hasNext()) {
			Counterparty cp = it.next();
			SubjectCapacity subjectCapacity = new SubjectCapacity();
			subjectCapacity.setProjectInfo(project);
			subjectCapacity.setCounterparty(cp);
			subjectCapacity.setCheckTime(sf.parse("2014-02-02"));
			subjectCapacity.setOwnerChanged(Boolean.FALSE);
			SubjectCapacity subjectCapacity2 = subjectCapacityDao.save(subjectCapacity);
			flush();

			List<SubjectCapacity> scList = subjectCapacityDao.findByProjIdCpId(
					project.getId(), cp.getId());
			assertNotNull(scList);
			assertTrue(scList.size() >= 1);

			SubjectCapacity subjectCapacity3 = subjectCapacityDao.get(subjectCapacity2.getId());
			assertEquals(subjectCapacity3, subjectCapacity);
			assertEquals(subjectCapacity3.getOwnerChanged(),
					subjectCapacity.getOwnerChanged());
		}
	}
	
	@Test(expected = DataAccessException.class)
	public void testRemove() {
		SubjectCapacity subjectCapacity = subjectCapacityDao.get(1L);
		subjectCapacityDao.remove(subjectCapacity);
		flush();
		
		// should throw DataAccessException
		subjectCapacityDao.get(1L);
	}
}
