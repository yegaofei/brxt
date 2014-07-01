package com.brxt.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.InvestmentProjectDao;
import com.brxt.model.projectprogress.InvestmentProject;

@Repository("investmentProjectDao")
public class InvestProjDaoHibernate extends GenericDaoHibernate<InvestmentProject, Long> implements InvestmentProjectDao {

	public InvestProjDaoHibernate() {
		super(InvestmentProject.class);
	}

	


}
