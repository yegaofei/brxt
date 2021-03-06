package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.Counterparty;
import com.brxt.model.Manager;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;

public interface ProjectInfoManager extends GenericManager<ProjectInfo, Long> {

	ProjectInfo findByProjectName(String projectName);
	
	List<ProjectInfo> findByProjectInfo(ProjectInfo projectInfo);
	
	void batchSaveProjectSizeList(List<ProjectSize> psList);
	
	List<ProjectSize> getAllProjectSize(Long projectInfoId);
	
	void deleteProjectSize(Long projectSizeId);
	
	void deleteProjectSize(ProjectSize projectSize);
	
	ProjectSize findProjectSize(Long projectSizeId);
	
	Counterparty saveCounterparty(Counterparty counterparty);
	
	public ProjectInfo loadProjectInfo(Long id);
	
	List<String> getAllProjectNames();
	
	public Counterparty findCounterparty(Counterparty counterparty);
	
	public List<Manager> getAllTrustManagers();
    
    public List<Manager> getAllRiskManagers();

    public List<Manager> getAllDelegateManagers();
    
    public int countProjectInfo(Counterparty counterparty);
	
}
