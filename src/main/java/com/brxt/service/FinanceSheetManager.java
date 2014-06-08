package com.brxt.service;

import java.util.List;

import com.brxt.model.Counterparty;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.FinanceStatement;

public interface FinanceSheetManager {

	public List<FinanceStatement> getAll(ProjectInfo projectInfo);
	
	public CorporateBalanceSheet getLatestCorpBalanceSheet(ProjectInfo projectInfo, Counterparty counterparty);
	
}
