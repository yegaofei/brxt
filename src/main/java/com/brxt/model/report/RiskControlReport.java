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

import com.brxt.model.ProjectInfo;
import com.brxt.model.finance.CorporateBalanceSheet;
import com.brxt.model.finance.InstituteBalanceSheet;
import com.brxt.model.finance.ProfitStatement;

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
	
	private Set<CorporateBalanceSheet> corporateBalanceSheets = new HashSet<CorporateBalanceSheet>();
	private Set<InstituteBalanceSheet> instituteBalanceSheet = new HashSet<InstituteBalanceSheet>();
	private Set<ProfitStatement> profitStatements = new HashSet<ProfitStatement>();
	
	private String financeCheckComment;//财务状况排查结论	
	private String investmentEvaluation; //资金投向综合评价
	private String policyChanges; //房地产市场调控政策有无变化
	private String priceChanges; //项目所在区域商品房价格走势情况；如难以取得上述数据，可结合周边楼盘定价及销售情况	
	private String collateralSummary; //担保物的状况
	private String statusBeforeMaturity; //到期前情况说明
	private String comments; //结论和建议
	
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
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},optional=true)  
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
	
	public String getFinanceCheckComment() {
		return financeCheckComment;
	}
	public void setFinanceCheckComment(String financeCheckComment) {
		this.financeCheckComment = financeCheckComment;
	}
	public String getInvestmentEvaluation() {
		return investmentEvaluation;
	}
	public void setInvestmentEvaluation(String investmentEvaluation) {
		this.investmentEvaluation = investmentEvaluation;
	}
	public String getPolicyChanges() {
		return policyChanges;
	}
	public void setPolicyChanges(String policyChanges) {
		this.policyChanges = policyChanges;
	}
	public String getPriceChanges() {
		return priceChanges;
	}
	public void setPriceChanges(String priceChanges) {
		this.priceChanges = priceChanges;
	}
	public String getCollateralSummary() {
		return collateralSummary;
	}
	public void setCollateralSummary(String collateralSummary) {
		this.collateralSummary = collateralSummary;
	}
	public String getStatusBeforeMaturity() {
		return statusBeforeMaturity;
	}
	public void setStatusBeforeMaturity(String statusBeforeMaturity) {
		this.statusBeforeMaturity = statusBeforeMaturity;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
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
