package com.brxt.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.RepaymentManager;

@Controller
public class RiskControlReportController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	private ProjProgressManager progressManager = null;
	private RepaymentManager repaymentManager;
	
	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}
	
	@Autowired
	public void setProjectProgressManager(@Qualifier("projectProgressManager") ProjProgressManager progressManager){
		this.progressManager =  progressManager;
	}
	
	@Autowired
	public void setRepaymentManager(
			@Qualifier("repaymentManager") RepaymentManager repaymentManager) {
		this.repaymentManager = repaymentManager;
	}
	
	@ModelAttribute
	public ProjectInfo getProjectInfo(final HttpServletRequest request){
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			return loadProjectInfo(new Long(id));
		}
		return new ProjectInfo();
	}
	
	@ModelAttribute("repaymentList")
	public List<Repayment> getRepaymentList(final HttpServletRequest request) {
		String projectInfoId = request.getParameter("id");
		List<Repayment> ciList = null;
		if (!StringUtils.isBlank(projectInfoId)) {
			ciList = repaymentManager.findByProjId(Long.valueOf(projectInfoId));
		}
		return ciList;
	}
	
	private ProjectInfo loadProjectInfo(Long id)
	{
		ProjectInfo pi = projectInfoManager.get(new Long(id));
		if(pi != null)
		{
			List<InvestmentProject> investmentProjs = progressManager.getInvestmentProjects(pi.getId());
			if (investmentProjs != null && !investmentProjs.isEmpty()) {
				for(InvestmentProject ip : investmentProjs)
				{
					pi.getInvestments().add(new InvestmentStatus(ip.getName(), ip.getType()));
				}
			}
		}
		return pi;	
	}
	
	@RequestMapping(value="/reports/riskControlReport*", method = RequestMethod.GET)
	public String handleRequest(final HttpServletRequest request)
	{
		
		return "/reports/riskControlReport";
	}
	
	@RequestMapping(value="/reports/reportSearch*", method = RequestMethod.GET)
	public ModelAndView handleRequest()
	{
		return new ModelAndView("/reports/reportSearch").addObject(projectInfoManager.getAll());
	}
	
	@RequestMapping(value="/reports/reportSearch*", method = RequestMethod.POST)
	public ModelAndView onSearch(@ModelAttribute("projectInfo") final ProjectInfo projectInfo,
			final HttpServletRequest request)
	{
		String method = request.getParameter("method");
		
		if(StringUtils.isBlank(method))
		{
			return new ModelAndView();
		}
		
		switch (method)
		{
		case "SearchProjectInfo":
			List<ProjectInfo> projectInfos = projectInfoManager.findByProjectInfo(projectInfo);
			return new ModelAndView("/reports/reportSearch").addObject("projectInfoList", projectInfos);
			default:
		}
		
		return new ModelAndView("/reports/reportSearch").addObject(projectInfoManager.getAll());
	}
}
