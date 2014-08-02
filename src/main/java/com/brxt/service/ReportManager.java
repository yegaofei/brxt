package com.brxt.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.brxt.model.InvestmentStatus;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.report.FinanceCheck;
import com.brxt.model.report.RiskControlReport;

public interface ReportManager extends GenericManager<RiskControlReport, Long>  {

	public RiskControlReport findRiskControlReport(ProjectInfo projectInfo, String reportSeason);
	
	public List<RiskControlReport> findByReport(RiskControlReport report);
	
	public void calculateFinanceRatio(final CorporateBalanceSheet prevTermCbs, final CorporateBalanceSheet currTermCbs,
            final ProfitStatement prevProfitStatement, final ProfitStatement currProfitStatement, final FinanceCheck financeCheck);
	
	public CorporateBalanceSheet calculate(CorporateBalanceSheet prevTermCbs, CorporateBalanceSheet currTermCbs);
	
	public List<FinanceCheck> getFinanceCheckListTab6(final RiskControlReport report);
	
	public List<FinanceCheck> getFinanceCheckList(final RiskControlReport report);
	
	public InvestmentStatus findInvesetmentStatus(ProjectInfo projectInfo, Long investmentStatusId);
	
}
