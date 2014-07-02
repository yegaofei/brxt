package com.brxt.webapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectProgress;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.service.InvestmentProjectsManager;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;

@Controller
public class ProjectProgressController extends BaseFormController {

	private ProjectInfoManager projectInfoManager;
	private ProjProgressManager projectProgressManager;
	private InvestmentProjectsManager investmentProjectsManager;

	@Autowired
	public void setProjectInfoManager(@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Autowired
	public void setProjectProgressManager(@Qualifier("projectProgressManager") ProjProgressManager projectProgressManager) {
		this.projectProgressManager = projectProgressManager;
	}

	@Autowired
	public void setInvestmentProjectsManager(@Qualifier("investmentProjectsManager") InvestmentProjectsManager investmentProjectsManager) {
		this.investmentProjectsManager = investmentProjectsManager;
	}

	@ModelAttribute("projectInfoId")
	protected Long getProjectInfoId(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
		if (StringUtils.isBlank(projectInfoId)) {
			final Locale locale = request.getLocale();
			saveError(request, getText("errors.projectInfoId.required", locale));
		} else {
			return Long.valueOf(projectInfoId);
		}
		return null;
	}

	@ModelAttribute("progressList")
	public List<ProjectProgress> getProjectProgressList(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} else {
			return projectProgressManager.getProjectProgressList(Long.valueOf(projectInfoId));
		}
		return null;
	}

	@RequestMapping(value = "/progress/addProgress*", method = RequestMethod.GET)
	public ModelAndView addProgress(final HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/progress/addProgress");
		String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
		ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
		Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
		List<ProjectProgress> investments = new ArrayList<ProjectProgress>();
		List<ProjectProgress> repayments = new ArrayList<ProjectProgress>();
		if (investmentStatusSet != null) {
			Iterator<InvestmentStatus> itS = investmentStatusSet.iterator();
			while (itS.hasNext()) {
				InvestmentStatus is = itS.next();
				String projectType = is.getProjectType();
				CapitalInvestmentType type = CapitalInvestmentType.valueOf(projectType.toUpperCase());
				ProjectProgress pp = new ProjectProgress();
				pp.setProjectName(is.getProjectName());
				pp.setId(is.getId());
				switch (type) {
				case REPAYMENT_PROJECT:
					pp.setCapitalInvestmentType(CapitalInvestmentType.REPAYMENT_PROJECT);
					pp.setInvestment(false);
					pp.setSupplyLiquid(false);
					repayments.add(pp);
					break;
				case SUPPLEMENTAL_LIQUIDITY:
					pp.setCapitalInvestmentType(CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY);
					pp.setInvestment(false);
					pp.setSupplyLiquid(true);
					investments.add(pp);
					break;
				default:
					pp.setCapitalInvestmentType(type);
					pp.setInvestment(true);
					pp.setSupplyLiquid(false);
					investments.add(pp);
				}
			}

			if (!investments.isEmpty()) {
				mav.addObject("investments", investments);
			}

			if (!repayments.isEmpty()) {
				mav.addObject("repayments", repayments);
			}
		}
		return mav;
	}

	@RequestMapping(value = "/projectProgress*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "projectProgress";
	}

	@RequestMapping(value = "/projectProgress*", method = RequestMethod.POST)
	public String onSubmit(final HttpServletRequest request, final HttpServletResponse response) {
		String method = request.getParameter("method");
		String projectProgresstId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method) || StringUtils.isBlank(projectProgresstId)) {
			saveError(request, getText("errors.repaymentId.required", locale));
		} else {
			final Long projectProgresstID = Long.valueOf(projectProgresstId);
			switch (method) {
			case "Edit":
				String editForm = projectProgressManager.getProgressForm(projectProgresstID);
				Long id = projectProgressManager.getRealId(projectProgresstID);
				return "redirect:/progress/" + editForm + "?id=" + id;
			case "Delete":
				projectProgressManager.remove(projectProgresstID);
				saveMessage(request, getText("projectProgress.delete.successful", locale));
				return "redirect:/projectProgress";
			default:
			}
		}
		return "projectProgress";
	}

	@RequestMapping(value = "/progress/investmentProjectForm*", method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String investmentStatusId = request.getParameter("investmentStatusId");
		String projectProgresstId = request.getParameter("id");
		if (StringUtils.isBlank(projectProgresstId)) {
			// Add
			InvestmentStatus investmentStatus = investmentProjectsManager.get(Long.valueOf(investmentStatusId));
			InvestmentProject investmentProject = new InvestmentProject();
			investmentProject.setInvestmentStatus(investmentStatus);
			investmentProject.setInvestmentProjectType(investmentStatus.getProjectType());
			mav.addObject("investmentProject", investmentProject);
		} else {
			// Edit
			Long id = Long.valueOf(projectProgresstId);
			InvestmentProject investmentProject = projectProgressManager.get(id);
			mav.addObject("investmentProject", investmentProject);
		}
		mav.setViewName("/progress/investmentProjectForm");
		return mav;
	}

	@RequestMapping(value = "/progress/investmentProjectForm*", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("investmentProject") final InvestmentProject investmentProject,
			@ModelAttribute("projectInfoId") final Long projectInfoId, final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)) {
			saveError(request, getText("errors.token", locale));
			return "";
		} else {
			if (validator != null) { // validator is null during testing
				validator.validate(investmentProject, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..." + errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveprojectProgress":
				Date now = new Date();
				User currentUser = getCurrentUser();
				Long projectProgressId = investmentProject.getId();
				if (projectProgressId == null) {
					// Save new
					String investmentStatusId = request.getParameter("investmentStatusId");
					InvestmentStatus investmentStatus = investmentProjectsManager.get(Long.valueOf(investmentStatusId));
					investmentProject.setInvestmentStatus(investmentStatus);
					investmentProject.setCreateTime(now);
					investmentProject.setCreateUser(currentUser.getUsername());
					investmentStatus.getInvestmentProjects().add(investmentProject);
					investmentProjectsManager.save(investmentStatus);
					if (investmentProject.getSameAsRepayment()) {
						// repaymentProject = new RepaymentProject();
						// repaymentProject.setCreateTime(now);
						// repaymentProject.setUpdateTime(now);
						// repaymentProject.setCreateUser(currentUser.getUsername());
						// repaymentProject.setUpdateUser(currentUser.getUsername());
					}
				} else {
					// Update Existed
					investmentProject.setUpdateTime(now);
					investmentProject.setUpdateUser(currentUser.getUsername());
					projectProgressManager.save(investmentProject);
				}
				// if(repaymentProject != null)
				// {
				// projectProgressManager.saveRepaymentProject(repaymentProject);
				// }

				ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
				updateProjectInfoStatus(projectInfo, true);
				saveMessage(request, getText("investmentProject.save.successful", locale));
				break;
			default:
			}
		}
		return "redirect:/projectProgress";
	}

	@RequestMapping(value = "/progress/repaymentProjectForm*", method = RequestMethod.GET)
	public ModelAndView handleRequestRepaymentProjectForm(final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String projectProgresstId = request.getParameter("id");
		String investmentStatusId = request.getParameter("investmentStatusId");
		if (StringUtils.isBlank(projectProgresstId)) {
			// Add
			InvestmentStatus investmentStatus = investmentProjectsManager.get(Long.valueOf(investmentStatusId));
			RepaymentProject repaymentProject = new RepaymentProject();
			repaymentProject.setInvestmentStatus(investmentStatus);
			mav.addObject("repaymentProject", repaymentProject);
		} else {
			// Edit
			Long id = Long.valueOf(projectProgresstId);
			RepaymentProject repaymentProject = projectProgressManager.getRepaymentProject(id);
			mav.addObject("repaymentProject", repaymentProject);
		}

		mav.setViewName("/progress/repaymentProjectForm");
		return mav;
	}

	@RequestMapping(value = "/progress/repaymentProjectForm*", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("repaymentProject") final RepaymentProject repaymentProject,
			@ModelAttribute("projectInfoId") final Long projectInfoId, final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)) {
			saveError(request, getText("errors.token", locale));
			return "";
		} else {
			if (validator != null) { // validator is null during testing
				validator.validate(repaymentProject, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..." + errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveprojectProgress":				
				User currentUser = getCurrentUser();
				Long projectProgressId = repaymentProject.getId();
				if (projectProgressId == null) {
					// Save new
					String investmentStatusId = request.getParameter("investmentStatusId");
					InvestmentStatus investmentStatus = investmentProjectsManager.get(Long.valueOf(investmentStatusId));
					repaymentProject.setCreateTime(new Date());
					repaymentProject.setCreateUser(currentUser.getUsername());
					repaymentProject.setInvestmentStatus(investmentStatus);
					investmentStatus.getRepaymentProjects().add(repaymentProject);
					investmentProjectsManager.save(investmentStatus);
				} else {
					// Update Existed
					repaymentProject.setUpdateTime(new Date());
					repaymentProject.setUpdateUser(currentUser.getUsername());
					projectProgressManager.saveRepaymentProject(repaymentProject);
				}
				
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				updateProjectInfoStatus(projectInfo, true);
				saveMessage(request, getText("repaymentProject.save.successful", locale));
				break;
			default:
			}
		}
		return "redirect:/projectProgress";
	}

	@RequestMapping(value = "/progress/supplyLiqProjectForm*", method = RequestMethod.GET)
	public ModelAndView handleRequestSupplyLiqProjectForm(final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String projectProgresstId = request.getParameter("id");
		String investmentStatusId = request.getParameter("investmentStatusId");
		if (StringUtils.isBlank(projectProgresstId)) {
			// Add
			InvestmentStatus investmentStatus = investmentProjectsManager.get(Long.valueOf(investmentStatusId));
			SupplyLiquidProject supplyLiquidProject = new SupplyLiquidProject();
			supplyLiquidProject.setInvestmentStatus(investmentStatus);
			mav.addObject("supplyLiquidProject", supplyLiquidProject);
		} else {
			// Edit
			Long id = Long.valueOf(projectProgresstId);
			SupplyLiquidProject supplyLiquidProject = projectProgressManager.getSupplyLiquidProject(id);
			mav.addObject("supplyLiquidProject", supplyLiquidProject);
		}

		mav.setViewName("/progress/supplyLiqProjectForm");
		return mav;
	}

	@RequestMapping(value = "/progress/supplyLiqProjectForm*", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("supplyLiquidProject") final SupplyLiquidProject supplyLiquidProject,
			@ModelAttribute("projectInfoId") final Long projectInfoId, final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)) {
			saveError(request, getText("errors.token", locale));
			return "";
		} else {
			if (validator != null) { // validator is null during testing
				validator.validate(supplyLiquidProject, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..." + errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveprojectProgress":
				User currentUser = getCurrentUser();
				Long projectProgressId = supplyLiquidProject.getId();
				
				if (projectProgressId == null) {
					// Save new
					String investmentStatusId = request.getParameter("investmentStatusId");
					InvestmentStatus investmentStatus = investmentProjectsManager.get(Long.valueOf(investmentStatusId));
					supplyLiquidProject.setCreateTime(new Date());
					supplyLiquidProject.setCreateUser(currentUser.getUsername());
					supplyLiquidProject.setInvestmentStatus(investmentStatus);
					investmentStatus.getSupplyLiquidProjects().add(supplyLiquidProject);
					investmentProjectsManager.save(investmentStatus);
				} else {
					// Update Existed
					supplyLiquidProject.setUpdateTime(new Date());
					supplyLiquidProject.setUpdateUser(currentUser.getUsername());
					projectProgressManager.saveSupplyLiqidProject(supplyLiquidProject);
				}
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				updateProjectInfoStatus(projectInfo, true);
				saveMessage(request, getText("supplyLiquidProject.save.successful", locale));
				break;
			default:
			}
		}
		return "redirect:/projectProgress";
	}

	private void updateProjectInfoStatus(ProjectInfo projectInfo, boolean newStatus) {

		if (projectInfo.getProjectInfoStatus().getProjectProgress() != newStatus) {
			projectInfo.getProjectInfoStatus().setProjectProgress(newStatus);
			projectInfoManager.save(projectInfo);
		}
	}

}
