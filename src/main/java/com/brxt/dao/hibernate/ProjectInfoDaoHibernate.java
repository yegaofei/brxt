package com.brxt.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
	public List<ProjectInfo> findByProjectName(String projectName) {
		return getSession().createCriteria(ProjectInfo.class)
				.add(Restrictions.eq("projectName", projectName)).list();
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

		if (projectInfo.getCreateTime() != null) {
			Date createDate = projectInfo.getCreateTime();
			Calendar calendar = Calendar.getInstance(Locale.CHINESE);
			calendar.setTime(createDate);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			Date createDate1 = calendar.getTime();

			calendar.setTime(createDate);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			Date createDate2 = calendar.getTime();
			criteria.add(Restrictions.ge("createTime", createDate1));
			criteria.add(Restrictions.le("createTime", createDate2));
		}

		return criteria.list();
	}

}
