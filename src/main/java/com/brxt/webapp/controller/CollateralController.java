package com.brxt.webapp.controller;

import java.util.ArrayList;
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

import com.brxt.model.ProjectInfo;
import com.brxt.model.collateral.Collateral;
import com.brxt.model.collateral.CollateralDataModel;
import com.brxt.model.collateral.CollateralListItem;
import com.brxt.model.collateral.CollateralOverview;
import com.brxt.model.collateral.CollateralType;
import com.brxt.model.collateral.DetailListItem;
import com.brxt.service.CollateralManager;
import com.brxt.service.ProjectInfoManager;

@Controller
public class CollateralController extends BaseFormController{

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

	@ModelAttribute("collateralList")
	public List<CollateralListItem> getCollateralList(
			final HttpServletRequest request, final HttpSession session) {
		List<CollateralListItem> cList = null;

		List<CollateralOverview> overviewList = collateralManager.getAll();
		if (overviewList != null) {
			cList = new ArrayList<CollateralListItem>();
			for (CollateralOverview overview : overviewList) {
				CollateralListItem item = new CollateralListItem();
				item.setId(overview.getId());
				item.setProjectName(overview.getProjectInfo().getProjectName());
				if (overview.getCollaterals() != null
						&& !overview.getCollaterals().isEmpty()) {
					Collateral collateral = overview.getCollaterals().get(0);
					if (collateral.getLandList() != null
							&& !collateral.getLandList().isEmpty()) {
						item.setCollateralType(CollateralType.LAND);
					} else if (collateral.getPropertyList() != null
							&& !collateral.getPropertyList().isEmpty()) {
						item.setCollateralType(CollateralType.PROPERTY);
					} else if (collateral.getConstructingProjectList() != null
							&& !collateral.getConstructingProjectList()
									.isEmpty()) {
						item.setCollateralType(CollateralType.CONSTRUCTING_PROJECT);
					}

					item.setExecutor(collateral.getExecutor());
				}
				cList.add(item);
			}
		}
		return cList;
	}

	@ModelAttribute("collateralDataModel")
	public CollateralDataModel getCollateralDataModel(
			final HttpServletRequest request) {
		String id = request.getParameter("id");
		CollateralDataModel cdm = new CollateralDataModel();
		if (!StringUtils.isBlank(id)) {
			Long collateralOverviewId = Long.valueOf(id);
			CollateralOverview cov = collateralManager
					.get(collateralOverviewId);
			cdm.setId(collateralOverviewId);
			cdm.setProjectName(cov.getProjectInfo().getProjectName());
			cdm.setDescription(cov.getDescription());
			cdm.setRate(cov.getRate());
			cdm.setEvaluatedValue(cov.getEvaluatedValue());

			List<Collateral> collaterals = cov.getCollaterals();
			for (int i = 1; i <= collaterals.size(); i++) {
				Collateral collateral = collaterals.get(i - 1);
				DetailListItem detail = new DetailListItem();
				detail.setId(Long.valueOf(i));
				detail.setDisplayId(getText("collateral.form.title", request.getLocale()) + String.valueOf(i));
				detail.setEvaluatedTime(collateral.getEvaluatedTime());
				detail.setRealId(collateral.getId());
				detail.setCollateralValue(collateral.getEvaluatedValue());

				if (!collateral.getLandList().isEmpty()) {
					detail.setType(CollateralType.LAND);
				} else if (!collateral.getPropertyList().isEmpty()) {
					detail.setType(CollateralType.PROPERTY);
				} else if (!collateral.getConstructingProjectList().isEmpty()) {
					detail.setType(CollateralType.CONSTRUCTING_PROJECT);
				}
				cdm.getDetailList().add(detail);
			}
		}

		return cdm;
	}

	@RequestMapping(value = "/collateral/collateralList*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "/collateral/collateralList";
	}

	@RequestMapping(value = "/collateral/collateralForm*", method = RequestMethod.GET)
	public String addCollateral() throws Exception {
		return "/collateral/collateralForm";

	}
	
	@RequestMapping(value = "/collateral/collateralList*", method = RequestMethod.POST)
	public String onSubmit(final HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		String id = request.getParameter("id");
		if (StringUtils.isBlank(method) || StringUtils.isBlank(id)) {
			saveMessage(request, getText("errors.token", request.getLocale()));
			return "/collateral/collateralList";
		}

		switch (method) {
		case "Edit":
			break;
		case "Delete":
			Long collateralOverviewId = Long.valueOf(id);
			collateralManager.remove(collateralOverviewId);
			saveMessage(request, getText("collateralOverview.deleted", request.getLocale()));
			return "redirect:/collateral/collateralList";
			default:
				saveMessage(request, getText("errors.token", request.getLocale()));
				return "/collateral/collateralList";
		}
		return "/collateral/collateralForm";
	}
	/*
	@RequestMapping(value = "/collateral/editCollateralDetail*", method = RequestMethod.GET)
	public String editCollateralDetail(final HttpServletRequest request){
		String overviewId = request.getParameter("id");
		String collateralId = request.getParameter("collateralId");
		String collateralType = request.getParameter("collateralType");
		String returnView = "/collateral/collateralForm";
		if(StringUtils.isNotBlank(collateralType))
		{
			CollateralType type = CollateralType.valueOf(collateralType);
			switch(type)
			{
			case LAND:
				returnView = "redirect:/collateral/collateralLand?overviewId="+overviewId+"&collateralId="+collateralId;
				break;
			case PROPERTY:
				break;
			case CONSTRUCTING_PROJECT:
				break;
				default:
			}
		}
		
		
		
		return "";
	}
*/
	@RequestMapping(value = "/collateral/collateralForm*", method = RequestMethod.POST)
	public String onSubmit(
			@ModelAttribute("collateralDataModel") CollateralDataModel collateralDataModel, BindingResult errors, 
			final HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		if (validator != null) { 
			if(collateralDataModel == null)
			{
				saveMessage(request, "Data binding failed");
				return getCancelView();
			} 
			else
			{
				validator.validate(collateralDataModel, errors);
				if (errors.hasErrors()) {
					log.debug("error happens 'onSubmit' method..." + errors.toString());
					saveMessage(request, errors.toString());
					return getCancelView();
				}
			}
			
		}

		if (StringUtils.isBlank(method)) {
			// error
		}

		switch (method) {
		case "Save":
			saveCollateralDetail(collateralDataModel, request);
			return "redirect:/collateral/collateralForm?id="+collateralDataModel.getId();
		case "AddCollateral":
			CollateralType addType = collateralDataModel.getAddType();
			switch (addType) {
			case LAND:
				return "redirect:/collateral/collateralLand?overviewId=" + collateralDataModel.getId();
			case PROPERTY:
				break;
			case CONSTRUCTING_PROJECT:
				break;
			default:
			}

			break;
		case "Cancel":
			return "/collateral/collateralList";
		default:
		}

		return "/collateral/collateralForm";

	}
	
	private void saveCollateralDetail(CollateralDataModel collateralDataModel, final HttpServletRequest request)
	{
		final Locale locale = request.getLocale();
		Long collateralOverviewId = collateralDataModel.getId();
		if(collateralOverviewId == null)
		{
			//Add
			String projectName = collateralDataModel.getProjectName();
			ProjectInfo projectInfo = getProjectInfo(projectName, request);
			if(projectInfo == null)
			{
				return ;
			}
			CollateralOverview collateralOverview = new CollateralOverview();
			collateralOverview.setProjectInfo(projectInfo);
			collateralOverview.setDescription(collateralDataModel.getDescription());
			collateralOverview.setEvaluatedValue(collateralDataModel.getEvaluatedValue());
			collateralOverview.setRate(collateralDataModel.getRate());
			collateralOverview.setCreateUser(getCurrentUser().getUsername());
			collateralOverview.setCreateTime(new Date());
			CollateralOverview overview = collateralManager.save(collateralOverview);
			collateralDataModel.setId(overview.getId());
			saveMessage(request, getText("collateralOverview.added", locale));
		}
		else
		{
			//Edit
			CollateralOverview collateralOverview = collateralManager.get(collateralOverviewId);
			if(collateralOverview != null)
			{
				if(!collateralOverview.getProjectInfo().getProjectName().equals(collateralDataModel.getProjectName()))
				{
					String projectName = collateralDataModel.getProjectName();
					ProjectInfo projectInfo = getProjectInfo(projectName, request);
					if(projectInfo == null)
					{
						return ;
					}
				}
				
				collateralOverview.setDescription(collateralDataModel.getDescription());
				collateralOverview.setEvaluatedValue(collateralDataModel.getEvaluatedValue());
				collateralOverview.setRate(collateralDataModel.getRate());
				collateralManager.save(collateralOverview);
				saveMessage(request, getText("collateralOverview.updated", locale));
			}
		}
	}
	
	private ProjectInfo getProjectInfo(String projectName, final HttpServletRequest request)
	{
		List<ProjectInfo> projectInfoList = projectInfoManager.findByProjectName(projectName);
		if(projectInfoList == null || projectInfoList.isEmpty())
		{
			//Error
			saveError(request, getText("projectInfo.not.existed", projectName, request.getLocale()));
			
			return null;
		}
		
		if(projectInfoList.size() > 1)
		{
			//Error
			saveError(request, getText("projectInfo.error.more", projectName, request.getLocale()));
			
			return null;
		}
		
		return projectInfoList.get(0);
	}
}
