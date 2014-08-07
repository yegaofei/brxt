package com.brxt.dao.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.Table;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.brxt.dao.SupplyLiquidProjectDao;
import com.brxt.model.InvestmentStatus;
import com.brxt.model.projectprogress.SupplyLiquidProject;

@Repository("supplyLiquidProjectDao")
public class SuplyLiqProjDaoHibernate extends
GenericDaoHibernate<SupplyLiquidProject, Long> implements SupplyLiquidProjectDao{

    private JdbcTemplate jdbcTemplate = null;
    private final Table table = AnnotationUtils.findAnnotation(SupplyLiquidProject.class, Table.class);
    
	public SuplyLiqProjDaoHibernate()
	{
		super(SupplyLiquidProject.class);
	}

	@Override
	public SupplyLiquidProject find(InvestmentStatus investmentStatus, Date projectEndTime) {
		List<SupplyLiquidProject> results = getSession()
				.createCriteria(SupplyLiquidProject.class)
				.add(Restrictions.eq("investmentStatus", investmentStatus))
				.add(Restrictions.eq("projectEndTime", projectEndTime)).list();
		if (results != null && !results.isEmpty()) {
			return results.get(0);
		}
		return null;
	}

    @Override
    public List<Date> listProjectEndTime(Long investmentStatusId) {
        if(jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        }
        return jdbcTemplate.queryForList(
                "select projectEndTime from " + table.name() + " where investment_status_id=?", Date.class, investmentStatusId);
    }

}



