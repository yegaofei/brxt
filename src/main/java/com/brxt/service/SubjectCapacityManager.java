package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.SubjectCapacity;

public interface SubjectCapacityManager extends GenericManager<SubjectCapacity, Long> { 

	public List<SubjectCapacity> findByProjIdCpId(Long projectInfoId, Long counterpartyId);
	
	public List<SubjectCapacity> findByProjId(Long projectInfoId);
}
