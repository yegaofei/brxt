package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.InvestmentStatus;

public interface InvestmentStatusDao extends GenericDao<InvestmentStatus, Long>{
	
	public List<InvestmentStatus> findByName(String projectName);
	
	public List<InvestmentStatus> findByInvestmentStatus(InvestmentStatus investmentStatus);

}
