package com.brxt.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.SubjectCapacity;
import com.brxt.service.SubjectCapacityManager;


@Controller
@RequestMapping("/subjectCapacityForm*")
public class SubjectCapacityFormController extends BaseFormController {
	
	private SubjectCapacityManager subjectCapacityManager;

	@Autowired
	public void setSubjectCapacityManager(
			@Qualifier("subjectCapacityManager") SubjectCapacityManager subjectCapacityManager) {
		this.subjectCapacityManager = subjectCapacityManager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("subjectCapacity", getNewSubjectCapacity());
		
		return mav;
	}
	
	private void addNewSubjectCapacity()
	{
		
	}
	
	private SubjectCapacity getNewSubjectCapacity()
	{
		SubjectCapacity subjectCapacity = new SubjectCapacity();
		subjectCapacity.setBizScopeChanged(false);
		subjectCapacity.setCapitalChanged(false);
		subjectCapacity.setDevCapacityChanged(false);
		subjectCapacity.setLoanCardValid(false);
		subjectCapacity.setNameChanged(false);
		subjectCapacity.setOtherBigChanges(false);
		subjectCapacity.setOwnerChanged(false);
		subjectCapacity.setOwnershipChanged(false);
		
		return subjectCapacity;
	}

}
