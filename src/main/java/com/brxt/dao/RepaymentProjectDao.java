package com.brxt.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.InvestmentStatus;
import com.brxt.model.projectprogress.RepaymentProject;

public interface RepaymentProjectDao extends GenericDao<RepaymentProject, Long> {

	public RepaymentProject find(InvestmentStatus investmentStatus, Date projectEndTime);
	public List<Date> listProjectEndTime(Long investmentStatusId);
}
