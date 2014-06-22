package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
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
		// TODO Auto-generated method stub
		return null;
	}

}
