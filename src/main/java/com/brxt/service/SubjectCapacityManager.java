package com.brxt.service;

import java.util.Date;
import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;

public interface SubjectCapacityManager extends GenericManager<SubjectCapacity, Long> { 

	
	public List<SubjectCapacity> findByProjIdCpId(ProjectInfo projectInfo, Counterparty counterparty, Date startTime, Date endTime);
	
	public List<SubjectCapacity> findByProjId(Long projectInfoId);
	
	public SubjectCapacity find(ProjectInfo projectInfo, Counterparty counterparty, Date checkTime); 
}
