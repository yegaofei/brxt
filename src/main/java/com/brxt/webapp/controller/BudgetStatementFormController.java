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
import com.brxt.model.MockBudgetRepsitory;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.StatementType;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.model.finance.BudgetStatement;
import com.brxt.model.finance.BudgetStatementModel;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/finance/budgetStatementForm*")
public class BudgetStatementFormController extends BaseFormController {
	// In the form of <2014, <201405, Statement> >
	public static Map<String, Map<String, BudgetStatement>> savedBudgetStatement = new HashMap<String, Map<String, BudgetStatement>>();
	private static final Map<String, String> availReportMonths = new TreeMap<String, String>();
	private static final Map<String, String> budgetTypes = new TreeMap<String, String>();
	private static final int monthRange = 24;

	private static final Map<String, String> statementTypes = new TreeMap<String, String>();

	public BudgetStatementFormController() {
		setCancelView("redirect:/finance/budgetStatementForm");
		setSuccessView("redirect:/finance/budgetStatementForm");
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
	public Map<String, String> getStatementTypes(
			final HttpServletRequest request) {
		if (statementTypes.isEmpty()) {
			loadDropDownList(request.getLocale());
		}
		return statementTypes;
	}

	@RequestMapping(method = RequestMethod.GET)
	protected String showForm() {
		return "/finance/budgetStatementForm";
	}

	@ModelAttribute("budgetStatementModel")
	public BudgetStatementModel getBudgetStatementModel(
			final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session
				.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		String counterpartyIdStr = request.getParameter("counterpartyId");
		String trStr = request.getParameter("type");
		String ctype = request.getParameter("ctype");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoIdStr)
				|| StringUtils.isBlank(counterpartyIdStr)
				|| StringUtils.isBlank(trStr) || StringUtils.isBlank(ctype)) {
			// Error out;
			saveError(request, "request parameters are not enough");
			return null;
		}

		
		if(ctype.equalsIgnoreCase(CounterpartyType.COMMERCE_COMPANY.toString()) || ctype.equalsIgnoreCase(CounterpartyType.REAL_ESTATE_FIRM.toString()))
		{
			saveError(request, getText("budgetStatementForm.error.type", locale));
		}
		
		TradingRelationship tradingRelationship = TradingRelationship
				.valueOf(trStr.toUpperCase());
		Long projectInfoId = Long.valueOf(projectInfoIdStr);
		Long counterpartyId = Long.valueOf(counterpartyIdStr);

		BudgetStatementModel bsm = new BudgetStatementModel();
		bsm.setCounterpartyId(counterpartyId);
		bsm.setProjectId(projectInfoId);

		ProjectInfo projectInfo = projectInfoManager.get(projectInfoId);
		Counterparty cpObj = null;
		switch (tradingRelationship) {
		case COUNTERPARTY:
			Set<Counterparty> cp = projectInfo.getCounterparties();
			Iterator<Counterparty> it = cp.iterator();
			while (it.hasNext()) {
				Counterparty counterparty = it.next();
				if (counterparty.getId() == counterpartyId) {
					cpObj = counterparty;
					break;
				}
			}
			break;
		case GUARANTOR:
			Set<Counterparty> ga = projectInfo.getGuarantors();
			Iterator<Counterparty> iterator = ga.iterator();
			while (iterator.hasNext()) {
				Counterparty counterparty = iterator.next();
				if (counterparty.getId() == counterpartyId) {
					cpObj = counterparty;
					break;
				}
			}
			break;
		default:
		}

		bsm.setCounterpartyName(cpObj.getName());
		BudgetStatement latestBudgetStatement = financeSheetManager
				.getLatestBudgetStatement(projectInfo, cpObj);
		if (latestBudgetStatement != null) {
			// TODO:
			bsm.setThisYear(latestBudgetStatement);
			bsm.setReportYear(latestBudgetStatement.getReportYear().toString());
		} else {
			bsm.setReportYear("");
		}

		return bsm;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(BudgetStatement budgetStatement,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
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
				saveMessage(request,
						getText("budgetStatementForm.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "Save":
				mav = saveBudgetStatement(budgetStatement, errors, request, mav);
				break;
			default:
				// Error
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

		boolean isNew = false;// (budgetStatement.getId() == null);
		User currentUser = getCurrentUser();
		if (isNew) {
			// Add
			budgetStatement.setCreateUser(currentUser.getUsername());
			budgetStatement.setCreateTime(new Date());
		} else {
			budgetStatement.setUpdateUser(currentUser.getUsername());
			budgetStatement.setUpdateTime(new Date());
		}

		String reportYear = budgetStatement.getReportYear().toString();
		String reportMonth = budgetStatement.getReportMonth().toString();

		// if
		// (BudgetType.BUDGET_MONTH.toString().equals(budgetStatement.getBudgetType())){
		// reportMonth = reportYear + "00";
		// }
		// budgetStatement.setReportMonth(reportMonth);

		MockBudgetRepsitory.getInstance().addOrUpdateBudget(budgetStatement);

		mav.addObject("budgetStatementFormModel", budgetStatement);
		String key = (isNew) ? "budgetStatementForm.added"
				: "budgetStatementForm.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName(getSuccessView());
		return mav;
	}

}
