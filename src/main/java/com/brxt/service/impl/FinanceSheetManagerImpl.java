package com.brxt.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
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

	private static final Long BASE_BUDGETSTATEMENT_ID = 100000L;
	private static final Long BASE_CORPBALANCESHEET_ID = 300000L;
	private static final Long BASE_INSTBALANCE_ID = 600000L;
	private static final Long BASE_PROFITSTATEMENT_ID = 900000L;
	
	public Long getRealId(Long id)
	{
		Long realId = 0L;
		if(id > BASE_PROFITSTATEMENT_ID)
		{
			realId = id - BASE_PROFITSTATEMENT_ID;
		}
		else if (id > BASE_INSTBALANCE_ID)
		{
			realId = id - BASE_INSTBALANCE_ID;
		} 
		else if (id > BASE_CORPBALANCESHEET_ID)
		{
			realId = id - BASE_CORPBALANCESHEET_ID;
		}
		else if (id > BASE_BUDGETSTATEMENT_ID)
		{
			realId = id - BASE_BUDGETSTATEMENT_ID;
		}
		return realId;
	}
	
	public String getStatementForm(Long id)
	{
		if(id > BASE_PROFITSTATEMENT_ID)
		{
			return "profitStatement"; 
		}
		else if (id > BASE_INSTBALANCE_ID)
		{
			return "instBalanceSheet";
		} 
		else if (id > BASE_CORPBALANCESHEET_ID)
		{
			return  "corpBalanceSheet";
		}
		else if (id > BASE_BUDGETSTATEMENT_ID)
		{
			return "budgetStatementForm";
		}
		return "";
	}
	
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
		Set<Counterparty> counterparties = new HashSet<Counterparty>();
		counterparties.addAll(projectInfo.getCounterparties());
		counterparties.addAll(projectInfo.getGuarantors());
		List<BudgetStatement> budgetStatements = budgetStatementDao.getAll(counterparties);
		List<CorporateBalanceSheet> corporateBalanceSheets = corpBalanceSheetDao.getAll(counterparties);
		List<InstituteBalanceSheet> instituteBalanceSheets = instBalanceDao.getAll(counterparties);
		List<ProfitStatement> profitStatements = profitStatementDao.getAll(counterparties);
		
		Set<Counterparty> cp = projectInfo.getCounterparties();
		Set<Counterparty> ga = projectInfo.getGuarantors();
		
		if(budgetStatements != null && !budgetStatements.isEmpty())
		{
			String tableName = budgetStatementDao.getTableName();
			for(BudgetStatement bs : budgetStatements)
			{
				if(bs.getReportMonth() == (short)0)
				{
					continue;
				}
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId() + BASE_BUDGETSTATEMENT_ID);
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
				if(bs.getReportMonth() == (short)0)
				{
					continue;
				}
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId() + BASE_CORPBALANCESHEET_ID);
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
				if(bs.getReportMonth() == (short)0)
				{
					continue;
				}
				FinanceStatement fs = new FinanceStatement();
				fs.setCounterpartyName(bs.getCounterparty().getName());
				fs.setId(bs.getId() + BASE_INSTBALANCE_ID);
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
				fs.setId(bs.getId() + BASE_PROFITSTATEMENT_ID);
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

	@Override
	public void remove(Long id) {
		
		Long realId = 0L;
		
		if (id > BASE_PROFITSTATEMENT_ID)
		{
			realId = id - BASE_PROFITSTATEMENT_ID;
			profitStatementDao.remove(realId);
		}
		else if (id > BASE_INSTBALANCE_ID)
		{
			realId = id - BASE_INSTBALANCE_ID;
			instBalanceDao.remove(realId);
		}
		else if (id > BASE_CORPBALANCESHEET_ID)
		{
			realId = id - BASE_CORPBALANCESHEET_ID;
			corpBalanceSheetDao.remove(realId);
		} 
		else if(id > BASE_BUDGETSTATEMENT_ID)
		{
			realId = id - BASE_BUDGETSTATEMENT_ID;
			budgetStatementDao.remove(realId);
		}
	}

	@Override
	public CorporateBalanceSheet getCorpBalanceSheet(Long id) {
		return corpBalanceSheetDao.get(id);
	}

	@Override
	public ProfitStatement getProfitStatement(Long id) {
		return profitStatementDao.get(id);
	}

	@Override
	public InstituteBalanceSheet getInstituteBalanceSheet(Long id) {
		return instBalanceDao.get(id);
	}

	@Override
	public BudgetStatement getBudgetStatement(Long id) {
		return budgetStatementDao.get(id);
	}

	
}
