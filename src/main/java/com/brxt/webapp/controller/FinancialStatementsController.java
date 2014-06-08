package com.brxt.webapp.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.tuckey.web.filters.urlrewrite.utils.StringUtils;

import com.brxt.constant.SessionAttributes;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

@Controller
@RequestMapping("/finance/financialStatements*")
public class FinancialStatementsController extends BaseFormController {

	private ProjectInfoManager projectInfoManager;
	private FinanceSheetManager financeSheetManager;

	@Autowired
	public void setProjectInfoManager(
			@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	public void setFinanceSheetManager(
			@Qualifier("financeSheetManager") FinanceSheetManager financeSheetManager) {
		this.financeSheetManager = financeSheetManager;
	}

	protected Long getProjectInfoId(final HttpServletRequest request) {
		String projectInfoId = (String) request.getSession().getAttribute(
				SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
		} else {
			return Long.valueOf(projectInfoId);
		}
		return null;
	}

	@ModelAttribute
	protected ProjectInfo getProjectInfo(final HttpServletRequest request) {
		Long projectInfoId = getProjectInfoId(request);
		if (projectInfoId != null) {
			return projectInfoManager.get(projectInfoId);
		} else {
			// Error
		}
		return null;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		String returnView = null;
		String counterpartyId = request.getParameter("counterpartyId");
		String guarantorId = request.getParameter("guarantorId");
		String counterpartyType = request.getParameter("counterpartyType");
		if (!StringUtils.isBlank(counterpartyType)) {
			CounterpartyType type = CounterpartyType.valueOf(counterpartyType
					.toUpperCase());
			switch (type) {
			case REAL_ESTATE_FIRM:
			case COMMERCE_COMPANY:
				returnView = "redirect:/finance/corpBalanceSheet";
				break;
			case INSTITUTION:
				returnView = "redirect:/finance/budgetStatementForm";
				break;
			default:
			}

			if (!StringUtils.isBlank(counterpartyId)) {
				returnView += ("?counterpartyId=" + counterpartyId + "&type="
						+ TradingRelationship.COUNTERPARTY.getTitle()
						+ "&ctype=" + type.getTitle());
			}

			if (!StringUtils.isBlank(guarantorId)) {
				returnView += ("?counterpartyId=" + guarantorId + "&type="
						+ TradingRelationship.GUARANTOR.getTitle() + "&ctype=" + type
						.getTitle());
			}
		}
		mav.setViewName(returnView);
		return mav;
	}

}
