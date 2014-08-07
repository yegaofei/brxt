package com.brxt.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;

public interface RepaymentDao extends GenericDao<Repayment, Long> {

	public List<Repayment> findByProjId(Long projectInfoId);
	public List<Repayment> findByProjectInfo(ProjectInfo projectInfo, Date timeRangeStart, Date timeRangeEnd);
	
}
