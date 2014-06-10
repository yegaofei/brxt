package com.brxt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.FinancialSheetDao;
import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.enums.StatementType;
import com.brxt.model.enums.TradingRelationship;
import com.brxt.model.finance.BudgetStatement;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.FinanceStatement;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.service.FinanceSheetManager;

@Service("financeSheetManager")
public class FinanceSheetManagerImpl implements FinanceSheetManager {

	private FinancialSheetDao<BudgetStatement, Long> budgetStatementDao;
	private FinancialSheetDao<CorporateBalanceSheet, Long> corpBalanceSheetDao;
	private FinancialSheetDao<InstituteBalanceSheet, Long> instBalanceDao;
	private FinancialSheetDao<ProfitStatement, Long> profitStatementDao;

	@Autowired
	public void setBudgetStatementDao(
			FinancialSheetDao<BudgetStatement, Long> budgetStatementDao) {
		this.budgetStatementDao = budgetStatementDao;
	}

	@Autowired
	public void setCorpBalanceSheetDao(
			FinancialSheetDao<CorporateBalanceSheet, Long> corpBalanceSheetDao) {
		this.corpBalanceSheetDao = corpBalanceSheetDao;
	}

	@Autowired
	public void setInstBalanceDao(
			FinancialSheetDao<InstituteBalanceSheet, Long> instBalanceDao) {
		this.instBalanceDao = instBalanceDao;
	}

	@Autowired
	public void setProfitStatementDao(
			FinancialSheetDao<ProfitStatement, Long> profitStatementDao) {
		this.profitStatementDao = profitStatementDao;
	}
	
	public CorporateBalanceSheet getLatestCorpBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty){
		return corpBalanceSheetDao.findLatest(projectInfo, counterparty);
	}
	
	public List<FinanceStatement> getAll(ProjectInfo projectInfo) {
		List<FinanceStatement> statements = new ArrayList<FinanceStatement>();
		List<BudgetStatement> budgetStatements = budgetStatementDao.getAll(projectInfo);
		List<CorporateBalanceSheet> corporateBalanceSheets = corpBalanceSheetDao.getAll(projectInfo);
		List<InstituteBalanceSheet> instituteBalanceSheets = instBalanceDao.getAll(projectInfo);
		List<ProfitStatement> profitStatements = profitStatementDao.getAll(projectInfo);
		
		Set<Counterparty> cp = projectInfo.getCounterparties();
		Set<Counterparty> ga = projectInfo.getGuarantors();
		
		if(budgetStatements != null && !budgetStatements.isEmpty())
		{
			String tableName = budgetStatementDao.getTableName();
			for(BudgetStatement bs : budgetStatements)
			{
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId());
				fs.setTableName(tableName);
				fs.setStatementTime(bs.getReportYear().toString() + "-" + bs.getReportMonth().toString());
				fs.setStatementType(StatementType.BUDGET_SHEET);
				
				if(cp != null && !cp.isEmpty() && cp.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.COUNTERPARTY);
				}
				
				if(ga != null && !ga.isEmpty() && ga.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.GUARANTOR);
				}	
				
				statements.add(fs);
			}
		}
		
		if(corporateBalanceSheets != null && !corporateBalanceSheets.isEmpty())
		{
			String tableName = corpBalanceSheetDao.getTableName();
			for(CorporateBalanceSheet bs : corporateBalanceSheets)
			{
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId());
				fs.setTableName(tableName);
				fs.setStatementTime(bs.getReportYear().toString() + "-" + bs.getReportMonth().toString());
				fs.setStatementType(StatementType.BALANCE_SHEET);
				
				if(cp != null && !cp.isEmpty() && cp.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.COUNTERPARTY);
				}
				
				if(ga != null && !ga.isEmpty() && ga.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.GUARANTOR);
				}	
				
				statements.add(fs);
			}
		}
		
		if(instituteBalanceSheets != null && !instituteBalanceSheets.isEmpty())
		{
			String tableName = instBalanceDao.getTableName();
			for(InstituteBalanceSheet bs : instituteBalanceSheets)
			{
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId());
				fs.setTableName(tableName);
				fs.setStatementTime(bs.getReportYear().toString() + "-" + bs.getReportMonth().toString());
				fs.setStatementType(StatementType.BALANCE_SHEET);
				
				if(cp != null && !cp.isEmpty() && cp.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.COUNTERPARTY);
				}
				
				if(ga != null && !ga.isEmpty() && ga.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.GUARANTOR);
				}	
				
				statements.add(fs);
			}
		}
		
		if(profitStatements != null && !profitStatements.isEmpty())
		{
			String tableName = profitStatementDao.getTableName();
			for(ProfitStatement bs : profitStatements)
			{
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId());
				fs.setTableName(tableName);
				fs.setStatementTime(bs.getReportYear().toString() + "-" + bs.getReportMonth().toString());
				fs.setStatementType(StatementType.PROFIT_SHEET);
				
				if(cp != null && !cp.isEmpty() && cp.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.COUNTERPARTY);
				}
				
				if(ga != null && !ga.isEmpty() && ga.contains(bs.getCounterparty()))
				{
					fs.setTradingRelationship(TradingRelationship.GUARANTOR);
				}	
				
				statements.add(fs);
			}
		}
		
		
		return statements;
	}

	@Override
	public ProfitStatement getLatestProfitStatement(ProjectInfo projectInfo,
			Counterparty counterparty) {
		return profitStatementDao.findLatest(projectInfo, counterparty);
	}

	@Override
	public InstituteBalanceSheet getLatestInstituteBalanceSheet(
			ProjectInfo projectInfo, Counterparty counterparty) {
		return instBalanceDao.findLatest(projectInfo, counterparty);
	}

	@Override
	public BudgetStatement getLatestBudgetStatement(ProjectInfo projectInfo,
			Counterparty counterparty) {
		return budgetStatementDao.findLatest(projectInfo, counterparty);
	}

	@Override
	public void saveCorpBalanceSheets(CorporateBalanceSheet beginB,
			CorporateBalanceSheet endB) {
		corpBalanceSheetDao.save(beginB);
		corpBalanceSheetDao.save(endB);
	}
	
	public CorporateBalanceSheet findCorporateBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty, Integer year, Integer month){
		return corpBalanceSheetDao.find(projectInfo, counterparty, year, month.shortValue());
	}

	@Override
	public ProfitStatement findProfitStatement(ProjectInfo projectInfo,
			Counterparty counterparty, Integer year, Integer month) {
		return profitStatementDao.find(projectInfo, counterparty, year, month.shortValue());
	}

	@Override
	public void saveProfitStatement(ProfitStatement statement) {
		profitStatementDao.save(statement);
	}

	@Override
	public InstituteBalanceSheet findInstituteBalanceSheet(
			ProjectInfo projectInfo, Counterparty counterparty, Integer year,
			Integer month) {
		return instBalanceDao.find(projectInfo, counterparty, year, month.shortValue());
	}

	@Override
	public void saveInstituteBalanceSheets(InstituteBalanceSheet beginB,
			InstituteBalanceSheet endB) {
		instBalanceDao.save(beginB);
		instBalanceDao.save(endB);
	}

	@Override
	public BudgetStatement findBudgetStatement(ProjectInfo projectInfo,
			Counterparty counterparty, Integer year, Integer month) {
		return budgetStatementDao.find(projectInfo, counterparty, year, month.shortValue());
	}

	@Override
	public void saveBudgetStatements(BudgetStatement beginB,
			BudgetStatement endB) {
		budgetStatementDao.save(beginB);
		budgetStatementDao.save(endB);
	}

	
}
