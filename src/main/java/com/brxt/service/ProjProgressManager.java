package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.ProjectProgress;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;

public interface ProjProgressManager extends
		GenericManager<InvestmentProject, Long> {

	public List<ProjectProgress> getProjectProgressList(Long projectInfoId);
	
	public List<InvestmentProject> getInvestmentProjects(Long projectInfoId);

	public String getProgressForm(Long id);

	public Long getRealId(Long id);

	public RepaymentProject getRepaymentProject(Long id);

	public SupplyLiquidProject getSupplyLiquidProject(Long id);

	public void saveRepaymentProject(RepaymentProject o);
	
	public void saveSupplyLiqidProject(SupplyLiquidProject o);
}
