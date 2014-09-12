package com.brxt.webapp.controller;

import java.util.ArrayList;
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
import org.apache.commons.lang.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.enums.RepaymentType;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.RepaymentManager;

@Controller
public class RepaymentController extends BaseFormController {

	private RepaymentManager repaymentManager;
	private ProjectInfoManager projectInfoManager;

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
	
	@ModelAttribute("repaymentTypes")
	public List<String> getAllRepaymentTypes()
	{
		List<String> repaymentTypes = new ArrayList<String>();
		RepaymentType[] types = RepaymentType.values();
		for(RepaymentType type : types)
		{
			repaymentTypes.add(type.getTitle());
		}
		return repaymentTypes;
	}

	@RequestMapping(value = "/repayment*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "repayment";
	}
	
	@RequestMapping(value = "/repayment*", method = RequestMethod.POST)
	public ModelAndView onSubmit(final HttpServletRequest request, final HttpServletResponse response)
	{
		ModelAndView mav = new ModelAndView();
		String method = request.getParameter("method");
		String repaymentId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if( StringUtils.isBlank(method) || StringUtils.isBlank(repaymentId))
		{
			saveError(request, getText("errors.repaymentId.required", locale));
		}
		else
		{
			switch (method) {
			case "Edit":
				mav.setViewName("redirect:/repaymentForm?id=" + repaymentId);
				break;
			case "Delete":				
				repaymentManager.remove(Long.valueOf(repaymentId));
				saveMessage(request, getText("repayment.delete.successful", locale));
				mav.setViewName("redirect:/repayment");
				break;
				default:
			}
		}
		return mav;
	}

	@ModelAttribute("repaymentList")
	public List<Repayment> getRepaymentList(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		List<Repayment> ciList = null;
		if (!StringUtils.isBlank(projectInfoId)) {
			ciList = repaymentManager.findByProjId(Long.valueOf(projectInfoId));
		}
		return ciList;
	}

	@ModelAttribute
	public Repayment getRepayment(final HttpServletRequest request) {
		String repaymentId = request.getParameter("id");
		Repayment repayment = null;
		if (!StringUtils.isBlank(repaymentId)) {
			// Edit
			repayment = repaymentManager.get(Long.valueOf(repaymentId));
		} else {
			// Add
			repayment = new Repayment();
		}
		return repayment;
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

	@RequestMapping(value = "/repaymentForm*", method = RequestMethod.GET)
	public String showForm(final HttpServletRequest request) throws Exception {
		return "repaymentForm";
	}

	@RequestMapping(value = "/repaymentForm*", method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("repayment") final Repayment repayment,
			@ModelAttribute("projectInfoId") final Long projectInfoId,
			final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (!StringUtils.isBlank(method)) {
			if (validator != null) { // validator is null during testing
				validator.validate(repayment, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveRepayment":
				User currentUser = getCurrentUser();
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				repayment.setProjectInfo(projectInfo);
				Long repaymentId = repayment.getId();
				if (repaymentId == null) {
					// Save new subject capacity
					repayment.setCreateTime(new Date());
					repayment.setUpdateTime(new Date());
					repayment.setCreateUser(currentUser);
					repayment.setUpdateUser(currentUser);
				} else {
					// Update Existed
					User createUser = getUserManager().getUserByUsername(repayment.getCreateUser().getUsername());
					repayment.setCreateUser(createUser);
					repayment.setUpdateTime(new Date());
					repayment.setUpdateUser(currentUser);
				}
				repaymentManager.save(repayment);
				if(!projectInfo.getProjectInfoStatus().getRepayment())
				{
					projectInfo.getProjectInfoStatus().setRepayment(true);
					projectInfoManager.save(projectInfo);
				}
				saveMessage(request,
						getText("repayment.save.successful", locale));
				break;
				default:
			}
		} else {
			saveError(request, getText("errors.token", locale));
			return "";
		}
		
		return "redirect:/repayment";
	}

}
