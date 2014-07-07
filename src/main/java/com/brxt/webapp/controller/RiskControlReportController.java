package com.brxt.webapp.controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.brxt.model.Counterparty;
import com.brxt.model.CreditInformation;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.ReportContentKey;
import com.brxt.model.SubjectCapacity;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.model.report.FinanceCheck;
import com.brxt.model.report.FinanceRatio;
import com.brxt.model.report.RiskControlReport;
import com.brxt.service.CreditInformationManager;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.RepaymentManager;
import com.brxt.service.ReportManager;
import com.brxt.service.SubjectCapacityManager;

@Controller
public class RiskControlReportController extends BaseSheetController {
	private static final MathContext mc = new MathContext(2, RoundingMode.HALF_DOWN);
	protected ProjectInfoManager projectInfoManager = null;
	protected RepaymentManager repaymentManager = null;
	protected SubjectCapacityManager subjectCapacityManager = null;
	protected SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	protected CreditInformationManager creditInformationManager;
	protected ProjProgressManager projectProgressManager;
	protected ReportManager reportManager;

	@Autowired
	public void setProjectInfoManager(@Qualifier("projectInfoManager") ProjectInfoManager projectInfoManager) {
		this.projectInfoManager = projectInfoManager;
	}

	@Autowired
	public void setRepaymentManager(@Qualifier("repaymentManager") RepaymentManager repaymentManager) {
		this.repaymentManager = repaymentManager;
	}

	@Autowired
	public void setSubjectCapacityManager(@Qualifier("subjectCapacityManager") SubjectCapacityManager subjectCapacityManager) {
		this.subjectCapacityManager = subjectCapacityManager;
	}

	@Autowired
	public void setCreditInformationManager(@Qualifier("creditInformationManager") CreditInformationManager creditInformationManager) {
		this.creditInformationManager = creditInformationManager;
	}

	@Autowired
	public void setProjectProgressManager(@Qualifier("projectProgressManager") ProjProgressManager projectProgressManager) {
		this.projectProgressManager = projectProgressManager;
	}
	
	@Autowired
	public void setReportManager(@Qualifier("reportManager") ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	@ModelAttribute
	public ProjectInfo getProjectInfo(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			return projectInfoManager.get(Long.valueOf(id));
		} 
		return new ProjectInfo();
	}
	
	@ModelAttribute
	public RiskControlReport getRiskControlReport(final HttpServletRequest request) {
		String reportId = request.getParameter("reportId");
		if (!StringUtils.isBlank(reportId)) {
			return reportManager.get(Long.valueOf(reportId));
		} 
		return new RiskControlReport();
	}

	@ModelAttribute("repaymentList")
	public List<Repayment> getRepaymentList(final HttpServletRequest request) {
		ProjectInfo projectInfo = getProjectInfo(request);
		RiskControlReport report = getRiskControlReport(request);
		List<Repayment> ciList = null;
		if (projectInfo.getId() != null && report != null) {
			ciList = repaymentManager.findByProjId(projectInfo, report.getTimeRangeStart(), report.getTimeRangeEnd());
		}
		return ciList;
	}

	@ModelAttribute("subjectCapacities")
	public List<SubjectCapacity> getSubjectCapacity(final HttpServletRequest request) throws ParseException {
		ReportContentKey rck = getReportContentKey(request);
		if (rck == null) {
			return null;
		}
		List<SubjectCapacity> subjectCapacities = subjectCapacityManager.findByProjIdCpId(rck.getProjectInfo(), rck.getCounterparty(),
				rck.getStartTime(), rck.getEndTime());
		if (subjectCapacities != null && subjectCapacities.size() > 0) {
			return subjectCapacities;
		}

		if (rck.getCounterparty() != null && "tab2".equals(rck.getActiveTab())) {
			saveMessage(request, getText("report.subject.error", rck.getCounterparty().getName(), request.getLocale()));
		}
		return null;
	}

	@ModelAttribute("counterparties")
	public List<Counterparty> getCounterparties(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(id));
			Set<Counterparty> counterparties = projectInfo.getCounterparties();
			if (counterparties == null || counterparties.isEmpty()) {
				return null;
			}
			List<Counterparty> cpList = new ArrayList<Counterparty>(counterparties);
			return cpList;
		}
		return null;
	}

	@ModelAttribute("guarantors")
	public List<Counterparty> getGuarantors(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(id));
			Set<Counterparty> guarantors = projectInfo.getGuarantors();
			if (guarantors == null || guarantors.isEmpty()) {
				return null;
			}
			List<Counterparty> cpList = new ArrayList<Counterparty>();

			Iterator<Counterparty> gIt = guarantors.iterator();
			while (gIt.hasNext()) {
				Counterparty guarant = gIt.next();
				//if (guarant.getCounterpartyType().equalsIgnoreCase(CounterpartyType.INSTITUTION.getTitle())) {
				//	continue;
				//}
				cpList.add(guarant);
			}
			return cpList;
		}
		return null;
	}

	public CreditInformation getCreditInformation(final HttpServletRequest request) throws ParseException {
		ReportContentKey rck = getReportContentKey(request);
		if (rck == null) {
			return null;
		}
		List<CreditInformation> creditInformations = null;
		String cpId = request.getParameter("counterpartiesTab21");
		if (StringUtils.isNotBlank(cpId) && "tab2-1".equals(rck.getActiveTab())) {
			ProjectInfo projectInfo  = rck.getProjectInfo();
			Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(cpId));
			creditInformations = creditInformationManager.findByProjIdCpId(projectInfo, counterparty, rck.getStartTime(),
					rck.getEndTime());

			if (creditInformations == null || creditInformations.isEmpty()) {
				saveMessage(request, getText("report.creditInfo.error", rck.getCounterparty().getName(), request.getLocale()));
			}
		}

		if (creditInformations != null && !creditInformations.isEmpty()) {
			return creditInformations.get(0);
		}

		return null;
	}

	public CreditInformation getCreditInformationTab6(final HttpServletRequest request) throws ParseException {
		ReportContentKey rck = getReportContentKey(request);
		if (rck == null) {
			return null;
		}
		List<CreditInformation> creditInformations = null;

		if (rck.getGuarantor() != null && "tab6".equals(rck.getActiveTab())) {
			creditInformations = creditInformationManager.findByProjIdCpId(rck.getProjectInfo(), rck.getGuarantor(), rck.getStartTime(),
					rck.getEndTime());

			if (creditInformations == null || creditInformations.isEmpty()) {
				saveMessage(request, getText("report.creditInfo.error", rck.getGuarantor().getName(), request.getLocale()));
			}
		}

		if (creditInformations != null && !creditInformations.isEmpty()) {
			return creditInformations.get(0);
		}

		return null;
	}

	@ModelAttribute("investmentProjects")
	public List<InvestmentProject> getInvestmentProjects(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			List<InvestmentProject> investmentProjects = projectProgressManager.getInvestmentProjects(Long.valueOf(id));
			if (investmentProjects == null | investmentProjects.isEmpty()) {
				return null;
			}
			return investmentProjects;
		}
		return null;
	}

	@ModelAttribute("supplyLiquidProjects")
	public List<SupplyLiquidProject> getSupplyLiquidProjects(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			List<SupplyLiquidProject> supplyLiquidProjects = projectProgressManager.getSupplyLiquidProjects(Long.valueOf(id));
			if (supplyLiquidProjects == null | supplyLiquidProjects.isEmpty()) {
				return null;
			}
			return supplyLiquidProjects;
		}
		return null;
	}

	@ModelAttribute("repaymentProjects")
	public List<RepaymentProject> getRepaymentProjects(final HttpServletRequest request) {
		String id = request.getParameter("id");
		if (!StringUtils.isBlank(id)) {
			List<RepaymentProject> repaymentProjects = projectProgressManager.getRepaymentProjects(Long.valueOf(id));
			if (repaymentProjects == null | repaymentProjects.isEmpty()) {
				return null;
			}
			return repaymentProjects;
		}
		return null;
	}
	
	@ModelAttribute("collateralSummary")
	public String getCollateralSummary(final HttpServletRequest request) {
		String projectInfoId = request.getParameter("id");
		if (!StringUtils.isBlank(projectInfoId)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			RiskControlReport report = loadRiskControlReport(projectInfo);
			if(report != null)
			{
				return report.getCollateralSummary();
			}
		}
		return null;
	}

	private ReportContentKey getReportContentKey(final HttpServletRequest request) throws ParseException {
		String projectInfoId = request.getParameter("id");
		String counterpartyId = request.getParameter("counterpartyId");
		String guarantorId = request.getParameter("guarantorId");
		String activeTab = request.getParameter("activeTab");
		if (!StringUtils.isBlank(projectInfoId)) {
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			if (projectInfo == null) {
				log.error("No project information for id " + projectInfoId);
				return null;
			}
			Counterparty counterparty = null;
			if (!StringUtils.isBlank(counterpartyId)) {
				counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
			} else {
				Iterator<Counterparty> itc = projectInfo.getCounterparties().iterator();
				if (itc != null && itc.hasNext()) {
					counterparty = itc.next();
				}
			}

			Counterparty guarantor = null;
			if (!StringUtils.isBlank(guarantorId)) {
				guarantor = findGuarantor(projectInfo, Long.valueOf(guarantorId));
			} else {
				Iterator<Counterparty> itc = projectInfo.getGuarantors().iterator();
				if (itc != null && itc.hasNext()) {
					guarantor = itc.next();
				}
			}

			if (counterparty == null && guarantor == null) {
				log.debug("No counterparty and guarantor for project info [" + projectInfo.toString() + "]");
				return null;
			}

			RiskControlReport report = getRiskControlReport(request);
			ReportContentKey rck = new ReportContentKey();
			rck.setProjectInfo(projectInfo);
			if (activeTab != null && activeTab.startsWith("tab2")) {
				rck.setCounterparty(counterparty);
			}
			if (activeTab != null && activeTab.equals("tab6")) {
				rck.setGuarantor(guarantor);
			}
			rck.setStartTime(report.getTimeRangeStart());
			rck.setEndTime(report.getTimeRangeEnd());
			rck.setActiveTab(activeTab);
			return rck;
		}
		return null;
	}

	@RequestMapping(value = "/reports/riskControlReport*", method = RequestMethod.GET)
	public String handleRequest(final HttpServletRequest request) {
		return "/reports/riskControlReport";
	}
	
	private ModelAndView saveTab2(final HttpServletRequest request, ModelAndView mav)
	{
		String checkTime = request.getParameter("checkTime");
		String projectInfoId = request.getParameter("id");
		String counterpartyId = request.getParameter("counterpartiesTab2");
		if (StringUtils.isBlank(checkTime) || StringUtils.isBlank(projectInfoId) || StringUtils.isBlank(counterpartyId)) {
			saveError(request, getText("report.counterparty.required.error", request.getLocale()));
			return mav;
		}
		
		SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
		try{
			Date checkTimaDate = shortDate.parse(checkTime);
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
			SubjectCapacity subjectCapacity = subjectCapacityManager.find(projectInfo, counterparty, checkTimaDate);
			RiskControlReport report = getRiskControlReport(request);
			Iterator<SubjectCapacity> scIt = report.getSubjectCapacities().iterator();
			while(scIt != null && scIt.hasNext())
			{
				SubjectCapacity sc = scIt.next();
				if(sc.getCounterparty().equals(counterparty))
				{
					scIt.remove();
				}
			}
			report.getSubjectCapacities().add(subjectCapacity);
			reportManager.save(report); 
			mav.addObject("subjectCapacity", subjectCapacity);
			saveMessage(request, getText("report.update.success", request.getLocale()));
		} 
		catch (ParseException e)
		{
			saveError(request, "Date format is incorrect ");
			return mav;
		}
		return mav;
	}
	
	private ModelAndView subjectCapacityCheck(final HttpServletRequest request, ModelAndView mav)
	{
		String checkTime = request.getParameter("checkTime");
		String projectInfoId = request.getParameter("id");
		String counterpartyId = request.getParameter("counterpartiesTab2");
		if (StringUtils.isBlank(checkTime) || StringUtils.isBlank(projectInfoId) || StringUtils.isBlank(counterpartyId)) {
			saveError(request, getText("report.counterparty.required.error", request.getLocale()));
			return mav;
		}
		
		SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
		try{
			Date checkTimaDate = shortDate.parse(checkTime);
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
			SubjectCapacity subjectCapacity = subjectCapacityManager.find(projectInfo, counterparty, checkTimaDate);
			mav.addObject("subjectCapacity", subjectCapacity);
		} 
		catch (ParseException e)
		{
			saveError(request, "Date format is incorrect ");
			return mav;
		}
		
		return mav;
	}
	
	private ModelAndView fetchFinanceReportsTab6(final HttpServletRequest request, ModelAndView mav)
	{
		String prevTermTime = request.getParameter("prevTermTimeTab6");
		String currTermTime = request.getParameter("currTermTimeTab6");
		String projectInfoId = request.getParameter("id");
		String guarantorId = request.getParameter("guarantorsTab6");
		if (StringUtils.isBlank(prevTermTime) || StringUtils.isBlank(currTermTime) || StringUtils.isBlank(guarantorId)) {
			saveError(request, getText("report.counterparty.required.error", request.getLocale()));
			return mav;
		}
		SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
		try {
			Date prevTerm = shortDate.parse(prevTermTime);
			Date currTerm = shortDate.parse(currTermTime);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(prevTerm);
			c2.setTime(currTerm);
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			Counterparty guarantor = findGuarantor(projectInfo, Long.valueOf(guarantorId));
			FinanceCheck financeCheck = new FinanceCheck();
			financeCheck.setCounterparty(guarantor);
			//房地产企业和一般工商企业
			if(guarantor.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString()) || 
					guarantor.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())){
				CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor,
						c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor,
						c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);
				CorporateBalanceSheet corpBalanceSheetChanges = calculate(prevTermCbs, currTermCbs);
				
				financeCheck.setCurrCorpBalanceSheet(currTermCbs);
				financeCheck.setPrevCorpBalanceSheet(prevTermCbs);
				financeCheck.setCorpBalanceSheetChanges(corpBalanceSheetChanges);
				
				CreditInformation creditInformation = getCreditInformationTab6(request);
				if(currTermCbs != null && creditInformation != null)
				{
					if(currTermCbs.getLongLoan() != null)
					{
						creditInformation.setDebtBalance(currTermCbs.getLongLoan().add(currTermCbs.getShortLoan()));					
					}
					else
					{
						creditInformation.setDebtBalance(currTermCbs.getShortLoan());	
					}
				}
				mav.addObject("creditInformationTab6", creditInformation);
				
			}
			
			// 事业法人
			if(guarantor.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
				InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor,
						c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor,
						c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);
				
				financeCheck.setCurrInstituteBalanceSheet(currIBS);
				financeCheck.setPrevInstituteBalanceSheet(prevIBS);
			}
			mav.addObject("financeCheckTab6", financeCheck);
			
		} catch (ParseException e) {
			saveError(request, "Date format is incorrect ");
			return mav;
		}
		return mav;
		
	}
	
	private ModelAndView fetchFinanceReports(final HttpServletRequest request, ModelAndView mav)
	{
		String prevTermTime = request.getParameter("prevTermTime");
		String currTermTime = request.getParameter("currTermTime");
		String projectInfoId = request.getParameter("id");
		String counterpartyId = request.getParameter("counterpartiesTab21");
		if (StringUtils.isBlank(prevTermTime) || StringUtils.isBlank(currTermTime) || StringUtils.isBlank(counterpartyId)) {
			saveError(request, getText("report.counterparty.required.error", request.getLocale()));
			return mav;
		}
		SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
		try {
			Date prevTerm = shortDate.parse(prevTermTime);
			Date currTerm = shortDate.parse(currTermTime);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(prevTerm);
			c2.setTime(currTerm);
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
			FinanceCheck financeCheck = new FinanceCheck();
			financeCheck.setCounterparty(counterparty);
			//房地产企业和一般工商企业
			if(counterparty.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString()) || 
					counterparty.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())){
				CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty,
						c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty,
						c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);
				CorporateBalanceSheet corpBalanceSheetChanges = calculate(prevTermCbs, currTermCbs);
				
				financeCheck.setCurrCorpBalanceSheet(currTermCbs);
				financeCheck.setPrevCorpBalanceSheet(prevTermCbs);
				financeCheck.setCorpBalanceSheetChanges(corpBalanceSheetChanges);

				ProfitStatement prevProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c1.get(Calendar.YEAR),
						c1.get(Calendar.MONTH) + 1);
				ProfitStatement currProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c2.get(Calendar.YEAR),
						c2.get(Calendar.MONTH) + 1);
				financeCheck.setPrevProfitStatement(prevProfitStatement);
				financeCheck.setCurrProfitStatement(currProfitStatement);

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
					
					if(currFinanceRatio.getAssetLiabilityRatio() != null)
					{
						financeRatioChanges.setAssetLiabilityRatio(currFinanceRatio.getAssetLiabilityRatio().subtract(prevFinanceRatio.getAssetLiabilityRatio()));
					}
					
					if(currFinanceRatio.getLiquidityRatio() != null)
					{
						financeRatioChanges.setLiquidityRatio(currFinanceRatio.getLiquidityRatio().subtract(prevFinanceRatio.getLiquidityRatio()));							
					}
					
					if(currFinanceRatio.getQuickRatio() != null)
					{
						financeRatioChanges.setQuickRatio(currFinanceRatio.getQuickRatio().subtract(prevFinanceRatio.getQuickRatio()));
					}
					
					if(currFinanceRatio.getAssetRoR() != null)
					{
						financeRatioChanges.setAssetRoR(currFinanceRatio.getAssetRoR().subtract(prevFinanceRatio.getAssetRoR()));
					}
					financeCheck.setFinanceRatioChanges(financeRatioChanges);
				}
				
				CreditInformation creditInformation = getCreditInformation(request);
				if(currTermCbs != null && creditInformation != null)
				{
					if(currTermCbs.getLongLoan() != null)
					{
						creditInformation.setDebtBalance(currTermCbs.getLongLoan().add(currTermCbs.getShortLoan()));					
					}
					else
					{
						creditInformation.setDebtBalance(currTermCbs.getShortLoan());	
					}
				}
				mav.addObject("creditInformation", creditInformation);
			}
			
			// 事业法人
			if(counterparty.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
				InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty,
						c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty,
						c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);
				
				financeCheck.setCurrInstituteBalanceSheet(currIBS);
				financeCheck.setPrevInstituteBalanceSheet(prevIBS);
			}
			mav.addObject("financeCheck", financeCheck);
		} catch (ParseException e) {
			saveError(request, "Date format is incorrect ");
			return mav;
		}
		return mav;
	}
	
	private ModelAndView saveFinanceCheckTab6(final HttpServletRequest request, ModelAndView mav)
	{
		String guarantorId = request.getParameter("guarantorIdTab6");
		String prevTermTime = request.getParameter("pttTab6");
		String currTermTime = request.getParameter("cttTab6");
		String projectInfoId = request.getParameter("id");
		String comments = request.getParameter("guarantorCheckComment");
		
		if(StringUtils.isBlank(guarantorId) || StringUtils.isBlank(prevTermTime) || StringUtils.isBlank(currTermTime) || StringUtils.isBlank(projectInfoId))
		{
			saveError(request, getText("report.counterparty.required.error", request.getLocale()));
			return mav;
		}
		SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
		try {
			Date prevTerm = shortDate.parse(prevTermTime);
			Date currTerm = shortDate.parse(currTermTime);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(prevTerm);
			c2.setTime(currTerm);
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			RiskControlReport report = getRiskControlReport(request);
			Counterparty guarantor = findGuarantor(projectInfo, Long.valueOf(guarantorId));
			if(guarantor.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString()) || 
					guarantor.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())){
				CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor,
					c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor,
					c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);

				Iterator<CorporateBalanceSheet> stIt = report.getGuarantorCorpBalanceSheets().iterator();
				while(stIt != null && stIt.hasNext())
				{
					CorporateBalanceSheet ps = stIt.next();
					if(ps.getCounterparty().equals(guarantor))
					{
						stIt.remove();
					}
				}
				
				if(prevTermCbs != null)
				{
					report.getGuarantorCorpBalanceSheets().add(prevTermCbs);				
				}
				
				if(currTermCbs != null)
				{
					report.getGuarantorCorpBalanceSheets().add(currTermCbs);				
				}
			} else if(guarantor.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
				InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor,
						c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor,
						c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);

				Iterator<InstituteBalanceSheet> stIt = report.getGuarantorInstBalanceSheet().iterator();
				while(stIt != null && stIt.hasNext())
				{
					InstituteBalanceSheet ps = stIt.next();
					if(ps.getCounterparty().equals(guarantor))
					{
						stIt.remove();
					}
				}
				
				if(prevIBS != null)
				{
					report.getGuarantorInstBalanceSheet().add(prevIBS);
				}
				
				if(currIBS != null)
				{
					report.getGuarantorInstBalanceSheet().add(currIBS);
				}
			}
			
			report.setGuarantorCheckComment(comments);
			reportManager.save(report);
			saveMessage(request, getText("report.update.success", request.getLocale()));
			mav.addObject("riskControlReport", report);
		} catch (ParseException e) {
			saveError(request, "Date format is incorrect ");
			return mav;
		}
		return mav;	
			
	}
	
	private ModelAndView saveTab3(final HttpServletRequest request, final ModelAndView mav)
	{
		RiskControlReport report = getRiskControlReport(request);
		String policyChanges = request.getParameter("policyChanges");
		String priceChanges = request.getParameter("priceChanges");
		String investmentEvaluation = request.getParameter("investmentEvaluation");
		report.setPolicyChanges(policyChanges);
		report.setPriceChanges(priceChanges);
		report.setInvestmentEvaluation(investmentEvaluation);
		reportManager.save(report);
		saveMessage(request, getText("report.update.success", request.getLocale()));		
		mav.addObject("riskControlReport", report);
		return mav;
	}
	
	private ModelAndView saveTab4(final HttpServletRequest request, final ModelAndView mav)
	{
		RiskControlReport report = getRiskControlReport(request);
		String repaymentEvaluation = request.getParameter("repaymentEvaluation");
		report.setRepaymentEvaluation(repaymentEvaluation);
		reportManager.save(report);
		saveMessage(request, getText("report.update.success", request.getLocale()));	
		mav.addObject("riskControlReport", report);
		return mav;
	}
	
	private ModelAndView saveTab5(final HttpServletRequest request, final ModelAndView mav)
	{
		RiskControlReport report = getRiskControlReport(request);
		String collateralSummary = request.getParameter("collateralSummary");
		report.setCollateralSummary(collateralSummary);
		reportManager.save(report);
		saveMessage(request, getText("report.update.success", request.getLocale()));	
		mav.addObject("riskControlReport", report);
		return mav;
	}
	
	private ModelAndView saveTab7(final HttpServletRequest request, final ModelAndView mav)
	{
		RiskControlReport report = getRiskControlReport(request);
		String statusBeforeMaturity = request.getParameter("statusBeforeMaturity");
		report.setStatusBeforeMaturity(statusBeforeMaturity);
		reportManager.save(report);
		saveMessage(request, getText("report.update.success", request.getLocale()));	
		mav.addObject("riskControlReport", report);
		return mav;
	}
	
	private ModelAndView saveTab8(final HttpServletRequest request, final ModelAndView mav)
	{
		RiskControlReport report = getRiskControlReport(request);
		String comments = request.getParameter("comments");
		report.setComments(comments);
		reportManager.save(report);
		saveMessage(request, getText("report.update.success", request.getLocale()));	
		mav.addObject("riskControlReport", report);
		return mav;
	}
	
	private ModelAndView saveFinanceCheck(final HttpServletRequest request, ModelAndView mav)
	{
		String counterpartyId = request.getParameter("counterpartyIdTab21");
		String prevTermTime = request.getParameter("prevTermTimeTab21");
		String currTermTime = request.getParameter("currTermTimeTab21");
		String projectInfoId = request.getParameter("id");
		String comments = request.getParameter("financeStatementSummary");
		
		if(StringUtils.isBlank(counterpartyId) || StringUtils.isBlank(prevTermTime) || StringUtils.isBlank(currTermTime) || StringUtils.isBlank(projectInfoId))
		{
			saveError(request, getText("report.counterparty.required.error", request.getLocale()));
			return mav;
		}
		
		SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
		try {
			Date prevTerm = shortDate.parse(prevTermTime);
			Date currTerm = shortDate.parse(currTermTime);
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(prevTerm);
			c2.setTime(currTerm);
			ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
			RiskControlReport report = getRiskControlReport(request);
			Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
			if(counterparty.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString()) || 
					counterparty.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())){
			CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty,
					c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
			CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty,
					c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);
			
			Iterator<CorporateBalanceSheet> stIt = report.getCorporateBalanceSheets().iterator();
			while(stIt != null && stIt.hasNext())
			{
				CorporateBalanceSheet ps = stIt.next();
				if(ps.getCounterparty().equals(counterparty))
				{
					stIt.remove();
				}
			}
			
			if(prevTermCbs != null)
			{
				report.getCorporateBalanceSheets().add(prevTermCbs);				
			}
			
			if(currTermCbs != null)
			{
				report.getCorporateBalanceSheets().add(currTermCbs);				
			}

			ProfitStatement prevProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c1.get(Calendar.YEAR),
					c1.get(Calendar.MONTH) + 1);
			ProfitStatement currProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c2.get(Calendar.YEAR),
					c2.get(Calendar.MONTH) + 1);
			Iterator<ProfitStatement> psIt = report.getProfitStatements().iterator();
			while(psIt != null && psIt.hasNext())
			{
				ProfitStatement ps = psIt.next();
				if(ps.getCounterparty().equals(counterparty))
				{
					psIt.remove();
				}
			}
			
			if(prevProfitStatement != null)
			{
				report.getProfitStatements().add(prevProfitStatement);				
			}
			
			if(currProfitStatement != null)
			{
				report.getProfitStatements().add(currProfitStatement);
			}
			}else if(counterparty.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
				InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty,
						c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1);
				InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty,
						c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1);
				
				Iterator<InstituteBalanceSheet> stIt = report.getInstituteBalanceSheet().iterator();
				while(stIt != null && stIt.hasNext())
				{
					InstituteBalanceSheet ps = stIt.next();
					if(ps.getCounterparty().equals(counterparty))
					{
						stIt.remove();
					}
				}
				if(prevIBS != null)
				{
					report.getInstituteBalanceSheet().add(prevIBS);
				}
				
				if(currIBS != null)
				{
					report.getInstituteBalanceSheet().add(currIBS);
				}
			}
			
			report.setFinanceCheckComment(comments);
			reportManager.save(report);
			saveMessage(request, getText("report.update.success", request.getLocale()));
			mav.addObject("riskControlReport", report);
		} catch (ParseException e) {
			saveError(request, "Date format is incorrect ");
			return mav;
		}
		return mav;
	}
	
	private RiskControlReport loadRiskControlReport(ProjectInfo projectInfo)
	{
		//RiskControlReport report = projectInfo.getRiskControlReport();
		RiskControlReport report = null;
		if(report == null)
		{
			report = new RiskControlReport();
			report.setProjectInfo(projectInfo);
			report.setCreateUser(getCurrentUser().getUsername());
			report.setCreateTime(new Date());
			//projectInfo.setRiskControlReport(report);
		}
//		else
//		{
//			report.setUpdateUser(getCurrentUser().getUsername());
//			report.setUpdateTime(new Date());
//		}
		return report;
	}
	
	

	@RequestMapping(value = "/reports/riskControlReport*", method = RequestMethod.POST)
	public ModelAndView onSubmit(final HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/reports/riskControlReport");
		String method = request.getParameter("method");
		if (!StringUtils.isBlank(method)) {

			switch (method) {
			case "SubjectCapacityCheck":
				mav = subjectCapacityCheck(request, mav);
				break;
			case "SaveTab2":
				mav = saveTab2(request, mav);
				break;
			case "FinanceCheckTab21":
				mav = fetchFinanceReports(request, mav);
				break;
			case "SaveFinanceCheckTab21":
				mav = saveFinanceCheck(request, mav);
				break;
			case "SaveTab3":
				mav = saveTab3(request, mav);
				break;
			case "SaveTab4":
				mav = saveTab4(request, mav);
				break;
			case "SaveTab5":
				mav = saveTab5(request, mav);
				break;
			case "FinanceCheckTab6":
				mav = fetchFinanceReportsTab6(request, mav);
				break;
			case "SaveFinanceCheckTab6":
				mav = saveFinanceCheckTab6(request, mav);
				break;
			case "SaveTab7":
				mav = saveTab7(request, mav);
				break;
			case "SaveTab8":
				mav = saveTab8(request, mav);
				break;
			default:
			}
		}
		return mav;
	}

	private CorporateBalanceSheet calculate(CorporateBalanceSheet prevTermCbs, CorporateBalanceSheet currTermCbs) {
		if (prevTermCbs == null || currTermCbs == null) {
			return null;
		}

		CorporateBalanceSheet corpBalanceSheetChanges = new CorporateBalanceSheet();
		corpBalanceSheetChanges.setTotalAsset(calculateChangesRatio(currTermCbs.getTotalAsset(), prevTermCbs.getTotalAsset()));
		corpBalanceSheetChanges.setCash(calculateChangesRatio(currTermCbs.getCash(), prevTermCbs.getCash()));
		corpBalanceSheetChanges.setInventory(calculateChangesRatio(currTermCbs.getInventory(), prevTermCbs.getInventory()));
		corpBalanceSheetChanges.setTotalDebt(calculateChangesRatio(currTermCbs.getTotalDebt(), prevTermCbs.getTotalDebt()));
		corpBalanceSheetChanges.setPrereceive(calculateChangesRatio(currTermCbs.getPrereceive(), prevTermCbs.getPrereceive()));
		corpBalanceSheetChanges.setShortLoan(calculateChangesRatio(currTermCbs.getShortLoan(), prevTermCbs.getShortLoan()));
		corpBalanceSheetChanges.setLongLoan(calculateChangesRatio(currTermCbs.getLongLoan(), prevTermCbs.getLongLoan()));
		corpBalanceSheetChanges.setNetAsset(calculateChangesRatio(currTermCbs.getNetAsset(), prevTermCbs.getNetAsset()));
		corpBalanceSheetChanges.setActualCapital(calculateChangesRatio(currTermCbs.getActualCapital(), prevTermCbs.getActualCapital()));
		return corpBalanceSheetChanges;
	}

	private final BigDecimal calculateRatio(BigDecimal number, BigDecimal baseNumber) {
		if (number == null || baseNumber == null) {
			return null;
		}

		if (!baseNumber.equals(BigDecimal.ZERO)) {
			return number.divide(baseNumber, mc);
		}

		return null;
	}

	private final BigDecimal calculateChangesRatio(BigDecimal number, BigDecimal baseNumber) {
		if (number == null || baseNumber == null) {
			return null;
		}

		if (!baseNumber.equals(BigDecimal.ZERO)) {
			BigDecimal changes = number.subtract(baseNumber);
			return changes.divide(baseNumber, mc);
		}
		return null;
	}

	@RequestMapping(value = "/reports/reportSearch*", method = RequestMethod.GET)
	public ModelAndView reportSearch(final HttpServletRequest request) {
		List<RiskControlReport> reports = reportManager.getAll();
		if(reports == null || reports.isEmpty())
		{
			saveMessage(request, getText("report.add.help", request.getLocale()));
		}
		return new ModelAndView("/reports/reportSearch").addObject("reportList", reports);
	}

	@RequestMapping(value = "/reports/reportSearch*", method = RequestMethod.POST)
	public ModelAndView onSearch(@ModelAttribute("projectInfo") final ProjectInfo projectInfo, final HttpServletRequest request) {
		String method = request.getParameter("method");
		if (StringUtils.isBlank(method)) {
			return new ModelAndView();
		}
		switch (method) {
		case "SearchReport":
			RiskControlReport report = new RiskControlReport();
			report.setSearchTimeStart(projectInfo.getSearchTimeStart());
			report.setSearchTimeEnd(projectInfo.getSearchTimeEnd());
			List<RiskControlReport> reports = reportManager.findByReport(report);
			projectInfo.setSearchTimeStart(null);
			projectInfo.setSearchTimeEnd(null);
			List<ProjectInfo> projectInfoList = projectInfoManager.findByProjectInfo(projectInfo);
			projectInfo.setSearchTimeStart(report.getSearchTimeStart());
			projectInfo.setSearchTimeEnd(report.getSearchTimeEnd());
			Set<ProjectInfo> projectInfoSet = new HashSet<ProjectInfo>(projectInfoList);
			
			if(reports != null && !reports.isEmpty())
			{
				Iterator<RiskControlReport> itR = reports.iterator();
				while(itR.hasNext())
				{
					RiskControlReport r = itR.next();
					if(!projectInfoSet.contains(r.getProjectInfo())) 
					{
						itR.remove();
					}
				}
			}
			
			return new ModelAndView("/reports/reportSearch").addObject("reportList", reports);
		default:
		}
		return new ModelAndView("/reports/reportSearch").addObject(projectInfoManager.getAll());
	}
	
	@RequestMapping(value = "/reports/addReport*", method = RequestMethod.GET)
	public ModelAndView addReport() {
		ModelAndView mav =  new ModelAndView("/reports/addReport");
		Map<String, String> projectNames = new TreeMap<String, String>();
		List<String> nameList = projectInfoManager.getAllProjectNames();
		if(nameList !=null && !nameList.isEmpty())
		{
			for(int i = 0; i < nameList.size(); i++)
			{
				String name = nameList.get(i);
				projectNames.put(name, name);
			}
		}
		mav.addObject("allProjectNames", projectNames);
		
		Map<String, String> allReportSeasons = new TreeMap<String, String>();
		int startYear = getCurrentYear() - 3;
		int endYear = getCurrentYear() + 3;
		for(int y = startYear; y < endYear; y++)
		{
			for(int s = 1; s < 5; s++)
			{
				String reportSeason = y + "Q" + s;
				allReportSeasons.put(reportSeason, reportSeason);
			}
		}
		mav.addObject("allReportSeasons", allReportSeasons);
		RiskControlReport report = new RiskControlReport();
		int defaultQuater = getCurrentMonth() / 3 + (getCurrentMonth() % 3);
		report.setReportSeason(getCurrentYear() + "Q" + defaultQuater);
		mav.addObject("riskControlReport", report);
		return mav;
	}
	
	@RequestMapping(value = "/reports/addReport*", method = RequestMethod.POST)
	public ModelAndView addReport(final HttpServletRequest request) throws ParseException {
		ModelAndView mav =  addReport();
		String projectName = request.getParameter("projectInfo.projectName");
		String reportSeason = request.getParameter("reportSeason");
		ProjectInfo projectInfo = projectInfoManager.findByProjectName(projectName);
		RiskControlReport report = reportManager.findRiskControlReport(projectInfo, reportSeason);
		if(report != null)
		{
			saveError(request, getText("report.add.error.existd", new String[]{projectInfo.getProjectName(), reportSeason}, request.getLocale()) );
			mav.addObject("riskControlReport", report);
			return mav;
		}
		
		report = new RiskControlReport();
		report.setProjectInfo(projectInfo);
		report.setReportSeason(reportSeason);
		String reportYear = reportSeason.substring(0, 4);
		String reportQuanter = reportSeason.substring(5, 6);
		Date timeRangeStart = null;
		Date timeRangeEnd = null;
		switch (reportQuanter)
		{
		case "1":
			timeRangeStart = sf.parse(reportYear + "-01-01");
			timeRangeEnd = sf.parse(reportYear + "-03-31");
			break;
		case "2":
			timeRangeStart = sf.parse(reportYear + "-04-01");
			timeRangeEnd = sf.parse(reportYear + "-06-30");
			break;
		case "3":
			timeRangeStart = sf.parse(reportYear + "-07-01");
			timeRangeEnd = sf.parse(reportYear + "-09-30");
			break;
		case "4":
			timeRangeStart = sf.parse(reportYear + "-10-01");
			timeRangeEnd = sf.parse(reportYear + "-12-31");
			break;
			default:
				//Error
		}
		report.setTimeRangeStart(timeRangeStart);
		report.setTimeRangeEnd(timeRangeEnd);
		report.setCreateUser(getCurrentUser().getUsername());
		report.setCreateTime(new Date());
		reportManager.save(report);
		saveMessage(request, getText("report.add.success", new String[]{projectInfo.getProjectName(), reportSeason}, request.getLocale()) );
		mav.setViewName("redirect:/reports/reportSearch");
		return mav;
	}
	
	@RequestMapping(value = "/reports/previewReport*", method = RequestMethod.GET)
	public ModelAndView previewReport(final HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/reports/previewReport");
		//RiskControlReport report = getRiskControlReport(request);
		return mav;
	}
}
