package com.brxt.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.BudgetStatement;
import com.brxt.model.BudgetStatementModel;

@Controller
@RequestMapping("/budgetStatementInfo*")
public class BudgetStatementInfoController extends BaseFormController{
	
	public static Map<String,BudgetStatementModel> savedBudgetStatement= new HashMap<String,BudgetStatementModel>();
	private static final Map<String,String> availReportYears = new TreeMap<String,String>();
	private static final int yearRange = 10;
	
	public BudgetStatementInfoController() {
		setCancelView("redirect:budgetStatementInfo");
		setSuccessView("redirect:budgetStatementInfo");
	}
	
	private synchronized void loadDropDownList(final Locale locale)
	{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		int currentYear = Integer.parseInt(sdf.format(now));
		for (int i=currentYear; i>currentYear - yearRange; i--){
			availReportYears.put(String.valueOf(i), String.valueOf(i));
		}		
	}

	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm(HttpServletRequest request) throws Exception {		
		ModelAndView mav = new ModelAndView();	
		if(availReportYears.isEmpty())
		{
			loadDropDownList(request.getLocale());
		}
		mav.addObject("availReportYears", availReportYears);
		mav.addObject("budgetStatementInfoModel", getBudgetStatementModel(request));			
		return mav;
	}
	
	private BudgetStatementModel getBudgetStatementModel(HttpServletRequest request){
		String reportYear="2014";
		BudgetStatementModel budgetStatementModel= savedBudgetStatement.get(reportYear);		
		
		if (budgetStatementModel == null){
		
			String reportName="testReport";
			BudgetStatement fullYearBudget = new BudgetStatement();
			BudgetStatement thisYear = new BudgetStatement();
			BudgetStatement lastYear = new BudgetStatement();
			BudgetStatement budgetRatio = new BudgetStatement();
			BudgetStatement growthRate = new BudgetStatement();
			
			budgetStatementModel= new BudgetStatementModel();
			budgetStatementModel.setFullYearBudget(fullYearBudget);
			budgetStatementModel.setThisYear(thisYear);
			budgetStatementModel.setLastYear(lastYear);
			budgetStatementModel.setBudgetRatio(budgetRatio);
			budgetStatementModel.setGrowthRate(growthRate);			
			
			budgetStatementModel.setReportName(reportName);
			budgetStatementModel.setProjectId(thisYear.getProjectId());
			budgetStatementModel.setCounterpartyId(thisYear.getCounterpartyId());
			budgetStatementModel.setCounterpartyName("testCounterParty");
			budgetStatementModel.setProjectName("testProjectName");
			budgetStatementModel.setReportYear(reportYear);
		
		}
		
		return budgetStatementModel;		
	}	
	
}
