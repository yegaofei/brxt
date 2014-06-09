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
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.StatementType;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.finance.ProfitStatementModel;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/finance/profitStatement*")
public class ProfitStatementController extends BaseFormController{
	
	private Map<String,ProfitStatementModel> savedBalanceSheet= new HashMap<String,ProfitStatementModel>();
	private static final Map<String, String> statementTypes = new TreeMap<String, String>();
	
	private ProjectInfoManager projectInfoManager;
	private FinanceSheetManager financeSheetManager;
	
	public ProfitStatementController() {
		setCancelView("redirect:/finance/profitStatement");
		setSuccessView("redirect:/finance/profitStatement");
	}

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

	
	@ModelAttribute("profitStatementModel")
	public ProfitStatementModel getProfitStatementModel(final HttpServletRequest request, final HttpSession session){
		String projectInfoIdStr = (String) session.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		String counterpartyIdStr = request.getParameter("counterpartyId");
		String trStr = request.getParameter("type");
		String ctype = request.getParameter("ctype");
		final Locale locale = request.getLocale();
		if(StringUtils.isBlank(projectInfoIdStr) || StringUtils.isBlank(counterpartyIdStr) || StringUtils.isBlank(trStr))
		{
			//Error out;
			saveError(request, "request parameters are not enough"); 
			return null;
		}
		
		if(ctype.equalsIgnoreCase(CounterpartyType.INSTITUTION.toString()))
		{
			saveError(request, getText("budgetStatementForm.error.type", locale));
		}
		
		TradingRelationship tradingRelationship = TradingRelationship.valueOf(trStr.toUpperCase());
		Long projectInfoId = Long.valueOf(projectInfoIdStr);
		Long counterpartyId = Long.valueOf(counterpartyIdStr);
		ProfitStatementModel psm = new ProfitStatementModel();
		psm.setCounterpartyId(counterpartyId);
		psm.setProjectId(projectInfoId);
		
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
		
		psm.setCounterpartyName(cpObj.getName());
		ProfitStatement latestPSheet = financeSheetManager.getLatestProfitStatement(projectInfo, cpObj);
		if(latestPSheet != null)
		{
			//TODO:
			psm.setBeginBalSheet(latestPSheet);
			psm.setReportYear(latestPSheet.getReportYear().toString());
		}
		else
		{
			psm.setReportYear("2014-06");
		}
		
		return psm;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	protected String showForm() {		
		return "/finance/profitStatement";
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
			//profitStatementModel.setProjectId(beginBalSheet.getProjectId());
			//profitStatementModel.setCounterpartyId(beginBalSheet.getCounterpartyId());
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
			//profitStatementModel.getBeginBalSheet().setReportYear(profitStatementModel.getReportYear());
			//profitStatementModel.getBeginBalSheet().setProjectId(profitStatementModel.getProjectId());
			//profitStatementModel.getBeginBalSheet().setCounterpartyId(profitStatementModel.getCounterpartyId());			
			//profitStatementModel.getEndBalSheet().setReportYear(profitStatementModel.getReportYear());
			//profitStatementModel.getEndBalSheet().setProjectId(profitStatementModel.getProjectId());
			//profitStatementModel.getEndBalSheet().setCounterpartyId(profitStatementModel.getCounterpartyId());
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
		
		savedBalanceSheet.put(profitStatementModel.getReportYear(), profitStatementModel);
		mav.addObject("profitStatementModel", profitStatementModel);
		String key = (isNew) ? "profitStatement.added" : "profitStatement.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName(getSuccessView());
		return mav;
	}
	
}
