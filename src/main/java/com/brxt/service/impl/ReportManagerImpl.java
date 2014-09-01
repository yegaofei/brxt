package com.brxt.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brxt.dao.RiskControlReportDao;
import com.brxt.model.Counterparty;
import com.brxt.model.CreditInformation;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.report.FinanceCheck;
import com.brxt.model.report.FinanceRatio;
import com.brxt.model.report.RiskControlReport;
import com.brxt.service.ReportManager;

@Service("reportManager")
public class ReportManagerImpl extends
		GenericManagerImpl<RiskControlReport, Long> implements ReportManager {

	RiskControlReportDao riskControlReportDao;
	private static final MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
	
	@Autowired
	public void setRiskControlReportDao(RiskControlReportDao riskControlReportDao) {
		this.dao = riskControlReportDao;
		this.riskControlReportDao = riskControlReportDao;
	}
	
	public RiskControlReport findRiskControlReport(ProjectInfo projectInfo, String reportSeason)
	{
		return riskControlReportDao.find(projectInfo, reportSeason);
	}
	
	public List<RiskControlReport> findByReport(RiskControlReport report){
		return riskControlReportDao.findByReport(report);
	}

    @Override
    public void calculateFinanceRatio(CorporateBalanceSheet prevTermCbs, CorporateBalanceSheet currTermCbs, ProfitStatement prevProfitStatement,
            ProfitStatement currProfitStatement, FinanceCheck financeCheck) {
        if (prevTermCbs != null) {
            FinanceRatio prevFinanceRatio = new FinanceRatio();
            prevFinanceRatio.setAssetLiabilityRatio(calculateRatio(prevTermCbs.getTotalDebt(), prevTermCbs.getTotalAsset()));
            if(prevProfitStatement != null)
            {
                prevFinanceRatio.setAssetRoR(calculateRatio(prevProfitStatement.getNetProfit(), prevTermCbs.getNetAsset()));
            }
            prevFinanceRatio.setLiquidityRatio(calculateRatio(prevTermCbs.getLiquidAsset(), prevTermCbs.getLiquidDebt()));
            if(prevTermCbs.getLiquidAsset() != null)
            {
                prevFinanceRatio.setQuickRatio(calculateRatio(prevTermCbs.getLiquidAsset().subtract(prevTermCbs.getInventory()),
                        prevTermCbs.getLiquidDebt()));
            }
            if (currProfitStatement != null && prevProfitStatement != null) {
                prevFinanceRatio.setSalesIncrementRatio(calculateRatio(
                        currProfitStatement.getOperatingIncome().subtract(prevProfitStatement.getOperatingIncome()),
                        prevProfitStatement.getOperatingIncome()));
            }
            financeCheck.setPrevFinanceRatio(prevFinanceRatio);
        } 

        if (currTermCbs != null) {
            FinanceRatio currFinanceRatio = new FinanceRatio();
            currFinanceRatio.setAssetLiabilityRatio(calculateRatio(currTermCbs.getTotalDebt(), currTermCbs.getTotalAsset()));
            if(currProfitStatement != null)
            {
                currFinanceRatio.setAssetRoR(calculateRatio(currProfitStatement.getNetProfit(), currTermCbs.getNetAsset()));
            }
            currFinanceRatio.setLiquidityRatio(calculateRatio(currTermCbs.getLiquidAsset(), currTermCbs.getLiquidDebt()));
            if(currTermCbs.getLiquidAsset() != null){
            currFinanceRatio.setQuickRatio(calculateRatio(currTermCbs.getLiquidAsset().subtract(currTermCbs.getInventory()),
                    currTermCbs.getLiquidDebt()));
            }
            financeCheck.setCurrFinanceRatio(currFinanceRatio);
        }
        
        if(financeCheck.getCurrFinanceRatio() != null && financeCheck.getPrevFinanceRatio() != null)
        {
            FinanceRatio financeRatioChanges = new FinanceRatio();
            FinanceRatio currFinanceRatio = financeCheck.getCurrFinanceRatio();
            FinanceRatio prevFinanceRatio = financeCheck.getPrevFinanceRatio();
            
            if(currFinanceRatio.getAssetLiabilityRatio() != null && prevFinanceRatio != null)
            {
                financeRatioChanges.setAssetLiabilityRatio(currFinanceRatio.getAssetLiabilityRatio().subtract(prevFinanceRatio.getAssetLiabilityRatio()));
            }
            
            if(currFinanceRatio.getLiquidityRatio() != null && prevFinanceRatio != null)
            {
                financeRatioChanges.setLiquidityRatio(currFinanceRatio.getLiquidityRatio().subtract(prevFinanceRatio.getLiquidityRatio()));                         
            }
            
            if(currFinanceRatio.getQuickRatio() != null && prevFinanceRatio != null)
            {
                financeRatioChanges.setQuickRatio(currFinanceRatio.getQuickRatio().subtract(prevFinanceRatio.getQuickRatio()));
            }
            
            if(currFinanceRatio.getAssetRoR() != null && prevFinanceRatio != null)
            {
                financeRatioChanges.setAssetRoR(currFinanceRatio.getAssetRoR().subtract(prevFinanceRatio.getAssetRoR()));
            }
            financeCheck.setFinanceRatioChanges(financeRatioChanges);
        }
    }
    
    public CorporateBalanceSheet calculate(CorporateBalanceSheet prevTermCbs, CorporateBalanceSheet currTermCbs) {
        if (prevTermCbs == null || currTermCbs == null) {
            return null;
        }

        CorporateBalanceSheet corpBalanceSheetChanges = new CorporateBalanceSheet();
        corpBalanceSheetChanges.setTotalAsset(calculateChangesRatio(currTermCbs.getTotalAsset(), prevTermCbs.getTotalAsset()));
        corpBalanceSheetChanges.setCash(calculateChangesRatio(currTermCbs.getCash(), prevTermCbs.getCash()));
        corpBalanceSheetChanges.setInventory(calculateChangesRatio(currTermCbs.getInventory(), prevTermCbs.getInventory()));
        corpBalanceSheetChanges.setNonLiquid(calculateChangesRatio(currTermCbs.getNonLiquid(), prevTermCbs.getNonLiquid()));
        corpBalanceSheetChanges.setTotalDebt(calculateChangesRatio(currTermCbs.getTotalDebt(), prevTermCbs.getTotalDebt()));
        corpBalanceSheetChanges.setPrereceive(calculateChangesRatio(currTermCbs.getPrereceive(), prevTermCbs.getPrereceive()));
        corpBalanceSheetChanges.setShortLoan(calculateChangesRatio(currTermCbs.getShortLoan(), prevTermCbs.getShortLoan()));
        corpBalanceSheetChanges.setLongLoan(calculateChangesRatio(currTermCbs.getLongLoan(), prevTermCbs.getLongLoan()));
        corpBalanceSheetChanges.setNetAsset(calculateChangesRatio(currTermCbs.getNetAsset(), prevTermCbs.getNetAsset()));
        corpBalanceSheetChanges.setActualCapital(calculateChangesRatio(currTermCbs.getActualCapital(), prevTermCbs.getActualCapital()));
        return corpBalanceSheetChanges;
    }
    
    @Override
    public ProfitStatement calculate(ProfitStatement prevProfit, ProfitStatement currProfit) {
        if (prevProfit == null || currProfit == null) {
            return null;
        }
        
        ProfitStatement profitStatementChanges = new ProfitStatement();
        profitStatementChanges.setNetProfit(calculateChangesRatio(currProfit.getNetProfit(), prevProfit.getNetProfit()));
        profitStatementChanges.setOperatingProfit(calculateChangesRatio(currProfit.getOperatingProfit(), prevProfit.getOperatingProfit()));
        profitStatementChanges.setOperatingIncome(calculateChangesRatio(currProfit.getOperatingIncome(), prevProfit.getOperatingIncome()));
        profitStatementChanges.setOperatingTax(calculateChangesRatio(currProfit.getOperatingTax(), prevProfit.getOperatingTax()));
        return profitStatementChanges;
    }

    private final BigDecimal calculateRatio(BigDecimal number, BigDecimal baseNumber) {
        if (number == null || baseNumber == null) {
            return null;
        }

        if (baseNumber.compareTo(BigDecimal.ZERO) != 0) {
            return number.divide(baseNumber, mc);
        }

        return null;
    }

    private final BigDecimal calculateChangesRatio(BigDecimal number, BigDecimal baseNumber) {
        if (number == null || baseNumber == null) {
            return null;
        }

        if (baseNumber.compareTo(BigDecimal.ZERO) != 0) {
            BigDecimal changes = number.subtract(baseNumber);
            return changes.divide(baseNumber, mc);
        }
        return null;
    }
    
    public final List<FinanceCheck> getFinanceCheckListTab6(final RiskControlReport report)
    {
        Map<Counterparty, FinanceCheck> financeCheckTab6Map = new HashMap<Counterparty, FinanceCheck>();
        final ProjectInfo projectInfo = report.getProjectInfo();
        if(report.getGuarantorCorpBalanceSheets() != null)
        {
            for(CorporateBalanceSheet cbs : report.getGuarantorCorpBalanceSheets())
            {
                Long counterpartyId = cbs.getCounterparty().getId();
                if(findGuarantor(projectInfo, counterpartyId) == null) {
                    continue;
                }
                
                FinanceCheck financeCheck = new FinanceCheck();
                financeCheck.setCounterparty(cbs.getCounterparty());
                Iterator<CorporateBalanceSheet> itCbs = report.getGuarantorCorpBalanceSheets().iterator();
                while(itCbs.hasNext())
                {
                    CorporateBalanceSheet cbs2 = itCbs.next();
                    if(!cbs2.equals(cbs) && cbs2.getCounterparty().equals(cbs.getCounterparty()))
                    {
                        if(cbs2.getReportYear().intValue() > cbs.getReportYear().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs2);
                            financeCheck.setPrevCorpBalanceSheet(cbs);
                        } 
                        else if (cbs2.getReportYear().intValue() < cbs.getReportYear().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs);
                            financeCheck.setPrevCorpBalanceSheet(cbs2);
                        } 
                        else if (cbs2.getReportMonth().intValue() > cbs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs2);
                            financeCheck.setPrevCorpBalanceSheet(cbs);
                        } else if (cbs2.getReportMonth().intValue() < cbs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs);
                            financeCheck.setPrevCorpBalanceSheet(cbs2);
                        }
                        
                        CorporateBalanceSheet corpBalanceSheetChanges = calculate(financeCheck.getPrevCorpBalanceSheet(), financeCheck.getCurrCorpBalanceSheet());
                        financeCheck.setCorpBalanceSheetChanges(corpBalanceSheetChanges);
                        financeCheckTab6Map.put(cbs.getCounterparty(), financeCheck);
                        break;
                    }
                }
            }
        }
        
        if(report.getGuarantorInstBalanceSheet() != null)
        {
            for(InstituteBalanceSheet ibs : report.getGuarantorInstBalanceSheet())
            {
                Long counterpartyId = ibs.getCounterparty().getId();
                if(findGuarantor(projectInfo, counterpartyId) == null) {
                    continue;
                }
                
                FinanceCheck financeCheck = financeCheckTab6Map.get(ibs.getCounterparty()); 
                if(financeCheck == null){
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(ibs.getCounterparty());
                }
                
                Iterator<InstituteBalanceSheet> itCbs = report.getGuarantorInstBalanceSheet().iterator();
                while(itCbs.hasNext())
                {
                    InstituteBalanceSheet ibs2 = itCbs.next();
                    if(!ibs2.equals(ibs) && ibs2.getCounterparty().equals(ibs.getCounterparty()))
                    {
                        if(ibs2.getReportYear().intValue() > ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs2);
                            financeCheck.setPrevInstituteBalanceSheet(ibs);
                        } 
                        else if (ibs2.getReportYear().intValue() < ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs);
                            financeCheck.setPrevInstituteBalanceSheet(ibs2);
                        } 
                        else if (ibs2.getReportMonth().intValue() > ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs2);
                            financeCheck.setPrevInstituteBalanceSheet(ibs);
                        } else if (ibs2.getReportMonth().intValue() < ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs);
                            financeCheck.setPrevInstituteBalanceSheet(ibs2);
                        }
                        break;
                    }
                }
                financeCheckTab6Map.put(ibs.getCounterparty(), financeCheck);
            }
        }
        
        if(report.getProfitStatements() != null && report.getProfitStatements().size() > 0)
        {
            for(ProfitStatement ibs : report.getProfitStatements())
            {
                Long counterpartyId = ibs.getCounterparty().getId();
                if(findGuarantor(projectInfo, counterpartyId) == null) {
                    continue;
                }
                
                FinanceCheck financeCheck = financeCheckTab6Map.get(ibs.getCounterparty()) ;
                if(financeCheck == null)
                {
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(ibs.getCounterparty());
                }
                
                if(financeCheck.getCurrProfitStatement() != null && financeCheck.getPrevProfitStatement() != null)
                {
                    continue;
                }
                
                Iterator<ProfitStatement> itPs = report.getProfitStatements().iterator();
                while(itPs.hasNext())
                {
                    ProfitStatement ibs2 = itPs.next();
                    if(!ibs2.equals(ibs) && ibs2.getCounterparty().equals(ibs.getCounterparty()))
                    {
                        if(ibs2.getReportYear().intValue() > ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs2);
                            financeCheck.setPrevProfitStatement(ibs);
                        } 
                        else if (ibs2.getReportYear().intValue() < ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs);
                            financeCheck.setPrevProfitStatement(ibs2);
                        } 
                        else if (ibs2.getReportMonth().intValue() > ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs2);
                            financeCheck.setPrevProfitStatement(ibs);
                        } else if (ibs2.getReportMonth().intValue() < ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs);
                            financeCheck.setPrevProfitStatement(ibs2);
                        }
                        calculateFinanceRatio(financeCheck.getPrevCorpBalanceSheet(), financeCheck.getCurrCorpBalanceSheet(), 
                                financeCheck.getPrevProfitStatement(), financeCheck.getCurrProfitStatement(), financeCheck);
                        
                        ProfitStatement profitStatementChanges = calculate(financeCheck.getPrevProfitStatement(), financeCheck.getCurrProfitStatement());
                        financeCheck.setProfitStatementChanges(profitStatementChanges);
                        financeCheckTab6Map.put(ibs.getCounterparty(), financeCheck);
                        break;
                    }
                }
            }
        }
        
        if(report.getCreditInformations() != null)
        {
            for(CreditInformation ci : report.getCreditInformations()) 
            {
                if(findGuarantor(report.getProjectInfo(), ci.getCounterparty().getId()) == null)
                {
                    continue;
                }
                FinanceCheck financeCheck = financeCheckTab6Map.get(ci.getCounterparty()) ;
                if(financeCheck == null)
                {
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(ci.getCounterparty());
                }
                
                if(financeCheck.getCurrCorpBalanceSheet() != null)
                {
                    if(financeCheck.getCurrCorpBalanceSheet().getLongLoan() != null)
                    {
                            ci.setDebtBalance(financeCheck.getCurrCorpBalanceSheet().getLongLoan().add(financeCheck.getCurrCorpBalanceSheet().getShortLoan()));                 
                    }
                    else
                    {
                            ci.setDebtBalance(financeCheck.getCurrCorpBalanceSheet().getShortLoan());   
                    }
                }
                financeCheck.setCreditInformation(ci);
                financeCheckTab6Map.put(ci.getCounterparty(), financeCheck);
            }
        }
        
        List<FinanceCheck> financeCheckListTab6 = new ArrayList<FinanceCheck>();
        Iterator<Counterparty> itCp = financeCheckTab6Map.keySet().iterator();
        while(itCp.hasNext())
        {
            Counterparty cp = itCp.next();
            financeCheckListTab6.add(financeCheckTab6Map.get(cp));
        }
        
        return financeCheckListTab6;
    }
    
    private final Counterparty findGuarantor(ProjectInfo projectInfo, Long counterpartyId)
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
    
    private final Counterparty findCounterparty(ProjectInfo projectInfo, Long counterpartyId)
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
    
    public InvestmentStatus findInvesetmentStatus(ProjectInfo projectInfo, Long investmentStatusId)
    {
        if(projectInfo == null || investmentStatusId == null)
        {
            return null;
        }
        
        Set<InvestmentStatus> isSet = projectInfo.getInvestments();
        Iterator<InvestmentStatus> isIt = isSet.iterator();
        while(isIt.hasNext())
        {
            InvestmentStatus investmentStatus = isIt.next();
            if(investmentStatus.getId().equals(investmentStatusId))
            {
                return investmentStatus;
            }
        }
        return null;
    }
    
    public List<FinanceCheck> getFinanceCheckList(final RiskControlReport report)
    {
        Map<Counterparty, FinanceCheck> financeCheckMap = new HashMap<Counterparty, FinanceCheck>();
        final ProjectInfo projectInfo = report.getProjectInfo();
        if(report.getCorporateBalanceSheets() != null && report.getCorporateBalanceSheets().size() > 0)
        {
            for(CorporateBalanceSheet cbs : report.getCorporateBalanceSheets())
            {
                Long counterpartyId = cbs.getCounterparty().getId();
                if(findCounterparty(projectInfo, counterpartyId) == null) {
                    continue;
                }
                
                FinanceCheck financeCheck = financeCheckMap.get(cbs.getCounterparty()) ;
                if(financeCheck == null)
                {
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(cbs.getCounterparty());
                }
                
                if(financeCheck.getCurrCorpBalanceSheet() != null && financeCheck.getPrevCorpBalanceSheet() != null)
                {
                    continue;
                }
                
                Iterator<CorporateBalanceSheet> itCbs = report.getCorporateBalanceSheets().iterator();
                while(itCbs.hasNext())
                {
                    CorporateBalanceSheet cbs2 = itCbs.next();
                    if(!cbs2.equals(cbs) && cbs2.getCounterparty().equals(cbs.getCounterparty()))
                    {
                        if(cbs2.getReportYear().intValue() > cbs.getReportYear().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs2);
                            financeCheck.setPrevCorpBalanceSheet(cbs);
                        } 
                        else if (cbs2.getReportYear().intValue() < cbs.getReportYear().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs);
                            financeCheck.setPrevCorpBalanceSheet(cbs2);
                        } 
                        else if (cbs2.getReportMonth().intValue() > cbs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs2);
                            financeCheck.setPrevCorpBalanceSheet(cbs);
                        } 
                        else if (cbs2.getReportMonth().intValue() < cbs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrCorpBalanceSheet(cbs);
                            financeCheck.setPrevCorpBalanceSheet(cbs2);
                        }
                        CorporateBalanceSheet corpBalanceSheetChanges = calculate(financeCheck.getPrevCorpBalanceSheet(), financeCheck.getCurrCorpBalanceSheet());
                        financeCheck.setCorpBalanceSheetChanges(corpBalanceSheetChanges);
                        financeCheckMap.put(cbs.getCounterparty(), financeCheck);
                        break;
                    }
                }
            }
        }
        
        if(report.getInstituteBalanceSheet() != null && report.getInstituteBalanceSheet().size() > 0)
        {
            for(InstituteBalanceSheet ibs : report.getInstituteBalanceSheet())
            {
                Long counterpartyId = ibs.getCounterparty().getId();
                if(findCounterparty(projectInfo, counterpartyId) == null) {
                    continue;
                }
                
                FinanceCheck financeCheck = financeCheckMap.get(ibs.getCounterparty()) ;
                if(financeCheck == null)
                {
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(ibs.getCounterparty());
                }
                
                if(financeCheck.getCurrInstituteBalanceSheet() != null && financeCheck.getPrevInstituteBalanceSheet() != null)
                {
                    continue;
                }
                
                Iterator<InstituteBalanceSheet> itCbs = report.getInstituteBalanceSheet().iterator();
                while(itCbs.hasNext())
                {
                    InstituteBalanceSheet ibs2 = itCbs.next();
                    if(!ibs2.equals(ibs) && ibs2.getCounterparty().equals(ibs.getCounterparty()))
                    {
                        if(ibs2.getReportYear().intValue() > ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs2);
                            financeCheck.setPrevInstituteBalanceSheet(ibs);
                        } 
                        else if (ibs2.getReportYear().intValue() < ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs);
                            financeCheck.setPrevInstituteBalanceSheet(ibs2);
                        } 
                        else if (ibs2.getReportMonth().intValue() > ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs2);
                            financeCheck.setPrevInstituteBalanceSheet(ibs);
                        } else if (ibs2.getReportMonth().intValue() < ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrInstituteBalanceSheet(ibs);
                            financeCheck.setPrevInstituteBalanceSheet(ibs2);
                        }
                        financeCheckMap.put(ibs.getCounterparty(), financeCheck);
                        break;
                    }
                }
            }
        }
        
        if(report.getProfitStatements() != null && report.getProfitStatements().size() > 0)
        {
            for(ProfitStatement ibs : report.getProfitStatements())
            {
                Long counterpartyId = ibs.getCounterparty().getId();
                if(findCounterparty(projectInfo, counterpartyId) == null) {
                    continue;
                }
                
                FinanceCheck financeCheck = financeCheckMap.get(ibs.getCounterparty()) ;
                if(financeCheck == null)
                {
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(ibs.getCounterparty());
                }
                
                if(financeCheck.getCurrProfitStatement() != null && financeCheck.getPrevProfitStatement() != null)
                {
                    continue;
                }
                
                Iterator<ProfitStatement> itPs = report.getProfitStatements().iterator();
                while(itPs.hasNext())
                {
                    ProfitStatement ibs2 = itPs.next();
                    if(!ibs2.equals(ibs) && ibs2.getCounterparty().equals(ibs.getCounterparty()))
                    {
                        if(ibs2.getReportYear().intValue() > ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs2);
                            financeCheck.setPrevProfitStatement(ibs);
                        } 
                        else if (ibs2.getReportYear().intValue() < ibs.getReportYear().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs);
                            financeCheck.setPrevProfitStatement(ibs2);
                        } 
                        else if (ibs2.getReportMonth().intValue() > ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs2);
                            financeCheck.setPrevProfitStatement(ibs);
                        } else if (ibs2.getReportMonth().intValue() < ibs.getReportMonth().intValue())
                        {
                            financeCheck.setCurrProfitStatement(ibs);
                            financeCheck.setPrevProfitStatement(ibs2);
                        }
                        calculateFinanceRatio(financeCheck.getPrevCorpBalanceSheet(), financeCheck.getCurrCorpBalanceSheet(), 
                                financeCheck.getPrevProfitStatement(), financeCheck.getCurrProfitStatement(), financeCheck);
                        ProfitStatement profitStatementChanges = calculate(financeCheck.getPrevProfitStatement(), financeCheck.getCurrProfitStatement());
                        financeCheck.setProfitStatementChanges(profitStatementChanges);
                        financeCheckMap.put(ibs.getCounterparty(), financeCheck);
                        break;
                    }
                }
            }
        }
        
        if(report.getCreditInformations() != null && report.getCreditInformations().size() > 0)
        {
            for(CreditInformation ci : report.getCreditInformations()) 
            {
                if(findCounterparty(report.getProjectInfo(), ci.getCounterparty().getId()) == null)
                {
                    continue;
                }
                FinanceCheck financeCheck = financeCheckMap.get(ci.getCounterparty()) ;
                if(financeCheck == null)
                {
                    financeCheck = new FinanceCheck();
                    financeCheck.setCounterparty(ci.getCounterparty());
                    financeCheckMap.put(ci.getCounterparty(), financeCheck);
                }
                
                if(ci.getCounterparty().equals(financeCheck.getCounterparty()))
                {
                    if(financeCheck.getCurrCorpBalanceSheet() != null)
                    {
                        if(financeCheck.getCurrCorpBalanceSheet().getLongLoan() != null)
                        {
                            ci.setDebtBalance(financeCheck.getCurrCorpBalanceSheet().getLongLoan().add(financeCheck.getCurrCorpBalanceSheet().getShortLoan()));                 
                        }
                        else
                        {
                            ci.setDebtBalance(financeCheck.getCurrCorpBalanceSheet().getShortLoan());   
                        }
                    }
                    financeCheck.setCreditInformation(ci);
                }
            }
        }
        
        List<FinanceCheck> financeCheckList = new ArrayList<FinanceCheck>();
        Iterator<Counterparty> itCp = financeCheckMap.keySet().iterator();
        while(itCp.hasNext())
        {
            Counterparty cp = itCp.next();
            financeCheckList.add(financeCheckMap.get(cp));
        }
        return financeCheckList;
    }

}
