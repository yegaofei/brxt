package com.brxt.webapp.controller;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.Assert.*;

public class ProjectInfoControllerTest extends BaseControllerTestCase {

	@Autowired
    private ProjectInfoController controller;
 
    @Test
    public void testHandleRequest() throws Exception {
        ModelAndView mav = controller.handleRequest();
        ModelMap m = mav.getModelMap();
        assertNotNull(m.get("projectInfoList"));
        assertTrue(((List) m.get("projectInfoList")).size() > 0);
    }
}
