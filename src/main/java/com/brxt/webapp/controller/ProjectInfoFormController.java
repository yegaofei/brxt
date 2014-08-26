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
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.Counterparty;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.Manager;
import com.brxt.model.ProjectInfo;
import com.brxt.model.ProjectSize;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.ProjectType;
import com.brxt.service.InvestmentProjectsManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/projectInfoForm*")
public class ProjectInfoFormController extends BaseFormController {

	private ProjectInfoManager projectInfoManager = null;
	private static final List<CapitalInvestmentType> CapitalInvestmentTypes = new ArrayList<CapitalInvestmentType>();
	private static final Map<String, String> ProjectTypes = new HashMap<String, String>();
	private static final List<CounterpartyType> CounterpartyTypes = new ArrayList<CounterpartyType>();
	private InvestmentProjectsManager investmentProjectsManager;

	@Autowired
	public void setProjectInfoManager(@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Autowired
	public void setInvestmentProjectsManager(@Qualifier("investmentProjectsManager") InvestmentProjectsManager investmentProjectsManager) {
		this.investmentProjectsManager = investmentProjectsManager;
	}

	public ProjectInfoFormController() {
		setCancelView("redirect:projectInfo");
		setSuccessView("redirect:projectInfo");
	}

	private synchronized void loadDropDownList(final Locale locale) {
		if (CapitalInvestmentTypes.isEmpty()) {
			CapitalInvestmentType[] cits = CapitalInvestmentType.values();
			for (CapitalInvestmentType cit : cits) {
				// if(cit == CapitalInvestmentType.SUPPLEMENTAL_LIQUIDITY){
				// continue;
				// }
				CapitalInvestmentTypes.add(cit);
			}
		}

		if (ProjectTypes.isEmpty()) {
			ProjectType[] pts = ProjectType.values();
			for (ProjectType pt : pts) {
				ProjectTypes.put(pt.toString(), getText(pt.toString(), locale));
			}
		}

		if (CounterpartyTypes.isEmpty()) {
			CounterpartyType[] cts = CounterpartyType.values();
			for (CounterpartyType ct : cts) {
				CounterpartyTypes.add(ct);
			}
		}
	}

	@ModelAttribute
	public ProjectInfo getProjectInfo(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			return loadProjectInfo(new Long(id));
		}
		return new ProjectInfo();
	}

	private ProjectInfo loadProjectInfo(Long id) {
		return projectInfoManager.loadProjectInfo(new Long(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		if (CapitalInvestmentTypes.isEmpty() || ProjectTypes.isEmpty()) {
			loadDropDownList(request.getLocale());
		}
		mav.addObject("capitalInvestmentTypes", CapitalInvestmentTypes);
		mav.addObject("projectTypes", ProjectTypes);
		mav.addObject("counterpartyTypes", CounterpartyTypes);
		request.getSession().setAttribute(SessionAttributes.PROJECT_INFO_ID, request.getParameter("id"));
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, HttpServletResponse response)
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
			case "EditCounterparty":
				mav.addObject("projectInfo", getProjectInfo(request));
				mav.addObject("method", "EditCounterparty");
				mav.addObject("anchor", "counterparty");
				mav.setViewName("projectInfoForm");
				break;
			case "AddCounterparty":
				projectInfo = getProjectInfo(request);
				projectInfo.getCounterparties().add(new Counterparty());
				mav.addObject("projectInfo", projectInfo);
				mav.addObject("method", "EditCounterparty");
				mav.addObject("anchor", "counterparty");
				mav.setViewName("projectInfoForm");
				break;
			case "DeleteCounterparty":
				String counterpartyId = request.getParameter("counterpartyId");
				projectInfo = getProjectInfo(request);
				deleteCounterparty(counterpartyId, projectInfo);
				mav.addObject("method", "SaveCounterparty");
				projectInfo = projectInfoManager.save(projectInfo);
				projectInfo = loadProjectInfo(projectInfo.getId());
				mav.addObject("anchor", "counterparty");
				mav.addObject("projectInfo", projectInfo);
				break;
			case "SaveCounterparty":
				projectInfo = getProjectInfo(request);
				mav = saveCounterparty(projectInfo, errors, request, mav);
				mav.addObject("method", "SaveCounterparty");
				mav.addObject("anchor", "counterparty");
				break;
			case "EditGuarantor":
				mav.addObject("projectInfo", getProjectInfo(request));
				mav.addObject("method", "EditGuarantor");
				mav.addObject("anchor", "guarantor");
				mav.setViewName("projectInfoForm");
				break;
			case "AddGuarantor":
				projectInfo = getProjectInfo(request);
				projectInfo.getGuarantors().add(new Counterparty());
				mav.addObject("projectInfo", projectInfo);
				mav.addObject("method", "EditGuarantor");
				mav.addObject("anchor", "guarantor");
				mav.setViewName("projectInfoForm");
				break;
			case "DeleteGuarantor":
				String guarantorId = request.getParameter("guarantorId");
				projectInfo = getProjectInfo(request);
				deleteGuarantor(guarantorId, projectInfo);
				mav.addObject("method", "SaveGuarantor");
				projectInfo = projectInfoManager.save(projectInfo);
				projectInfo = loadProjectInfo(projectInfo.getId());
				mav.addObject("projectInfo", projectInfo);
				mav.addObject("anchor", "guarantor");
				break;
			case "SaveGuarantor":
				projectInfo = getProjectInfo(request);
				mav = saveGuarantor(projectInfo, errors, request, mav);
				mav.addObject("method", "SaveGuarantor");
				mav.addObject("anchor", "guarantor");
				break;
			case "CommitProjectInfo":
				projectInfo = getProjectInfo(request);
				projectInfo.getProjectInfoStatus().setCommitted(true);
				projectInfoManager.save(projectInfo);
				saveMessage(request, getText("projectInfo.committed", locale));
				mav.setViewName(getSuccessView());
				break;
			case "AddInvestment":
				String investmentId = request.getParameter("investmentId");
				if (StringUtils.isBlank(investmentId)) {
					projectInfo = getProjectInfo(request);
					projectInfo.getInvestments().add(new InvestmentStatus());
					mav.addObject("projectInfo", projectInfo);
					mav.addObject("method", "EditInvestment");
					mav.addObject("anchor", "investment");
				}
				mav.setViewName("projectInfoForm");
				break;
			case "SaveInvestment":
				projectInfo = getProjectInfo(request);
				mav = saveInvestment(projectInfo, errors, request, mav);
				mav.addObject("method", "SaveInvestment");
				mav.addObject("anchor", "investment");
				break;
			case "EditInvestment":
				mav.addObject("projectInfo", getProjectInfo(request));
				mav.addObject("method", "EditInvestment");
				mav.addObject("anchor", "investment");
				mav.setViewName("projectInfoForm");
				break;
			case "DeleteInvestment":
				projectInfo = getProjectInfo(request);
				mav = deleteInvestment(projectInfo, errors, request, mav);
				mav.addObject("method", "SaveInvestment");
				mav.addObject("anchor", "investment");
				break;
			case "CancelInvestment":
			case "CancelProjectSize":
			case "CancelCounterparty":
			case "CancelGuarantor":
				mav.addObject("projectInfo", getProjectInfo(request));
				mav.addObject("method", "");
				break;
			default:
				// Error
			}
			return mav;
		} else {
			// error
		}
		return new ModelAndView("projectInfoForm");
	}

	private ModelAndView saveProjectInfo(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, ModelAndView mav)
			throws Exception {
		final Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(projectInfo, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..." + errors.toString());
				saveMessage(request, errors.toString());
				mav.setViewName("projectInfoForm");
				return mav;
			}
		}

		boolean isNew = (projectInfo.getId() == null);
		User currentUser = getCurrentUser();
		String currentUserName = currentUser == null ? "" : currentUser.getUsername();
		if (isNew) {
			// Add
			projectInfo.setCreateUser(currentUserName);
			projectInfo.setCreateTime(new Date());
			ProjectInfo info = projectInfoManager.findByProjectName(projectInfo.getProjectName());
			if(info != null) {
			    saveError(request, projectInfo.getProjectName() + getText("errors.duplicated", locale));
			    mav.setViewName("redirect:/projectInfoForm");
			    return mav;
			}
			
		} else {
			projectInfo.setUpdateUser(currentUserName);
			projectInfo.setUpdateTime(new Date());
		}
		projectInfo = projectInfoManager.save(projectInfo);
		mav.addObject("ProjectInfo", projectInfo);
		String key = (isNew) ? "projectInfo.added" : "projectInfo.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName("redirect:/projectInfoForm");
		mav.addObject("id", projectInfo.getId());
		return mav;
	}

	private ModelAndView saveCounterparty(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, final ModelAndView mav)
			throws Exception {
		String id = request.getParameter("counterpartyId");
		String name = request.getParameter("counterpartyName");
		String type = request.getParameter("counterpartyType");
		if (StringUtils.isBlank(name)) {
			saveError(request, getText("empty.counterparty.name.error", request.getLocale()));
			return mav;
		}

		if (!StringUtils.isBlank(id)) {			// Edit
			Iterator<Counterparty> cpIt = projectInfo.getCounterparties().iterator();
			while (cpIt.hasNext()) {
				Counterparty cp = cpIt.next();
				if (cp.getId().equals(Long.valueOf(id))) {
					Counterparty tempCp = new Counterparty();
					tempCp.setName(name);
					tempCp.setCounterpartyType(type);
					Counterparty cpInDb = projectInfoManager.findCounterparty(tempCp);
					if (cpInDb != null) { // Existed in DB
						if (projectInfo.getCounterparties().contains(tempCp)) {
							saveError(request, getText("duplicate.counterparty.error", request.getLocale()));
						} else {
							cpIt.remove();
							projectInfo.getCounterparties().add(cpInDb);
							saveMessage(request, getText("projectInfo.counterparty.update.sccess", request.getLocale()));
						}
					} else { // Doesn't exist in DB
						cp.setName(name);
						cp.setCounterpartyType(type);
						projectInfoManager.saveCounterparty(cp);
					}
				}
			}
		} else {			// Add
			Counterparty cpObj = new Counterparty();
			cpObj.setName(name);
			cpObj.setCounterpartyType(type);
			Counterparty cpInDb = projectInfoManager.findCounterparty(cpObj);
			if (cpInDb != null) {
				if (projectInfo.getCounterparties().contains(cpInDb)) {
					saveError(request, getText("duplicate.counterparty.error", request.getLocale()));
				} else {
					projectInfo.getCounterparties().add(cpInDb);
					saveMessage(request, getText("projectInfo.counterparty.update.sccess", request.getLocale()));
				}
			} else {
				cpObj = projectInfoManager.saveCounterparty(cpObj);
				projectInfo.getCounterparties().add(cpObj);
			}
		}
		projectInfo = projectInfoManager.save(projectInfo);
		projectInfo = loadProjectInfo(projectInfo.getId());
		mav.addObject("projectInfo", projectInfo);
		return mav;
	}

	private ModelAndView deleteInvestment(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, final ModelAndView mav)
			throws Exception {
		String id = request.getParameter("investmentId");
		String[] idArray = request.getParameterValues("investmentId");
		if (idArray == null || idArray.length == 0 || "".equals(idArray[0])) {
			if (!StringUtils.isBlank(id)) {
				idArray = new String[] { id };
			}
		}
		if (idArray != null) {
			Set<InvestmentStatus> investmentSet = projectInfo.getInvestments();
			for (String investmentId : idArray) {
				Iterator<InvestmentStatus> itIs = investmentSet.iterator();
				while (itIs.hasNext()) {
					InvestmentStatus investmentStatus = itIs.next();
					if (investmentStatus.getId().equals(Long.valueOf(investmentId))) {
						itIs.remove();
					}
				}
			}
			projectInfoManager.save(projectInfo);
			projectInfo = loadProjectInfo(projectInfo.getId());
		}
		mav.addObject("projectInfo", projectInfo);
		return mav;
	}

	private ModelAndView saveInvestment(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, final ModelAndView mav)
			throws Exception {
		String id = request.getParameter("investmentId");
		String name = request.getParameter("investmentProjectName");
		String type = request.getParameter("investmentType");
		String oldInvestmentType = request.getParameter("oldInvestmentType");
		if (StringUtils.isBlank(name)) {
			saveError(request, getText("projectInfo.investmentName.error.empty", request.getLocale()));
			return mav;
		}
		if (!StringUtils.isBlank(id)) {
			// Edit
			Iterator<InvestmentStatus> isIt = projectInfo.getInvestments().iterator();
			while (isIt.hasNext()) {
				InvestmentStatus is = isIt.next();
				if (is.getId().equals(Long.valueOf(id))) {
					InvestmentStatus tempIs = new InvestmentStatus();
					tempIs.setProjectName(name);
					tempIs.setProjectType(type);
					InvestmentStatus investmentStatus = investmentProjectsManager.findByInvestmentStatus(tempIs);
					if (investmentStatus != null) {
						// if existed in database
						if (projectInfo.getInvestments().contains(tempIs)) {
							saveError(request, getText("projectInfo.investment.update.duplicate", new String[] { name }, request.getLocale()));
						} else {
							isIt.remove();
							projectInfo.getInvestments().add(investmentStatus);
							saveMessage(request, getText("projectInfo.investment.update.sccess", request.getLocale()));
						}
					} else {
						// if doesn't exist in database
						if (type.equals(CapitalInvestmentType.REAL_ESTATE_REPAYMENT_PROJECT.getTitle())) {
							isIt.remove();
							tempIs.setProjectName(name);
							tempIs.setProjectType(CapitalInvestmentType.REAL_ESTATE.getTitle());
							investmentStatus = investmentProjectsManager.findByInvestmentStatus(tempIs);
							if (investmentStatus != null) {
								if (!projectInfo.getInvestments().contains(tempIs)) {
									projectInfo.getInvestments().add(investmentStatus);
								}
							} else {
								tempIs = investmentProjectsManager.save(tempIs);
								projectInfo.getInvestments().add(tempIs);
							}

							tempIs = new InvestmentStatus();
							tempIs.setProjectName(name);
							tempIs.setProjectType(CapitalInvestmentType.REPAYMENT_PROJECT.getTitle());
							investmentStatus = investmentProjectsManager.findByInvestmentStatus(tempIs);
							if (investmentStatus != null) {
								if (!projectInfo.getInvestments().contains(tempIs)) {
									projectInfo.getInvestments().add(investmentStatus);
								}
							} else {
								tempIs = investmentProjectsManager.save(tempIs);
								projectInfo.getInvestments().add(tempIs);
							}
						} else {
							is.setProjectName(name);
							is.setProjectType(type);
							investmentProjectsManager.save(is);
						}
						saveMessage(request, getText("projectInfo.investment.update.sccess", request.getLocale()));
					}
					break;
				}
			}
		} else if (StringUtils.isBlank(oldInvestmentType)) {
			// Add
			if (!StringUtils.isBlank(name) || !StringUtils.isBlank(type)) {
				CapitalInvestmentType capitalInvestmentType = CapitalInvestmentType.valueOf(type.toUpperCase());
				switch (capitalInvestmentType) {
				case REAL_ESTATE:
				case INFRASTRUCTURE:
				case REPAYMENT_PROJECT:
				case SUPPLEMENTAL_LIQUIDITY:
					InvestmentStatus is = new InvestmentStatus();
					is.setProjectName(name);
					is.setProjectType(type);
					if (projectInfo.getInvestments().contains(is)) {
						saveError(request, getText("duplicate.investment.error", request.getLocale()));
					} else {
						addInvestmentStatus(projectInfo, is);
					}
					break;
				case REAL_ESTATE_REPAYMENT_PROJECT:
					is = new InvestmentStatus();
					is.setProjectName(name);
					is.setProjectType(CapitalInvestmentType.REAL_ESTATE.getTitle());
					if (projectInfo.getInvestments().contains(is)) {
						saveError(request, getText("duplicate.investment.error", request.getLocale()));
					} else {
						addInvestmentStatus(projectInfo, is);
					}

					is = new InvestmentStatus();
					is.setProjectName(name);
					is.setProjectType(CapitalInvestmentType.REPAYMENT_PROJECT.getTitle());
					if (projectInfo.getInvestments().contains(is)) {
						saveError(request, getText("duplicate.counterparty.error", request.getLocale()));
					} else {
						addInvestmentStatus(projectInfo, is);
					}
					break;
				default:
				}
			} else {
				// Error
			}
		}
		projectInfo = projectInfoManager.save(projectInfo);
		projectInfo = loadProjectInfo(projectInfo.getId());
		mav.addObject("projectInfo", projectInfo);
		return mav;
	}

	private void addInvestmentStatus(ProjectInfo projectInfo, InvestmentStatus is) {
		InvestmentStatus investmentStatus = investmentProjectsManager.findByInvestmentStatus(is);
		if (investmentStatus != null) {
			projectInfo.getInvestments().add(investmentStatus);
		} else {
			is = investmentProjectsManager.save(is);
			projectInfo.getInvestments().add(is);
		}
	}

	private ModelAndView saveGuarantor(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, final ModelAndView mav)
			throws Exception {
		String id = request.getParameter("guarantorId");
		String name = request.getParameter("guarantorName");
		String type = request.getParameter("guarantorType");
		if (StringUtils.isBlank(name)) {
			saveError(request, getText("empty.guarantor.name.error", request.getLocale()));
			return mav;
		}

		if (!StringUtils.isBlank(id)) { //Edit
			Iterator<Counterparty> cpIt = projectInfo.getGuarantors().iterator();
			while (cpIt.hasNext()) {
				Counterparty cp = cpIt.next();
				if (cp.getId().equals(Long.valueOf(id))) {
					Counterparty tempCp = new Counterparty();
					tempCp.setName(name);
					tempCp.setCounterpartyType(type);
					Counterparty cpInDb = projectInfoManager.findCounterparty(tempCp);
					if (cpInDb != null) { // Existed in DB
						if (projectInfo.getGuarantors().contains(tempCp)) {
							saveError(request, getText("duplicate.guarantor.error", request.getLocale()));
						} else {
							cpIt.remove();
							projectInfo.getGuarantors().add(cpInDb);
							saveMessage(request, getText("projectInfo.guarantor.update.sccess", request.getLocale()));
						}
					} else { // Doesn't exist in DB
						cp.setName(name);
						cp.setCounterpartyType(type);
						projectInfoManager.saveCounterparty(cp);
					}					
				}
			}
		} else {
			// Add
			Counterparty cpObj = new Counterparty();
			cpObj.setName(name);
			cpObj.setCounterpartyType(type);
			Counterparty cpInDb = projectInfoManager.findCounterparty(cpObj);
			if (cpInDb != null) {
				if (projectInfo.getGuarantors().contains(cpInDb)) {
					saveError(request, getText("duplicate.guarantor.error", request.getLocale()));
				} else {
					projectInfo.getGuarantors().add(cpInDb);
					saveMessage(request, getText("projectInfo.guarantor.update.sccess", request.getLocale()));
				}
			} else {
				cpObj = projectInfoManager.saveCounterparty(cpObj);
				projectInfo.getGuarantors().add(cpObj);
			}
		}
		projectInfo = projectInfoManager.save(projectInfo);
		projectInfo = loadProjectInfo(projectInfo.getId());
		mav.addObject("projectInfo", projectInfo);
		return mav;
	}

	private ModelAndView saveProjectSize(ProjectInfo projectInfo, BindingResult errors, HttpServletRequest request, final ModelAndView mav)
			throws Exception {
		String id = request.getParameter("projectSizeid");
		SimpleDateFormat sdf = new SimpleDateFormat(getText("date.format", request.getLocale()));
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
		log.debug("projectSize=" + projectSize + ", startTime=" + startTime + ", endTime=" + endTime);

		if (!StringUtils.isBlank(id)) {
			for (int i = 0; i < projectInfo.getProjectSizes().size(); i++) {
				ProjectSize ps = projectInfo.getProjectSizes().get(i);
				if (ps.getId().equals(Long.valueOf(id))) {
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
			if (!StringUtils.isBlank(startTime) || !StringUtils.isBlank(projectSize) || !StringUtils.isBlank(endTime)) {
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

	private List<ProjectSize> deleteProjectSize(String id, ProjectInfo projectInfo) {
		List<ProjectSize> list = projectInfo.getProjectSizes();
		if (StringUtils.isBlank(id) || list == null || list.size() == 0) {
			return null;
		}

		Iterator<ProjectSize> it = list.iterator();
		while (it.hasNext()) {
			ProjectSize ps = it.next();
			if (ps.getId().equals(Long.valueOf(id))) {
				it.remove();
				ps.setProjectInfo(null);
				projectInfoManager.deleteProjectSize(ps);
				break;
			}
		}
		return list;
	}

	private void deleteCounterparty(String counterpartyId, ProjectInfo projectInfo) {
		Set<Counterparty> counterparties = projectInfo.getCounterparties();
		if (StringUtils.isBlank(counterpartyId) || counterparties == null || counterparties.size() == 0) {
			return;
		}

		Iterator<Counterparty> it = counterparties.iterator();
		while (it.hasNext()) {
			Counterparty cp = it.next();
			if (cp.getId().equals(Long.valueOf(counterpartyId))) {
				it.remove();
				break;
			}
		}
	}

	private void deleteGuarantor(String counterpartyId, ProjectInfo projectInfo) {
		Set<Counterparty> counterparties = projectInfo.getGuarantors();
		if (StringUtils.isBlank(counterpartyId) || counterparties == null || counterparties.size() == 0) {
			return;
		}

		Iterator<Counterparty> it = counterparties.iterator();
		while (it.hasNext()) {
			Counterparty cp = it.next();
			if (cp.getId().equals(Long.valueOf(counterpartyId))) {
				it.remove();
				break;
			}
		}
	}
	
	@ModelAttribute("allTrustManagers")
	public Map<String, String> getAllTrustManagers()
	{
	    List<Manager> managers = this.projectInfoManager.getAllTrustManagers();
	    Map<String, String> allTrustManagers = new HashMap<String, String>();
	    for(Manager m : managers)
	    {
	        allTrustManagers.put(m.getName(), m.getName());
	    }
	    return allTrustManagers;
	}
	
	@ModelAttribute("allDelegateManagers")
    public Map<String, String> getAllDelegateManagers()
    {
	    List<Manager> managers = this.projectInfoManager.getAllDelegateManagers();
	    Map<String, String> allDelegateManagers = new HashMap<String, String>();
        for(Manager m : managers)
        {
            allDelegateManagers.put(m.getName(), m.getName());
        }
        return allDelegateManagers;
    }
	
	@ModelAttribute("allRiskManagers")
    public Map<String, String> getAllRiskManagers()
    {
	    List<Manager> managers = this.projectInfoManager.getAllRiskManagers();
        Map<String, String> allRiskManagers = new HashMap<String, String>();
        for(Manager m : managers)
        {
            allRiskManagers.put(m.getName(), m.getName());
        }
        return allRiskManagers;
    }
}
