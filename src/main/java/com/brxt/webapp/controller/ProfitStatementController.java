package com.brxt.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.brxt.model.ProfitStatement;
import com.brxt.model.ProfitStatementModel;

@Controller
@RequestMapping("/profitStatement*")
public class ProfitStatementController extends BaseFormController{
	
	private Map<String,ProfitStatementModel> savedBalanceSheet= new HashMap<String,ProfitStatementModel>();
	private static final Map<String,String> availReportYears = new TreeMap<String,String>();
	private static final int yearRange = 10;
	
	public ProfitStatementController() {
		setCancelView("redirect:profitStatement");
		setSuccessView("redirect:profitStatement");
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
		mav.addObject("profitStatementModel", getProfitStatementModel(request));			
		return mav;
	}
	
	private ProfitStatementModel getProfitStatementModel(HttpServletRequest request){
		String reportYear="2014";
		ProfitStatementModel profitStatementModel= savedBalanceSheet.get(reportYear);		
		
		if (profitStatementModel == null){
		
			String reportName="testReport";
			ProfitStatement beginBalSheet = new ProfitStatement();
			ProfitStatement endBalSheet = new ProfitStatement();
			
			profitStatementModel= new ProfitStatementModel();
			profitStatementModel.setBeginBalSheet(beginBalSheet);
			profitStatementModel.setEndBalSheet(endBalSheet);
			
			profitStatementModel.setReportName(reportName);
			profitStatementModel.setProjectId(beginBalSheet.getProjectId());
			profitStatementModel.setCounterpartyId(beginBalSheet.getCounterpartyId());
			profitStatementModel.setCounterpartyName("testCounterParty");
			profitStatementModel.setProjectName("testProjectName");
			profitStatementModel.setReportYear(reportYear);
		
		}
		
		return profitStatementModel;		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(ProfitStatementModel profitStatementModel, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (method != null) {
			ModelAndView mav = new ModelAndView();
			profitStatementModel.getBeginBalSheet().setReportYear(profitStatementModel.getReportYear());
			profitStatementModel.getBeginBalSheet().setProjectId(profitStatementModel.getProjectId());
			profitStatementModel.getBeginBalSheet().setCounterpartyId(profitStatementModel.getCounterpartyId());			
			profitStatementModel.getEndBalSheet().setReportYear(profitStatementModel.getReportYear());
			profitStatementModel.getEndBalSheet().setProjectId(profitStatementModel.getProjectId());
			profitStatementModel.getEndBalSheet().setCounterpartyId(profitStatementModel.getCounterpartyId());
			switch (method) {
			case "Cancel":
				mav.setViewName(getCancelView());
				break;
			case "Delete":
				savedBalanceSheet.remove(profitStatementModel.getReportYear());
				saveMessage(request, getText("profitStatement.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "Save":
				mav = saveProfitStatement(profitStatementModel, errors, request, mav);
				break;			
			default:
				//Error
			}
			return mav;
		} else {
			// error
		}
		return new ModelAndView("corpBalanceSheet");
	}
	
	private ModelAndView saveProfitStatement(ProfitStatementModel profitStatementModel,
			BindingResult errors, HttpServletRequest request, ModelAndView mav)
			throws Exception {
		final Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(profitStatementModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				mav.setViewName("corpBalanceSheet");
				return mav;
			}
		}
		
		ProfitStatement beginBalSheet = profitStatementModel.getBeginBalSheet();
		ProfitStatement endBalSheet = profitStatementModel.getEndBalSheet();

		boolean isNew = (beginBalSheet.getId() == null);
		User currentUser = null;
		final Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth != null) {
			currentUser = getCurrentUser(auth, getUserManager());
		}
		if (isNew) {
			// Add
			beginBalSheet.setCreateUser(currentUser);
			beginBalSheet.setCreateTime(new Date());
		} else {
			User createUser = getUserManager().getUserByUsername(beginBalSheet.getCreateUser().getUsername());
			beginBalSheet.setCreateUser(createUser);
			beginBalSheet.setUpdateUser(currentUser);
			beginBalSheet.setUpdateTime(new Date());
		}
		
		isNew = (endBalSheet.getId() == null);
		
		if (isNew) {
			// Add
			endBalSheet.setCreateUser(currentUser);
			endBalSheet.setCreateTime(new Date());
		} else {
			User createUser = getUserManager().getUserByUsername(endBalSheet.getCreateUser().getUsername());
			endBalSheet.setCreateUser(createUser);
			endBalSheet.setUpdateUser(currentUser);
			endBalSheet.setUpdateTime(new Date());
		}		
		
		savedBalanceSheet.put(profitStatementModel.getReportYear(), profitStatementModel);
		mav.addObject("profitStatementModel", profitStatementModel);
		String key = (isNew) ? "profitStatement.added" : "profitStatement.updated";
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
