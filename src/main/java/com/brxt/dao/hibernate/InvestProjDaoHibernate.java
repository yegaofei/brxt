package com.brxt.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.InvestmentProjectDao;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.projectprogress.InvestmentProject;

@Repository("investmentProjectDao")
public class InvestProjDaoHibernate extends GenericDaoHibernate<InvestmentProject, Long> implements InvestmentProjectDao {

	public InvestProjDaoHibernate() {
		super(InvestmentProject.class);
	}

	@Override
	public InvestmentProject find(InvestmentStatus investmentStatus, Date projectEndTime) {
		List<InvestmentProject> results = getSession()
				.createCriteria(InvestmentProject.class)
				.add(Restrictions.eq("investmentStatus", investmentStatus))
				.add(Restrictions.eq("projectEndTime", projectEndTime)).list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

}
