package com.brxt.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/projectInfo*")
public class ProjectInfoController {

	private ProjectInfoManager projectInfoManager;

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() throws Exception {
		return new ModelAndView().addObject(projectInfoManager.getAll());
	}
}
