package com.brxt.service;

import org.appfuse.service.GenericManager;

import com.brxt.model.InvestmentStatus;

public interface InvestmentProjectsManager extends GenericManager<InvestmentStatus, Long> {

	public InvestmentStatus findByProjectName(String projectName);
}
