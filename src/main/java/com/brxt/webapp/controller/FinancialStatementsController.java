package com.brxt.webapp.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.brxt.model.finance.FinanceStatement;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

@Controller
public class FinancialStatementsController extends BaseFormController {

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
	
	public List<FinanceStatement> getFinanceStatements(final HttpServletRequest request, final HttpSession session) {
		String projectInfoId = (String) session.getAttribute(SessionAttributes.PROJECT_INFO_ID);
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(projectInfoId)) {
			saveError(request, getText("errors.projectInfoId.required", locale));
			log.error("errors.projectInfoId.required");
			return null;
		} 
		ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
		return financeSheetManager.getAll(projectInfo);
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
	
	@RequestMapping(value="/finance/addStatements*", method = RequestMethod.GET)
	public String addStatements(final HttpServletRequest request)
			throws Exception {
		String returnView = "/finance/addStatements";
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
		return returnView;
	}

	@RequestMapping(value="/finance/financialStatements*", method = RequestMethod.GET)
	public ModelAndView handleRequest(final HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("/finance/financialStatements");
		mav.addObject("financeStatementList", getFinanceStatements(request, request.getSession()));
		return mav;
	}
	
	@RequestMapping(value="/finance/financialStatements*", method = RequestMethod.POST)
	public String onSubmit(final HttpServletRequest request) throws Exception {
		String method = request.getParameter("method");
		String financialStatementId = request.getParameter("id");
		final Locale locale = request.getLocale();
		if (StringUtils.isBlank(method)
				|| StringUtils.isBlank(financialStatementId)) {
			saveError(request, getText("errors.repaymentId.required", locale));
		} else {
			final Long financialStatementID = Long.valueOf(financialStatementId);
			switch (method) {
			case "Edit":
				String editForm = financeSheetManager.getStatementForm(financialStatementID);
				Long id = financeSheetManager.getRealId(financialStatementID);
				return "redirect:/finance/" + editForm + "?id=" + id;
			case "Delete":
				financeSheetManager.remove(financialStatementID);
				//Update the project info status after deleting
				String projectInfoId = (String) request.getSession().getAttribute(SessionAttributes.PROJECT_INFO_ID);
				ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
				List<FinanceStatement> financeSttements = financeSheetManager.getAll(projectInfo);
				if(financeSttements == null || financeSttements.isEmpty())
				{
					projectInfo.getProjectInfoStatus().setFinanceStatement(false);
					projectInfoManager.save(projectInfo);
				}
				
				saveMessage(request,
						getText("financeStatement.delete.successful", locale));
				return "redirect:/finance/financialStatements";
			default:
			}
		}
		return "/finance/financialStatements";
	}

}
