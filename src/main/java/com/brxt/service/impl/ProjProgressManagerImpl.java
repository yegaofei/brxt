package com.brxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.brxt.dao.InvestmentProjectDao;
import com.brxt.dao.RepaymentProjectDao;
import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.ProjectProgress;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.service.ProjProgressManager;

public class ProjProgressManagerImpl extends GenericManagerImpl<InvestmentProject, Long>
implements ProjProgressManager {

	private InvestmentProjectDao investmentProjectDao;
	private SupplyLiquidProjectDao supplyLiquidProjectDao;
	private RepaymentProjectDao repaymentProjectDao;
	
	@Autowired
	public void setInvestmentProjectDao(InvestmentProjectDao investmentProjectDao) {
		this.dao = investmentProjectDao;
		this.investmentProjectDao = investmentProjectDao;
	}
	
	@Autowired
	public void setSupplyLiquidProjectDao(
			SupplyLiquidProjectDao supplyLiquidProjectDao) {
		this.supplyLiquidProjectDao = supplyLiquidProjectDao;
	}
	
	@Autowired
	public void setRepaymentProjectDao(RepaymentProjectDao repaymentProjectDao) {
		this.repaymentProjectDao = repaymentProjectDao;
	}

	@Override
	public List<ProjectProgress> getProjectProgressList(Long projectInfoId) {
		List<InvestmentProject> investmentProjects = investmentProjectDao.findByProjId(projectInfoId);
		List<SupplyLiquidProject> supplyLiquidProjects = supplyLiquidProjectDao.findByProjId(projectInfoId);
		List<RepaymentProject> repaymentProjecs = repaymentProjectDao.findByProjId(projectInfoId);
		
		List<ProjectProgress> projectProgessList = new ArrayList<ProjectProgress>();
		
		
		return projectProgessList;
	}
	
	
	
	
}
