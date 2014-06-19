package com.brxt.webapp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import com.brxt.model.CreditInformation;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.ReportContentKey;
import com.brxt.model.SubjectCapacity;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.service.CreditInformationManager;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.RepaymentManager;
import com.brxt.service.SubjectCapacityManager;

@Controller
public class RiskControlReportController extends BaseSheetController {

	private ProjectInfoManager projectInfoManager = null;
	private RepaymentManager repaymentManager = null;
	private SubjectCapacityManager subjectCapacityManager = null;
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	private CreditInformationManager creditInformationManager;
	private ProjProgressManager projectProgressManager;

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

	@Autowired
	public void setCreditInformationManager(
			@Qualifier("creditInformationManager") CreditInformationManager creditInformationManager) {
		this.creditInformationManager = creditInformationManager;
	}
	
	@Autowired
	public void setProjectProgressManager(
			@Qualifier("projectProgressManager") ProjProgressManager projectProgressManager) {
		this.projectProgressManager = projectProgressManager;
	}

	@ModelAttribute
	public ProjectInfo getProjectInfo(final HttpServletRequest request) {
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
	public SubjectCapacity getSubjectCapacity(final HttpServletRequest request)
			throws ParseException {
		ReportContentKey rck = getReportContentKey(request);
		if (rck == null) {
			return null;
		}
		List<SubjectCapacity> subjectCapacities = subjectCapacityManager
				.findByProjIdCpId(rck.getProjectInfo(), rck.getCounterparty(),
						rck.getStartTime(), rck.getEndTime());
		if (subjectCapacities != null && subjectCapacities.size() > 0) {
			return subjectCapacities.get(0);
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
			List<Counterparty> cpList = new ArrayList<Counterparty>(
					counterparties);
			return cpList;
		}
		return null;
	}

	@ModelAttribute("creditInformation")
	public CreditInformation getCreditInformation(
			final HttpServletRequest request) throws ParseException {
		ReportContentKey rck = getReportContentKey(request);
		if (rck == null) {
			return null;
		}
		List<CreditInformation> creditInformations = creditInformationManager
				.findByProjIdCpId(rck.getProjectInfo(), rck.getCounterparty(),
						rck.getStartTime(), rck.getEndTime());
		if (creditInformations != null && !creditInformations.isEmpty()) {
			return creditInformations.get(0);
		}
		return null;
	}
	
	@ModelAttribute("investmentProjects")
	public List<InvestmentProject> getInvestmentProjects(final HttpServletRequest request){
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			List<InvestmentProject>  investmentProjects = projectProgressManager.getInvestmentProjects(Long.valueOf(id));
			if (investmentProjects == null | investmentProjects.isEmpty()) {
				return null;
			}
			return investmentProjects;
		}
		return null;
	}
	
	@ModelAttribute("supplyLiquidProjects")
	public List<SupplyLiquidProject> getSupplyLiquidProjects(final HttpServletRequest request){
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			List<SupplyLiquidProject>  supplyLiquidProjects = projectProgressManager.getSupplyLiquidProjects(Long.valueOf(id));
			if (supplyLiquidProjects == null | supplyLiquidProjects.isEmpty()) {
				return null;
			}
			return supplyLiquidProjects;
		}
		return null;
	}
	
	@ModelAttribute("repaymentProjects")
	public List<RepaymentProject> getRepaymentProjects(final HttpServletRequest request) {
		
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			List<RepaymentProject>  repaymentProjects = projectProgressManager.getRepaymentProjects(Long.valueOf(id));
			if (repaymentProjects == null | repaymentProjects.isEmpty()) {
				return null;
			}
			return repaymentProjects;
		}
		return null;
	}

	private ReportContentKey getReportContentKey(
			final HttpServletRequest request) throws ParseException {
		String projectInfoId = request.getParameter("id");
		String counterpartyId = request.getParameter("counterpartyId");
		if (!StringUtils.isBlank(projectInfoId)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long
					.valueOf(projectInfoId));
			if (projectInfo == null) {
				log.error("No project information for id " + projectInfoId);
				return null;
			}
			Counterparty counterparty = null;
			if (!StringUtils.isBlank(counterpartyId)) {
				counterparty = findCounterparty(projectInfo,
						Long.valueOf(counterpartyId));
			} else {
				Iterator<Counterparty> itc = projectInfo.getCounterparties()
						.iterator();
				if (itc != null && itc.hasNext()) {
					counterparty = itc.next();
				}
			}

			if (counterparty == null) {
				log.debug("No counterparty for project info ["
						+ projectInfo.toString() + "]");
				return null;
			}

			// TODO: How to confirm the date range ??
			Date projectInfoCreateTime = projectInfo.getCreateTime();
			Calendar createTime = Calendar.getInstance(request.getLocale());
			createTime.setTime(projectInfoCreateTime);

			int month = createTime.get(Calendar.MONTH);
			int year = createTime.get(Calendar.YEAR);
			Date startTime = null;
			Date endTime = null;
			StringBuilder sbStartTime = new StringBuilder();
			StringBuilder sbEndTime = new StringBuilder();
			if (month < 3) {
				// Q1
				sbStartTime.append(year).append("-01-01");
				sbEndTime.append(year).append("-03-31");
			} else if (month < 6) {
				// Q2
				sbStartTime.append(year).append("-04-01");
				sbEndTime.append(year).append("-06-30");

			} else if (month < 9) {
				// Q3
				sbStartTime.append(year).append("-07-01");
				sbEndTime.append(year).append("-09-30");

			} else if (month < 12) {
				// Q4
				sbStartTime.append(year).append("-10-01");
				sbEndTime.append(year).append("-12-31");
			}
			startTime = sf.parse(sbStartTime.toString());
			endTime = sf.parse(sbEndTime.toString());

			ReportContentKey rck = new ReportContentKey();
			rck.setProjectInfo(projectInfo);
			rck.setCounterparty(counterparty);
			rck.setStartTime(startTime);
			rck.setEndTime(endTime);
			return rck;
		}
		return null;
	}

	@RequestMapping(value = "/reports/riskControlReport*", method = RequestMethod.GET)
	public String handleRequest(final HttpServletRequest request) {

		return "/reports/riskControlReport";
	}

	@RequestMapping(value = "/reports/riskControlReport*", method = RequestMethod.POST)
	public String onSubmit(final HttpServletRequest request) {

		return "/reports/riskControlReport";
	}

	@RequestMapping(value = "/reports/reportSearch*", method = RequestMethod.GET)
	public ModelAndView handleRequest() {
		return new ModelAndView("/reports/reportSearch")
				.addObject(projectInfoManager.getAll());
	}

	@RequestMapping(value = "/reports/reportSearch*", method = RequestMethod.POST)
	public ModelAndView onSearch(
			@ModelAttribute("projectInfo") final ProjectInfo projectInfo,
			final HttpServletRequest request) {
		String method = request.getParameter("method");

		if (StringUtils.isBlank(method)) {
			return new ModelAndView();
		}

		switch (method) {
		case "SearchProjectInfo":
			List<ProjectInfo> projectInfos = projectInfoManager
					.findByProjectInfo(projectInfo);
			return new ModelAndView("/reports/reportSearch").addObject(
					"projectInfoList", projectInfos);
		default:
		}

		return new ModelAndView("/reports/reportSearch")
				.addObject(projectInfoManager.getAll());
	}
}
