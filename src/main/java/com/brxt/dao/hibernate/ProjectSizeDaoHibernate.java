package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.brxt.dao.ProjectSizeDao;
import com.brxt.model.ProjectSize;

@Repository("projectSizeDao")
public class ProjectSizeDaoHibernate extends
		GenericDaoHibernate<ProjectSize, Long> implements ProjectSizeDao {
	
	private static final int BATCH_UPDATE_SIZE = 50;

	public ProjectSizeDaoHibernate() {
		super(ProjectSize.class);
	}

	@Override
	public void batchSave(List<ProjectSize> projectSizeList) {

		if (projectSizeList == null || projectSizeList.size() == 0) {
			return;
		}

		Session session = getSession();
		for (int i = 0; i < projectSizeList.size(); i++) {
			ProjectSize projectSize = projectSizeList.get(i);
			if(projectSize.getProjectInfo() == null)
			{
				continue;
			}
			session.save(projectSize);
			
			if( i % BATCH_UPDATE_SIZE == 0 && i > 0)
			{
				session.flush();
				session.clear();
			}			
		}
	}

}
