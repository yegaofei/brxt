package com.brxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.brxt.dao.CounterpartyDao;
import com.brxt.dao.InvestmentProjectDao;
import com.brxt.dao.ProjectInfoDao;
import com.brxt.dao.ProjectSizeDao;
import com.brxt.dao.RepaymentProjectDao;
import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.Counterparty;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;

@Service("projectInfoManager")
public class ProjectInfoManagerImpl extends
		GenericManagerImpl<ProjectInfo, Long> implements ProjectInfoManager {

	ProjectInfoDao projectInfoDao;

	ProjectSizeDao projectSizeDao;
	
	CounterpartyDao counterpartyDao;

	InvestmentProjectDao investmentProjectDao;
	
	RepaymentProjectDao repaymentProjectDao;
	
	SupplyLiquidProjectDao supplyLiquidProjectDao;
	
	private ProjProgressManager projectProgressManager;

	@Autowired
	public void setProjectProgressManager(
			@Qualifier("projectProgressManager") ProjProgressManager projectProgressManager) {
		this.projectProgressManager = projectProgressManager;
	}
	
	@Autowired
	public void setInvestmentProjectDao(InvestmentProjectDao investmentProjectDao) {
		this.investmentProjectDao = investmentProjectDao;
	}
	
	@Autowired
	public void setRepaymentProjectDao(RepaymentProjectDao repaymentProjectDao) {
		this.repaymentProjectDao = repaymentProjectDao;
	}
	
	@Autowired
	public void setSupplyLiquidProjectDao(SupplyLiquidProjectDao supplyLiquidProjectDao) {
		this.supplyLiquidProjectDao = supplyLiquidProjectDao;
	}
	
	@Autowired
	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.dao = projectInfoDao;
		this.projectInfoDao = projectInfoDao;
	}

	@Autowired
	public void setProjectSizeDao(ProjectSizeDao projectSizeDao) {
		this.projectSizeDao = projectSizeDao;
	}

	@Autowired
	public void setCounterpartyDao(CounterpartyDao counterpartyDao) {
		this.counterpartyDao = counterpartyDao;
	}
	
	@Override
	public ProjectInfo findByProjectName(String projectName) {
		return projectInfoDao.findByProjectName(projectName);
	}

	@Override
	public List<ProjectSize> getAllProjectSize(Long projectInfoId) {
		return projectSizeDao.findByProjectInfoId(projectInfoId);
	}

	@Override
	public void batchSaveProjectSizeList(List<ProjectSize> psList) {
		projectSizeDao.batchSave(psList);
	}

	public void deleteProjectSize(Long projectSizeId) {
		projectSizeDao.remove(projectSizeId);
	}

	@Override
	public void deleteProjectSize(ProjectSize projectSize) {
		projectSizeDao.remove(projectSize);
	}

	@Override
	public ProjectSize findProjectSize(Long projectSizeId) {
		return projectSizeDao.get(projectSizeId);
	}

	@Override
	public Counterparty saveCounterparty(Counterparty counterparty) {
		return counterpartyDao.save(counterparty);
	}
	
	@Override
	public List<ProjectInfo> findByProjectInfo(ProjectInfo projectInfo) {
		
		if(projectInfo != null)
		{
			return projectInfoDao.findByProjectInfo(projectInfo);
		}
		
		return getAll();
	}

	@Override
	public ProjectInfo loadProjectInfo(Long id) {
		ProjectInfo pi = get(new Long(id));
		if(pi != null)
		{
			List<InvestmentProject> investmentProjs = investmentProjectDao.findByProjId(pi.getId());
			if (investmentProjs != null && !investmentProjs.isEmpty()) {
				for(InvestmentProject ip : investmentProjs)
				{
					pi.getInvestments().add(new InvestmentStatus(projectProgressManager.wrapId(ip.getId(), CapitalInvestmentType.REAL_ESTATE), ip.getName(), ip.getType()));
				}
			}
			
			List<RepaymentProject> repaymentProjs = repaymentProjectDao.findByProjId(pi.getId());
			if (repaymentProjs != null && !repaymentProjs.isEmpty()) {
				for(RepaymentProject rp : repaymentProjs)
				{
					pi.getInvestments().add(new InvestmentStatus(projectProgressManager.wrapId(rp.getId(), CapitalInvestmentType.REPAYMENT_PROJECT), rp.getName(), CapitalInvestmentType.REPAYMENT_PROJECT));
				}
			}
			
			List<SupplyLiquidProject> supplyLiquidProjects = supplyLiquidProjectDao.findByProjId(pi.getId());
			if(supplyLiquidProjects != null && !supplyLiquidProjects.isEmpty())
			{
				for(SupplyLiquidProject sp  : supplyLiquidProjects) {
					pi.getInvestments().add(new InvestmentStatus(projectProgressManager.wrapId(sp.getId(), CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY), sp.getName(), CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY));
				}
			}
		}
		return pi;	
	}
	
	@Override
	public List<String> getAllProjectNames() {
		List<ProjectInfo> projectInfos = projectInfoDao.getAll();
		if(projectInfos != null && !projectInfos.isEmpty())
		{
			List<String> names = new ArrayList<String>();
			for(ProjectInfo projectInfo : projectInfos)
			{
				names.add(projectInfo.getProjectName());
			}
			return names;
		}
		return null;
	}
	
}
