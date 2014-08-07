package com.brxt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.appfuse.service.impl.GenericManagerImpl;
import org.appfuse.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.InvestmentProjectDao;
import com.brxt.dao.InvestmentStatusDao;
import com.brxt.dao.ProjectInfoDao;
import com.brxt.dao.RepaymentProjectDao;
import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.ProjectInfo;
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
	private ProjectInfoDao projectInfoDao;
	private InvestmentStatusDao investmentStatusDao;
	
	private static final Long BASE_INVESTMENT_PROJECT_ID = 100000L;
	private static final Long BASE_SUPPLY_LIQUIDPROJECT_ID = 300000L;
	private static final Long BASE_REPAYMENT_PROJECT_ID = 600000L;
	
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

	@Autowired
	public void setProjectInfoDao(ProjectInfoDao projectInfoDao) {
		this.projectInfoDao = projectInfoDao;
	}
	
	@Autowired
	public void setInvestmentStatusDao(InvestmentStatusDao investmentStatusDao) {
	    this.investmentStatusDao = investmentStatusDao;
	}

	public RepaymentProject getRepaymentProject(Long id){
		return repaymentProjectDao.get(id);
	}
	
	public SupplyLiquidProject getSupplyLiquidProject(Long id){
		return supplyLiquidProjectDao.get(id);
	}
	
	public List<InvestmentProject> getInvestmentProjects(Long projectInfoId) {
		ProjectInfo projectInfo = projectInfoDao.get(projectInfoId);
		Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
		List<InvestmentProject> investmentProjects = new ArrayList<InvestmentProject>();
		
		if(investmentStatusSet != null)
		{
			Iterator<InvestmentStatus> itStatus = investmentStatusSet.iterator();
			while(itStatus.hasNext())
			{
				InvestmentStatus status = itStatus.next();
				investmentProjects.addAll(status.getInvestmentProjects());
			}
		}
		return investmentProjects;
	}
	
	public List<RepaymentProject> getRepaymentProjects(Long projectInfoId) {
		ProjectInfo projectInfo = projectInfoDao.get(projectInfoId);
		Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
		List<RepaymentProject> repaymentProjects = new ArrayList<RepaymentProject>();
		
		if(investmentStatusSet != null)
		{
			Iterator<InvestmentStatus> itStatus = investmentStatusSet.iterator();
			while(itStatus.hasNext())
			{
				InvestmentStatus status = itStatus.next();
				repaymentProjects.addAll(status.getRepaymentProjects());
			}
		}
		return repaymentProjects;
	}
	
	public List<SupplyLiquidProject> getSupplyLiquidProjects(Long projectInfoId) {
		ProjectInfo projectInfo = projectInfoDao.get(projectInfoId);
		Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
		List<SupplyLiquidProject> supplyLiquidProjects = new ArrayList<SupplyLiquidProject>();
		
		if(investmentStatusSet != null)
		{
			Iterator<InvestmentStatus> itStatus = investmentStatusSet.iterator();
			while(itStatus.hasNext())
			{
				InvestmentStatus status = itStatus.next();
				supplyLiquidProjects.addAll(status.getSupplyLiquidProjects());
			}
		}
		return supplyLiquidProjects;
	}
	
	@Override
	public List<ProjectProgress> getProjectProgressList(Long projectInfoId) {
		ProjectInfo projectInfo = projectInfoDao.get(projectInfoId);
		Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
		List<ProjectProgress> projectProgessList = new ArrayList<ProjectProgress>();
		
		if(investmentStatusSet != null)
		{
			Iterator<InvestmentStatus> itStatus = investmentStatusSet.iterator();
			while(itStatus.hasNext())
			{
				InvestmentStatus status = itStatus.next();
				List<InvestmentProject> investmentProjects = status.getInvestmentProjects();
				List<SupplyLiquidProject> supplyLiquidProjects = status.getSupplyLiquidProjects();
				List<RepaymentProject> repaymentProjecs = status.getRepaymentProjects();
				
				if(investmentProjects != null && !investmentProjects.isEmpty())
				{
					for(InvestmentProject ip : investmentProjects)
					{
						ProjectProgress pp = new ProjectProgress();
						pp.setId(wrapId(ip.getId(), CapitalInvestmentType.REAL_ESTATE));
						pp.setDeadline(ip.getProjectEndTime());
						CapitalInvestmentType type = CapitalInvestmentType.valueOf(ip.getInvestmentProjectType().toUpperCase());
						pp.setCapitalInvestmentType(type);
						pp.setProjectName(status.getProjectName());
						pp.setInvestment(true);
						pp.setSupplyLiquid(false);
						projectProgessList.add(pp);
					}
				}
				
				if(supplyLiquidProjects != null && !supplyLiquidProjects.isEmpty())
				{
					for(SupplyLiquidProject sp : supplyLiquidProjects)
					{
						ProjectProgress pp = new ProjectProgress();
						pp.setId(wrapId(sp.getId(), CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY));
						pp.setDeadline(sp.getProjectEndTime());
						pp.setProjectName(status.getProjectName());
						pp.setCapitalInvestmentType(CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY);
						pp.setInvestment(true);
						pp.setSupplyLiquid(true);
						projectProgessList.add(pp);
					}
				}
				
				if(repaymentProjecs != null && !repaymentProjecs.isEmpty())
				{
					for(RepaymentProject rp : repaymentProjecs)
					{
						ProjectProgress pp = new ProjectProgress();
						pp.setId(wrapId(rp.getId(), CapitalInvestmentType.REPAYMENT_PROJECT));
						pp.setDeadline(rp.getProjectEndTime());
						pp.setProjectName(status.getProjectName());
						pp.setCapitalInvestmentType(CapitalInvestmentType.REPAYMENT_PROJECT);
						pp.setInvestment(false);
						pp.setSupplyLiquid(false);
						projectProgessList.add(pp);
					}
				}
			}
		}
		return projectProgessList;
	}

	public Long wrapId(Long realId, CapitalInvestmentType type)
	{
		Long id = null;
		switch (type)
		{
		case INFRASTRUCTURE:
		case REAL_ESTATE:
			id = realId + BASE_INVESTMENT_PROJECT_ID;
			break;
		case REPAYMENT_PROJECT:
			id = realId + BASE_REPAYMENT_PROJECT_ID;
			break;
		case SUPPLEMENTAL_LIQUIDITY:
			id = realId + BASE_SUPPLY_LIQUIDPROJECT_ID;
			break;
			default:
		}
		return id;
	}
	
	@Override
	public void remove(Long id)
	{
		Long realId = null;
		InvestmentStatus investmentStatus = null;
		if(id > BASE_REPAYMENT_PROJECT_ID)
		{
			realId = id - BASE_REPAYMENT_PROJECT_ID;
			RepaymentProject repaymentProject = repaymentProjectDao.get(realId);
			investmentStatus = repaymentProject.getInvestmentStatus();
			List<RepaymentProject> repaymentProjectList  = investmentStatus.getRepaymentProjects();
			Iterator<RepaymentProject> it = repaymentProjectList.iterator();
			while(it.hasNext())
			{
				RepaymentProject itObj = it.next();
				if(itObj.getId().equals(realId))
				{
					it.remove();
				}
			}
			repaymentProjectDao.remove(realId);
		}
		else if (id > BASE_SUPPLY_LIQUIDPROJECT_ID)
		{
			realId = id - BASE_SUPPLY_LIQUIDPROJECT_ID;
			SupplyLiquidProject supplyLiquidProject = supplyLiquidProjectDao.get(realId);
			investmentStatus = supplyLiquidProject.getInvestmentStatus();
			List<SupplyLiquidProject> supplyLiquidProjectList = investmentStatus.getSupplyLiquidProjects();
			Iterator<SupplyLiquidProject> it = supplyLiquidProjectList.iterator();
			while(it.hasNext())
			{
				SupplyLiquidProject itObj = it.next();
				if(itObj.getId().equals(realId))
				{
					it.remove();
				}
			}
			supplyLiquidProjectDao.remove(realId);
		} 
		else if (id > BASE_INVESTMENT_PROJECT_ID)
		{
			realId = id - BASE_INVESTMENT_PROJECT_ID;
			InvestmentProject investmentProject = investmentProjectDao.get(realId);
			investmentStatus = investmentProject.getInvestmentStatus();
			List<InvestmentProject> investmentProjectList = investmentStatus.getInvestmentProjects();
			Iterator<InvestmentProject> it = investmentProjectList.iterator();
			while(it.hasNext())
			{
				InvestmentProject itObj = it.next();
				if(itObj.getId().equals(realId))
				{
					it.remove();
				}
			}
			investmentProjectDao.remove(realId);
		}
	}
	
	public Long getRealId(Long id)
	{
		Long realId = 0L;
		if(id > BASE_REPAYMENT_PROJECT_ID)
		{
			realId = id - BASE_REPAYMENT_PROJECT_ID;
		}
		else if (id > BASE_SUPPLY_LIQUIDPROJECT_ID)
		{
			realId = id - BASE_SUPPLY_LIQUIDPROJECT_ID;
		} 
		else if (id > BASE_INVESTMENT_PROJECT_ID)
		{
			realId = id - BASE_INVESTMENT_PROJECT_ID;
		}
		return realId;
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

	@Override
	public RepaymentProject saveRepaymentProject(RepaymentProject o) {
		return repaymentProjectDao.save(o);
	}

	@Override
	public SupplyLiquidProject saveSupplyLiqidProject(SupplyLiquidProject o) {
		return supplyLiquidProjectDao.save(o);
	}
	
	public InvestmentProject findInvestmentProject(InvestmentStatus investmentStatus, Date projectEndTime)
	{
		return investmentProjectDao.find(investmentStatus, projectEndTime);
	}

	@Override
	public SupplyLiquidProject findSupplyLiquidProject(
			InvestmentStatus investmentStatus, Date projectEndTime) {
		return supplyLiquidProjectDao.find(investmentStatus, projectEndTime);
	}

	@Override
	public RepaymentProject findRepaymentProject(
			InvestmentStatus investmentStatus, Date projectEndTime) {
		return repaymentProjectDao.find(investmentStatus, projectEndTime);
	}
	
	public List<String> getAvailableEndTimes(Long investmentStatusId) { 
	    String projectType = null;
	    InvestmentStatus investmentStatus = investmentStatusDao.get(investmentStatusId);
	    if(investmentStatus != null) {
	        projectType = investmentStatus.getProjectType();
	    }
	    if(projectType != null && investmentStatusId != null) {
	        List<Date> projectEndTimeList = null;
	        CapitalInvestmentType type = CapitalInvestmentType.valueOf(projectType.toUpperCase());
	        switch (type) {
	            case REAL_ESTATE:
	            case INFRASTRUCTURE:
	                projectEndTimeList = investmentProjectDao.listProjectEndTime(investmentStatusId);
	                break;
	            case SUPPLEMENTAL_LIQUIDITY:
	                projectEndTimeList = supplyLiquidProjectDao.listProjectEndTime(investmentStatusId);
	                break;
	            case REPAYMENT_PROJECT:
	                projectEndTimeList = repaymentProjectDao.listProjectEndTime(investmentStatusId);
	                break;
	                default:
	        }
	        
	        if(projectEndTimeList == null ) {
	            return null;
	        }
	        
	        SimpleDateFormat sf = new SimpleDateFormat(DateUtil.getDatePattern());
	        List<String> availableEndTimes = new ArrayList<String>();
	        for(Date d : projectEndTimeList) {
	            availableEndTimes.add(sf.format(d));
	        }
	        return availableEndTimes;
	    }
	    
	    return null;
	}

}
