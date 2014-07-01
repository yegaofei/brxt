package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.projectprogress.InvestmentProject;

public interface InvestmentProjectDao extends GenericDao<InvestmentProject, Long> {

	
	public List<InvestmentProject> findByProjId(Long projectInfoId);
	
	public List<InvestmentProject> findUniqueProjects(Long projectInfoId) ;
	
}
