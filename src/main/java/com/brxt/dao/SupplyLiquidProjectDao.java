package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.projectprogress.SupplyLiquidProject;


public interface SupplyLiquidProjectDao extends GenericDao<SupplyLiquidProject, Long> {

	public List<SupplyLiquidProject> findByProjId(Long projectInfoId);
	
}
