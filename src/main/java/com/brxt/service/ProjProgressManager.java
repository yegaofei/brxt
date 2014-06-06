package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.ProjectProgress;
import com.brxt.model.projectprogress.InvestmentProject;

public interface ProjProgressManager extends GenericManager<InvestmentProject, Long> {

	public List<ProjectProgress> getProjectProgressList(Long projectInfoId);
	public String getProgressForm(Long id);
}
