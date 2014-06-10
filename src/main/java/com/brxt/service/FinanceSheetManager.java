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

	public Long getRealId(Long id);
	public List<FinanceStatement> getAll(ProjectInfo projectInfo);
	public String getStatementForm(Long id);
	
	public CorporateBalanceSheet getCorpBalanceSheet(Long id);
	public CorporateBalanceSheet getLatestCorpBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty);
	public CorporateBalanceSheet findCorporateBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Integer month);
	public void saveCorpBalanceSheets(CorporateBalanceSheet beginB, CorporateBalanceSheet endB);
	
	public ProfitStatement getProfitStatement(Long id);
	public ProfitStatement getLatestProfitStatement(ProjectInfo projectInfo, Counterparty counterparty);
	public ProfitStatement findProfitStatement(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Integer month);
	public void saveProfitStatement(ProfitStatement statement);
	
	public InstituteBalanceSheet getInstituteBalanceSheet(Long id);
	public InstituteBalanceSheet getLatestInstituteBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty);
	public InstituteBalanceSheet findInstituteBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Integer month);
	public void saveInstituteBalanceSheets(InstituteBalanceSheet beginB, InstituteBalanceSheet endB);
	
	public BudgetStatement getBudgetStatement(Long id);
	public BudgetStatement getLatestBudgetStatement(ProjectInfo projectInfo, Counterparty counterparty);
	public BudgetStatement findBudgetStatement(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Integer month);
	public void saveBudgetStatements(BudgetStatement beginB, BudgetStatement endB);
	
	public void remove(Long financialStatementId);
}
