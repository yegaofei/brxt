package com.brxt.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.RepaymentProjectDao;
import com.brxt.model.projectprogress.RepaymentProject;

@Repository("repaymentProjectDao")
public class RepaymentProjDaoHibernate extends
GenericDaoHibernate<RepaymentProject, Long> implements RepaymentProjectDao{

	public RepaymentProjDaoHibernate()
	{
		super(RepaymentProject.class);
	}
	
	@Override
	public List<RepaymentProject> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		return findByNamedQuery("searchRPByProjectInfoId", queryParams);
	}

}