package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
	
	


}
