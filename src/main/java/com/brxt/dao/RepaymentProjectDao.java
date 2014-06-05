package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.projectprogress.RepaymentProject;

public interface RepaymentProjectDao extends GenericDao<RepaymentProject, Long> {

	public List<RepaymentProject> findByProjId(Long projectInfoId);
	
}
