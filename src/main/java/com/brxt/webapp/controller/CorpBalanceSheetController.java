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

import com.brxt.model.finance.CorpBalanceSheetModel;
import com.brxt.model.finance.CorporateBalanceSheet;


@Controller
@RequestMapping("/corpBalanceSheet*")
public class CorpBalanceSheetController extends BaseFormController{
	
	private Map<String,CorpBalanceSheetModel> savedBalanceSheet= new HashMap<String,CorpBalanceSheetModel>();
	private static final Map<String,String> availReportYears = new TreeMap<String,String>();
	private static final int yearRange = 10;
	
	public CorpBalanceSheetController() {
		setCancelView("redirect:corpBalanceSheet");
		setSuccessView("redirect:corpBalanceSheet");
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
		ModelAndView mav = new ModelAndView("corpBalanceSheet");	
		if(availReportYears.isEmpty())
		{
			loadDropDownList(request.getLocale());
		}
		mav.addObject("availReportYears", availReportYears);
		mav.addObject("corpBalanceSheetModel", getCorpBalanceSheetModel(request));			
		return mav;
	}
	
	private CorpBalanceSheetModel getCorpBalanceSheetModel(HttpServletRequest request){
		String reportYear="2014";
		CorpBalanceSheetModel corpBalanceSheetModel= savedBalanceSheet.get(reportYear);		
		
		if (corpBalanceSheetModel == null){
		
			String reportName="testReport";
			CorporateBalanceSheet beginBalSheet = new CorporateBalanceSheet();
			CorporateBalanceSheet endBalSheet = new CorporateBalanceSheet();
			
			corpBalanceSheetModel= new CorpBalanceSheetModel();
			corpBalanceSheetModel.setBeginBalSheet(beginBalSheet);
			corpBalanceSheetModel.setEndBalSheet(endBalSheet);
			
			corpBalanceSheetModel.setReportName(reportName);
			corpBalanceSheetModel.setProjectId(beginBalSheet.getProjectInfo().getId());
			corpBalanceSheetModel.setCounterpartyId(beginBalSheet.getCounterparty().getId());
			corpBalanceSheetModel.setCounterpartyName("testCounterParty");
			corpBalanceSheetModel.setProjectName("testProjectName");
			corpBalanceSheetModel.setReportYear(reportYear);
		
		}
		
		return corpBalanceSheetModel;		
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(CorpBalanceSheetModel corpBalanceSheetModel, 
			BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (method != null) {
			ModelAndView mav = new ModelAndView();
			//corpBalanceSheetModel.getBeginBalSheet().setReportYear(corpBalanceSheetModel.getReportYear());
			//corpBalanceSheetModel.getBeginBalSheet().setProjectId(corpBalanceSheetModel.getCounterparty().getId());
			//corpBalanceSheetModel.getBeginBalSheet().setCounterparty(corpBalanceSheetModel.getCounterparty());			
			//corpBalanceSheetModel.getEndBalSheet().setReportYear(corpBalanceSheetModel.getReportYear());
			//corpBalanceSheetModel.getEndBalSheet().setProjectId(corpBalanceSheetModel.getProjectId());
			//corpBalanceSheetModel.getEndBalSheet().setCounterparty(corpBalanceSheetModel.getCounterparty());
			switch (method) {
			case "Cancel":
				mav.setViewName(getCancelView());
				break;
			case "Delete":
				//savedBalanceSheet.remove(corpBalanceSheetModel.getReportYear());
				saveMessage(request, getText("corpBalanceSheet.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "Save":
				mav = saveCorpBalanceSheet(corpBalanceSheetModel, errors, request, mav);
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
	
	private ModelAndView saveCorpBalanceSheet(CorpBalanceSheetModel corpBalanceSheetModel,
			BindingResult errors, HttpServletRequest request, ModelAndView mav)
			throws Exception {
		final Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(corpBalanceSheetModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				mav.setViewName("corpBalanceSheet");
				return mav;
			}
		}
		
		CorporateBalanceSheet beginBalSheet = corpBalanceSheetModel.getBeginBalSheet();
		CorporateBalanceSheet endBalSheet = corpBalanceSheetModel.getEndBalSheet();

		boolean isNew = (beginBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		if (isNew) {
			// Add
			beginBalSheet.setCreateUser(currentUser.getUsername());
			beginBalSheet.setCreateTime(new Date());
		} else {
//			User createUser = getUserManager().getUserByUsername(beginBalSheet.getCreateUser().getUsername());
//			beginBalSheet.setCreateUser(createUser);
			beginBalSheet.setUpdateUser(currentUser.getUsername());
			beginBalSheet.setUpdateTime(new Date());
		}
		
		isNew = (endBalSheet.getId() == null);
		
		if (isNew) {
			// Add
			endBalSheet.setCreateUser(currentUser.getUsername());
			endBalSheet.setCreateTime(new Date());
		} else {
			
			endBalSheet.setUpdateUser(currentUser.getUsername());
			endBalSheet.setUpdateTime(new Date());
		}		
		
		savedBalanceSheet.put(corpBalanceSheetModel.getReportYear(), corpBalanceSheetModel);
		mav.addObject("corpBalanceSheetModel", corpBalanceSheetModel);
		String key = (isNew) ? "corpBalanceSheet.added" : "corpBalanceSheet.updated";
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
