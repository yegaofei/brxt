package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;

public interface ProjectInfoManager extends GenericManager<ProjectInfo, Long> {

	List<ProjectInfo> findByProjectName(String projectName);
	
	void batchSaveProjectSizeList(List<ProjectSize> psList);
	
}
