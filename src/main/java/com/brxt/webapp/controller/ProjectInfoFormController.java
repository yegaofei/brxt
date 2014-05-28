package com.brxt.webapp.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/projectInfoForm*")
public class ProjectInfoFormController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public ProjectInfoFormController() {
		setCancelView("redirect:projectInfo");
		setSuccessView("redirect:projectInfo");
	}

	@ModelAttribute
	@RequestMapping(method = RequestMethod.GET)
	protected ProjectInfo showForm(HttpServletRequest request) throws Exception {
		String id = request.getParameter("id");

		if (!StringUtils.isBlank(id)) {
			return projectInfoManager.get(new Long(id));
		}

		return new ProjectInfo();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(ProjectInfo projectInfo, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getParameter("cancel") != null) {
			return new ModelAndView(getCancelView());
		}

		if (validator != null) { // validator is null during testing
			validator.validate(projectInfo, errors);

			if (errors.hasErrors() && request.getParameter("delete") == null) { // don't
																				// validate
																				// when
																				// deleting
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				return new ModelAndView("projectInfoForm");
			}
		}

		log.debug("entering 'onSubmit' method...");

		boolean isNew = (projectInfo.getId() == null);
		String success = getSuccessView();
		Locale locale = request.getLocale();
		User currentUser = null;
		final Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			currentUser = getCurrentUser(auth, getUserManager());
		}
		ModelAndView mav = new ModelAndView(success);
		if (request.getParameter("delete") != null) {
			projectInfoManager.remove(projectInfo.getId());
			saveMessage(request, getText("projectInfo.deleted", locale));
		} else {
			if (isNew) {
				projectInfo.setCreateUser(currentUser);
				projectInfo.setCreateTime(new Date());
			} else {
				// Update
				List<ProjectSize> projectSize = onProjectSizeSubmit(projectInfo, request, mav);

				ProjectInfo pi = projectInfoManager.get(projectInfo.getId());
				User createUser = pi.getCreateUser();
				Date createTime = pi.getCreateTime();

				projectInfo.setCreateUser(createUser);
				projectInfo.setCreateTime(createTime);
				projectInfo.setUpdateUser(currentUser);
				projectInfo.setUpdateTime(new Date());
				if (projectSize != null) {
					for(ProjectSize ps : projectSize)
					{
						ps.setProjectInfo(projectInfo);
						projectInfo.getProjectSizes().add(ps);
					}
				}
			}
			projectInfoManager.save(projectInfo);
			mav.addObject("ProjectInfo", projectInfo);
			String key = (isNew) ? "projectInfo.added" : "projectInfo.updated";
			saveMessage(request, getText(key, locale));
		}

		return mav;
	}

	private User getCurrentUser(Authentication auth, UserManager userManager) {
		User currentUser;
		if (auth.getPrincipal() instanceof LdapUserDetails) {
			LdapUserDetails ldapDetails = (LdapUserDetails) auth.getPrincipal();
			String username = ldapDetails.getUsername();
			currentUser = userManager.getUserByUsername(username);
		} else if (auth.getPrincipal() instanceof UserDetails) {
			currentUser = (User) auth.getPrincipal();
		} else if (auth.getDetails() instanceof UserDetails) {
			currentUser = (User) auth.getDetails();
		} else {
			throw new AccessDeniedException("User not properly authenticated.");
		}
		return currentUser;
	}

	private List<ProjectSize> onProjectSizeSubmit(final ProjectInfo projectInfo,
			HttpServletRequest request, ModelAndView mav) throws Exception {
		String method = request.getParameter("method");
		String id = request.getParameter("projectSizeid");
		mav.addObject("projectInfoId", projectInfo.getId());
//		List<ProjectSize> projectSizeList = projectInfoManager
//				.getAllProjectSize(projectInfo.getId());
		List<ProjectSize> projectSizeList = projectInfo.getProjectSizes();
		if(projectSizeList == null)
		{
			log.error("The project size list is empty ====>");
		}
		
		if(method != null)
		{
			switch (method) {
			case "AddProjectSize":
				projectSizeList.add(new ProjectSize());
				// mav.addObject("projectSizeList", projectSizeList);
				mav.addObject("method", "EditProjectSize");
				mav.setViewName("projectInfoForm");
				break;
			case "EditProjectSize":
				// mav.addObject("projectSizeList", projectSizeList);
				mav.addObject("method", "EditProjectSize");
				mav.setViewName("projectInfoForm");
				break;
			case "DeleteProjectSize":
				projectSizeList = deleteProjectSize(id, projectInfo);
				// mav.addObject("projectSizeList", projectSizeList);
				mav.addObject("method", "SaveProjectSize");
				break;
			case "SaveProjectSize":
				// how many records have been submitted
				log.debug("submit project size id: " + id);
				String[] idArray = request.getParameterValues("id");
				if (idArray != null && idArray.length > 0 && !"".equals(idArray[0])) {
					StringBuilder sb = new StringBuilder(
							"submit project size ids: ");
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
				if (StringUtils.isBlank(id)) {
					if (idArray != null && idArray.length > 0
							&& !"".equals(idArray[0])) {
						String[] projectSizeArray = request
								.getParameterValues("projectSize");
						String[] startTimeArray = request
								.getParameterValues("sartTime");
						String[] endTimeArray = request
								.getParameterValues("endTime");
						// batch update or add
					} else {
						projectSizeList = saveProjectSize(id, startTime,
								projectSize, endTime, projectInfo);
					}
				} else {
					projectSizeList = saveProjectSize(id, startTime, projectSize,
							endTime, projectInfo);
				}
				// mav.addObject("projectSizeList", projectSizeList);
				mav.addObject("method", "SaveProjectSize");
				break;
			default:
			}
		}
		return projectSizeList;
	}

	private List<ProjectSize> deleteProjectSize(String id, ProjectInfo projectInfo) {
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

	private List<ProjectSize> saveProjectSize(String id, String startTime,
			String projectSize, String endTime, ProjectInfo projectInfo)
			throws ParseException {
		//ProjectInfo pi = projectInfoManager.get(projectInfoId);
		List<ProjectSize> list = projectInfo.getProjectSizes();
		if (list == null) {
			log.debug("==========================>> ProjectSize is null");
			list = new ArrayList<ProjectSize>();
			projectInfo.setProjectSizes(list);
		}
		if (!StringUtils.isBlank(id)) {
			for (int i = 0; i < list.size(); i++) {
				ProjectSize ps = list.get(i);
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
				list.add(projectSizeObj);
			} else {
				return list;
			}
		}
		//projectInfoManager.save(pi);
		return list;
	}
}
