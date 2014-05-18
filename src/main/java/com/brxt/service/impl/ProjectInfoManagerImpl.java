package com.brxt.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.model.ProjectInfo;
import com.brxt.service.ProjectInfoManager;

@Service("projectInfoManager")
public class ProjectInfoManagerImpl extends GenericManagerImpl<ProjectInfo, Long> implements ProjectInfoManager{

	ProjectInfoDao projectInfoDao;
	
	public ProjectInfoManagerImpl(ProjectInfoDao projectInfoDao) {
        super(projectInfoDao);
        this.projectInfoDao = projectInfoDao;
    }
	
	@Override
	public List<ProjectInfo> findByProjectName(String projectName) {
		return projectInfoDao.findByProjectName(projectName);
	}


}
