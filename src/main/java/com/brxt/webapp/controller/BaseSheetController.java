package com.brxt.webapp.controller;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.StatementType;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

public class BaseSheetController extends BaseFormController {

	protected static final Map<String, String> statementTypes = new TreeMap<String, String>();
	protected ProjectInfoManager projectInfoManager;
	protected FinanceSheetManager financeSheetManager;
	protected static final Calendar CALENDAR = Calendar.getInstance(Locale.CHINA);
	
	protected synchronized void loadDropDownList(final Locale locale) {
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
	
	protected Integer getCurrentYear()
	{
		return CALENDAR.get(Calendar.YEAR);
	}
	
	protected Integer getCurrentMonth()
	{
		return CALENDAR.get(Calendar.MONTH) + 1;
	}
	
	protected Counterparty findCounterparty(ProjectInfo projectInfo, Long counterpartyId)
	{
		Set<Counterparty> cp = projectInfo.getCounterparties();
		Counterparty cpObj = null;
		Iterator<Counterparty> it = cp.iterator();
		while (it.hasNext()) {
			Counterparty counterparty = it.next();
			if (counterparty.getId() == counterpartyId) {
				cpObj = counterparty;
				break;
			}
		}
		return cpObj;
	}
	
	protected Counterparty findGuarantor(ProjectInfo projectInfo, Long counterpartyId)
	{
		Set<Counterparty> ga = projectInfo.getGuarantors();
		Counterparty cpObj = null;
		Iterator<Counterparty> iterator = ga.iterator();
		while (iterator.hasNext()) {
			Counterparty counterparty = iterator.next();
			if (counterparty.getId() == counterpartyId) {
				cpObj = counterparty;
				break;
			}
		}
		return cpObj;
	}

}
