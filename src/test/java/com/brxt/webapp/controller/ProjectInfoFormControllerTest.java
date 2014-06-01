package com.brxt.webapp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectInfo;

public class ProjectInfoFormControllerTest extends BaseControllerTestCase {
	@Autowired
    private ProjectInfoFormController form;
    private ProjectInfo projectInfo;
    private MockHttpServletRequest request;
 
    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/projectInfoform");
        request.addParameter("id", "1");
        ModelAndView mav = form.showForm(request);
        assertNotNull(mav);
        assertNotNull(mav.getModelMap().get("projectInfo"));
        assertNotNull(mav.getModelMap().get("capitalInvestmentTypes"));
    }
 
    @Test
    public void testSave() throws Exception {
        request = newGet("/projectInfoform");
        request.addParameter("id", "1");
        ModelAndView mav = form.showForm(request);
        assertNotNull(mav);        
        projectInfo = (ProjectInfo) mav.getModelMap().get("projectInfo");
        
        request = newPost("/projectInfoform");
        request.addParameter("method", "SaveProjectInfo");
        // update required fields
        projectInfo.setProjectName("TestProject5");
        projectInfo.setExpectedReturn(88d);
 
        BindingResult errors = new DataBinder(projectInfo).getBindingResult();
        form.onSubmit(projectInfo, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
        
        request = newGet("/projectInfoform");
        request.addParameter("id", "1");
        mav = form.showForm(request);
        assertNotNull(mav);        
        projectInfo = (ProjectInfo) mav.getModelMap().get("projectInfo");
        assertTrue(projectInfo.getExpectedReturn() == 88d);
    }
 
    @Test
    public void testRemove() throws Exception {
        request = newPost("/projectInfoform");
        request.addParameter("method", "DeleteProjectInfo");
        projectInfo = new ProjectInfo();
        projectInfo.setId(2L);
 
        BindingResult errors = new DataBinder(projectInfo).getBindingResult();
        form.onSubmit(projectInfo, errors, request, new MockHttpServletResponse());
 
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
    
    @Test
    public void testAddProjectSize() throws Exception {
    	request = newGet("/projectInfoform");
        request.addParameter("id", "1");
        ModelAndView mav = form.showForm(request);
        assertNotNull(mav);        
        projectInfo = (ProjectInfo) mav.getModelMap().get("projectInfo");
        int oldProjectSizeListSize = projectInfo.getProjectSizes().size();
        
    	request = newPost("/projectInfoform");
    	request.addParameter("id", "1");
        request.addParameter("method", "AddProjectSize");
        
        BindingResult errors = new DataBinder(projectInfo).getBindingResult();
        mav = form.onSubmit(projectInfo, errors, request, new MockHttpServletResponse());
        assertNotNull(mav);       
        projectInfo = (ProjectInfo) mav.getModelMap().get("projectInfo");
        int newProjectSizeListSize = projectInfo.getProjectSizes().size();
        assertTrue(newProjectSizeListSize -  oldProjectSizeListSize == 1);
    }
}
