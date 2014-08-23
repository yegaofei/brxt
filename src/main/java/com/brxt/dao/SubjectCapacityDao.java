package com.brxt.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;

public interface SubjectCapacityDao extends GenericDao<SubjectCapacity, Long> {
	
	
	public List<SubjectCapacity> findByProjIdCpId(ProjectInfo projectInfo, Counterparty counterparty, Date startDate, Date endDate);
	
	public List<SubjectCapacity> findByProjId(Long projectInfoId);
	
	public SubjectCapacity find(ProjectInfo projectInfo, Counterparty counterparty, Date checkTime);

}
