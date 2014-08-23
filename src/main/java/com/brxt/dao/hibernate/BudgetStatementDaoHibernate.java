package com.brxt.dao.hibernate;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.stereotype.Repository;

import com.brxt.dao.FinancialSheetDao;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.BudgetStatement;

@Repository("budgetStatementDao")
public class BudgetStatementDaoHibernate extends
		GenericDaoHibernate<BudgetStatement, Long> implements
		FinancialSheetDao<BudgetStatement, Long> {

	public BudgetStatementDaoHibernate() {
		super(BudgetStatement.class);
	}

	@Override
	public BudgetStatement find(ProjectInfo projectInfo,
			Counterparty counterparty, Integer year, Short month) {
		List<BudgetStatement> results = getSession()
				.createCriteria(BudgetStatement.class)
				//.add(Restrictions.eq("projectInfo", projectInfo))
				.add(Restrictions.eq("counterparty", counterparty))
				.add(Restrictions.eq("reportYear", year))
				.add(Restrictions.eq("reportMonth", month)).list();

		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public BudgetStatement findLatest(ProjectInfo projectInfo,
			Counterparty counterparty) {
		List<BudgetStatement> results = getSession()
				.createCriteria(BudgetStatement.class)
				//.add(Restrictions.eq("projectInfo", projectInfo))
				.add(Restrictions.eq("counterparty", counterparty))
				.addOrder(Order.desc("reportYear"))
				.addOrder(Order.desc("reportMonth")).list();

		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

	@Override
	public List<BudgetStatement> getAll(Set<Counterparty> counterparties) {
		List<BudgetStatement> results = getSession()
				.createCriteria(BudgetStatement.class)
				.add(Restrictions.in("counterparty", counterparties)).list();
		return results;
	}

	public String getTableName() {
		AbstractEntityPersister classMetadata = (AbstractEntityPersister) getSessionFactory()
				.getClassMetadata(BudgetStatement.class);
		return classMetadata.getTableName();
	}
}
