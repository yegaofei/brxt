package com.brxt.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brxt.model.report.RiskControlReport;
import com.brxt.service.ReportManager;

@Controller
public class PreviewReportController extends BaseFormController {

	protected ReportManager reportManager;
	
	@Autowired
	public void setReportManager(@Qualifier("reportManager") ReportManager reportManager) {
		this.reportManager = reportManager;
	}
	
	@RequestMapping(value = "/reports/commitReport*", method = RequestMethod.GET)
	public String handleRequest(final HttpServletRequest request) {
		String reportIdStr = request.getParameter("reportId");
		if(!StringUtils.isBlank(reportIdStr))
		{
			RiskControlReport report = reportManager.get(Long.valueOf(reportIdStr));
			report.getReportStatus().setCommitReport(true);
			reportManager.save(report);
			String reportName = report.getProjectInfo().getProjectName() + report.getReportSeason();
			saveMessage(request, getText("report.commit.success", new String[]{reportName}, request.getLocale()));
		}
		return "redirect:/reports/reportSearch";
	}
	
	@RequestMapping(value = "/reports/auditCommentForm*", method = RequestMethod.POST)
	public String auditComment(final HttpServletRequest request) {
	    String reportIdStr = request.getParameter("reportId");
	    if(!StringUtils.isBlank(reportIdStr)) 
	    {
	        String auditComment = request.getParameter("auditComment");
	        String inlineRadioOptions = request.getParameter("inlineRadioOptions");
	        RiskControlReport report = reportManager.get(Long.valueOf(reportIdStr));
	        report.getReportStatus().setReportAudit(inlineRadioOptions.equals("true"));
	        report.getReportStatus().setAuditComment(auditComment);
	        reportManager.save(report);
	    }
	    return "redirect:/reports/reportSearch";
	}
	
	
}
