package com.brxt.webapp.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectInfo;

public class ProjectInfoControllerTest extends BaseControllerTestCase {

	@Autowired
    private ProjectInfoController controller;
 
    @Test
    public void testHandleRequest() throws Exception {
        ProjectInfo projectInfo = new ProjectInfo();
        ModelAndView mav = controller.handleRequest(projectInfo);
        ModelMap m = mav.getModelMap();
        assertNotNull(m.get("projectInfoList"));
        assertTrue(((List) m.get("projectInfoList")).size() >= 0);
    }
}
