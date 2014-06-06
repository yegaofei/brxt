package com.brxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.InvestmentProjectDao;
import com.brxt.dao.RepaymentProjectDao;
import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.ProjectProgress;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.service.ProjProgressManager;

@Service("projectProgressManager")
public class ProjProgressManagerImpl extends GenericManagerImpl<InvestmentProject, Long>
implements ProjProgressManager {

	private InvestmentProjectDao investmentProjectDao;
	private SupplyLiquidProjectDao supplyLiquidProjectDao;
	private RepaymentProjectDao repaymentProjectDao;
	
	private static final Long BASE_INVESTMENT_PROJECT_ID = 100000L;
	private static final Long BASE_SUPPLY_LIQUIDPROJECT_ID = 200000L;
	private static final Long BASE_REPAYMENT_PROJECT_ID = 300000L;
	
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
		if(investmentProjects != null)
		{
			for(InvestmentProject ip : investmentProjects)
			{
				ProjectProgress pp = new ProjectProgress();
				pp.setId(ip.getId() + BASE_INVESTMENT_PROJECT_ID);
				pp.setDeadline(ip.getProjectEndTime());
				pp.setCapitalInvestmentType(ip.getType());
				pp.setProjectName(ip.getName());
				pp.setInvestment(true);
				pp.setSupplyLiquid(false);
				projectProgessList.add(pp);
			}
		}
		
		if(supplyLiquidProjects != null)
		{
			for(SupplyLiquidProject sp : supplyLiquidProjects)
			{
				ProjectProgress pp = new ProjectProgress();
				pp.setId(sp.getId() + BASE_SUPPLY_LIQUIDPROJECT_ID);
				pp.setDeadline(sp.getProjectEndTime());
				pp.setProjectName(sp.getName());
				pp.setCapitalInvestmentType(CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY);
				pp.setInvestment(true);
				pp.setSupplyLiquid(true);
				projectProgessList.add(pp);
			}
		}
		
		if(repaymentProjecs != null)
		{
			for(RepaymentProject rp : repaymentProjecs)
			{
				ProjectProgress pp = new ProjectProgress();
				pp.setId(rp.getId() + BASE_REPAYMENT_PROJECT_ID);
				pp.setDeadline(rp.getProjectEndTime());
				pp.setProjectName(rp.getName());
				pp.setCapitalInvestmentType(rp.getType());
				pp.setInvestment(false);
				pp.setSupplyLiquid(false);
				projectProgessList.add(pp);
			}
		}
		
		return projectProgessList;
	}
	
	@Override
	public void remove(Long id)
	{
		if(id > BASE_REPAYMENT_PROJECT_ID)
		{
			repaymentProjectDao.remove(id - BASE_REPAYMENT_PROJECT_ID);
		}
		else if (id > BASE_SUPPLY_LIQUIDPROJECT_ID)
		{
			supplyLiquidProjectDao.remove(id - BASE_SUPPLY_LIQUIDPROJECT_ID);
		} 
		else if (id > BASE_INVESTMENT_PROJECT_ID)
		{
			investmentProjectDao.remove(id - BASE_INVESTMENT_PROJECT_ID);
		}
	}
	
	public String getProgressForm(Long id)
	{
		if(id > BASE_REPAYMENT_PROJECT_ID)
		{
			return "repaymentProjectForm";
		}
		else if (id > BASE_SUPPLY_LIQUIDPROJECT_ID)
		{
			return "supplyLiqProjectForm";
		} 
		else if (id > BASE_INVESTMENT_PROJECT_ID)
		{
			return "investmentProjectForm";
		}
		else
		{
			return "";
		}
	}
}
