package com.brxt.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.RepaymentDao;
import com.brxt.model.Repayment;

@Repository("repaymentDao")
public class RepaymentDaoHibernate extends
GenericDaoHibernate<Repayment, Long> implements RepaymentDao{

	public RepaymentDaoHibernate() {
		super(Repayment.class);
	}	
	
	@Override
	public List<Repayment> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		return findByNamedQuery("searchRepaymentByProjectInfoId", queryParams);
	}

}
