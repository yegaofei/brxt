package com.brxt.webapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.constant.SessionAttributes;
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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
		String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
		ModelAndView mav = new ModelAndView();
		final Locale locale = request.getLocale();
		if(projectInfoId == null || projectInfoId.trim().equals(""))
		{
			saveError(request, getText("errors.projectInfoId.required", locale));
			mav.setViewName(getCancelView());
		}
		else
		{
			List<SubjectCapacity> scList = subjectCapacityManager.findByProjId(Long.valueOf(projectInfoId));
			mav.addObject("subjectCapacityList", scList);
		}
		
		return mav;
	}
}
