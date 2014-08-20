package com.brxt.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.StatementType;
import com.brxt.service.FinanceSheetManager;
import com.brxt.service.ProjectInfoManager;

public class BaseSheetController extends BaseFormController {

	protected static final Map<String, String> statementTypes1 = new TreeMap<String, String>();
	protected static final Map<String, String> statementTypes2 = new TreeMap<String, String>();
	protected ProjectInfoManager projectInfoManager;
	protected FinanceSheetManager financeSheetManager;
	protected static final Calendar CALENDAR = Calendar.getInstance(Locale.CHINA);
	
	protected synchronized void loadDropDownList(final Locale locale) {
		if (statementTypes1.isEmpty()) {
			StatementType[] types = StatementType.values();
			for (StatementType st : types) {
				if(st == StatementType.PROFIT_SHEET) continue;
				statementTypes1.put(st.toString(),
						getText(st.toString(), locale));
			}
		}
		
		if (statementTypes2.isEmpty()) {
			StatementType[] types = StatementType.values();
			for (StatementType st : types) {
				if(st == StatementType.BUDGET_SHEET) continue;
				statementTypes2.put(st.toString(),
						getText(st.toString(), locale));
			}
		}
	}
	
	@ModelAttribute("statementTypes1")
	public Map<String, String> getStatementTypes1(
			final HttpServletRequest request) {
		if (statementTypes1.isEmpty()) {
			loadDropDownList(request.getLocale());
		}
		return statementTypes1;
	}
	
	@ModelAttribute("statementTypes2")
	public Map<String, String> getStatementTypes2(
			final HttpServletRequest request) {
		if (statementTypes2.isEmpty()) {
			loadDropDownList(request.getLocale());
		}
		return statementTypes2;
	}
	
	@Override
	@InitBinder
	protected void initBinder(final HttpServletRequest request,
			final ServletRequestDataBinder binder) {
		super.initBinder(request, binder);
		SimpleDateFormat checkTimeDateFormat = new SimpleDateFormat(getText(
				"date.format.short", request.getLocale()));
		binder.registerCustomEditor(Date.class, "reportTime",
				new CustomDateEditor(checkTimeDateFormat, true));
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
	
	protected Integer getYear(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}
	
	protected Short getMonth(Date date)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.setTime(date);
		return (short)(calendar.get(Calendar.MONTH) + 1);
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
			if (counterparty.getId().equals(counterpartyId)) {
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
			if (counterparty.getId().equals(counterpartyId)) {
				cpObj = counterparty;
				break;
			}
		}
		return cpObj;
	}
	
	protected void updateProjectInfoStatus(ProjectInfo projectInfo, boolean newStatus)
	{
		if(projectInfo.getProjectInfoStatus().getFinanceStatement() != newStatus)
		{
			projectInfo.getProjectInfoStatus().setFinanceStatement(newStatus);
			projectInfoManager.save(projectInfo);
		}
	}
	
	protected Date getDateObj(Integer year, Short month)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINESE);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, (int)month - 1);
		calendar.set(Calendar.DATE, 1);
		return calendar.getTime();
	}
	
	

}
