package com.brxt.webapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
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

@Controller
@RequestMapping("/subjectCapacityForm*")
public class SubjectCapacityFormController extends BaseFormController {

	private SubjectCapacityManager subjectCapacityManager;
	private ProjectInfoManager projectInfoManager;

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		List<Counterparty> counterparties = null;
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
			mav.setViewName(getCancelView());
			return mav;
		} else {
			counterparties = getCounterparties(Long.valueOf(projectInfoId));
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

	private void addNewSubjectCapacity() {

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
