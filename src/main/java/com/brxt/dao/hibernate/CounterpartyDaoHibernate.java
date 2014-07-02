package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.CounterpartyDao;
import com.brxt.model.Counterparty;

@Repository("counterpartyDao")
public class CounterpartyDaoHibernate extends
GenericDaoHibernate<Counterparty, Long> implements CounterpartyDao {

	public CounterpartyDaoHibernate()
	{
		super(Counterparty.class);
	}
	
	@Override
	public Counterparty findByCounterpartyName(String counterpartyName) {
		List<Counterparty> cpList  = getSession().createCriteria(Counterparty.class)
				.add(Restrictions.eq("name", counterpartyName)).list();
		if(cpList == null ||  cpList.size() == 0)
		{
			return null;
		}
		else
		{
			return cpList.get(0);
		}
	}
	
	public Counterparty findByCounterparty(Counterparty counterparty) {
		List<Counterparty> cpList  = getSession().createCriteria(Counterparty.class)
				.add(Restrictions.eq("name", counterparty.getName()))
				.add(Restrictions.eq("counterpartyType", counterparty.getCounterpartyType()))
				.list();
		if(cpList == null ||  cpList.size() == 0)
		{
			return null;
		}
		else
		{
			return cpList.get(0);
		}
	}

}
