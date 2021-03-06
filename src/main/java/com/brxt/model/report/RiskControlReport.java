package com.brxt.model.report;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.appfuse.model.BaseObject;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.brxt.model.CreditInformation;
import com.brxt.model.ProjectInfo;
import com.brxt.model.SubjectCapacity;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;
import com.brxt.model.projectprogress.InvestmentProject;
import com.brxt.model.projectprogress.RepaymentProject;
import com.brxt.model.projectprogress.SupplyLiquidProject;

@Entity
@Table(name = "risk_control_report")
public class RiskControlReport extends BaseObject{

	private static final long serialVersionUID = -6533912247249058510L;
	private Long id;
	private ProjectInfo projectInfo;
	private ReportStatus reportStatus = new ReportStatus();
	private String reportSeason; //报表季度  2014Q1, 2014Q2
	private Date timeRangeStart;  
	private Date timeRangeEnd;  
	
	//主体资格排查
	private Set<SubjectCapacity> subjectCapacities = new HashSet<SubjectCapacity>();
	
	//借款人财务排查
	private Set<CorporateBalanceSheet> corporateBalanceSheets = new HashSet<CorporateBalanceSheet>();
	private Set<InstituteBalanceSheet> instituteBalanceSheet = new HashSet<InstituteBalanceSheet>();
	private Set<ProfitStatement> profitStatements = new HashSet<ProfitStatement>();
	
	//征信信息排查
	private Set<CreditInformation> creditInformations = new HashSet<CreditInformation>();
	
	//保证人财务排查
	private Set<CorporateBalanceSheet> guarantorCorpBalanceSheets = new HashSet<CorporateBalanceSheet>();
	private Set<InstituteBalanceSheet> guarantorInstBalanceSheet = new HashSet<InstituteBalanceSheet>();
	
	//项目进展
	private Set<InvestmentProject> investmentProjects = new HashSet<InvestmentProject>();
	private Set<RepaymentProject> repaymentProjects = new HashSet<RepaymentProject>();
	private Set<SupplyLiquidProject> supplyLiquidProjects = new HashSet<SupplyLiquidProject>();
	
	private String financeCheckComment;//财务状况排查结论	
	private String financeCheckComment_report;//财务状况排查结论	
	private String investmentEvaluation; //资金投向综合评价
	private String investmentEvaluation_report; //资金投向综合评价
	private String policyChanges; //房地产市场调控政策有无变化
	private String priceChanges; //项目所在区域商品房价格走势情况；如难以取得上述数据，可结合周边楼盘定价及销售情况	
	private String repaymentEvaluation; //还款来源综合评价
	private String repaymentEvaluation_report; //还款来源综合评价
	private String collateralSummary; //担保物的状况
	private String collateralSummary_report; //担保物的状况
	private String guarantorCheckComment; //保证人担保能力综合评价
	private String guarantorCheckComment_report; //保证人担保能力综合评价
	private String guarantorSummary; //项目管理人员认为需要说明的其他事项可补充填列
	private String statusBeforeMaturity; //到期前情况说明
	private String statusBeforeMaturity_report; //到期前情况说明
	private String comments; //结论和建议
	private String comments_report; //结论和建议
	
	private String tab8Option;
	
	private Date createTime;
	private String createUser;
	private Date updateTime;
	private String updateUser;
	private Integer version;
	
	private Date searchTimeStart;
	private Date searchTimeEnd;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH},optional=true)  
	@JoinColumn(name="projectInfo_id") 
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}
	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}
	
	@OneToOne(cascade = CascadeType.ALL)
	public ReportStatus getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}
	
	@Column(nullable = false)
	public String getReportSeason() {
		return reportSeason;
	}
	public void setReportSeason(String reportSeason) {
		this.reportSeason = reportSeason;
	}
	public Date getTimeRangeStart() {
		return timeRangeStart;
	}
	public void setTimeRangeStart(Date timeRangeStart) {
		this.timeRangeStart = timeRangeStart;
	}
	public Date getTimeRangeEnd() {
		return timeRangeEnd;
	}
	public void setTimeRangeEnd(Date timeRangeEnd) {
		this.timeRangeEnd = timeRangeEnd;
	}

	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_corp_balancesheet",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "corp_balancesheet_id")
    )
	public Set<CorporateBalanceSheet> getCorporateBalanceSheets() {
		return corporateBalanceSheets;
	}
	public void setCorporateBalanceSheets(
			Set<CorporateBalanceSheet> corporateBalanceSheets) {
		this.corporateBalanceSheets = corporateBalanceSheets;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_inst_balancesheet",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "inst_balancesheet_id")
    )
	public Set<InstituteBalanceSheet> getInstituteBalanceSheet() {
		return instituteBalanceSheet;
	}
	public void setInstituteBalanceSheet(
			Set<InstituteBalanceSheet> instituteBalanceSheet) {
		this.instituteBalanceSheet = instituteBalanceSheet;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_profit_statement",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "profit_statement_id")
    )
	public Set<ProfitStatement> getProfitStatements() {
		return profitStatements;
	}
	public void setProfitStatements(Set<ProfitStatement> profitStatements) {
		this.profitStatements = profitStatements;
	}
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_credit_information",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "credit_information_id")
    )
	public Set<CreditInformation> getCreditInformations() {
		return creditInformations;
	}
	public void setCreditInformations(Set<CreditInformation> creditInformations) {
		this.creditInformations = creditInformations;
	}
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_guarantor_corp_balancesheet",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "corp_balancesheet_id")
    )
	public Set<CorporateBalanceSheet> getGuarantorCorpBalanceSheets() {
		return guarantorCorpBalanceSheets;
	}
	public void setGuarantorCorpBalanceSheets(
			Set<CorporateBalanceSheet> guarantorCorpBalanceSheets) {
		this.guarantorCorpBalanceSheets = guarantorCorpBalanceSheets;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_guarantor_inst_balancesheet",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "inst_balancesheet_id")
    )
	public Set<InstituteBalanceSheet> getGuarantorInstBalanceSheet() {
		return guarantorInstBalanceSheet;
	}
	public void setGuarantorInstBalanceSheet(
			Set<InstituteBalanceSheet> guarantorInstBalanceSheet) {
		this.guarantorInstBalanceSheet = guarantorInstBalanceSheet;
	}
	
	@Column(columnDefinition = "longtext")
	public String getFinanceCheckComment() {
		return financeCheckComment;
	}
	
	public void setFinanceCheckComment(String financeCheckComment) {
		this.financeCheckComment = financeCheckComment;
	}
	@Column(columnDefinition = "longtext")
	public String getInvestmentEvaluation() {
		return investmentEvaluation;
	}
	public void setInvestmentEvaluation(String investmentEvaluation) {
		this.investmentEvaluation = investmentEvaluation;
	}
	@Column(columnDefinition = "longtext")
	public String getPolicyChanges() {
		return policyChanges;
	}
	public void setPolicyChanges(String policyChanges) {
		this.policyChanges = policyChanges;
	}
	@Column(columnDefinition = "longtext")
	public String getPriceChanges() {
		return priceChanges;
	}
	public void setPriceChanges(String priceChanges) {
		this.priceChanges = priceChanges;
	}
	@Column(columnDefinition = "longtext")
	public String getRepaymentEvaluation() {
		return repaymentEvaluation;
	}
	public void setRepaymentEvaluation(String repaymentEvaluation) {
		this.repaymentEvaluation = repaymentEvaluation;
	}
	@Column(columnDefinition = "longtext")
	public String getCollateralSummary() {
		return collateralSummary;
	}
	public void setCollateralSummary(String collateralSummary) {
		this.collateralSummary = collateralSummary;
	}
	@Column(columnDefinition = "longtext")
	public String getGuarantorCheckComment() {
		return guarantorCheckComment;
	}
	public void setGuarantorCheckComment(String guarantorCheckComment) {
		this.guarantorCheckComment = guarantorCheckComment;
	}
	@Column(columnDefinition = "longtext")
	public String getGuarantorSummary() {
		return guarantorSummary;
	}
	public void setGuarantorSummary(String guarantorSummary) {
		this.guarantorSummary = guarantorSummary;
	}
	@Column(columnDefinition = "longtext")
	public String getStatusBeforeMaturity() {
		return statusBeforeMaturity;
	}
	public void setStatusBeforeMaturity(String statusBeforeMaturity) {
		this.statusBeforeMaturity = statusBeforeMaturity;
	}
	@Column(columnDefinition = "longtext")
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	@Transient
	public String getTab8Option() {
        return tab8Option;
    }
    public void setTab8Option(String tab8Option) {
        this.tab8Option = tab8Option;
    }
    public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	
	@Version
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	@Transient
	public Date getSearchTimeStart() {
		return searchTimeStart;
	}

	public void setSearchTimeStart(Date searchTimeStart) {
		this.searchTimeStart = searchTimeStart;
	}

	@Transient
	public Date getSearchTimeEnd() {
		return searchTimeEnd;
	}

	public void setSearchTimeEnd(Date searchTimeEnd) {
		this.searchTimeEnd = searchTimeEnd;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_subject_capacity",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "subject_capacity_id")
    )
	public Set<SubjectCapacity> getSubjectCapacities() {
		return subjectCapacities;
	}
	public void setSubjectCapacities(Set<SubjectCapacity> subjectCapacities) {
		this.subjectCapacities = subjectCapacities;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_investment_project",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "investment_project_id")
    )
	public Set<InvestmentProject> getInvestmentProjects() {
		return investmentProjects;
	}
	public void setInvestmentProjects(Set<InvestmentProject> investmentProjects) {
		this.investmentProjects = investmentProjects;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_repayment_project",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "repayment_project_id")
    )
	public Set<RepaymentProject> getRepaymentProjects() {
		return repaymentProjects;
	}
	public void setRepaymentProjects(Set<RepaymentProject> repaymentProjects) {
		this.repaymentProjects = repaymentProjects;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)    
    @JoinTable(
            name = "report_supply_liquid_project",
            joinColumns = { @JoinColumn(name = "risk_control_report_id") },
            inverseJoinColumns = @JoinColumn(name = "supply_liquid_project_id")
    )
	public Set<SupplyLiquidProject> getSupplyLiquidProjects() {
		return supplyLiquidProjects;
	}
	public void setSupplyLiquidProjects(
			Set<SupplyLiquidProject> supplyLiquidProjects) {
		this.supplyLiquidProjects = supplyLiquidProjects;
	}
	
	@Column(columnDefinition = "longtext")
	public String getFinanceCheckComment_report() {
		return financeCheckComment_report;
	}
	public void setFinanceCheckComment_report(String financeCheckComment_report) {
		this.financeCheckComment_report = financeCheckComment_report;
	}
	
	@Column(columnDefinition = "longtext")
	public String getInvestmentEvaluation_report() {
		return investmentEvaluation_report;
	}
	public void setInvestmentEvaluation_report(String investmentEvaluation_report) {
		this.investmentEvaluation_report = investmentEvaluation_report;
	}
	
	@Column(columnDefinition = "longtext")
	public String getRepaymentEvaluation_report() {
		return repaymentEvaluation_report;
	}
	public void setRepaymentEvaluation_report(String repaymentEvaluation_report) {
		this.repaymentEvaluation_report = repaymentEvaluation_report;
	}
	
	@Column(columnDefinition = "longtext")
	public String getCollateralSummary_report() {
		return collateralSummary_report;
	}
	public void setCollateralSummary_report(String collateralSummary_report) {
		this.collateralSummary_report = collateralSummary_report;
	}
	
	@Column(columnDefinition = "longtext")
	public String getGuarantorCheckComment_report() {
		return guarantorCheckComment_report;
	}
	public void setGuarantorCheckComment_report(String guarantorCheckComment_report) {
		this.guarantorCheckComment_report = guarantorCheckComment_report;
	}
	
	@Column(columnDefinition = "longtext")
	public String getStatusBeforeMaturity_report() {
		return statusBeforeMaturity_report;
	}
	public void setStatusBeforeMaturity_report(String statusBeforeMaturity_report) {
		this.statusBeforeMaturity_report = statusBeforeMaturity_report;
	}
	
	@Column(columnDefinition = "longtext")
	public String getComments_report() {
		return comments_report;
	}
	public void setComments_report(String comments_report) {
		this.comments_report = comments_report;
	}
	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(
				this.reportSeason).append(this.projectInfo).toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof RiskControlReport)) {
			return false;
		}

		final RiskControlReport report = (RiskControlReport) o;

		return !(projectInfo != null ? !projectInfo
				.equals(report.projectInfo)
				: report.projectInfo != null)
				&& !(reportSeason != null ? !reportSeason
						.equals(report.reportSeason)
						: report.reportSeason != null);
	}
	
	@Override
	public int hashCode() {
		int result;
        result = (projectInfo != null ? projectInfo.hashCode() : 0);
        result = 29 * result + (reportSeason != null ? reportSeason.hashCode() : 0);
        return result;
	}

}
