package com.brxt.webapp.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.ProjectType;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/projectInfoForm*")
public class ProjectInfoFormController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static final Map<String,String> CapitalInvestmentTypes = new HashMap<String,String>();
	private static final Map<String,String> ProjectTypes = new HashMap<String,String>();
	private static final List<CounterpartyType> CounterpartyTypes = new ArrayList<CounterpartyType>();
	
	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public ProjectInfoFormController() {
		setCancelView("redirect:projectInfo");
		setSuccessView("redirect:projectInfo");
	}
	
	private synchronized void loadDropDownList(final Locale locale)
	{
		if(CapitalInvestmentTypes.isEmpty())
		{
			CapitalInvestmentType[] cits = CapitalInvestmentType.values();
			for(CapitalInvestmentType cit : cits)
			{
				CapitalInvestmentTypes.put(cit.toString(), getText(cit.toString(), locale));
			}
		}
		
		if(ProjectTypes.isEmpty())
		{
			ProjectType[] pts = ProjectType.values();
			for(ProjectType pt : pts)
			{
				ProjectTypes.put(pt.toString(), getText(pt.toString(), locale));
			}
		}
		
		if(CounterpartyTypes.isEmpty())
		{
			CounterpartyType[] cts = CounterpartyType.values();
			for(CounterpartyType ct : cts)
			{
				CounterpartyTypes.add(ct);
			}
		}
	}
	
	private ProjectInfo getProjectInfo(HttpServletRequest request){
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			ProjectInfo pi = projectInfoManager.get(new Long(id));			
			return pi;		
		}
		return new ProjectInfo();
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		if(CapitalInvestmentTypes.isEmpty() || ProjectTypes.isEmpty())
		{
			loadDropDownList(request.getLocale());
		}
		mav.addObject("capitalInvestmentTypes", CapitalInvestmentTypes);
		mav.addObject("projectTypes", ProjectTypes);
		mav.addObject("counterpartyTypes", CounterpartyTypes);
		mav.addObject("projectInfo", getProjectInfo(request));	
		request.getSession().setAttribute(SessionAttributes.PROJECT_INFO_ID, request.getParameter("id"));
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(ProjectInfo projectInfo, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (method != null) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("capitalInvestmentTypes", CapitalInvestmentTypes);
			mav.addObject("projectTypes", ProjectTypes);
			mav.addObject("counterpartyTypes", CounterpartyTypes);
			switch (method) {
			case "Cancel":
				mav.setViewName(getCancelView());
				break;
			case "DeleteProjectInfo":
				projectInfoManager.remove(projectInfo.getId());
				saveMessage(request, getText("projectInfo.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "SaveProjectInfo":
				mav = saveProjectInfo(projectInfo, errors, request, mav);
				break;
			case "AddProjectSize":
				projectInfo = getProjectInfo(request);
				projectInfo.getProjectSizes().add(new ProjectSize());
				mav.addObject("projectInfo", projectInfo);
				mav.addObject("method", "EditProjectSize");
				mav.setViewName("projectInfoForm");
				break;
			case "EditProjectSize":
				mav.addObject("projectInfo", getProjectInfo(request));
				mav.addObject("method", "EditProjectSize");
				mav.setViewName("projectInfoForm");
				break;
			case "DeleteProjectSize":
				String projectSizeid = request.getParameter("projectSizeid");
				projectInfo = getProjectInfo(request);
				deleteProjectSize(projectSizeid, projectInfo);
				mav.addObject("method", "SaveProjectSize");
				mav.addObject("projectInfo", projectInfo);
				break;
			case "SaveProjectSize":
				projectInfo = getProjectInfo(request);
				mav = saveProjectSize(projectInfo, errors, request, mav);
				mav.addObject("method", "SaveProjectSize");
				mav.addObject("projectInfo", projectInfo);
				break;
			default:
				//Error
			}
			return mav;
		} else {
			// error
		}
		return new ModelAndView("projectInfoForm");
	}
	
	private ModelAndView saveProjectInfo(ProjectInfo projectInfo,
			BindingResult errors, HttpServletRequest request, ModelAndView mav)
			throws Exception {
		final Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(projectInfo, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				mav.setViewName("projectInfoForm");
				return mav;
			}
		}

		boolean isNew = (projectInfo.getId() == null);
		User currentUser = getCurrentUser();
		if (isNew) {
			// Add
			projectInfo.setCreateUser(currentUser);
			projectInfo.setCreateTime(new Date());
		} else {
			User createUser = getUserManager().getUserByUsername(projectInfo.getCreateUser().getUsername());
			projectInfo.setCreateUser(createUser);
			projectInfo.setUpdateUser(currentUser);
			projectInfo.setUpdateTime(new Date());
		}
		projectInfoManager.save(projectInfo);
		mav.addObject("ProjectInfo", projectInfo);
		String key = (isNew) ? "projectInfo.added" : "projectInfo.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName(getSuccessView());
		return mav;
	}

	private ModelAndView saveProjectSize(ProjectInfo projectInfo,
			BindingResult errors, HttpServletRequest request,
			final ModelAndView mav) throws Exception {
		String id = request.getParameter("projectSizeid");
		log.debug("submit project size id: " + id);
		String[] idArray = request.getParameterValues("prjectSizeid");
		if (idArray != null && idArray.length > 0 && !"".equals(idArray[0])) {
			StringBuilder sb = new StringBuilder("submit project size ids: ");
			for (String idStr : idArray) {
				sb.append(idStr).append(",");
			}
			log.debug(sb);
		}
		String projectSize = request.getParameter("projectSize");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		log.debug("projectSize=" + projectSize + ", startTime=" + startTime
				+ ", endTime=" + endTime);

		if (!StringUtils.isBlank(id)) {
			for (int i = 0; i < projectInfo.getProjectSizes().size(); i++) {
				ProjectSize ps = projectInfo.getProjectSizes().get(i);
				if (ps.getId() == Long.valueOf(id)) {
					// Update
					if (startTime != null) {
						ps.setStartTime(sdf.parse(startTime));
					}

					if (projectSize != null) {
						ps.setProjectSize(new BigDecimal(projectSize));
					}

					if (endTime != null) {
						ps.setEndTime(sdf.parse(endTime));
					}
				}
			}
		} else {
			// Add
			if (!StringUtils.isBlank(startTime)
					|| !StringUtils.isBlank(projectSize)
					|| !StringUtils.isBlank(endTime)) {
				ProjectSize projectSizeObj = new ProjectSize();
				if (!StringUtils.isBlank(startTime)) {
					projectSizeObj.setStartTime(sdf.parse(startTime));
				}

				if (!StringUtils.isBlank(projectSize)) {
					projectSizeObj.setProjectSize(new BigDecimal(projectSize));
				}

				if (!StringUtils.isBlank(endTime)) {
					projectSizeObj.setEndTime(sdf.parse(endTime));
				}
				projectSizeObj.setProjectInfo(projectInfo);
				projectInfo.getProjectSizes().add(projectSizeObj);
			} else {
				// Error
			}
		}
		projectInfoManager.save(projectInfo);
		return mav;
	}

	private List<ProjectSize> deleteProjectSize(String id,
			ProjectInfo projectInfo) {
		List<ProjectSize> list = projectInfo.getProjectSizes();
		if (StringUtils.isBlank(id) || list == null || list.size() == 0) {
			return null;
		}

		Iterator<ProjectSize> it = list.iterator();
		while (it.hasNext()) {
			ProjectSize ps = it.next();
			if (ps.getId() == Long.valueOf(id)) {
				it.remove();
				ps.setProjectInfo(null);
				projectInfoManager.deleteProjectSize(ps);
				break;
			}
		}
		return list;
	}
}
