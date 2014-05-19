package com.brxt.webapp.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

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
 
        projectInfo = form.showForm(request);
        assertNotNull(projectInfo);
    }
 
    @Test
    public void testSave() throws Exception {
        request = newGet("/projectInfoform");
        request.addParameter("id", "1");
 
        projectInfo = form.showForm(request);
        assertNotNull(projectInfo);
 
        request = newPost("/projectInfoform");
 
        projectInfo = form.showForm(request);
        // update required fields
        projectInfo.setProjectName("TestProject5");
        projectInfo.setExpectedReturn(88d);
 
        BindingResult errors = new DataBinder(projectInfo).getBindingResult();
        form.onSubmit(projectInfo, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
 
    @Test
    public void testRemove() throws Exception {
        request = newPost("/projectInfoform");
        request.addParameter("delete", "");
        projectInfo = new ProjectInfo();
        projectInfo.setId(2L);
 
        BindingResult errors = new DataBinder(projectInfo).getBindingResult();
        form.onSubmit(projectInfo, errors, request, new MockHttpServletResponse());
 
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
