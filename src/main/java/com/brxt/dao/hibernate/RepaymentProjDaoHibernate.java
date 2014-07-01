package com.brxt.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.RepaymentProjectDao;
import com.brxt.model.projectprogress.RepaymentProject;

@Repository("repaymentProjectDao")
public class RepaymentProjDaoHibernate extends
GenericDaoHibernate<RepaymentProject, Long> implements RepaymentProjectDao{

	public RepaymentProjDaoHibernate()
	{
		super(RepaymentProject.class);
	}
	
	

}