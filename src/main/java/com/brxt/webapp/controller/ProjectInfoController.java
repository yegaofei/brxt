package com.brxt.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectInfo;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/projectInfo*")
public class ProjectInfoController extends BaseFormController{

	private ProjectInfoManager projectInfoManager;

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@ModelAttribute
	public ProjectInfo getProjectInfo() {
		return new ProjectInfo();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest() throws Exception {
		return new ModelAndView().addObject(projectInfoManager.getAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSearch(
			@ModelAttribute("projectInfo") final ProjectInfo projectInfo,
			final HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		
		if(StringUtils.isBlank(method))
		{
			return new ModelAndView();
		}
		
		switch (method)
		{
		case "SearchProjectInfo":
			List<ProjectInfo> projectInfos = projectInfoManager.findByProjectInfo(projectInfo);
			return new ModelAndView("/projectInfo").addObject("projectInfoList", projectInfos);
			default:
		}
		
		
		return new ModelAndView().addObject(projectInfoManager.getAll());
	}

}
