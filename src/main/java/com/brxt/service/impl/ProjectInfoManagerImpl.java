package com.brxt.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

import com.brxt.dao.ProjectInfoDao;
import com.brxt.dao.ProjectSizeDao;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.service.ProjectInfoManager;

@Service("projectInfoManager")
public class ProjectInfoManagerImpl extends GenericManagerImpl<ProjectInfo, Long> implements ProjectInfoManager{

	ProjectInfoDao projectInfoDao;
	
	ProjectSizeDao projectSizeDao;
	
	public ProjectInfoManagerImpl(ProjectInfoDao projectInfoDao, ProjectSizeDao projectSizeDao) {
        super(projectInfoDao);
        this.projectInfoDao = projectInfoDao;
        this.projectSizeDao = projectSizeDao;
    }
	
	@Override
	public List<ProjectInfo> findByProjectName(String projectName) {
		return projectInfoDao.findByProjectName(projectName);
	}
	
	@Override
	public List<ProjectSize> getAllProjectSize(Long projectInfoId){
		return projectSizeDao.findByProjectInfoId(projectInfoId);
	}

	@Override
	public void batchSaveProjectSizeList(List<ProjectSize> psList) {
		projectSizeDao.batchSave(psList);
	}
	
	public void deleteProjectSize(Long projectSizeId){
		projectSizeDao.remove(projectSizeId);
	}

	@Override
	public void deleteProjectSize(ProjectSize projectSize) {
		projectSizeDao.remove(projectSize);
	}

	@Override
	public ProjectSize findProjectSize(Long projectSizeId) {
		return projectSizeDao.get(projectSizeId);
	}

	
}
