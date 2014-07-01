package com.brxt.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.InvestmentStatusDao;
import com.brxt.model.InvestmentStatus;
import com.brxt.service.InvestmentProjectsManager;

@Service("investmentProjectsManager")
public class InvestmentProjectsManagerImpl extends GenericManagerImpl<InvestmentStatus, Long> implements InvestmentProjectsManager{

	InvestmentStatusDao investmentStatusDao; 
	
	@Autowired
	public void setInvestmentStatusDao(InvestmentStatusDao dao) {
		this.dao = dao;
		this.investmentStatusDao = dao;
	}
	
	@Override
	public InvestmentStatus findByProjectName(String projectName) {
		List<InvestmentStatus> isList = investmentStatusDao.findByName(projectName);
		if(isList != null && !isList.isEmpty())
		{
			return isList.get(0);
		}
		return null;
	}

}
