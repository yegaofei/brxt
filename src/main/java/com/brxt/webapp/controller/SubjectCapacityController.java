package com.brxt.webapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;
import com.brxt.service.SubjectCapacityManager;

@Controller
@RequestMapping("/subjectCapacity*")
public class SubjectCapacityController extends BaseFormController {
	
	private SubjectCapacityManager subjectCapacityManager;

	@Autowired
	public void setSubjectCapacityManager(
			@Qualifier("subjectCapacityManager") SubjectCapacityManager subjectCapacityManager) {
		this.subjectCapacityManager = subjectCapacityManager;
	}
	
	
	public SubjectCapacityController() {
		setCancelView("redirect:/projectInfoForm");
        setSuccessView("subjectCapacity");
	}
	
	private List<SubjectCapacity> fetchSubjectCapacityList(HttpServletRequest request)
	{
		String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
		List<SubjectCapacity> scList = null;
		if(projectInfoId == null || projectInfoId.trim().equals(""))
		{
			return null;
		}
		else
		{
			scList = subjectCapacityManager.findByProjId(Long.valueOf(projectInfoId));
		}
		return scList;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<SubjectCapacity> scList = fetchSubjectCapacityList(request);
		if(scList == null)
		{
			final Locale locale = request.getLocale();
			saveError(request, getText("errors.projectInfoId.required", locale));
			mav.setViewName(getCancelView());
		}
		else
		{
			mav.addObject("subjectCapacityList", scList);
		}
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String method = request.getParameter("method");
		String subjectCapacityId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if( StringUtils.isBlank(method) || StringUtils.isBlank(subjectCapacityId))
		{
			saveError(request, getText("errors.subjectCapacityId.required", locale));
			mav.addObject("subjectCapacityList", fetchSubjectCapacityList(request));
		}
		else
		{
			switch (method) {
			case "Edit":
				mav.setViewName("redirect:/subjectCapacityForm?id=" + subjectCapacityId);
				break;
			case "Delete":				
				subjectCapacityManager.remove(Long.valueOf(subjectCapacityId));
				mav.addObject("subjectCapacityList", fetchSubjectCapacityList(request));
				saveMessage(request, getText("subjectCapacity.delete.successful", locale));
				break;
				default:
			}
		}
		return mav;
	}
}
