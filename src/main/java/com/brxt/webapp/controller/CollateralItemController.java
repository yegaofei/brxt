package com.brxt.webapp.controller;

import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brxt.model.collateral.Collateral;
import com.brxt.model.collateral.CollateralLandModel;
import com.brxt.model.collateral.CollateralOverview;
import com.brxt.model.collateral.Land;
import com.brxt.service.CollateralManager;
import com.brxt.service.ProjectInfoManager;

@Controller
public class CollateralItemController extends BaseFormController{

	private CollateralManager collateralManager;
	private ProjectInfoManager projectInfoManager;

	@Autowired
	public void setCollateralManager(CollateralManager collateralManager) {
		this.collateralManager = collateralManager;
	}
	
	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}
	
	@RequestMapping(value = "/collateral/collateralLand*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "/collateral/collateralLand";
	}
	
	@RequestMapping(value = "/collateral/collateralLand*", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("collateralLand") CollateralLandModel collateralLandModel, 
			final BindingResult errors, 
			final HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		String returnView = "redirect:/collateral/collateralForm";
		final Locale locale = request.getLocale();
		
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
			Date now = new Date();
			CollateralOverview collateralOverview  = collateralLandModel.getCollateralOverview();
			
			Collateral collateral = (Collateral)collateralLandModel;
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
			returnView = returnView + "?id=" + collateralOverview.getId();
			break;
			default:
		}
		
		
		return returnView;
	}

	
	@ModelAttribute("collateralLand")
	public CollateralLandModel getCollateralLandModel(
			final HttpServletRequest request, final HttpSession session) {
		String overviewId = request.getParameter("overviewId");
		String method = request.getParameter("method");
		
		if(StringUtils.isBlank(method))
		{
			if(StringUtils.isBlank(overviewId))
			{
				saveError(request, "over view id is null");
				return null;
			}
		}
		
		
		CollateralLandModel clm = null;
		CollateralOverview overview = collateralManager.get(Long.valueOf(overviewId));
		String collateralId = request.getParameter("collateralId");
		if(StringUtils.isBlank(collateralId))
		{
			//Add
			clm = new CollateralLandModel();
			clm.setCollateralOverview(overview);
			
		}
		else
		{
			//Edit
			
			
		}
		
		
		
		return clm;
	}

}
