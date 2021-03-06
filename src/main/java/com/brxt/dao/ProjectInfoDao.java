package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.ProjectInfo;

public interface ProjectInfoDao extends GenericDao<ProjectInfo, Long> {

	
	public ProjectInfo findByProjectName(String projectName);
	
	public List<ProjectInfo> findByProjectInfo(ProjectInfo projectInfo);
}
