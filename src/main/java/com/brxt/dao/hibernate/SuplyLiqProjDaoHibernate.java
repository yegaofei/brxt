package com.brxt.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	@Override
	public List<SupplyLiquidProject> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		return findByNamedQuery("searchSLPByProjectInfoId", queryParams);
	}

}



