package com.brxt.dao.hibernate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.CreditInformationDao;
import com.brxt.model.Counterparty;
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

	@Override
	public List<CreditInformation> findByCounterpartyId(Counterparty counterparty, Date startDate, Date endDate) {
		Criteria criteria = getSession().createCriteria(CreditInformation.class);
		criteria.add(Restrictions.eq("counterparty", counterparty));
		criteria.add(Restrictions.ge("queryTime", startDate));
		criteria.add(Restrictions.le("queryTime", endDate));
		criteria.addOrder(Order.desc("queryTime"));
		return criteria.list();
	}
	
	@Override
    public List<CreditInformation> findByCounterpartyId(Counterparty counterparty) {
        Criteria criteria = getSession().createCriteria(CreditInformation.class);
        criteria.add(Restrictions.eq("counterparty", counterparty));
        criteria.addOrder(Order.desc("queryTime"));
        return criteria.list();
    }

}
