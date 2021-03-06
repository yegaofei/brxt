package com.brxt.service.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.stereotype.Service;

import com.brxt.dao.RepaymentDao;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.service.RepaymentManager;

@Service("repaymentManager")
public class RepaymentManagerImpl extends GenericManagerImpl<Repayment, Long>
		implements RepaymentManager {

	RepaymentDao repaymentDao;

	public RepaymentManagerImpl(RepaymentDao dao) {
		super(dao);
		this.repaymentDao = dao;
	}

	@Override
	public List<Repayment> findByProjId(Long projectInfoId) {
		return repaymentDao.findByProjId(projectInfoId);
	}

	@Override
	public List<Repayment> findByProjId(ProjectInfo projectInfo, Date startTime,
			Date endTime) {
		return repaymentDao.findByProjectInfo(projectInfo, startTime, endTime);
	}

}
