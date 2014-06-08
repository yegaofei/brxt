package com.brxt.service;

import java.util.List;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.BudgetStatement;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.FinanceStatement;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;

public interface FinanceSheetManager {

	public List<FinanceStatement> getAll(ProjectInfo projectInfo);
	
	public CorporateBalanceSheet getLatestCorpBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty);
	
	public ProfitStatement getLatestProfitStatement(ProjectInfo projectInfo, Counterparty counterparty);
	
	public InstituteBalanceSheet getLatestInstituteBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty);
	
	public BudgetStatement getLatestBudgetStatement(ProjectInfo projectInfo, Counterparty counterparty);
	
}
