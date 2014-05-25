package com.brxt.webapp.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectSize;

public class ProjectSizeFormControllerTest extends BaseControllerTestCase  {

	@Autowired
    private ProjectSizeFormController form;
    private MockHttpServletRequest request;
    
    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/projectSizeform");
        request.addParameter("projectInfoId", "1");
 
        ModelAndView mav = form.handleRequest(request);
        ModelMap m = mav.getModelMap();
        assertNotNull(m.get("projectSizeList"));
        assertTrue(((List<ProjectSize>) m.get("projectSizeList")).size() > 0);
    }
    
    @Test
    public void testAdd() throws Exception {
    	
    	request = newGet("/projectSizeform");
        request.addParameter("projectInfoId", "1");
        ModelAndView mav = form.handleRequest(request);
        ModelMap m = mav.getModelMap();
        List<ProjectSize> projectSizeList = (List<ProjectSize>) m.get("projectSizeList");
        int size = projectSizeList.size();
        
        request = newGet("/projectSizeform");
        request.addParameter("projectInfoId", "1");
        request.addParameter("method", "Add");
        mav = form.onSubmit(Long.valueOf(1), request);
        m = mav.getModelMap();
        int sizeAfterAdd = ((List<ProjectSize>) m.get("projectSizeList")).size();
        assertTrue(sizeAfterAdd - size == 1);
    	
    }
 
}
