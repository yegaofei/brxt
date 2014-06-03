package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.CreditInformation;

public interface CreditInformationManager  extends GenericManager<CreditInformation, Long> {

	public List<CreditInformation> findByProjId(Long projectInfoId);
}
