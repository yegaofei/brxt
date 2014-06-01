package com.brxt.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.SubjectCapacityDao;
import com.brxt.model.SubjectCapacity;

@Repository("subjectCapacityDao")
public class SubjectCapacityDaoHibernate extends
GenericDaoHibernate<SubjectCapacity, Long> implements SubjectCapacityDao{

	public SubjectCapacityDaoHibernate() {
		super(SubjectCapacity.class);
	}	
	
	@Override
	public List<SubjectCapacity> findByProjIdCpId(Long projectInfoId,
			Long counterpartyId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		queryParams.put("counterparty_id", counterpartyId);
		return findByNamedQuery("searchByProjectInfoIdCounterpartyId", queryParams);
	}

	@Override
	public List<SubjectCapacity> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		return findByNamedQuery("searchByProjectInfoId", queryParams);
	}

}
