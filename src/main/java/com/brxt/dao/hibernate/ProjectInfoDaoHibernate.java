package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.model.ProjectInfo;

@Repository("projectInfoDao")
public class ProjectInfoDaoHibernate extends
		GenericDaoHibernate<ProjectInfo, Long> implements ProjectInfoDao {

	public ProjectInfoDaoHibernate() {
		super(ProjectInfo.class);
	}

	@Override
	public List<ProjectInfo> findByProjectName(String projectName) {
		return getSession().createCriteria(ProjectInfo.class)
				.add(Restrictions.eq("projectName", projectName)).list();
	}

}
