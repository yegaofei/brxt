package com.brxt.webapp.controller;

import java.util.Date;
import java.util.List;
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
	
	private void saveCollateralLand(CollateralLandModel collateralLandModel, final HttpServletRequest request)
	{
		String collateralId = request.getParameter("collateralId");
		Date now = new Date();
		if(StringUtils.isBlank(collateralId))
		{
			//Add new
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
		}
		else
		{
			//Save Edit
			CollateralOverview overview  = collateralLandModel.getCollateralOverview();
			overview.setUpdateUser(getCurrentUser().getUsername());
			overview.setUpdateTime(now);
			
			Collateral collateral = (Collateral)collateralLandModel;
			
			List<Collateral> clist  = overview.getCollaterals();
			int i = 0;
			for(i = 0; i < clist.size(); i++)
			{
				Collateral c = clist.get(i);
				if(c.getId() == collateral.getId())
				{
					c.setArchived(collateral.getArchived());
					c.setBookedFundUsage(collateral.getBookedFundUsage());
					c.setCertificate(collateral.getCertificate());
					c.setCertificateNo(collateral.getCertificateNo());
					//c.setCollateralOverview(overview);
					c.setCollateralType(collateral.getCollateralType());
					c.setConstructingProjectList(collateral.getConstructingProjectList());
					c.setContractFundUsage(collateral.getContractFundUsage());
					c.setCreateTime(collateral.getCreateTime());
					c.setCreateUser(collateral.getCreateUser());
					c.setDistrict(collateral.getDistrict());
					c.setEvaluatedMethod1(collateral.getEvaluatedMethod1());
					c.setEvaluatedMethod2(collateral.getEvaluatedMethod2());
					c.setEvaluatedTime(collateral.getEvaluatedTime());
					c.setEvaluatedValue(collateral.getEvaluatedValue());
					c.setExecutor(collateral.getExecutor());
					c.setId(collateral.getId());
					c.setLandList(collateral.getLandList());
					c.setLocation(collateral.getLocation());
					c.setOwner(collateral.getOwner());
					c.setPrivilegesOrder(collateral.getPrivilegesOrder());
					c.setPropertyList(collateral.getPropertyList());
					c.setRate(collateral.getRate());
					c.setValue1(collateral.getValue1());
					c.setValue2(collateral.getValue2());
					c.setUpdateTime(now);
					c.setUpdateUser(getCurrentUser().getUsername());
					
					List<Land> lands  = c.getLandList();
					if(!lands.isEmpty())
					{
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
	
	@RequestMapping(value = "/collateral/collateralLand*", method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("collateralLand") CollateralLandModel collateralLandModel, 
			final BindingResult errors, 
			final HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		String overviewId = request.getParameter("overviewId");
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
			saveCollateralLand(collateralLandModel, request);
			returnView = returnView + "?id=" + overviewId;
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
		String collateralId = request.getParameter("collateralId");
		
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
		if(StringUtils.isBlank(collateralId))
		{
			//Add
			clm = new CollateralLandModel();
			clm.setCollateralOverview(overview);
			
		}
		else
		{
			//Edit
			clm = new CollateralLandModel();
			List<Collateral> collaterals = overview.getCollaterals();
			for(Collateral c : collaterals)
			{
				if(c.getId() == Long.valueOf(collateralId))
				{
					clm.setArchived(c.getArchived());
					clm.setBookedFundUsage(c.getBookedFundUsage());
					clm.setCertificate(c.getCertificate());
					clm.setCertificateNo(c.getCertificateNo());
					clm.setCollateralOverview(overview);
					clm.setCollateralType(c.getCollateralType());
					clm.setConstructingProjectList(c.getConstructingProjectList());
					clm.setContractFundUsage(c.getContractFundUsage());
					clm.setCreateTime(c.getCreateTime());
					clm.setCreateUser(c.getCreateUser());
					clm.setDistrict(c.getDistrict());
					clm.setEvaluatedMethod1(c.getEvaluatedMethod1());
					clm.setEvaluatedMethod2(c.getEvaluatedMethod2());
					clm.setEvaluatedTime(c.getEvaluatedTime());
					clm.setEvaluatedValue(c.getEvaluatedValue());
					clm.setExecutor(c.getExecutor());
					clm.setId(c.getId());
					clm.setLandList(c.getLandList());
					clm.setLocation(c.getLocation());
					clm.setOwner(c.getOwner());
					clm.setPrivilegesOrder(c.getPrivilegesOrder());
					clm.setPropertyList(c.getPropertyList());
					clm.setRate(c.getRate());
					clm.setValue1(c.getValue1());
					clm.setValue2(c.getValue2());
					
					List<Land> lands = c.getLandList();
					if(!lands.isEmpty())
					{
						Land land = lands.get(0);
						clm.setLand(land);
					}
				}
			}
			
		}
		
		
		
		return clm;
	}

}
