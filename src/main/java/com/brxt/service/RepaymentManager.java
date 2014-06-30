package com.brxt.service;

import java.util.Date;
import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;

public interface RepaymentManager extends GenericManager<Repayment, Long> {

	public List<Repayment> findByProjId(Long projectInfoId);
	public List<Repayment> findByProjId(ProjectInfo projectInfo, Date startTime, Date endTime);

}
