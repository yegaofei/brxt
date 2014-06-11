package com.brxt.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.CollateralOverviewDao;
import com.brxt.model.collateral.CollateralOverview;

@Repository("collateralOverviewDao")
public class CollateralOverviewDaoHibernate extends GenericDaoHibernate<CollateralOverview, Long> implements CollateralOverviewDao {

	public CollateralOverviewDaoHibernate(){
		super(CollateralOverview.class);
	}
}
