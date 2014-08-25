package com.brxt.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Counterparty;
import com.brxt.model.CreditInformation;

public interface CreditInformationDao extends GenericDao<CreditInformation, Long> {

	public List<CreditInformation> findByProjId(Long projectInfoId);
	
	public List<CreditInformation> findByCounterpartyId(Counterparty counterparty, Date startDate, Date endDate);
	
	public List<CreditInformation> findByCounterpartyId(Counterparty counterparty);
	
}
