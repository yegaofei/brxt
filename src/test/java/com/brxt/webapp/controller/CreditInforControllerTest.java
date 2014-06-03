package com.brxt.webapp.controller;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.CreditInformation;

public class CreditInforControllerTest extends BaseControllerTestCase{

	@Autowired
    private CreditInformationController c = null;
    private MockHttpServletRequest request;
    
    @Test
    public void testGet() throws Exception {
        log.debug("testing get credit information list ....");
        request = newGet("/creditInformation");
        request.getSession().setAttribute(SessionAttributes.PROJECT_INFO_ID, "1");

        List<CreditInformation> list = c.getCreditInformationList(request);
        assertNotNull(list);
    }
}
