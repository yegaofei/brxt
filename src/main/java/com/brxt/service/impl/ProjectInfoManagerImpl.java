package com.brxt.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.CounterpartyDao;
import com.brxt.dao.InvestmentProjectDao;
import com.brxt.dao.ManagerDao;
import com.brxt.dao.ProjectInfoDao;
import com.brxt.dao.ProjectSizeDao;
import com.brxt.dao.RepaymentProjectDao;
import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.Counterparty;
import com.brxt.model.Manager;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.model.enums.ManagerType;
import com.brxt.service.ProjectInfoManager;

@Service("projectInfoManager")
public class ProjectInfoManagerImpl extends GenericManagerImpl<ProjectInfo, Long> implements ProjectInfoManager {

	ProjectInfoDao projectInfoDao;

	ProjectSizeDao projectSizeDao;

	CounterpartyDao counterpartyDao;

	InvestmentProjectDao investmentProjectDao;

	RepaymentProjectDao repaymentProjectDao;

	SupplyLiquidProjectDao supplyLiquidProjectDao;
	
	ManagerDao managerDao;

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
	
	@Autowired
    public void setManagerDao(ManagerDao managerDao) {
        this.managerDao = managerDao;
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

		if (projectInfo != null) {
			return projectInfoDao.findByProjectInfo(projectInfo);
		}

		return getAll();
	}

	@Override
	public ProjectInfo loadProjectInfo(Long id) {
		ProjectInfo pi = get(new Long(id));
		return pi;
	}

	@Override
	public List<String> getAllProjectNames() {
		List<ProjectInfo> projectInfos = projectInfoDao.getAll();
		if (projectInfos != null && !projectInfos.isEmpty()) {
			List<String> names = new ArrayList<String>();
			for (ProjectInfo projectInfo : projectInfos) {
				names.add(projectInfo.getProjectName());
			}
			return names;
		}
		return null;
	}

	public Counterparty findCounterparty(Counterparty counterparty) {
		return counterpartyDao.findByCounterparty(counterparty);
	}
	
	public List<Manager> getAllTrustManagers() {
	    return managerDao.findByType(ManagerType.TRUST_MANAGER);
	}
	
	public List<Manager> getAllRiskManagers() {
	    return managerDao.findByType(ManagerType.RISK_MANAGER);
    }

	public List<Manager> getAllDelegateManagers() {
	    return managerDao.findByType(ManagerType.DELEGATE_MANAGER);
	}

}
