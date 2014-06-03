package com.brxt.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.brxt.dao.CreditInformationDao;
import com.brxt.model.CreditInformation;

@Repository("creditInformationDao")
public class CreditInformationDaoHibernate extends
GenericDaoHibernate<CreditInformation, Long> implements CreditInformationDao{

	public CreditInformationDaoHibernate() {
		super(CreditInformation.class);
	}

	@Override
	public List<CreditInformation> findByProjId(Long projectInfoId) {
		Map<String, Object> queryParams = new HashMap<String, Object>();
		queryParams.put("projectInfo_id", projectInfoId);
		return findByNamedQuery("searchCIByProjectInfoId", queryParams);
	}

}
