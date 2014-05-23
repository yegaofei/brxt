package com.brxt.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.BaseDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;

public class ProjectSizeDaoTest extends BaseDaoTestCase {

	@Autowired
	private ProjectInfoDao projectInfoDao;

	@Autowired
	private ProjectSizeDao projectSizeDao;

	@Test
	public void testBatchSave() throws Exception {

		ProjectSize projectSize = projectSizeDao.get(1L);
		assertNotNull(projectSize);

		ProjectInfo projectInfo = projectSize.getProjectInfo();
		assertNotNull(projectInfo);

		ProjectInfo projectInfoFromDB = projectInfoDao.get(projectInfo.getId());
		assertNotNull(projectInfoFromDB);

		List<ProjectSize> projectSizeList = projectInfoFromDB.getProjectSizes();
		assertNotNull(projectSizeList);

		int existedProjectSizeListSize = projectSizeList.size();
		assertTrue(existedProjectSizeListSize >= 1);

		for (int i = 0; i < 10; i++) {
			ProjectSize ps = new ProjectSize();
			ps.setProjectSize(new BigDecimal(i));
			ps.setStartTime(new Date());
			ps.setEndTime(new Date());
			ps.setProjectInfo(projectInfoFromDB);
			projectSizeList.add(ps);
		}

		projectSizeDao.batchSave(projectSizeList);

		ProjectInfo newProjectInfoFromDB = projectInfoDao.get(projectInfo.getId());
		assertNotNull(newProjectInfoFromDB);
		assertEquals(projectInfoFromDB, newProjectInfoFromDB);

		projectSizeList = newProjectInfoFromDB.getProjectSizes();
		assertNotNull(projectSizeList);
		assertTrue((existedProjectSizeListSize + 10) == projectSizeList.size());

	}

}
