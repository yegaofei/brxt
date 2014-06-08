package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;

import com.brxt.dao.FinancialSheetDao;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.BudgetStatement;
import com.brxt.model.finance.InstituteBalanceSheet;

@Repository("instBalanceDao")
public class InstBalanceDaoHibernate extends
GenericDaoHibernate<InstituteBalanceSheet, Long> implements
FinancialSheetDao<InstituteBalanceSheet, Long>{

	public InstBalanceDaoHibernate()
	{
		super(InstituteBalanceSheet.class);
	}
	
	@Override
	public InstituteBalanceSheet find(ProjectInfo projectInfo,
			Counterparty counterparty, Integer year, Short month) {
		List<InstituteBalanceSheet> results = getSession()
				.createCriteria(InstituteBalanceSheet.class)
				.add(Restrictions.eq("projectInfo", projectInfo))
				.add(Restrictions.eq("counterparty", counterparty))
				.add(Restrictions.eq("reportYear", year))
				.add(Restrictions.eq("reportMonth", month)).list();

		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public InstituteBalanceSheet findLatest(ProjectInfo projectInfo,
			Counterparty counterparty) {
		List<InstituteBalanceSheet> results = getSession()
				.createCriteria(InstituteBalanceSheet.class)
				.add(Restrictions.eq("projectInfo", projectInfo))
				.add(Restrictions.eq("counterparty", counterparty))
				 .addOrder(Order.desc("reportYear")) 
				 .addOrder(Order.desc("reportMonth")).list();

		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public List<InstituteBalanceSheet> getAll(ProjectInfo projectInfo) {
		List<InstituteBalanceSheet> results = getSession()
				.createCriteria(InstituteBalanceSheet.class)
				.add(Restrictions.eq("projectInfo", projectInfo))
				.list();

		return results;
	}
	
	public String getTableName() {
		AbstractEntityPersister classMetadata = (AbstractEntityPersister) getSessionFactory()
				.getClassMetadata(InstituteBalanceSheet.class);
		return classMetadata.getTableName();
	}

}
