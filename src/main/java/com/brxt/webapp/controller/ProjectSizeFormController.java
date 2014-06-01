package com.brxt.webapp.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/projectSizeForm*")
public class ProjectSizeFormController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public ProjectSizeFormController() {
		setCancelView("redirect:projectInfoForm");
		setSuccessView("redirect:projectInfoForm");
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request)
			throws Exception {
		String projectInfoId = request.getParameter("projectInfoId");
		List<ProjectSize> projectSizeList = null;
		ModelAndView mav = new ModelAndView();
		if (!StringUtils.isBlank(projectInfoId)) {
			projectSizeList = projectInfoManager.getAllProjectSize(Long
					.valueOf(projectInfoId));
			if (projectSizeList == null) {
				projectSizeList = new ArrayList<ProjectSize>();
			}
			mav.addObject("projectInfoId", projectInfoId);
			mav.addObject("projectSizeList", projectSizeList);
		} else {
			final Locale locale = request.getLocale();
			saveError(request, getText("errors.cancel", locale));
			mav.setViewName("projectInfoForm");
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(
			@RequestParam(value = "projectInfoId", required = true) final Long projectInfoId,
			HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView();
		mav.addObject("projectInfoId", projectInfoId);
		List<ProjectSize> projectSizeList = projectInfoManager
				.getAllProjectSize(Long.valueOf(projectInfoId));
		switch (method) {
		case "Add":
			projectSizeList.add(new ProjectSize());
			mav.addObject("projectSizeList", projectSizeList);
			mav.addObject("method", "Edit");
			break;
		case "Edit":
			mav.addObject("projectSizeList", projectSizeList);
			mav.addObject("method", "Edit");
			break;
		case "Delete":
			projectSizeList = delete(id, projectInfoId);
			mav.addObject("projectSizeList", projectSizeList);
			mav.addObject("method", "Save");
			break;
		case "Save":
			// how many records have been submitted
			log.debug("submit project size id: " + id);
			String[] idArray = request.getParameterValues("id");
			if(idArray != null && idArray.length > 0 && !"".equals(idArray[0]))
			{
				StringBuilder sb = new StringBuilder("submit project size ids: ");
				for(String idStr : idArray){
					sb.append(idStr).append(",");
				}
				log.debug(sb);
			}
			String projectSize = request.getParameter("projectSize");
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			log.debug("projectSize=" + projectSize + ", startTime=" + startTime + ", endTime=" + endTime);
			if (StringUtils.isBlank(id)) {
				if (idArray != null && idArray.length > 0 && !"".equals(idArray[0])) {
					String[] projectSizeArray = request
							.getParameterValues("projectSize");
					String[] startTimeArray = request
							.getParameterValues("sartTime");
					String[] endTimeArray = request
							.getParameterValues("endTime");
					// batch update or add
				} else {
					projectSizeList = save(id, startTime, projectSize, endTime, projectInfoId);
				}
			} else {
				projectSizeList = save(id, startTime, projectSize, endTime, projectInfoId);
			}
			mav.addObject("projectSizeList", projectSizeList);
			mav.addObject("method", "Save");
			break;
		default:
		}
		return mav;
	}
	
	private List<ProjectSize> delete(String id, Long projectInfoId){
		ProjectInfo pi = projectInfoManager.get(projectInfoId);
		List<ProjectSize> list = pi.getProjectSizes();
		if(StringUtils.isBlank(id) || list == null || list.size() == 0)
		{
			return null;
		}
		
		Iterator<ProjectSize> it = list.iterator();
		while(it.hasNext())
		{
			ProjectSize ps = it.next();
			if(ps.getId() == Long.valueOf(id))
			{
				it.remove();
				ps.setProjectInfo(null);
				projectInfoManager.deleteProjectSize(ps);
				break;
			}
		}
		return list;
	}

	private List<ProjectSize> save(String id, String startTime, String projectSize,
			String endTime, Long projectInfoId) throws ParseException {
		ProjectInfo pi = projectInfoManager.get(projectInfoId);
		List<ProjectSize> list = pi.getProjectSizes();
		if (list == null) {
			list = new ArrayList<ProjectSize>();
			pi.setProjectSizes(list);
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
			if(!StringUtils.isBlank(startTime) || !StringUtils.isBlank(projectSize) || !StringUtils.isBlank(endTime))
			{
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
				projectSizeObj.setProjectInfo(pi);
				list.add(projectSizeObj);				
			}
			else 
			{
				return list;
			}
		}
		projectInfoManager.save(pi);
		return list;
	}
}
