package com.brxt.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.InvestmentStatus;
import com.brxt.model.projectprogress.SupplyLiquidProject;


public interface SupplyLiquidProjectDao extends GenericDao<SupplyLiquidProject, Long> {

	public SupplyLiquidProject find(InvestmentStatus investmentStatus, Date projectEndTime);
	public List<Date> listProjectEndTime(Long investmentStatusId);
}
