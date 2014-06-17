package com.brxt.service.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

import com.brxt.dao.SubjectCapacityDao;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;
import com.brxt.service.SubjectCapacityManager;


@Service("subjectCapacityManager")
public class SubjectCapacityManagerImpl extends GenericManagerImpl<SubjectCapacity, Long> implements SubjectCapacityManager{

	SubjectCapacityDao subjectCapacityDao;
	
	public SubjectCapacityManagerImpl(SubjectCapacityDao dao)
	{
		super(dao);
		this.subjectCapacityDao = dao;
	}
	
	@Override
	public List<SubjectCapacity> findByProjIdCpId(Long projectInfoId,
			Long counterpartyId) {
		return subjectCapacityDao.findByProjIdCpId(projectInfoId, counterpartyId);
	}

	@Override
	public List<SubjectCapacity> findByProjId(Long projectInfoId) {
		return subjectCapacityDao.findByProjId(projectInfoId);
	}

	@Override
	public List<SubjectCapacity> findByProjIdCpId(ProjectInfo projectInfo,
			Counterparty counterparty, Date startTime, Date endTime) {
		return subjectCapacityDao.findByProjIdCpId(projectInfo, counterparty, startTime, endTime);
	}

}
