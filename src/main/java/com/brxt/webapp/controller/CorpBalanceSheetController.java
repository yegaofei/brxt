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
import com.brxt.model.finance.CorpBalanceSheetModel;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/finance/corpBalanceSheet*")
public class CorpBalanceSheetController extends BaseFormController {

	private Map<String, CorpBalanceSheetModel> savedBalanceSheet = new HashMap<String, CorpBalanceSheetModel>();
	private static final Map<String, String> statementTypes = new TreeMap<String, String>();

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

	public CorpBalanceSheetController() {
		setCancelView("redirect:/finace/corpBalanceSheet");
		setSuccessView("redirect:/finance/corpBalanceSheet");
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
	public String showForm() {
		return "/finance/corpBalanceSheet";
	}

	@ModelAttribute("corpBalanceSheetModel")
	public CorpBalanceSheetModel getCorpBalanceSheetModel(
			final HttpServletRequest request, final HttpSession session) {
		String projectInfoIdStr = (String) session
				.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		String counterpartyIdStr = request.getParameter("counterpartyId");
		String trStr = request.getParameter("type");
		String ctypeStr = request.getParameter("ctype");
		if (StringUtils.isBlank(projectInfoIdStr)
				|| StringUtils.isBlank(counterpartyIdStr)
				|| StringUtils.isBlank(trStr) || StringUtils.isBlank(ctypeStr)) {
			// Error out;
		}

		TradingRelationship tradingRelationship = TradingRelationship
				.valueOf(trStr.toUpperCase());
		Long projectInfoId = Long.valueOf(projectInfoIdStr);
		Long counterpartyId = Long.valueOf(counterpartyIdStr);

		CorpBalanceSheetModel csm = new CorpBalanceSheetModel();
		csm.setCounterpartyId(counterpartyId);
		csm.setProjectId(projectInfoId);
		csm.setTradingRelationship(tradingRelationship);

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

		csm.setCounterpartyName(cpObj.getName());
		CorporateBalanceSheet latestCBSheet = financeSheetManager
				.getLatestCorpBalanceSheet(projectInfo, cpObj);
		if (latestCBSheet != null) {
			// TODO:
			csm.setBeginBalSheet(latestCBSheet);
			csm.setReportYear(latestCBSheet.getReportYear().toString());
		}

		return csm;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView onSubmit(CorpBalanceSheetModel corpBalanceSheetModel,
			BindingResult errors, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String method = request.getParameter("method");
		final Locale locale = request.getLocale();
		if (method != null) {
			ModelAndView mav = new ModelAndView();
			// corpBalanceSheetModel.getBeginBalSheet().setReportYear(corpBalanceSheetModel.getReportYear());
			// corpBalanceSheetModel.getBeginBalSheet().setProjectId(corpBalanceSheetModel.getCounterparty().getId());
			// corpBalanceSheetModel.getBeginBalSheet().setCounterparty(corpBalanceSheetModel.getCounterparty());
			// corpBalanceSheetModel.getEndBalSheet().setReportYear(corpBalanceSheetModel.getReportYear());
			// corpBalanceSheetModel.getEndBalSheet().setProjectId(corpBalanceSheetModel.getProjectId());
			// corpBalanceSheetModel.getEndBalSheet().setCounterparty(corpBalanceSheetModel.getCounterparty());
			switch (method) {
			case "Cancel":
				mav.setViewName(getCancelView());
				break;
			case "Delete":
				// savedBalanceSheet.remove(corpBalanceSheetModel.getReportYear());
				saveMessage(request,
						getText("corpBalanceSheet.deleted", locale));
				mav.setViewName(getSuccessView());
				break;
			case "Save":
				mav = saveCorpBalanceSheet(corpBalanceSheetModel, errors,
						request, mav);
				break;
			default:
				// Error
			}
			return mav;
		} else {
			// error
		}
		return new ModelAndView("corpBalanceSheet");
	}

	private ModelAndView saveCorpBalanceSheet(
			CorpBalanceSheetModel corpBalanceSheetModel, BindingResult errors,
			HttpServletRequest request, ModelAndView mav) throws Exception {
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

		CorporateBalanceSheet beginBalSheet = corpBalanceSheetModel
				.getBeginBalSheet();
		CorporateBalanceSheet endBalSheet = corpBalanceSheetModel
				.getEndBalSheet();

		boolean isNew = (beginBalSheet.getId() == null);
		User currentUser = getCurrentUser();
		if (isNew) {
			// Add
			beginBalSheet.setCreateUser(currentUser.getUsername());
			beginBalSheet.setCreateTime(new Date());
		} else {
			// User createUser =
			// getUserManager().getUserByUsername(beginBalSheet.getCreateUser().getUsername());
			// beginBalSheet.setCreateUser(createUser);
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

		savedBalanceSheet.put(corpBalanceSheetModel.getReportYear(),
				corpBalanceSheetModel);
		mav.addObject("corpBalanceSheetModel", corpBalanceSheetModel);
		String key = (isNew) ? "corpBalanceSheet.added"
				: "corpBalanceSheet.updated";
		saveMessage(request, getText(key, locale));
		mav.setViewName(getSuccessView());
		return mav;
	}

}
