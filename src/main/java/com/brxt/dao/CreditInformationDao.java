package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.CreditInformation;

public interface CreditInformationDao extends GenericDao<CreditInformation, Long> {

	public List<CreditInformation> findByProjId(Long projectInfoId);
	
}
