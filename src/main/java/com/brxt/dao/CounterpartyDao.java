package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Counterparty;

public interface CounterpartyDao extends GenericDao<Counterparty, Long> {
	
	public Counterparty findByCounterpartyName(String counterpartyName);
	
}
