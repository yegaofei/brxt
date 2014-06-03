package com.brxt.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

import com.brxt.dao.CreditInformationDao;
import com.brxt.model.CreditInformation;
import com.brxt.service.CreditInformationManager;

@Service("creditInformationManager")
public class CreditInformationManagerImpl extends GenericManagerImpl<CreditInformation, Long> implements CreditInformationManager{

	CreditInformationDao creditInformationDao;
	
	public CreditInformationManagerImpl(CreditInformationDao dao)
	{
		super(dao);
		this.creditInformationDao = dao;
	}
	
	@Override
	public List<CreditInformation> findByProjId(Long projectInfoId) {
		
		return creditInformationDao.findByProjId(projectInfoId);
	}

}
