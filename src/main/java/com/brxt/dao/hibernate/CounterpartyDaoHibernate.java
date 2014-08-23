package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.stereotype.Repository;

import com.brxt.dao.CounterpartyDao;
import com.brxt.model.Counterparty;

@Repository("counterpartyDao")
public class CounterpartyDaoHibernate extends GenericDaoHibernate<Counterparty, Long> implements CounterpartyDao {

    private JdbcTemplate jdbcTemplate = null;

    private static final String SQL_COUNT_PROJECT_INFO = "select g.project_info_id from project_info_guarantors g where g.guarantor_id = ? "
            + "UNION select cp.project_info_id from project_info_counterparties cp where cp.counterparty_id = ?";

    public CounterpartyDaoHibernate() {
        super(Counterparty.class);
    }

    @Override
    public Counterparty findByCounterpartyName(String counterpartyName) {
        List<Counterparty> cpList = getSession().createCriteria(Counterparty.class).add(Restrictions.eq("name", counterpartyName)).list();
        if (cpList == null || cpList.size() == 0) {
            return null;
        } else {
            return cpList.get(0);
        }
    }

    public Counterparty findByCounterparty(Counterparty counterparty) {
        List<Counterparty> cpList = getSession().createCriteria(Counterparty.class).add(Restrictions.eq("name", counterparty.getName()))
                .add(Restrictions.eq("counterpartyType", counterparty.getCounterpartyType())).list();
        if (cpList == null || cpList.size() == 0) {
            return null;
        } else {
            return cpList.get(0);
        }
    }

    public int countProjectInfoId(Counterparty counterparty) {
	    if(jdbcTemplate == null) {
            jdbcTemplate = new JdbcTemplate(SessionFactoryUtils.getDataSource(getSessionFactory()));
        }
	    List<Integer> r = jdbcTemplate.queryForList(SQL_COUNT_PROJECT_INFO, new Object[]{counterparty.getId(), counterparty.getId()}, Integer.class);
	    if(r == null || r.isEmpty()) {
	        return 0;
	    } else {
	        return r.size();
	    }
	}
}
