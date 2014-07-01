package com.brxt.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.projectprogress.SupplyLiquidProject;

@Repository("supplyLiquidProjectDao")
public class SuplyLiqProjDaoHibernate extends
GenericDaoHibernate<SupplyLiquidProject, Long> implements SupplyLiquidProjectDao{

	public SuplyLiqProjDaoHibernate()
	{
		super(SupplyLiquidProject.class);
	}

}



