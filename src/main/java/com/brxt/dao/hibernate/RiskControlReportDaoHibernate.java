package com.brxt.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.RiskControlReportDao;
import com.brxt.model.ProjectInfo;
import com.brxt.model.report.RiskControlReport;

@Repository("riskControlReportDao")
public class RiskControlReportDaoHibernate extends
		GenericDaoHibernate<RiskControlReport, Long> implements
		RiskControlReportDao {

	public RiskControlReportDaoHibernate() {
		super(RiskControlReport.class);
	}

	@Override
	public List<RiskControlReport> findByProjectInfo(ProjectInfo projectInfo) {
		Criteria criteria = getSession().createCriteria(RiskControlReport.class);
		criteria.add(Restrictions.eq("projectInfo", projectInfo));
		return criteria.list();
	}

	@Override
	public RiskControlReport find(ProjectInfo projectInfo, String reportSeason) {
		Criteria criteria = getSession().createCriteria(RiskControlReport.class);
		criteria.add(Restrictions.eq("projectInfo", projectInfo));
		criteria.add(Restrictions.eq("reportSeason", reportSeason));
		criteria.setMaxResults(1);
		List<RiskControlReport> reports = criteria.list();
		if(reports != null && !reports.isEmpty())
		{
			return reports.get(0);
		}
		return null;
	}
	
	public List<RiskControlReport> findByReport(RiskControlReport report)
	{
		Criteria criteria = getSession().createCriteria(RiskControlReport.class);
		
		if(report != null)
		{
			if (report.getSearchTimeStart() != null && report.getSearchTimeEnd() != null) {
				Date startDate = report.getSearchTimeStart();
				Date endDate = report.getSearchTimeEnd();
				criteria.add(Restrictions.ge("createTime", startDate));
				criteria.add(Restrictions.le("createTime", endDate));
			} else if(report.getSearchTimeStart() != null) {
				Date startDate = report.getSearchTimeStart();
				criteria.add(Restrictions.ge("createTime", startDate));
			} else if(report.getSearchTimeEnd() != null) {
				Date endDate = report.getSearchTimeEnd();
				criteria.add(Restrictions.le("createTime", endDate));
			}
		}
		return criteria.list();
	}
	
}
