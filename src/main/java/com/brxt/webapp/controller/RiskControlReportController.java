package com.brxt.webapp.controller;

import java.math.BigDecimal;
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
import javax.servlet.http.HttpServletResponse;

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
import com.brxt.model.InvestmentStatus;
import com.brxt.model.Manager;
import com.brxt.model.ProjectInfo;
import com.brxt.model.Repayment;
import com.brxt.model.ReportContentKey;
import com.brxt.model.SubjectCapacity;
import com.brxt.model.enums.CapitalInvestmentType;
import com.brxt.model.enums.CounterpartyType;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;
import com.brxt.model.report.FinanceCheck;
import com.brxt.model.report.RiskControlReport;
import com.brxt.service.CreditInformationManager;
import com.brxt.service.ProjProgressManager;
import com.brxt.service.ProjectInfoManager;
import com.brxt.service.RepaymentManager;
import com.brxt.service.ReportManager;
import com.brxt.service.SubjectCapacityManager;
import com.brxt.webapp.spring.RiskControlReportPDF;

@Controller
public class RiskControlReportController extends BaseSheetController {

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
            final String tab8Option1 = getText("report.riskcontrol.tab8.option1", request.getLocale());
            final String tab8Option2 = getText("report.riskcontrol.tab8.option2", request.getLocale());

            RiskControlReport report = reportManager.get(Long.valueOf(reportId));
            if (tab8Option1.equals(report.getComments())) {
                report.setTab8Option("option1");
            } else if (tab8Option2.equals(report.getComments())) {
                report.setTab8Option("option2");
            } else {
                report.setTab8Option("option3");
            }
            return report;
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

    @ModelAttribute("investmentStatus")
    public List<InvestmentStatus> getInvestmentStatus(final HttpServletRequest request) {
        ProjectInfo projectInfo = getProjectInfo(request);
        Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
        if (investmentStatusSet != null) {
            List<InvestmentStatus> investmentList = new ArrayList<InvestmentStatus>();
            Iterator<InvestmentStatus> itIs = investmentStatusSet.iterator();
            while (itIs.hasNext()) {
                InvestmentStatus status = itIs.next();
                if (!status.getProjectType().equals(CapitalInvestmentType.REPAYMENT_PROJECT.getTitle())) {
                    investmentList.add(status);
                }
            }
            return investmentList;
        }
        return null;
    }

    @ModelAttribute("repaymentInvestmentStatus")
    public List<InvestmentStatus> getRepaymentInvestmentStatus(final HttpServletRequest request) {
        ProjectInfo projectInfo = getProjectInfo(request);
        Set<InvestmentStatus> investmentStatusSet = projectInfo.getInvestments();
        if (investmentStatusSet != null) {
            List<InvestmentStatus> investmentList = new ArrayList<InvestmentStatus>();
            Iterator<InvestmentStatus> itIs = investmentStatusSet.iterator();
            while (itIs.hasNext()) {
                InvestmentStatus status = itIs.next();
                if (status.getProjectType().equals(CapitalInvestmentType.REPAYMENT_PROJECT.getTitle())) {
                    investmentList.add(status);
                }
            }
            return investmentList;
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
                // if
                // (guarant.getCounterpartyType().equalsIgnoreCase(CounterpartyType.INSTITUTION.getTitle()))
                // {
                // continue;
                // }
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
            ProjectInfo projectInfo = rck.getProjectInfo();
            Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(cpId));
            creditInformations = creditInformationManager.findByProjIdCpId(projectInfo, counterparty, rck.getStartTime(), rck.getEndTime());

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
            if (rck.getGuarantor().getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
                return null;
            }
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

    @ModelAttribute("collateralSummary")
    public String getCollateralSummary(final HttpServletRequest request) {
        String projectInfoId = request.getParameter("id");
        if (!StringUtils.isBlank(projectInfoId)) {
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            RiskControlReport report = loadRiskControlReport(projectInfo);
            if (report != null) {
                return report.getCollateralSummary();
            }
        }
        return null;
    }

    private ReportContentKey getReportContentKey(final HttpServletRequest request) throws ParseException {
        String projectInfoId = request.getParameter("id");
        String counterpartyId = request.getParameter("counterpartyId");
        String guarantorId = request.getParameter("guarantorsTab6");
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

    private ModelAndView saveTab2(final HttpServletRequest request, ModelAndView mav) {
        String checkTime = request.getParameter("checkTime");
        String projectInfoId = request.getParameter("id");
        String counterpartyId = request.getParameter("counterpartiesTab2");
        if (StringUtils.isBlank(checkTime) || StringUtils.isBlank(projectInfoId) || StringUtils.isBlank(counterpartyId)) {
            saveError(request, getText("report.counterparty.required.error", request.getLocale()));
            return mav;
        }

        SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
        try {
            Date checkTimaDate = shortDate.parse(checkTime);
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
            SubjectCapacity subjectCapacity = subjectCapacityManager.find(projectInfo, counterparty, checkTimaDate);
            if (subjectCapacity == null) {
                saveMessage(request, getText("report.subjectCapacity.notFound", new String[]{counterparty.getName(), checkTime}, request.getLocale()));
                return mav;
            }

            RiskControlReport report = getRiskControlReport(request);
            Iterator<SubjectCapacity> scIt = report.getSubjectCapacities().iterator();
            while (scIt != null && scIt.hasNext()) {
                SubjectCapacity sc = scIt.next();
                if (sc.getCounterparty().equals(counterparty)) {
                    scIt.remove();
                }
            }
            report.getSubjectCapacities().add(subjectCapacity);
            reportManager.save(report);
            mav.addObject("riskControlReport", report);
            saveMessage(request, getText("report.update.success", request.getLocale()));
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;
    }

    private ModelAndView subjectCapacityCheck(final HttpServletRequest request, ModelAndView mav) {
        String checkTime = request.getParameter("checkTime");
        String projectInfoId = request.getParameter("id");
        String counterpartyId = request.getParameter("counterpartiesTab2");
        if (StringUtils.isBlank(checkTime) || StringUtils.isBlank(projectInfoId) || StringUtils.isBlank(counterpartyId)) {
            saveError(request, getText("report.counterparty.required.error", request.getLocale()));
            return mav;
        }

        SimpleDateFormat shortDate = new SimpleDateFormat(getText("date.format.short", request.getLocale()));
        try {
            Date checkTimaDate = shortDate.parse(checkTime);
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            Counterparty counterparty = findCounterparty(projectInfo, Long.valueOf(counterpartyId));
            SubjectCapacity subjectCapacity = subjectCapacityManager.find(projectInfo, counterparty, checkTimaDate);
            if (subjectCapacity == null) {
                saveMessage(request, getText("report.subjectCapacity.notFound", new String[]{counterparty.getName(), checkTime}, request.getLocale()));
            }
            mav.addObject("subjectCapacity", subjectCapacity);
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;
    }

    private ModelAndView fetchFinanceReportsTab6(final HttpServletRequest request, ModelAndView mav) {
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
            // 房地产企业和一般工商企业
            if (guarantor.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString())
                    || guarantor.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())) {
                CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                if (prevTermCbs == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{guarantor.getName(), prevTermTime}, request.getLocale()));
                }
                CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);
                if (currTermCbs == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{guarantor.getName(), currTermTime}, request.getLocale()));
                }
                CorporateBalanceSheet corpBalanceSheetChanges = reportManager.calculate(prevTermCbs, currTermCbs);

                financeCheck.setCurrCorpBalanceSheet(currTermCbs);
                financeCheck.setPrevCorpBalanceSheet(prevTermCbs);
                financeCheck.setCorpBalanceSheetChanges(corpBalanceSheetChanges);

                CreditInformation creditInformation = getCreditInformationTab6(request);
                if (currTermCbs != null && creditInformation != null) {
                    if (currTermCbs.getLongLoan() != null) {
                        creditInformation.setDebtBalance(currTermCbs.getLongLoan().add(
                                currTermCbs.getShortLoan() == null ? BigDecimal.ZERO : currTermCbs.getShortLoan()));
                    } else {
                        creditInformation.setDebtBalance(currTermCbs.getShortLoan() == null ? BigDecimal.ZERO : currTermCbs.getShortLoan());
                    }
                }
                mav.addObject("creditInformationTab6", creditInformation);
            }

            // 事业法人
            if (guarantor.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
                InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);

                if (prevIBS == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{guarantor.getName(), prevTermTime}, request.getLocale()));
                } else {
                    financeCheck.setPrevInstituteBalanceSheet(prevIBS);
                }

                if (currIBS == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{guarantor.getName(), currTermTime}, request.getLocale()));
                } else {
                    financeCheck.setCurrInstituteBalanceSheet(currIBS);
                }
            }
            mav.addObject("financeCheckTab6", financeCheck);

        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;

    }

    private ModelAndView projectProgressTab3(final HttpServletRequest request, final ModelAndView mav) {
        String projectEndTime = request.getParameter("projectEndTime");
        String projectInfoId = request.getParameter("id");
        String invesetmentStatusId = request.getParameter("investmentTab3");
        if (StringUtils.isBlank(projectEndTime) || StringUtils.isBlank(invesetmentStatusId)) {
            saveError(request, getText("report.investment.project.required.error", request.getLocale()));
            return mav;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
        try {
            Date projectEndTimeDate = dateFormat.parse(projectEndTime);
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            InvestmentStatus investmentStatus = reportManager.findInvesetmentStatus(projectInfo, Long.valueOf(invesetmentStatusId));
            mav.addObject("selectedInvestmentStatus", investmentStatus);
            List<String> projectEndTimeList = this.projectProgressManager.getAvailableEndTimes(investmentStatus.getId());
            mav.addObject("projectEndTimeList", projectEndTimeList);
            String projectType = investmentStatus.getProjectType();
            CapitalInvestmentType investmentType = CapitalInvestmentType.valueOf(projectType.toUpperCase());
            switch (investmentType) {
                case REAL_ESTATE :
                case INFRASTRUCTURE :
                    InvestmentProject investmentProject = projectProgressManager.findInvestmentProject(investmentStatus, projectEndTimeDate);
                    List<InvestmentProject> investmentProjects = new ArrayList<InvestmentProject>();
                    if (investmentProject != null) {
                        investmentProjects.add(investmentProject);
                    } else {
                        saveMessage(
                                request,
                                getText("report.investment.project.notFound", new String[]{investmentStatus.getProjectName(), projectEndTime},
                                        request.getLocale()));
                    }
                    mav.addObject("investmentProjects", investmentProjects);
                    break;
                case SUPPLEMENTAL_LIQUIDITY :
                    SupplyLiquidProject supplyLiquidProject = projectProgressManager.findSupplyLiquidProject(investmentStatus, projectEndTimeDate);
                    List<SupplyLiquidProject> supplyLiquidProjects = new ArrayList<SupplyLiquidProject>();
                    if (supplyLiquidProject != null) {
                        supplyLiquidProjects.add(supplyLiquidProject);
                    } else {
                        saveMessage(
                                request,
                                getText("report.investment.project.notFound", new String[]{investmentStatus.getProjectName(), projectEndTime},
                                        request.getLocale()));
                    }
                    mav.addObject("supplyLiquidProjects", supplyLiquidProjects);
                    break;
                default :
            }
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;
    }

    private ModelAndView projectProgressTab4(final HttpServletRequest request, final ModelAndView mav) {
        String projectEndTime = request.getParameter("projectEndTimeTab4");
        String projectInfoId = request.getParameter("id");
        String invesetmentStatusId = request.getParameter("investmentTab4");
        if (StringUtils.isBlank(projectEndTime) || StringUtils.isBlank(invesetmentStatusId)) {
            saveError(request, getText("report.investment.project.required.error", request.getLocale()));
            return mav;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
        try {
            Date projectEndTimeDate = dateFormat.parse(projectEndTime);
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            InvestmentStatus investmentStatus = reportManager.findInvesetmentStatus(projectInfo, Long.valueOf(invesetmentStatusId));
            String projectType = investmentStatus.getProjectType();
            CapitalInvestmentType investmentType = CapitalInvestmentType.valueOf(projectType.toUpperCase());
            switch (investmentType) {
                case REPAYMENT_PROJECT :
                    RepaymentProject repaymentProject = projectProgressManager.findRepaymentProject(investmentStatus, projectEndTimeDate);
                    List<RepaymentProject> repaymentProjects = new ArrayList<RepaymentProject>();
                    if (repaymentProject != null) {
                        repaymentProjects.add(repaymentProject);
                    } else {
                        saveMessage(
                                request,
                                getText("report.repayment.project.notFound", new String[]{investmentStatus.getProjectName(), projectEndTime},
                                        request.getLocale()));
                    }
                    mav.addObject("repaymentProjects", repaymentProjects);
                    break;
                default :
            }
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;
    }

    private ModelAndView fetchFinanceReports(final HttpServletRequest request, final ModelAndView mav) {
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
            // 房地产企业和一般工商企业
            if (counterparty.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString())
                    || counterparty.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())) {
                CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);
                CorporateBalanceSheet corpBalanceSheetChanges = reportManager.calculate(prevTermCbs, currTermCbs);

                financeCheck.setCurrCorpBalanceSheet(currTermCbs);
                financeCheck.setPrevCorpBalanceSheet(prevTermCbs);
                financeCheck.setCorpBalanceSheetChanges(corpBalanceSheetChanges);

                ProfitStatement prevProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                ProfitStatement currProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);
                financeCheck.setPrevProfitStatement(prevProfitStatement);
                financeCheck.setCurrProfitStatement(currProfitStatement);

                if (prevTermCbs == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{counterparty.getName(), prevTermTime}, request.getLocale()));
                }

                if (currTermCbs == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{counterparty.getName(), currTermTime}, request.getLocale()));
                }
                reportManager.calculateFinanceRatio(prevTermCbs, currTermCbs, prevProfitStatement, currProfitStatement, financeCheck);

                CreditInformation creditInformation = getCreditInformation(request);
                if (currTermCbs != null && creditInformation != null) {
                    if (currTermCbs.getLongLoan() != null) {
                        creditInformation.setDebtBalance(currTermCbs.getLongLoan().add(currTermCbs.getShortLoan()));
                    } else {
                        creditInformation.setDebtBalance(currTermCbs.getShortLoan());
                    }
                }
                mav.addObject("creditInformation", creditInformation);
            }

            // 事业法人
            if (counterparty.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
                InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);

                if (currIBS == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{counterparty.getName(), currTermTime}, request.getLocale()));
                }

                if (prevIBS == null) {
                    saveMessage(request,
                            getText("report.financeCheck.notFound", new String[]{counterparty.getName(), prevTermTime}, request.getLocale()));
                }

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

    private ModelAndView saveFinanceCheckTab6(final HttpServletRequest request, ModelAndView mav) {
        String guarantorId = request.getParameter("guarantorIdTab6");
        String prevTermTime = request.getParameter("pttTab6");
        String currTermTime = request.getParameter("cttTab6");
        String projectInfoId = request.getParameter("id");
        String comments = request.getParameter("guarantorCheckComment");

        if (StringUtils.isBlank(guarantorId) || StringUtils.isBlank(prevTermTime) || StringUtils.isBlank(currTermTime)
                || StringUtils.isBlank(projectInfoId)) {
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
            if (guarantor.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString())
                    || guarantor.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())) {
                CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, guarantor, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);

                Iterator<CorporateBalanceSheet> stIt = report.getGuarantorCorpBalanceSheets().iterator();
                while (stIt != null && stIt.hasNext()) {
                    CorporateBalanceSheet ps = stIt.next();
                    if (ps.getCounterparty().equals(guarantor)) {
                        stIt.remove();
                    }
                }

                if (prevTermCbs != null) {
                    report.getGuarantorCorpBalanceSheets().add(prevTermCbs);
                }

                if (currTermCbs != null) {
                    report.getGuarantorCorpBalanceSheets().add(currTermCbs);
                }
            } else if (guarantor.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
                InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, guarantor, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);

                Iterator<InstituteBalanceSheet> stIt = report.getGuarantorInstBalanceSheet().iterator();
                while (stIt != null && stIt.hasNext()) {
                    InstituteBalanceSheet ps = stIt.next();
                    if (ps.getCounterparty().equals(guarantor)) {
                        stIt.remove();
                    }
                }

                if (prevIBS != null) {
                    report.getGuarantorInstBalanceSheet().add(prevIBS);
                }

                if (currIBS != null) {
                    report.getGuarantorInstBalanceSheet().add(currIBS);
                }
            }

            CreditInformation creditInformation = getCreditInformationTab6(request);
            if (creditInformation != null) {
                report.getCreditInformations().add(creditInformation);
            }

            report.setGuarantorCheckComment(comments);
            reportManager.save(report);
            List<FinanceCheck> financeCheckListTab6 = reportManager.getFinanceCheckListTab6(report);
            mav.addObject("financeCheckListTab6", financeCheckListTab6);
            saveMessage(request, getText("report.update.success", request.getLocale()));
            mav.addObject("riskControlReport", report);
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;

    }

    private ModelAndView saveTab3(final HttpServletRequest request, final ModelAndView mav) {
        String projectEndTime = request.getParameter("projectEndTimeTab3");
        String invesetmentStatusId = request.getParameter("investmentIdTab3");
        if (StringUtils.isBlank(projectEndTime) || StringUtils.isBlank(invesetmentStatusId)) {
            saveError(request, getText("report.investment.project.required.error", request.getLocale()));
            return mav;
        }

        RiskControlReport report = getRiskControlReport(request);
        String policyChanges = request.getParameter("policyChanges");
        String priceChanges = request.getParameter("priceChanges");
        String investmentEvaluation = request.getParameter("investmentEvaluation");
        report.setPolicyChanges(policyChanges);
        report.setPriceChanges(priceChanges);
        report.setInvestmentEvaluation(investmentEvaluation);

        SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
        try {
            Date projectEndTimeDate = dateFormat.parse(projectEndTime);
            String projectInfoId = request.getParameter("id");
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            InvestmentStatus investmentStatus = reportManager.findInvesetmentStatus(projectInfo, Long.valueOf(invesetmentStatusId));
            String projectType = investmentStatus.getProjectType();
            CapitalInvestmentType investmentType = CapitalInvestmentType.valueOf(projectType.toUpperCase());
            switch (investmentType) {
                case REAL_ESTATE :
                case INFRASTRUCTURE :
                    InvestmentProject investmentProject = projectProgressManager.findInvestmentProject(investmentStatus, projectEndTimeDate);
                    Set<InvestmentProject> setIp = report.getInvestmentProjects();
                    if (setIp != null) {
                        Iterator<InvestmentProject> itIp = setIp.iterator();
                        while (itIp.hasNext()) {
                            InvestmentProject ip = itIp.next();
                            if (ip.getInvestmentStatus().getId().equals(investmentStatus.getId())) {
                                itIp.remove();
                            }
                        }
                    }
                    List<InvestmentProject> investmentProjects = new ArrayList<InvestmentProject>();
                    if (investmentProject != null) {
                        report.getInvestmentProjects().add(investmentProject);
                        investmentProjects.add(investmentProject);
                    }
                    mav.addObject("investmentProjects", investmentProjects);
                    break;
                case SUPPLEMENTAL_LIQUIDITY :
                    SupplyLiquidProject supplyLiquidProject = projectProgressManager.findSupplyLiquidProject(investmentStatus, projectEndTimeDate);
                    Set<SupplyLiquidProject> setSlp = report.getSupplyLiquidProjects();
                    if (setSlp != null) {
                        Iterator<SupplyLiquidProject> itSlp = setSlp.iterator();
                        while (itSlp.hasNext()) {
                            SupplyLiquidProject ip = itSlp.next();
                            if (ip.getInvestmentStatus().getId().equals(investmentStatus.getId())) {
                                itSlp.remove();
                            }
                        }
                    }
                    List<SupplyLiquidProject> supplyLiquidProjects = new ArrayList<SupplyLiquidProject>();
                    if (supplyLiquidProject != null) {
                        updateSupplyLiquidProject(supplyLiquidProject, request);
                        report.getSupplyLiquidProjects().add(supplyLiquidProject);
                        supplyLiquidProjects.add(supplyLiquidProject);
                    }
                    mav.addObject("supplyLiquidProjects", supplyLiquidProjects);
                    break;
                default :
            }
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }

        reportManager.save(report);
        saveMessage(request, getText("report.update.success", request.getLocale()));
        mav.addObject("riskControlReport", report);
        return mav;
    }

    private void updateSupplyLiquidProject(final SupplyLiquidProject supplyLiquidProject, final HttpServletRequest request) {
        String industryVista = request.getParameter("industryVista");
        supplyLiquidProject.setIndustryVista(industryVista);

        String firmSize = request.getParameter("firmSize");
        supplyLiquidProject.setFirmSize(firmSize);

        String description = request.getParameter("description");
        supplyLiquidProject.setDescription(description);

        String saleSituation = request.getParameter("saleSituation");
        supplyLiquidProject.setSaleSituation(saleSituation);

        String bigChanges = request.getParameter("bigChanges");
        supplyLiquidProject.setBigChanges("true".equals(bigChanges));

        String investmentProgress = request.getParameter("investmentProgress");
        supplyLiquidProject.setInvestmentProgress(investmentProgress);

        String comments = request.getParameter("supplyLiquidProject.comments");
        supplyLiquidProject.setComments(comments);

        projectProgressManager.saveSupplyLiqidProject(supplyLiquidProject);
    }

    private ModelAndView saveTab4(final HttpServletRequest request, final ModelAndView mav) {
        String projectEndTime = request.getParameter("projectEndTimeTab4");
        String invesetmentStatusId = request.getParameter("investmentTab4");
        if (StringUtils.isBlank(projectEndTime) || StringUtils.isBlank(invesetmentStatusId)) {
            saveError(request, getText("report.repayment.project.required.error", request.getLocale()));
            return mav;
        }

        RiskControlReport report = getRiskControlReport(request);
        String repaymentEvaluation = request.getParameter("repaymentEvaluation");
        report.setRepaymentEvaluation(repaymentEvaluation);

        SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
        try {
            Date projectEndTimeDate = dateFormat.parse(projectEndTime);
            String projectInfoId = request.getParameter("id");
            ProjectInfo projectInfo = projectInfoManager.get(Long.valueOf(projectInfoId));
            InvestmentStatus investmentStatus = reportManager.findInvesetmentStatus(projectInfo, Long.valueOf(invesetmentStatusId));
            mav.addObject("selectedInvestmentStatus", investmentStatus);
            String projectType = investmentStatus.getProjectType();
            CapitalInvestmentType investmentType = CapitalInvestmentType.valueOf(projectType.toUpperCase());
            switch (investmentType) {
                case REPAYMENT_PROJECT :
                    RepaymentProject repaymentProject = projectProgressManager.findRepaymentProject(investmentStatus, projectEndTimeDate);
                    Set<RepaymentProject> setIp = report.getRepaymentProjects();
                    if (setIp != null) {
                        Iterator<RepaymentProject> itIp = setIp.iterator();
                        while (itIp.hasNext()) {
                            RepaymentProject ip = itIp.next();
                            if (ip.getInvestmentStatus().getId().equals(investmentStatus.getId())) {
                                itIp.remove();
                            }
                        }
                    }

                    List<RepaymentProject> repaymentProjects = new ArrayList<RepaymentProject>();
                    if (repaymentProject != null) {
                        repaymentProjects.add(repaymentProject);
                        report.getRepaymentProjects().add(repaymentProject);
                    } else {
                        saveMessage(
                                request,
                                getText("report.repayment.project.notFound", new String[]{investmentStatus.getProjectName(), projectEndTime},
                                        request.getLocale()));
                    }
                    mav.addObject("repaymentProjects", repaymentProjects);
                    break;
                default :
            }
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        reportManager.save(report);
        saveMessage(request, getText("report.update.success", request.getLocale()));
        mav.addObject("riskControlReport", report);
        return mav;
    }

    private ModelAndView saveTab5(final HttpServletRequest request, final ModelAndView mav) {
        RiskControlReport report = getRiskControlReport(request);
        String collateralSummary = request.getParameter("collateralSummary");
        report.setCollateralSummary(collateralSummary);
        reportManager.save(report);
        saveMessage(request, getText("report.update.success", request.getLocale()));
        mav.addObject("riskControlReport", report);
        return mav;
    }

    private ModelAndView saveTab7(final HttpServletRequest request, final ModelAndView mav) {
        RiskControlReport report = getRiskControlReport(request);
        String statusBeforeMaturity = request.getParameter("statusBeforeMaturity");
        report.setStatusBeforeMaturity(statusBeforeMaturity);
        reportManager.save(report);
        saveMessage(request, getText("report.update.success", request.getLocale()));
        mav.addObject("riskControlReport", report);
        return mav;
    }

    private ModelAndView saveTab8(final HttpServletRequest request, final ModelAndView mav) {
        RiskControlReport report = getRiskControlReport(request);
        String comments = request.getParameter("comments");
        String optionsRadios = request.getParameter("optionsRadios");
        report.setTab8Option(optionsRadios);
        switch (optionsRadios) {
            case "option1" :
                report.setComments(getText("report.riskcontrol.tab8.option1", request.getLocale()));
                break;
            case "option2" :
                report.setComments(getText("report.riskcontrol.tab8.option2", request.getLocale()));
                break;
            case "option3" :
                report.setComments(comments);
                break;
            default :
        }
        reportManager.save(report);
        saveMessage(request, getText("report.update.success", request.getLocale()));
        mav.addObject("riskControlReport", report);
        return mav;
    }

    private ModelAndView saveFinanceCheck(final HttpServletRequest request, ModelAndView mav) {
        String counterpartyId = request.getParameter("counterpartyIdTab21");
        String prevTermTime = request.getParameter("prevTermTimeTab21");
        String currTermTime = request.getParameter("currTermTimeTab21");
        String projectInfoId = request.getParameter("id");
        String comments = request.getParameter("financeStatementSummary");

        if (!StringUtils.isBlank(comments) && StringUtils.isBlank(counterpartyId)) {
            // Save comments only
            RiskControlReport report = getRiskControlReport(request);
            report.setFinanceCheckComment(comments);
            reportManager.save(report);
            saveMessage(request, getText("report.update.success", request.getLocale()));
            mav.addObject("riskControlReport", report);
            List<FinanceCheck> financeCheckList = reportManager.getFinanceCheckList(report);
            mav.addObject("financeCheckList", financeCheckList);
            return mav;
        }

        if (StringUtils.isBlank(counterpartyId) || StringUtils.isBlank(prevTermTime) || StringUtils.isBlank(currTermTime)
                || StringUtils.isBlank(projectInfoId)) {
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
            if (counterparty.getCounterpartyType().equals(CounterpartyType.REAL_ESTATE_FIRM.toString())
                    || counterparty.getCounterpartyType().equals(CounterpartyType.COMMERCE_COMPANY.toString())) {
                CorporateBalanceSheet prevTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                CorporateBalanceSheet currTermCbs = financeSheetManager.findCorporateBalanceSheet(projectInfo, counterparty, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);

                Iterator<CorporateBalanceSheet> stIt = report.getCorporateBalanceSheets().iterator();
                while (stIt != null && stIt.hasNext()) {
                    CorporateBalanceSheet ps = stIt.next();
                    if (ps.getCounterparty().equals(counterparty)) {
                        stIt.remove();
                    }
                }

                if (prevTermCbs != null) {
                    report.getCorporateBalanceSheets().add(prevTermCbs);
                }

                if (currTermCbs != null) {
                    report.getCorporateBalanceSheets().add(currTermCbs);
                }

                ProfitStatement prevProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                ProfitStatement currProfitStatement = financeSheetManager.findProfitStatement(projectInfo, counterparty, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);
                Iterator<ProfitStatement> psIt = report.getProfitStatements().iterator();
                while (psIt != null && psIt.hasNext()) {
                    ProfitStatement ps = psIt.next();
                    if (ps.getCounterparty().equals(counterparty)) {
                        psIt.remove();
                    }
                }

                if (prevProfitStatement != null) {
                    report.getProfitStatements().add(prevProfitStatement);
                }

                if (currProfitStatement != null) {
                    report.getProfitStatements().add(currProfitStatement);
                }
            } else if (counterparty.getCounterpartyType().equals(CounterpartyType.INSTITUTION.toString())) {
                InstituteBalanceSheet prevIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty, c1.get(Calendar.YEAR),
                        c1.get(Calendar.MONTH) + 1);
                InstituteBalanceSheet currIBS = financeSheetManager.findInstituteBalanceSheet(projectInfo, counterparty, c2.get(Calendar.YEAR),
                        c2.get(Calendar.MONTH) + 1);

                Iterator<InstituteBalanceSheet> stIt = report.getInstituteBalanceSheet().iterator();
                while (stIt != null && stIt.hasNext()) {
                    InstituteBalanceSheet ps = stIt.next();
                    if (ps.getCounterparty().equals(counterparty)) {
                        stIt.remove();
                    }
                }
                if (prevIBS != null) {
                    report.getInstituteBalanceSheet().add(prevIBS);
                }

                if (currIBS != null) {
                    report.getInstituteBalanceSheet().add(currIBS);
                }
            }

            CreditInformation creditInformation = getCreditInformation(request);
            if (creditInformation != null) {
                report.getCreditInformations().add(creditInformation);
            }

            report.setFinanceCheckComment(comments);
            reportManager.save(report);
            saveMessage(request, getText("report.update.success", request.getLocale()));
            mav.addObject("riskControlReport", report);
            List<FinanceCheck> financeCheckList = reportManager.getFinanceCheckList(report);
            mav.addObject("financeCheckList", financeCheckList);
        } catch (ParseException e) {
            saveError(request, "Date format is incorrect ");
            return mav;
        }
        return mav;
    }

    private RiskControlReport loadRiskControlReport(ProjectInfo projectInfo) {
        // RiskControlReport report = projectInfo.getRiskControlReport();
        RiskControlReport report = null;
        if (report == null) {
            report = new RiskControlReport();
            report.setProjectInfo(projectInfo);
            report.setCreateUser(getCurrentUser().getUsername());
            report.setCreateTime(new Date());
            // projectInfo.setRiskControlReport(report);
        }
        // else
        // {
        // report.setUpdateUser(getCurrentUser().getUsername());
        // report.setUpdateTime(new Date());
        // }
        return report;
    }

    @RequestMapping(value = "/reports/riskControlReport*", method = RequestMethod.POST)
    public ModelAndView onSubmit(final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/reports/riskControlReport");
        String method = request.getParameter("method");
        if (!StringUtils.isBlank(method)) {

            switch (method) {
                case "SubjectCapacityCheck" :
                    mav = subjectCapacityCheck(request, mav);
                    break;
                case "SaveTab2" :
                    mav = saveTab2(request, mav);
                    break;
                case "FinanceCheckTab21" :
                    mav = fetchFinanceReports(request, mav);
                    break;
                case "SaveFinanceCheckTab21" :
                    mav = saveFinanceCheck(request, mav);
                    break;
                case "ProjectProgressTab3" :
                    mav = projectProgressTab3(request, mav);
                    break;
                case "SaveTab3" :
                    mav = saveTab3(request, mav);
                    break;
                case "ProjectProgressTab4" :
                    mav = projectProgressTab4(request, mav);
                    break;
                case "SaveTab4" :
                    mav = saveTab4(request, mav);
                    break;
                case "SaveTab5" :
                    mav = saveTab5(request, mav);
                    break;
                case "FinanceCheckTab6" :
                    mav = fetchFinanceReportsTab6(request, mav);
                    break;
                case "SaveFinanceCheckTab6" :
                    mav = saveFinanceCheckTab6(request, mav);
                    break;
                case "SaveTab7" :
                    mav = saveTab7(request, mav);
                    break;
                case "SaveTab8" :
                    mav = saveTab8(request, mav);
                    break;
                default :
            }
        }
        return mav;
    }

    @RequestMapping(value = "/reports/reportSearch*", method = RequestMethod.GET)
    public ModelAndView reportSearch(final HttpServletRequest request) {
        List<RiskControlReport> reports = reportManager.getAll();
        if (reports == null || reports.isEmpty()) {
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
            case "SearchReport" :
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

                if (reports != null && !reports.isEmpty()) {
                    Iterator<RiskControlReport> itR = reports.iterator();
                    while (itR.hasNext()) {
                        RiskControlReport r = itR.next();
                        if (!projectInfoSet.contains(r.getProjectInfo())) {
                            itR.remove();
                        }
                    }
                }

                return new ModelAndView("/reports/reportSearch").addObject("reportList", reports);
            default :
        }
        return new ModelAndView("/reports/reportSearch").addObject(projectInfoManager.getAll());
    }

    @RequestMapping(value = "/reports/addReport*", method = RequestMethod.GET)
    public ModelAndView addReport() {
        ModelAndView mav = new ModelAndView("/reports/addReport");
        Map<String, String> projectNames = new TreeMap<String, String>();
        List<String> nameList = projectInfoManager.getAllProjectNames();
        if (nameList != null && !nameList.isEmpty()) {
            for (int i = 0; i < nameList.size(); i++) {
                String name = nameList.get(i);
                projectNames.put(name, name);
            }
        }
        mav.addObject("allProjectNames", projectNames);

        Map<String, String> allReportSeasons = new TreeMap<String, String>();
        int startYear = getCurrentYear() - 3;
        int endYear = getCurrentYear() + 3;
        for (int y = startYear; y < endYear; y++) {
            for (int s = 1; s < 5; s++) {
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
        ModelAndView mav = addReport();
        String projectName = request.getParameter("projectInfo.projectName");
        String reportSeason = request.getParameter("reportSeason");
        ProjectInfo projectInfo = projectInfoManager.findByProjectName(projectName);
        RiskControlReport report = reportManager.findRiskControlReport(projectInfo, reportSeason);
        if (report != null) {
            saveError(request, getText("report.add.error.existd", new String[]{projectInfo.getProjectName(), reportSeason}, request.getLocale()));
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
        switch (reportQuanter) {
            case "1" :
                timeRangeStart = sf.parse(reportYear + "-01-01");
                timeRangeEnd = sf.parse(reportYear + "-03-31");
                break;
            case "2" :
                timeRangeStart = sf.parse(reportYear + "-04-01");
                timeRangeEnd = sf.parse(reportYear + "-06-30");
                break;
            case "3" :
                timeRangeStart = sf.parse(reportYear + "-07-01");
                timeRangeEnd = sf.parse(reportYear + "-09-30");
                break;
            case "4" :
                timeRangeStart = sf.parse(reportYear + "-10-01");
                timeRangeEnd = sf.parse(reportYear + "-12-31");
                break;
            default :
                // Error
        }
        report.setTimeRangeStart(timeRangeStart);
        report.setTimeRangeEnd(timeRangeEnd);
        report.setCreateUser(getCurrentUser().getUsername());
        report.setCreateTime(new Date());
        reportManager.save(report);
        saveMessage(request, getText("report.add.success", new String[]{projectInfo.getProjectName(), reportSeason}, request.getLocale()));
        mav.setViewName("redirect:/reports/reportSearch");
        return mav;
    }

    @ModelAttribute("financeCheckList")
    public List<FinanceCheck> getFinanceCheckList(final HttpServletRequest request) {
        RiskControlReport report = getRiskControlReport(request);
        return reportManager.getFinanceCheckList(report);
    }

    @RequestMapping(value = "/reports/previewReport*", method = RequestMethod.GET)
    public ModelAndView previewReport(final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/reports/previewReport");
        mav.addObject("user", getCurrentUser());
        List<String> tabList = new ArrayList<String>();
        for(int i = 1; i <= 8; i++)
        {
            tabList.add("Tab" + i);
        }
        mav.addObject("tabList", tabList);
        return mav;
    }
    
    @RequestMapping(value = "/reports/removeTab*", method = RequestMethod.GET)
    public ModelAndView removeTab(final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("/reports/previewReport");
        mav.addObject("user", getCurrentUser());
        String removedTabId = request.getParameter("tabId");
        String tabList = request.getParameter("tabList");
        String[] tabArray = tabList.substring(1, tabList.length() - 1).split("\\s*,\\s*");
        List<String> tabListArray = new ArrayList<String>();
        for(String tab : tabArray) {
            if(tab.equalsIgnoreCase(removedTabId)) {
                continue;
            }
            tabListArray.add(tab);
        }
        mav.addObject("tabList", tabListArray);
        return mav;
    }

    @ModelAttribute("financeCheckListTab6")
    public List<FinanceCheck> getFinanceCheckListTab6(final HttpServletRequest request) {
        RiskControlReport report = getRiskControlReport(request);
        return reportManager.getFinanceCheckListTab6(report);
    }

    @RequestMapping(value = "/reports/generateReport*", method = RequestMethod.GET)
    public ModelAndView generateReport(final HttpServletRequest request, HttpServletResponse response) {
        RiskControlReport report = getRiskControlReport(request);
        RiskControlReportPDF pdf = new RiskControlReportPDF(report, report.getProjectInfo());
        return new ModelAndView(pdf);
    }

    @ModelAttribute("allTrustManagers")
    public Map<String, String> getAllTrustManagers() {
        List<Manager> managers = this.projectInfoManager.getAllTrustManagers();
        Map<String, String> allTrustManagers = new TreeMap<String, String>();
        for (Manager m : managers) {
            allTrustManagers.put(m.getName(), m.getName());
        }
        return allTrustManagers;
    }

    @ModelAttribute("allDelegateManagers")
    public Map<String, String> getAllDelegateManagers() {
        List<Manager> managers = this.projectInfoManager.getAllDelegateManagers();
        Map<String, String> allDelegateManagers = new TreeMap<String, String>();
        for (Manager m : managers) {
            allDelegateManagers.put(m.getName(), m.getName());
        }
        return allDelegateManagers;
    }

    @ModelAttribute("allRiskManagers")
    public Map<String, String> getAllRiskManagers() {
        List<Manager> managers = this.projectInfoManager.getAllRiskManagers();
        Map<String, String> allRiskManagers = new TreeMap<String, String>();
        for (Manager m : managers) {
            allRiskManagers.put(m.getName(), m.getName());
        }
        return allRiskManagers;
    }

}
