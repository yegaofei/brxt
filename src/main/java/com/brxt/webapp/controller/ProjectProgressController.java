package com.brxt.webapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectProgress;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;

@Controller
public class ProjectProgressController extends BaseFormController {

	private ProjectInfoManager projectInfoManager;
	private ProjProgressManager projectProgressManager;

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Autowired
	public void setProjectProgressManager(
			@Qualifier("projectProgressManager") ProjProgressManager projectProgressManager) {
		this.projectProgressManager = projectProgressManager;
	}

	@ModelAttribute("projectInfoId")
	protected Long getProjectInfoId(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} else {
			return Long.valueOf(projectInfoId);
		}
		return null;
	}

	@ModelAttribute("progressList")
	public List<ProjectProgress> getProjectProgressList(
			final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} else {
			return projectProgressManager.getProjectProgressList(Long
					.valueOf(projectInfoId));
		}
		return null;
	}

	@RequestMapping(value = "/projectProgress*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "projectProgress";
	}

	@RequestMapping(value = "/projectProgress*", method = RequestMethod.POST)
	public String onSubmit(final HttpServletRequest request,
			final HttpServletResponse response) {
		String method = request.getParameter("method");
		String projectProgresstId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)
				|| StringUtils.isBlank(projectProgresstId)) {
			saveError(request, getText("errors.repaymentId.required", locale));
		} else {
			final Long projectProgresstID = Long.valueOf(projectProgresstId);
			switch (method) {
			case "Edit":
				String editForm = projectProgressManager
						.getProgressForm(projectProgresstID);
				Long id = projectProgressManager.getRealId(projectProgresstID);
				return "redirect:/progress/" + editForm + "?id=" + id;
			case "Delete":
				projectProgressManager.remove(projectProgresstID);
				saveMessage(request,
						getText("projectProgress.delete.successful", locale));
				return "redirect:/projectProgress";
			default:
			}
		}
		return "projectProgress";
	}

	@RequestMapping(value = "/progress/investmentProjectForm*", method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String projectProgresstId = request.getParameter("id");
		String type = request.getParameter("type");
		if (StringUtils.isBlank(projectProgresstId)) {
			// Add
			InvestmentProject investmentProject = new InvestmentProject();
			if (!StringUtils.isBlank(type)) {
				investmentProject.setInvestmentProjectType(type);
				investmentProject.setType(CapitalInvestmentType.valueOf(type
						.toUpperCase()));
			} else {
				investmentProject.setType(CapitalInvestmentType.REAL_ESTATE);
				investmentProject
						.setInvestmentProjectType(CapitalInvestmentType.REAL_ESTATE
								.getTitle());
			}
			mav.addObject("investmentProject", investmentProject);
		} else {
			// Edit
			Long id = Long.valueOf(projectProgresstId);
			InvestmentProject investmentProject = projectProgressManager
					.get(id);
			mav.addObject("investmentProject", investmentProject);
		}

		mav.setViewName("/progress/investmentProjectForm");
		return mav;
	}

	@RequestMapping(value = "/progress/investmentProjectForm*", method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("investmentProject") final InvestmentProject investmentProject,
			@ModelAttribute("projectInfoId") final Long projectInfoId,
			final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)) {
			saveError(request, getText("errors.token", locale));
			return "";
		} else {
			if (validator != null) { // validator is null during testing
				validator.validate(investmentProject, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveprojectProgress":
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				investmentProject.setProjectInfo(projectInfo);
				RepaymentProject repaymentProject = null;
				
				User currentUser = getCurrentUser();
				Long projectProgressId = investmentProject.getId();
				if(projectProgressId == null)
				{
					// Save new  
					Date now = new Date();
					investmentProject.setCreateTime(now);
					investmentProject.setUpdateTime(now);
					investmentProject.setCreateUser(currentUser);
					investmentProject.setUpdateUser(currentUser);
					
					if(investmentProject.getSameAsRepayment())
					{
						repaymentProject = new RepaymentProject();
						repaymentProject.setProjectInfo(projectInfo);
						repaymentProject.setName(investmentProject.getName());
						repaymentProject.setCreateTime(now);
						repaymentProject.setUpdateTime(now);
						repaymentProject.setCreateUser(currentUser);
						repaymentProject.setUpdateUser(currentUser);
					}
				}
				else
				{
					// Update Existed
					User createUser = getUserManager().getUserByUsername(investmentProject.getCreateUser().getUsername());
					investmentProject.setCreateUser(createUser);
					investmentProject.setUpdateTime(new Date());
					investmentProject.setUpdateUser(currentUser);
				}
				projectProgressManager.save(investmentProject);
				if(repaymentProject != null)
				{
					projectProgressManager.saveRepaymentProject(repaymentProject);
				}
				saveMessage(request,
						getText("investmentProject.save.successful", locale));
				break;
			default:
			}
		}
		return "redirect:/projectProgress";
	}

	@RequestMapping(value = "/progress/repaymentProjectForm*", method = RequestMethod.GET)
	public ModelAndView handleRequestRepaymentProjectForm(
			final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String projectProgresstId = request.getParameter("id");
		if (StringUtils.isBlank(projectProgresstId)) {
			// Add
			mav.addObject("repaymentProject", new RepaymentProject());
		} else {
			// Edit
			Long id = Long.valueOf(projectProgresstId);
			RepaymentProject repaymentProject = projectProgressManager
					.getRepaymentProject(id);
			mav.addObject("repaymentProject", repaymentProject);
		}

		mav.setViewName("/progress/repaymentProjectForm");
		return mav;
	}
	
	@RequestMapping(value = "/progress/repaymentProjectForm*", method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("repaymentProject") final RepaymentProject repaymentProject,
			@ModelAttribute("projectInfoId") final Long projectInfoId,
			final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)) {
			saveError(request, getText("errors.token", locale));
			return "";
		} else {
			if (validator != null) { // validator is null during testing
				validator.validate(repaymentProject, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveprojectProgress":
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				repaymentProject.setProjectInfo(projectInfo);
				User currentUser = getCurrentUser();
				Long projectProgressId = repaymentProject.getId();
				if(projectProgressId == null)
				{
					// Save new  
					repaymentProject.setCreateTime(new Date());
					repaymentProject.setUpdateTime(new Date());
					repaymentProject.setCreateUser(currentUser);
					repaymentProject.setUpdateUser(currentUser);
				}
				else
				{
					// Update Existed
					User createUser = getUserManager().getUserByUsername(repaymentProject.getCreateUser().getUsername());
					repaymentProject.setCreateUser(createUser);
					repaymentProject.setUpdateTime(new Date());
					repaymentProject.setUpdateUser(currentUser);
				}
				projectProgressManager.saveRepaymentProject(repaymentProject);
				saveMessage(request,
						getText("repaymentProject.save.successful", locale));
				break;
			default:
			}
		}
		return "redirect:/projectProgress";
	}

	@RequestMapping(value = "/progress/supplyLiqProjectForm*", method = RequestMethod.GET)
	public ModelAndView handleRequestSupplyLiqProjectForm(
			final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String projectProgresstId = request.getParameter("id");
		if (StringUtils.isBlank(projectProgresstId)) {
			// Add
			mav.addObject("supplyLiquidProject", new SupplyLiquidProject());
		} else {
			// Edit
			Long id = Long.valueOf(projectProgresstId);
			SupplyLiquidProject supplyLiquidProject = projectProgressManager
					.getSupplyLiquidProject(id);
			mav.addObject("supplyLiquidProject", supplyLiquidProject);
		}

		mav.setViewName("/progress/supplyLiqProjectForm");
		return mav;
	}
	
	@RequestMapping(value = "/progress/supplyLiqProjectForm*", method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("supplyLiquidProject") final SupplyLiquidProject supplyLiquidProject,
			@ModelAttribute("projectInfoId") final Long projectInfoId,
			final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)) {
			saveError(request, getText("errors.token", locale));
			return "";
		} else {
			if (validator != null) { // validator is null during testing
				validator.validate(supplyLiquidProject, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveprojectProgress":
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				supplyLiquidProject.setProjectInfo(projectInfo);
				User currentUser = getCurrentUser();
				Long projectProgressId = supplyLiquidProject.getId();
				if(projectProgressId == null)
				{
					// Save new  
					supplyLiquidProject.setCreateTime(new Date());
					supplyLiquidProject.setUpdateTime(new Date());
					supplyLiquidProject.setCreateUser(currentUser);
					supplyLiquidProject.setUpdateUser(currentUser);
				}
				else
				{
					// Update Existed
					User createUser = getUserManager().getUserByUsername(supplyLiquidProject.getCreateUser().getUsername());
					supplyLiquidProject.setCreateUser(createUser);
					supplyLiquidProject.setUpdateTime(new Date());
					supplyLiquidProject.setUpdateUser(currentUser);
				}
				projectProgressManager.saveSupplyLiqidProject(supplyLiquidProject);
				saveMessage(request,
						getText("supplyLiquidProject.save.successful", locale));
				break;
			default:
			}
		}
		return "redirect:/projectProgress";
	}

}
