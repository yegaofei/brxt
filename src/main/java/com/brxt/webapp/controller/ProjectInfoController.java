package com.brxt.webapp.controller;

import org.appfuse.service.GenericManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectInfo;

@Controller
@RequestMapping("/projectInfo*")
public class ProjectInfoController {

	private GenericManager<ProjectInfo, Long> projectInfoManager;

	@Autowired
	public void setPersonManager(
			@Qualifier("projectInfoManager") GenericManager<ProjectInfo, Long> projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() throws Exception {
		return new ModelAndView().addObject(projectInfoManager.getAll());
	}
}
