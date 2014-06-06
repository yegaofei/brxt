package com.brxt.webapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.ProjectProgress;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;

@Controller
public class ProjectProgressController extends BaseFormController {
	
	private ProjectInfoManager projectInfoManager;
	private ProjProgressManager projectProgressManager;
	
	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}
	
	@Autowired
	public void setProjectProgressManager(
			@Qualifier("projectProgressManager") ProjProgressManager projectProgressManager) {
		this.projectProgressManager = projectProgressManager;
	}
	
	
	@ModelAttribute("progressList")
	public List<ProjectProgress> getProjectProgressList(final HttpServletRequest request)
	{
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} 
		else
		{
			return projectProgressManager.getProjectProgressList(Long.valueOf(projectInfoId));	
		}
		return null;
	}

	
	@RequestMapping(value="/projectProgress*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "projectProgress";
	}
	
	@RequestMapping(value="/projectProgress*", method = RequestMethod.POST)
	public String onSubmit(final HttpServletRequest request, final HttpServletResponse response)
	{
		String method = request.getParameter("method");
		String projectProgresstId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if( StringUtils.isBlank(method) || StringUtils.isBlank(projectProgresstId))
		{
			saveError(request, getText("errors.repaymentId.required", locale));
		}
		else
		{
			switch (method) {
			case "Edit":
				return "redirect:/projectProgressForm?id=" + projectProgresstId;
			case "Delete":				
				projectProgressManager.remove(Long.valueOf(projectProgresstId));
				saveMessage(request, getText("projectProgress.delete.successful", locale));
				return "redirect:/projectProgress";
				default:
			}
		}
		return "projectProgress";
	}
	
	@RequestMapping(value="/projectProgressForm*", method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String projectProgresstId = request.getParameter("id");
		if(StringUtils.isBlank(projectProgresstId))
		{
			//Add
		}
		else
		{
			
		}
		
		
		return mav;
	}
}
