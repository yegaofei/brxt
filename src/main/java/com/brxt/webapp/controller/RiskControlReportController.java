package com.brxt.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RiskControlReportController extends BaseFormController {

	@RequestMapping(value="/reports/riskControlReport*", method = RequestMethod.GET)
	public String handleRequest(final HttpServletRequest request)
	{
		return "/reports/riskControlReport";
	}
	
	@RequestMapping(value="/reports/reportSearch*", method = RequestMethod.GET)
	public String handleRequest()
	{
		return "/reports/reportSearch";
	}
}
