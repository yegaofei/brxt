package com.brxt.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.appfuse.model.User;
import org.appfuse.service.UserManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.BudgetStatement;
import com.brxt.model.BudgetType;
import com.brxt.model.MockBudgetRepsitory;

@Controller
@RequestMapping("/budgetStatementForm*")
public class BudgetStatementFormController extends BaseFormController{
	//In the form of <2014, <201405, Statement> >
	public static Map<String, Map<String,BudgetStatement>> savedBudgetStatement = new HashMap<String, Map<String,BudgetStatement>>();
	private static final Map<String,String> availReportMonths = new TreeMap<String,String>();
	private static final Map<String,String> budgetTypes = new TreeMap<String,String>();
	private static final int monthRange = 24;
	public BudgetStatementFormController() {		
		setCancelView("redirect:budgetStatementInfo");
		setSuccessView("redirect:budgetStatementInfo");
	}
	
	private synchronized void loadDropDownList(final Locale locale)
	{
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");		
		int currentMonth = Integer.parseInt(sdf.format(now));
		availReportMonths.put(String.valueOf(currentMonth), String.valueOf(currentMonth));
		for (int i=0; i<monthRange; i++){
			now = DateUtils.addMonths(now, -1);
			currentMonth = Integer.parseInt(sdf.format(now));
			availReportMonths.put(String.valueOf(currentMonth), String.valueOf(currentMonth));
		}		
		
		if(budgetTypes.isEmpty())
		{
			BudgetType[] cits = BudgetType.values();
			for(BudgetType cit : cits)
			{
				budgetTypes.put(cit.toString(), getText(cit.toString(), locale));
			}
		}
	}

	
	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView showForm(HttpServletRequest request) throws Exception {			
		ModelAndView mav = new ModelAndView();	
		if(availReportMonths.isEmpty())
		{
			loadDropDownList(request.getLocale());
		}
		mav.addObject("budgetTypes", budgetTypes);
		mav.addObject("availReportMonths", availReportMonths);
		mav.addObject("budgetStatementFormModel", new BudgetStatement());			
		return mav;
	}	
		
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(BudgetStatement budgetStatement, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (method != null) {
			ModelAndView mav = new ModelAndView();
			
			switch (method) {
			case "Cancel":
				mav.setViewName(getCancelView());
				break;
			case "Delete":
				MockBudgetRepsitory.getInstance().deleteBudget(budgetStatement);				
				saveMessage(request, getText("budgetStatementForm.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "Save":
				mav = saveBudgetStatement(budgetStatement, errors, request, mav);
				break;			
			default:
				//Error
			}
			return mav;
		} else {
			// error
		}
		return new ModelAndView("budgetStatementForm");
	}
	
	private ModelAndView saveBudgetStatement(BudgetStatement budgetStatement,
			BindingResult errors, HttpServletRequest request, ModelAndView mav)
			throws Exception {
		final Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(budgetStatement, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				mav.setViewName("budgetStatementForm");
				return mav;
			}
		}		

		boolean isNew = (budgetStatement.getId() == null);
		User currentUser = null;
		final Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			currentUser = getCurrentUser(auth, getUserManager());
		}
		if (isNew) {
			// Add
			budgetStatement.setCreateUser(currentUser);
			budgetStatement.setCreateTime(new Date());
		} else {
			User createUser = getUserManager().getUserByUsername(budgetStatement.getCreateUser().getUsername());
			budgetStatement.setCreateUser(createUser);
			budgetStatement.setUpdateUser(currentUser);
			budgetStatement.setUpdateTime(new Date());
		}
		
		MockBudgetRepsitory.getInstance().addOrUpdateBudget(budgetStatement);
		
		mav.addObject("budgetStatementFormModel", budgetStatement);
		String key = (isNew) ? "budgetStatementForm.added" : "budgetStatementForm.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName(getSuccessView());
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


	
}
