package com.brxt.webapp.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.SubjectCapacity;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.RepaymentManager;
import com.brxt.service.SubjectCapacityManager;

@Controller
public class RiskControlReportController extends BaseSheetController {

	private ProjectInfoManager projectInfoManager = null;
	private RepaymentManager repaymentManager = null;
	private SubjectCapacityManager subjectCapacityManager = null;
	
	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}
	
	@Autowired
	public void setRepaymentManager(
			@Qualifier("repaymentManager") RepaymentManager repaymentManager) {
		this.repaymentManager = repaymentManager;
	}
	
	@Autowired
	public void setSubjectCapacityManager(
			@Qualifier("subjectCapacityManager") SubjectCapacityManager subjectCapacityManager) {
		this.subjectCapacityManager = subjectCapacityManager;
	}
	
	@ModelAttribute
	public ProjectInfo getProjectInfo(final HttpServletRequest request){
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			return projectInfoManager.loadProjectInfo(new Long(id));
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
	
	@ModelAttribute("subjectCapacity")
	public SubjectCapacity getSubjectCapacity(final HttpServletRequest request) {
		String projectInfoId = request.getParameter("id");
		String counterpartyId = request.getParameter("counterpartyId");
		if (!StringUtils.isBlank(projectInfoId)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			if(projectInfo == null)
			{
				log.error("No project information for id " + projectInfoId);
				return null;
			}
			Counterparty counterparty = null;
			if(!StringUtils.isBlank(counterpartyId))
			{
				counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
			}
			else
			{
				Iterator<Counterparty> itc = projectInfo.getCounterparties().iterator();
				if(itc!=null && itc.hasNext())
				{
					counterparty = itc.next();
				}
			}
			
			if(counterparty == null)
			{
				log.debug("No counterparty for project info [" + projectInfo.toString() + "]");
				return null;
			}
			
			//TODO: How to confirm the date range ??
			Date projectInfoCreateTime = projectInfo.getCreateTime();
			Calendar createTime = Calendar.getInstance(request.getLocale());
			createTime.setTime(projectInfoCreateTime);
			
			int month = createTime.get(Calendar.MONTH);
			int year = createTime.get(Calendar.YEAR);
			Calendar startTime = Calendar.getInstance(request.getLocale());
			Calendar endTime = Calendar.getInstance(request.getLocale());
			if(month < 3)
			{
				//Q1
				startTime.set(year, 0, 1, 0, 0, 0);
				endTime.set(year, 2, 31, 23, 59, 59);
				
			} else if (month < 6)
			{
				//Q2
				startTime.set(year, 3, 1, 0, 0, 0);
				endTime.set(year, 5, 30, 23, 59, 59);
				
			} else if (month < 9)
			{
				//Q3
				startTime.set(year, 6, 1, 0, 0, 0);
				endTime.set(year, 8, 30, 23, 59, 59);
				
			} else if (month < 12)
			{
				//Q4
				startTime.set(year, 9, 1, 0, 0, 0);
				endTime.set(year, 11, 31, 23, 59, 59);
			}
			
			List<SubjectCapacity> subjectCapacities = subjectCapacityManager.findByProjIdCpId(projectInfo, counterparty, startTime.getTime(), endTime.getTime());
			if(subjectCapacities != null && subjectCapacities.size() > 0)
			{
				return subjectCapacities.get(0);
			}
		}
		return null;
	}
	
	@ModelAttribute("counterparties")
	public List<Counterparty> getCounterparties(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(id));
			Set<Counterparty> counterparties = projectInfo.getCounterparties();
			if (counterparties == null | counterparties.isEmpty()) {
				return null;
			}
			List<Counterparty> cpList = new ArrayList<Counterparty>(counterparties);
			return cpList;
		}
		return null;
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
