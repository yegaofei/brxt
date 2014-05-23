package com.brxt.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectSize;
import com.brxt.service.ProjectInfoManager;


@Controller
@RequestMapping("/projectSizeForm*")
public class ProjectSizeFormController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	 
    @Autowired
    public void setProjectInfoManager(@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
        this.projectInfoManager = projectInfoManager;
    }
 
    public ProjectSizeFormController() {
        setCancelView("redirect:projectInfoForm");
        setSuccessView("redirect:projectInfoForm");
    }
    
    @RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request) throws Exception {
    	String id = request.getParameter("id");
    	List<ProjectSize> projectSizeList = null; 
        if (!StringUtils.isBlank(id)) {
        	projectSizeList = projectInfoManager.getAllProjectSize(Long.valueOf(id));
        }
        else
        {
        	projectSizeList = new ArrayList<ProjectSize>();
        }
        
		return new ModelAndView().addObject("projectSizeList", projectSizeList);
	}
}
