package com.brxt.dao;

import java.io.Serializable;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;


public interface FinancialSheetDao<T, PK extends Serializable> extends GenericDao<T, PK>{
	
	public T find(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Short month);
	
	public T findLatest(ProjectInfo projectInfo, Counterparty counterparty);

}
