package com.brxt.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.SubjectCapacityManager;
import com.brxt.webapp.util.RequestUtil;

@Controller
@RequestMapping("/subjectCapacityForm*")
public class SubjectCapacityFormController extends BaseFormController {

	private SubjectCapacityManager subjectCapacityManager;
	private ProjectInfoManager projectInfoManager;
	private SimpleDateFormat checkTimeDateFormat = new SimpleDateFormat(
			"yyyy-MM");

	@Autowired
	public void setSubjectCapacityManager(
			@Qualifier("subjectCapacityManager") SubjectCapacityManager subjectCapacityManager) {
		this.subjectCapacityManager = subjectCapacityManager;
	}

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Override
	@InitBinder
	protected void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
		binder.registerCustomEditor(Date.class, "checkTime",
				new CustomDateEditor(checkTimeDateFormat, true));
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("subjectCapacity") final SubjectCapacity subjectCapacity,
			@ModelAttribute("projectInfoId") final Long projectInfoId,
			@ModelAttribute("counterpartyId") final Long counterpartyId,
			final BindingResult errors, final HttpServletRequest request) {
		String method = request.getParameter("method");
		String returnView = "redirect:/subjectCapacity";
		final Locale locale = request.getLocale();
		if (!StringUtils.isBlank(method)) {
			if (validator != null) { // validator is null during testing
				validator.validate(subjectCapacity, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}

			switch (method) {
			case "SaveSubjectCapacity":
				User currentUser = getCurrentUser();
				Long subjectCapacityId = subjectCapacity.getId();
				ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
				subjectCapacity.setProjectInfo(projectInfo);
				//Lookup the underlying counterparty
				Set<Counterparty> cp = projectInfo.getCounterparties();
				Iterator<Counterparty> cpIt = cp.iterator();
				while(cpIt.hasNext())
				{
					Counterparty counterparty = cpIt.next();
					if(counterparty.getId() == counterpartyId)
					{
						subjectCapacity.setCounterparty(counterparty);
						break;
					}
				}
				
				if (subjectCapacityId == null) {
					// Save new subject capacity
					subjectCapacity.setCreateTime(new Date());
					subjectCapacity.setUpdateTime(new Date());
					subjectCapacity.setCreateUser(currentUser);
					subjectCapacity.setUpdateUser(currentUser);
				} else {
					// Update Existed
					User createUser = getUserManager().getUserByUsername(subjectCapacity.getCreateUser().getUsername());
					subjectCapacity.setCreateUser(createUser);
					subjectCapacity.setUpdateTime(new Date());
					subjectCapacity.setUpdateUser(currentUser);
				}
				subjectCapacityManager.save(subjectCapacity);
				saveMessage(request,
						getText("subjectCapacity.save.successful", locale));
				break;
			case "Cancel":
				break;
			default:
			}
		} else {
			saveError(request, getText("errors.token", locale));
			returnView = "";
		}
		return returnView;
	}

	@ModelAttribute("projectInfoId")
	protected Long getProjectInfoId(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} 
		else
		{
			return Long.valueOf(projectInfoId);			
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		List<Counterparty> counterparties = null;
		final Locale locale = request.getLocale();
		Long projectInfoId = getProjectInfoId(request);
		if (projectInfoId == null) {
			mav.setViewName(getCancelView());
			return mav;
		} else {
			counterparties = getCounterparties(projectInfoId);
		}

		if (counterparties == null) {
			saveError(request, getText("errors.counterparty.required", locale));
			mav.setViewName(getCancelView());
			return mav;
		}

		String subjectCapacityId = request.getParameter("id");
		if (!StringUtils.isBlank(subjectCapacityId)) {
			// Edit
			SubjectCapacity subjectCapacity = subjectCapacityManager.get(Long
					.valueOf(subjectCapacityId));
			mav.addObject("subjectCapacity", subjectCapacity);
		} else {
			// Add
			mav.addObject("subjectCapacity", getNewSubjectCapacity());
		}

		mav.addObject("counterparties", counterparties);
		return mav;
	}

	private List<Counterparty> getCounterparties(Long projectInfoId) {
		ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
		Set<Counterparty> counterparties = projectInfo.getCounterparties();
		if (counterparties == null | counterparties.isEmpty()) {
			return null;
		}
		List<Counterparty> cpList = new ArrayList<Counterparty>(counterparties);
		return cpList;
	}

	private SubjectCapacity getNewSubjectCapacity() {
		SubjectCapacity subjectCapacity = new SubjectCapacity();
		subjectCapacity.setBizScopeChanged(false);
		subjectCapacity.setCapitalChanged(false);
		subjectCapacity.setDevCapacityChanged(false);
		subjectCapacity.setLoanCardValid(false);
		subjectCapacity.setNameChanged(false);
		subjectCapacity.setOtherBigChanges(false);
		subjectCapacity.setOwnerChanged(false);
		subjectCapacity.setOwnershipChanged(false);

		return subjectCapacity;
	}

}
