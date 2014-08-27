package com.brxt.webapp.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brxt.model.collateral.Collateral;
import com.brxt.model.collateral.CollateralConstrProjModel;
import com.brxt.model.collateral.CollateralLandModel;
import com.brxt.model.collateral.CollateralOverview;
import com.brxt.model.collateral.CollateralPropertyModel;
import com.brxt.model.collateral.ConstructingProject;
import com.brxt.model.collateral.Land;
import com.brxt.model.collateral.Property;
import com.brxt.service.CollateralManager;

@Controller
public class CollateralItemController extends BaseFormController {

	private CollateralManager collateralManager;

	@Autowired
	public void setCollateralManager(CollateralManager collateralManager) {
		this.collateralManager = collateralManager;
	}

	@RequestMapping(value = "/collateral/collateralLand*", method = RequestMethod.GET)
	public String handleLand() throws Exception {
		return "/collateral/collateralLand";
	}
	
	@RequestMapping(value = "/collateral/collateralProperty*", method = RequestMethod.GET)
	public String handleProperty() throws Exception {
		return "/collateral/collateralProperty";
	}
	
	@RequestMapping(value = "/collateral/collateralConstrProj*", method = RequestMethod.GET)
	public String handleConstrProj() throws Exception {
		return "/collateral/collateralConstrProj";
	}

	private void saveCollateralLand(CollateralLandModel collateralLandModel,
			final HttpServletRequest request) {
		String collateralId = request.getParameter("collateralId");
		Date now = new Date();
		if (StringUtils.isBlank(collateralId)) {
			// Add new
			CollateralOverview collateralOverview = collateralLandModel
					.getCollateralOverview();
			Collateral collateral = (Collateral) collateralLandModel;
			collateral.setCreateUser(getCurrentUser().getUsername());
			collateral.setCreateTime(now);

			Land land = collateralLandModel.getLand();
			land.setCreateTime(now);
			land.setCreateUser(getCurrentUser().getUsername());
			land.setCollateral(collateral);
			collateral.getLandList().add(land);
			collateralOverview.getCollaterals().add(collateral);

			collateralOverview.setUpdateUser(getCurrentUser().getUsername());
			collateralOverview.setUpdateTime(now);
			collateralManager.save(collateralOverview);
		} else {
			// Save Edit
			CollateralOverview overview = collateralLandModel
					.getCollateralOverview();
			overview.setUpdateUser(getCurrentUser().getUsername());
			overview.setUpdateTime(now);
			Collateral collateral = (Collateral) collateralLandModel;
			List<Collateral> clist = overview.getCollaterals();
			int i = 0;
			for (i = 0; i < clist.size(); i++) {
				Collateral c = clist.get(i);
				if (c.getId() == collateral.getId()) {
					copyCollateral(collateral, c);
					c.setUpdateTime(now);
					c.setUpdateUser(getCurrentUser().getUsername());

					List<Land> lands = c.getLandList();
					if (!lands.isEmpty()) {
						Land landInDB = lands.get(0);
						Land landInWeb = collateralLandModel.getLand();
						landInDB.setLandAttribute(landInWeb.getLandAttribute());
						landInDB.setOwner(landInWeb.getOwner());
						landInDB.setPrice(landInWeb.getPrice());
						landInDB.setSize(landInWeb.getSize());
						landInDB.setSourceMethod(landInWeb.getSourceMethod());
						landInDB.setUpdateTime(now);
						landInDB.setUpdateUser(getCurrentUser().getUsername());
					}
					break;
				}
			}
			collateralManager.save(overview);
		}
	}

	private void saveCollateralProperty(CollateralPropertyModel collateralPropertyModel, 
			final HttpServletRequest request){
		String collateralId = request.getParameter("collateralId");
		Date now = new Date();
		if (StringUtils.isBlank(collateralId)) {
			// Add new
			CollateralOverview collateralOverview = collateralPropertyModel
					.getCollateralOverview();
			Collateral collateral = (Collateral) collateralPropertyModel;
			collateral.setCreateUser(getCurrentUser().getUsername());
			collateral.setCreateTime(now);

			Property property = collateralPropertyModel.getProperty();
			property.setCreateTime(now);
			property.setCreateUser(getCurrentUser().getUsername());
			property.setCollateral(collateral);
			collateral.getPropertyList().add(property);
			collateralOverview.getCollaterals().add(collateral);

			collateralOverview.setUpdateUser(getCurrentUser().getUsername());
			collateralOverview.setUpdateTime(now);
			collateralManager.save(collateralOverview);
		} else {
			// Save Edit
			CollateralOverview overview = collateralPropertyModel
					.getCollateralOverview();
			overview.setUpdateUser(getCurrentUser().getUsername());
			overview.setUpdateTime(now);
			Collateral collateral = (Collateral) collateralPropertyModel;
			List<Collateral> clist = overview.getCollaterals();
			for (int i = 0; i < clist.size(); i++) {
				Collateral c = clist.get(i);
				if (c.getId() == collateral.getId()) {
					copyCollateral(collateral, c);
					c.setUpdateTime(now);
					c.setUpdateUser(getCurrentUser().getUsername());

					List<Property> propertyList = c.getPropertyList();
					if (!propertyList.isEmpty()) {
						Property propertyInDB = propertyList.get(0);
						Property propertyInWeb = collateralPropertyModel.getProperty();
						propertyInDB.setLandUsageType(propertyInWeb.getLandUsageType());
						propertyInDB.setLandUsageYears(propertyInWeb.getLandUsageYears());
						propertyInDB.setPrice(propertyInWeb.getPrice());
						propertyInDB.setPropertyType(propertyInWeb.getPropertyType());
						propertyInDB.setYearsLimit(propertyInWeb.getYearsLimit());
						propertyInDB.setUpdateTime(now);
						propertyInDB.setUpdateUser(getCurrentUser().getUsername());
					}
					break;
				}
			}
			collateralManager.save(overview);
		}
	}
	
	
	private void saveCollateralConstrProj(CollateralConstrProjModel collateralConstrProjModel, 
			final HttpServletRequest request){
		String collateralId = request.getParameter("collateralId");
		Date now = new Date();
		if (StringUtils.isBlank(collateralId)) {
			// Add new
			CollateralOverview collateralOverview = collateralConstrProjModel
					.getCollateralOverview();
			Collateral collateral = (Collateral) collateralConstrProjModel;
			collateral.setCreateUser(getCurrentUser().getUsername());
			collateral.setCreateTime(now);

			ConstructingProject project = collateralConstrProjModel.getConstrProj();
			project.setCreateTime(now);
			project.setCreateUser(getCurrentUser().getUsername());
			project.setCollateral(collateral);
			collateral.getConstructingProjectList().add(project);
			collateralOverview.getCollaterals().add(collateral);

			collateralOverview.setUpdateUser(getCurrentUser().getUsername());
			collateralOverview.setUpdateTime(now);
			collateralManager.save(collateralOverview);
		} else {
			// Save Edit
			CollateralOverview overview = collateralConstrProjModel
					.getCollateralOverview();
			overview.setUpdateUser(getCurrentUser().getUsername());
			overview.setUpdateTime(now);
			Collateral collateral = (Collateral) collateralConstrProjModel;
			List<Collateral> clist = overview.getCollaterals();
			for (int i = 0; i < clist.size(); i++) {
				Collateral c = clist.get(i);
				if (c.getId() == collateral.getId()) {
					copyCollateral(collateral, c);
					c.setUpdateTime(now);
					c.setUpdateUser(getCurrentUser().getUsername());

					List<ConstructingProject> projectList = c.getConstructingProjectList();
					if (!projectList.isEmpty()) {
						ConstructingProject projectInDB = projectList.get(0);
						ConstructingProject projectInWeb = collateralConstrProjModel.getConstrProj();
						projectInDB.setFloor(projectInWeb.getFloor());
						projectInDB.setPrice(projectInWeb.getPrice());
						projectInDB.setProjectSize(projectInWeb.getProjectSize());
						projectInDB.setRightsType(projectInWeb.getRightsType());
						projectInDB.setType(projectInWeb.getType());
						projectInDB.setYearsLimit(projectInWeb.getYearsLimit());
						projectInDB.setUpdateTime(now);
						projectInDB.setUpdateUser(getCurrentUser().getUsername());
					}
					break;
				}
			}
			collateralManager.save(overview);
		}
	}
	
	@RequestMapping(value = "/collateral/collateralLand*", method = RequestMethod.POST)
	public String onLandSubmit(
			@ModelAttribute("collateralLand") CollateralLandModel collateralLandModel,
			final BindingResult errors, final HttpServletRequest request)
			throws Exception {
		String method = request.getParameter("method");
		String overviewId = request.getParameter("overviewId");
		String returnView = "redirect:/collateral/collateralForm";
		if (!StringUtils.isBlank(method)) {
			if (validator != null) {
				validator.validate(collateralLandModel, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}
		}
		switch (method) {
		case "Save":
			saveCollateralLand(collateralLandModel, request);
			returnView = returnView + "?id=" + overviewId;
			break;
		default:
		}
		return returnView;
	}
	
	@RequestMapping(value = "/collateral/collateralProperty*", method = RequestMethod.POST)
	public String onPropertySubmit(
			@ModelAttribute("collateralProperty") CollateralPropertyModel collateralPropertyModel,
			final BindingResult errors, final HttpServletRequest request)
			throws Exception {
		String method = request.getParameter("method");
		String overviewId = request.getParameter("overviewId");
		String returnView = "redirect:/collateral/collateralForm";
		if (!StringUtils.isBlank(method)) {
			if (validator != null) {
				validator.validate(collateralPropertyModel, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}
		}
		switch (method) {
		case "Save":
			saveCollateralProperty(collateralPropertyModel, request);
			returnView = returnView + "?id=" + overviewId;
			break;
		default:
		}
		return returnView;
	}

	@RequestMapping(value = "/collateral/collateralConstrProj*", method = RequestMethod.POST)
	public String onConstrProjSubmit(@ModelAttribute("collateralConstrProj") CollateralConstrProjModel collateralConstrProjModel,
			final BindingResult errors, final HttpServletRequest request){
		String method = request.getParameter("method");
		String overviewId = request.getParameter("overviewId");
		String returnView = "redirect:/collateral/collateralForm";
		if (!StringUtils.isBlank(method)) {
			if (validator != null) {
				validator.validate(collateralConstrProjModel, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..."
							+ errors.toString());
					saveMessage(request, errors.toString());
					return "";
				}
			}
		}
		switch (method) {
		case "Save":
			saveCollateralConstrProj(collateralConstrProjModel, request);
			returnView = returnView + "?id=" + overviewId;
			break;
		default:
		}
		return returnView;
	}
	
	@ModelAttribute("collateralProperty")
	public CollateralPropertyModel getCollateralPropertyModel(
			final HttpServletRequest request, final HttpSession session) {
		String overviewId = request.getParameter("overviewId");
		String method = request.getParameter("method");
		String collateralId = request.getParameter("collateralId");

		if (StringUtils.isBlank(method)) {
			if (StringUtils.isBlank(overviewId)) {
				saveError(request, "over view id is null");
				return null;
			}
		}

		CollateralPropertyModel clm = null;
		CollateralOverview overview = collateralManager.get(Long
				.valueOf(overviewId));
		if (StringUtils.isBlank(collateralId)) {
			// Add
			clm = new CollateralPropertyModel();
			clm.setCollateralOverview(overview);

		} else {
			// Edit
			clm = new CollateralPropertyModel();
			List<Collateral> collaterals = overview.getCollaterals();
			for (Collateral c : collaterals) {
				if (c.getId().equals(Long.valueOf(collateralId))) {
					clm.setCollateralOverview(overview);
					copyCollateral(c, clm);

					List<Property> propertyList = c.getPropertyList();
					if (!propertyList.isEmpty()) {
						Property property = propertyList.get(0);
						clm.setProperty(property);
					}
				}
			}
		}
		return clm;
	}
	
	@ModelAttribute("collateralLand")
	public CollateralLandModel getCollateralLandModel(
			final HttpServletRequest request, final HttpSession session) {
		String overviewId = request.getParameter("overviewId");
		String method = request.getParameter("method");
		String collateralId = request.getParameter("collateralId");

		if (StringUtils.isBlank(method)) {
			if (StringUtils.isBlank(overviewId)) {
				saveError(request, "over view id is null");
				return null;
			}
		}

		CollateralLandModel clm = null;
		CollateralOverview overview = collateralManager.get(Long
				.valueOf(overviewId));
		if (StringUtils.isBlank(collateralId)) {
			// Add
			clm = new CollateralLandModel();
			clm.setCollateralOverview(overview);

		} else {
			// Edit
			clm = new CollateralLandModel();
			List<Collateral> collaterals = overview.getCollaterals();
			for (Collateral c : collaterals) {
				if (c.getId().equals(Long.valueOf(collateralId))) {
					clm.setCollateralOverview(overview);
					copyCollateral(c, clm);

					List<Land> lands = c.getLandList();
					if (!lands.isEmpty()) {
						Land land = lands.get(0);
						clm.setLand(land);
					}
				}
			}

		}

		return clm;
	}
	
	@ModelAttribute("collateralConstrProj")
	public CollateralConstrProjModel getCollateralConstrProjModel(
			final HttpServletRequest request, final HttpSession session) {
		String overviewId = request.getParameter("overviewId");
		String method = request.getParameter("method");
		String collateralId = request.getParameter("collateralId");

		if (StringUtils.isBlank(method)) {
			if (StringUtils.isBlank(overviewId)) {
				saveError(request, "over view id is null");
				return null;
			}
		}

		CollateralConstrProjModel clm = null;
		CollateralOverview overview = collateralManager.get(Long
				.valueOf(overviewId));
		if (StringUtils.isBlank(collateralId)) {
			// Add
			clm = new CollateralConstrProjModel();
			clm.setCollateralOverview(overview);
		} else {
			// Edit
			clm = new CollateralConstrProjModel();
			List<Collateral> collaterals = overview.getCollaterals();
			for (Collateral c : collaterals) {
				if (c.getId().equals(Long.valueOf(collateralId))) {
					clm.setCollateralOverview(overview);
					copyCollateral(c, clm);

					List<ConstructingProject> projects = c.getConstructingProjectList();
					if (!projects.isEmpty()) {
						ConstructingProject project = projects.get(0);
						clm.setConstrProj(project);
					}
				}
			}
		}
		return clm;
	}

	private final void copyCollateral(Collateral src, Collateral dest) {
		dest.setArchived(src.getArchived());
		dest.setBookedFundUsage(src.getBookedFundUsage());
		dest.setCertificate(src.getCertificate());
		dest.setCertificateNo(src.getCertificateNo());
		// dest.setCollateralOverview(overview);
		dest.setCollateralType(src.getCollateralType());
		dest.setConstructingProjectList(src.getConstructingProjectList());
		dest.setContractFundUsage(src.getContractFundUsage());
		dest.setCreateTime(src.getCreateTime());
		dest.setCreateUser(src.getCreateUser());
		dest.setDistrict(src.getDistrict());
		dest.setEvaluatedMethod1(src.getEvaluatedMethod1());
		dest.setEvaluatedMethod2(src.getEvaluatedMethod2());
		dest.setEvaluatedTime(src.getEvaluatedTime());
		dest.setEvaluatedValue(src.getEvaluatedValue());
		dest.setExecutor(src.getExecutor());
		dest.setId(src.getId());
		dest.setLandList(src.getLandList());
		dest.setLocation(src.getLocation());
		dest.setOwner(src.getOwner());
		dest.setPrivilegesOrder(src.getPrivilegesOrder());
		dest.setPropertyList(src.getPropertyList());
		dest.setRate(src.getRate());
		dest.setValue1(src.getValue1());
		dest.setValue2(src.getValue2());
	}

}
