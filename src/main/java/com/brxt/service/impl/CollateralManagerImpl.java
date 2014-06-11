package com.brxt.service.impl;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.CollateralOverviewDao;
import com.brxt.model.collateral.CollateralOverview;
import com.brxt.service.CollateralManager;

@Service("collateralManager")
public class CollateralManagerImpl extends GenericManagerImpl<CollateralOverview, Long>  implements CollateralManager{

	CollateralOverviewDao collateralOverviewDao;

	@Autowired
	public void setDao(CollateralOverviewDao dao) {
		this.dao = dao;
		this.collateralOverviewDao = dao;
		
	}
	
	
}
