package com.brxt.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.SubjectCapacityDao;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
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

	@Override
	public List<SubjectCapacity> findByProjIdCpId(ProjectInfo projectInfo,
			Counterparty counterparty, Date startDate, Date endDate) {
		Criteria criteria = getSession().createCriteria(SubjectCapacity.class);
		criteria.add(Restrictions.eq("projectInfo", projectInfo));
		criteria.add(Restrictions.eq("counterparty", counterparty));
		criteria.add(Restrictions.ge("checkTime", startDate));
		criteria.add(Restrictions.le("checkTime", endDate));
		return criteria.list();
	}
	
	public SubjectCapacity find(ProjectInfo projectInfo,
			Counterparty counterparty, Date checkTime) {
		Criteria criteria = getSession().createCriteria(SubjectCapacity.class);
		criteria.add(Restrictions.eq("projectInfo", projectInfo));
		criteria.add(Restrictions.eq("counterparty", counterparty));
		criteria.add(Restrictions.ge("checkTime", checkTime));
		List<SubjectCapacity> results = criteria.list();
		if(results != null && !results.isEmpty())
		{
			return results.get(0);
		}
		return null;
	}

}
