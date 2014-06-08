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
import com.brxt.model.finance.ProfitStatement;

@Repository("profitStatementDao")
public class ProfitStatementDaoHibernate extends
		GenericDaoHibernate<ProfitStatement, Long> implements
		FinancialSheetDao<ProfitStatement, Long> {

	public ProfitStatementDaoHibernate() {
		super(ProfitStatement.class);
	}

	@Override
	public ProfitStatement find(ProjectInfo projectInfo,
			Counterparty counterparty, Integer year, Short month) {
		List<ProfitStatement> results = getSession()
				.createCriteria(ProfitStatement.class)
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
	public ProfitStatement findLatest(ProjectInfo projectInfo,
			Counterparty counterparty) {
		List<ProfitStatement> results = getSession()
				.createCriteria(ProfitStatement.class)
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
	public List<ProfitStatement> getAll(ProjectInfo projectInfo) {
		List<ProfitStatement> results = getSession()
				.createCriteria(ProfitStatement.class)
				.add(Restrictions.eq("projectInfo", projectInfo))
				.list();
		return results;
	}
	
	public String getTableName() {
		AbstractEntityPersister classMetadata = (AbstractEntityPersister) getSessionFactory()
				.getClassMetadata(ProfitStatement.class);
		return classMetadata.getTableName();
	}

}
