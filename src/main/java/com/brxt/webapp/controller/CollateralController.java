package com.brxt.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brxt.model.collateral.Collateral;
import com.brxt.model.collateral.CollateralListItem;
import com.brxt.model.collateral.CollateralOverview;
import com.brxt.model.collateral.CollateralType;
import com.brxt.service.CollateralManager;

@Controller
public class CollateralController {
	
	private CollateralManager collateralManager;
	
	@Autowired
	public void setCollateralManager(CollateralManager collateralManager) {
		this.collateralManager = collateralManager;
	}

	@ModelAttribute("collateralList")
	public List<CollateralListItem> getCollateralList(final HttpServletRequest request, final HttpSession session)
	{
		List<CollateralListItem> cList = null;
		
		List<CollateralOverview> overviewList = collateralManager.getAll();
		if(overviewList != null)
		{
			cList = new ArrayList<CollateralListItem>();
			for(CollateralOverview overview : overviewList)
			{
				CollateralListItem item = new CollateralListItem();
				item.setId(overview.getId());
				item.setProjectName(overview.getProjectInfo().getProjectName());
				if(overview.getCollaterals() != null && !overview.getCollaterals().isEmpty())
				{
					Collateral collateral = overview.getCollaterals().get(0);
					if(collateral.getLandList() != null && !collateral.getLandList().isEmpty())
					{
						item.setCollateralType(CollateralType.LAND);
					}
					else if(collateral.getPropertyList() != null && !collateral.getPropertyList().isEmpty())
					{
						item.setCollateralType(CollateralType.PROPERTY);
					}
					else if(collateral.getConstructingProjectList()!= null && !collateral.getConstructingProjectList().isEmpty())
					{
						item.setCollateralType(CollateralType.CONSTRUCTING_PROJECT);
					}
					
					item.setExecutor(collateral.getExecutor());
				}
				cList.add(item);
			}
		}
		return cList;
	}
	
	@ModelAttribute("CollateralOverview")
	public CollateralOverview getCollateralOverview(final HttpServletRequest request)
	{
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id))
		{
			return new CollateralOverview();
		}
		else
		{
			Long collateralOverviewId = Long.valueOf(id);
			return collateralManager.get(collateralOverviewId);
		}
	}
	

	@RequestMapping(value = "/collateral/collateralList*", method = RequestMethod.GET)
	public String handleRequest() throws Exception {
		return "/collateral/collateralList";
	}
	
	@RequestMapping(value = "/collateral/addCollateral*", method = RequestMethod.GET)
	public String addCollateral() throws Exception {
		return "/collateral/addCollateral";
	}
}
