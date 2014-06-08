package com.brxt.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.appfuse.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.StatementType;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.model.finance.InstBalanceSheetModel;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/finance/instBalanceSheet*")
public class InstBalanceSheetController extends BaseFormController{
	
	private Map<String,InstBalanceSheetModel> savedBalanceSheet= new HashMap<String,InstBalanceSheetModel>();
	private static final Map<String, String> statementTypes = new TreeMap<String, String>();
	
	public InstBalanceSheetController() {
		setCancelView("redirect:/finance/instBalanceSheet");
		setSuccessView("redirect:/finance/instBalanceSheet");
	}
	
	private ProjectInfoManager projectInfoManager;
	private FinanceSheetManager financeSheetManager;
	
	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Autowired
	public void setFinanceSheetManager(
			@Qualifier("financeSheetManager") FinanceSheetManager financeSheetManager) {
		this.financeSheetManager = financeSheetManager;
	}
	private synchronized void loadDropDownList(final Locale locale) {
		if (statementTypes.isEmpty()) {
			StatementType[] types = StatementType.values();
			for (StatementType st : types) {
				statementTypes.put(st.toString(),
						getText(st.toString(), locale));
			}
		}
	}
	
	@ModelAttribute("statementTypes")
	public Map<String, String> getStatementTypes(final HttpServletRequest request)
	{
		if (statementTypes.isEmpty()) {
			loadDropDownList(request.getLocale());
		}
		return statementTypes;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected String showForm() {		
		return "/finance/instBalanceSheet";
	}
	
	@ModelAttribute("instBalanceSheetModel")
	public InstBalanceSheetModel getInstBalanceSheetModel(final HttpServletRequest request, final HttpSession session){
		String projectInfoIdStr = (String) session.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		String counterpartyIdStr = request.getParameter("counterpartyId");
		String trStr = request.getParameter("type");
		if(StringUtils.isBlank(projectInfoIdStr) || StringUtils.isBlank(counterpartyIdStr) || StringUtils.isBlank(trStr))
		{
			//Error out;
			saveError(request, "request parameters are not enough"); 
			return null;
		}
		TradingRelationship tradingRelationship = TradingRelationship.valueOf(trStr.toUpperCase());
		Long projectInfoId = Long.valueOf(projectInfoIdStr);
		Long counterpartyId = Long.valueOf(counterpartyIdStr);
		
		InstBalanceSheetModel ibs = new InstBalanceSheetModel();
		ibs.setCounterpartyId(counterpartyId);
		ibs.setProjectId(projectInfoId);
		
		ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
		Counterparty cpObj = null;
		switch (tradingRelationship)
		{
		case COUNTERPARTY:
			Set<Counterparty> cp = projectInfo.getCounterparties();
			Iterator<Counterparty> it = cp.iterator();
			while(it.hasNext())
			{
				Counterparty counterparty = it.next();
				if(counterparty.getId() == counterpartyId)
				{
					cpObj = counterparty;
					break;
				}
			}
			break;
		case GUARANTOR:
			Set<Counterparty> ga = projectInfo.getGuarantors();
			Iterator<Counterparty> iterator = ga.iterator();
			while(iterator.hasNext())
			{
				Counterparty counterparty = iterator.next();
				if(counterparty.getId() == counterpartyId)
				{
					cpObj = counterparty;
					break;
				}
			}
			break;
			default:
		}
		
		ibs.setCounterpartyName(cpObj.getName());
		InstituteBalanceSheet latestPSheet = financeSheetManager.getLatestInstituteBalanceSheet(projectInfo, cpObj);
		if(latestPSheet != null)
		{
			//TODO:
			ibs.setBeginBalSheet(latestPSheet);
			ibs.setReportYear(latestPSheet.getReportYear().toString());
		}
		else
		{
			ibs.setReportYear("");
		}
		
		return ibs;
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(InstBalanceSheetModel instBalanceSheetModel, BindingResult errors,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (method != null) {
			ModelAndView mav = new ModelAndView();
			//instBalanceSheetModel.getBeginBalSheet().setReportYear(instBalanceSheetModel.getReportYear());
			//instBalanceSheetModel.getBeginBalSheet().setProjectId(instBalanceSheetModel.getProjectId());
			//instBalanceSheetModel.getBeginBalSheet().setCounterpartyId(instBalanceSheetModel.getCounterpartyId());			
			//instBalanceSheetModel.getEndBalSheet().setReportYear(instBalanceSheetModel.getReportYear());
			//instBalanceSheetModel.getEndBalSheet().setProjectId(instBalanceSheetModel.getProjectId());
			//instBalanceSheetModel.getEndBalSheet().setCounterpartyId(instBalanceSheetModel.getCounterpartyId());
			switch (method) {
			case "Cancel":
				mav.setViewName(getCancelView());
				break;
			case "Delete":
				savedBalanceSheet.remove(instBalanceSheetModel.getReportYear());
				saveMessage(request, getText("corpBalanceSheet.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "Save":
				mav = saveInstBalanceSheet(instBalanceSheetModel, errors, request, mav);
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
	
	private ModelAndView saveInstBalanceSheet(InstBalanceSheetModel instBalanceSheetModel,
			BindingResult errors, HttpServletRequest request, ModelAndView mav)
			throws Exception {
		final Locale locale = request.getLocale();
		if (validator != null) { // validator is null during testing
			validator.validate(instBalanceSheetModel, errors);
			if (errors.hasErrors()) {
				log.debug("error happens 'onSubmit' method..."
						+ errors.toString());
				saveMessage(request, errors.toString());
				mav.setViewName("corpBalanceSheet");
				return mav;
			}
		}
		
		InstituteBalanceSheet beginBalSheet = instBalanceSheetModel.getBeginBalSheet();
		InstituteBalanceSheet endBalSheet = instBalanceSheetModel.getEndBalSheet();

		boolean isNew = (beginBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		
		if (isNew) {
			// Add
			//beginBalSheet.setCreateUser(currentUser);
			beginBalSheet.setCreateTime(new Date());
		} else {
			//User createUser = getUserManager().getUserByUsername(beginBalSheet.getCreateUser().getUsername());
			//beginBalSheet.setCreateUser(createUser);
			//beginBalSheet.setUpdateUser(currentUser);
			beginBalSheet.setUpdateTime(new Date());
		}
		
		isNew = (endBalSheet.getId() == null);
		
		if (isNew) {
			// Add
			//endBalSheet.setCreateUser(currentUser);
			endBalSheet.setCreateTime(new Date());
		} else {
			//User createUser = getUserManager().getUserByUsername(endBalSheet.getCreateUser().getUsername());
			//endBalSheet.setCreateUser(createUser);
			//endBalSheet.setUpdateUser(currentUser);
			endBalSheet.setUpdateTime(new Date());
		}		
		
		savedBalanceSheet.put(instBalanceSheetModel.getReportYear(), instBalanceSheetModel);
		mav.addObject("instBalanceSheetModel", instBalanceSheetModel);
		String key = (isNew) ? "instBalanceSheet.added" : "instBalanceSheet.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName(getSuccessView());
		return mav;
	}
	
}
