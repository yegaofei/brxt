package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.InvestmentStatusDao;
import com.brxt.model.InvestmentStatus;

@Repository("investmentStatusDao")
public class InvestmentStatusDaoHibernate extends
GenericDaoHibernate<InvestmentStatus, Long> implements InvestmentStatusDao {

	public InvestmentStatusDaoHibernate()
	{
		super(InvestmentStatus.class);
	}

	@Override
	public List<InvestmentStatus> findByName(String projectName) {
		List<InvestmentStatus> investmentStatusList =  getSession().createCriteria(InvestmentStatus.class)
				.add(Restrictions.eq("projectName", projectName)).list();
		return investmentStatusList;
	}

	@Override
	public List<InvestmentStatus> findByInvestmentStatus(
			InvestmentStatus investmentStatus) {
		Criteria criteria = getSession().createCriteria(InvestmentStatus.class);
		if(investmentStatus != null)
		{
			if(investmentStatus.getProjectName() != null)
			{
				criteria.add(Restrictions.eq("projectName", investmentStatus.getProjectName()));
			}
			
			if(investmentStatus.getProjectType() != null)
			{
				criteria.add(Restrictions.eq("projectType", investmentStatus.getProjectType()));
			}
		}
		return criteria.list();
	}
	
	


}
