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
import com.brxt.model.finance.CorporateBalanceSheet;

@Repository("corpBalanceSheetDao")
public class CorpBalanceSheetDaoHibernate extends
		GenericDaoHibernate<CorporateBalanceSheet, Long> implements
		FinancialSheetDao<CorporateBalanceSheet, Long>{

	public CorpBalanceSheetDaoHibernate() {
		super(CorporateBalanceSheet.class);
	}

	@Override
	public CorporateBalanceSheet find(ProjectInfo projectInfo,
			Counterparty counterparty, Integer year, Short month) {
		List<CorporateBalanceSheet> results = getSession()
				.createCriteria(CorporateBalanceSheet.class)
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
	public CorporateBalanceSheet findLatest(ProjectInfo projectInfo,
			Counterparty counterparty) {
		List<CorporateBalanceSheet> results = getSession()
				.createCriteria(CorporateBalanceSheet.class)
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
	public List<CorporateBalanceSheet> getAll(ProjectInfo projectInfo) {
		List<CorporateBalanceSheet> results = getSession()
				.createCriteria(CorporateBalanceSheet.class)
				.add(Restrictions.eq("projectInfo", projectInfo))
				 .list();
		return results;
	}
	
	public String getTableName() {
		AbstractEntityPersister classMetadata = (AbstractEntityPersister) getSessionFactory()
				.getClassMetadata(CorporateBalanceSheet.class);
		return classMetadata.getTableName();
	}

}
