package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.SubjectCapacity;

public interface SubjectCapacityDao extends GenericDao<SubjectCapacity, Long> {
	
	public List<SubjectCapacity> findByProjIdCpId(Long projectInfoId, Long counterpartyId);
	
	public List<SubjectCapacity> findByProjId(Long projectInfoId);

}
