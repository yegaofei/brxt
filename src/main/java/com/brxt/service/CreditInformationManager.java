package com.brxt.service;

import java.util.Date;
import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.Counterparty;
import com.brxt.model.CreditInformation;

public interface CreditInformationManager  extends GenericManager<CreditInformation, Long> {

	public List<CreditInformation> findByProjId(Long projectInfoId);
	
	public List<CreditInformation> findByCounterpartyId(Counterparty counterparty, Date startTime, Date endTime);
	
	public List<CreditInformation> findByCounterpartyId(Counterparty counterparty);
}
