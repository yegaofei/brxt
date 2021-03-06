package com.brxt.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;


public interface FinancialSheetDao<T, PK extends Serializable> extends GenericDao<T, PK>{
	
	public T find(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Short month);
	
	public T findLatest(ProjectInfo projectInfo, Counterparty counterparty);
	
	public List<T> getAll(Set<Counterparty> counterparties);
	
	public String getTableName();

}
