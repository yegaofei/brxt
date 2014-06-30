package com.brxt.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.model.ProjectInfo;

@SuppressWarnings("unchecked")
@Repository("projectInfoDao")
public class ProjectInfoDaoHibernate extends
		GenericDaoHibernate<ProjectInfo, Long> implements ProjectInfoDao {

	public ProjectInfoDaoHibernate() {
		super(ProjectInfo.class);
	}

	@Override
	public ProjectInfo findByProjectName(String projectName) {
		List<ProjectInfo> projectInfoList =  getSession().createCriteria(ProjectInfo.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		
		if(projectInfoList != null && !projectInfoList.isEmpty())
		{
			return projectInfoList.get(0);
		}
		return null;
	}

	public List<ProjectInfo> getAll() {
		Session sess = getSession();
		return sess.createCriteria(ProjectInfo.class)
				.addOrder(Order.desc("createTime")).setMaxResults(300).list();
	}

	@Override
	public List<ProjectInfo> findByProjectInfo(ProjectInfo projectInfo) {
		Criteria criteria = getSession().createCriteria(ProjectInfo.class);
		if (projectInfo.getProjectName() != null) {
			criteria.add(Restrictions.like("projectName",
					"%" + projectInfo.getProjectName() + "%"));
		}

		if (projectInfo.getDelegateManager() != null
				&& !"".equals(projectInfo.getDelegateManager().trim())) {
			criteria.add(Restrictions.eq("delegateManager",
					projectInfo.getDelegateManager()));
		}

		if (projectInfo.getTrustManager() != null
				&& !"".equals(projectInfo.getTrustManager().trim())) {
			criteria.add(Restrictions.eq("trustManager",
					projectInfo.getTrustManager()));
		}

		if (projectInfo.getRiskManager() != null
				&& !"".equals(projectInfo.getRiskManager().trim())) {
			criteria.add(Restrictions.eq("riskManager",
					projectInfo.getRiskManager()));
		}

		if (projectInfo.getSearchTimeStart() != null && projectInfo.getSearchTimeEnd() != null) {
			Date startDate = projectInfo.getSearchTimeStart();
			Date endDate = projectInfo.getSearchTimeEnd();
			criteria.add(Restrictions.ge("createTime", startDate));
			criteria.add(Restrictions.le("createTime", endDate));
		} else if (projectInfo.getSearchTimeStart() != null ) {
			Date startDate = projectInfo.getSearchTimeStart();
			criteria.add(Restrictions.ge("createTime", startDate));
		} else if (projectInfo.getSearchTimeEnd() != null) {
			Date endDate = projectInfo.getSearchTimeEnd();
			criteria.add(Restrictions.le("createTime", endDate));
		}

		return criteria.list();
	}

}
