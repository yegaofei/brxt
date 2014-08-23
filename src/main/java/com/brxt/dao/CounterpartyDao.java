package com.brxt.dao;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Counterparty;

public interface CounterpartyDao extends GenericDao<Counterparty, Long> {
	
	public Counterparty findByCounterpartyName(String counterpartyName);
	public Counterparty findByCounterparty(Counterparty counterparty);
	public int countProjectInfoId(Counterparty counterparty);
	
}
