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

import com.brxt.model.MockBudgetRepsitory;
import com.brxt.model.finance.BudgetStatementModel;

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
		return MockBudgetRepsitory.getInstance().getBudgetInfo(reportYear);		
	}	
	
}
