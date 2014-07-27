package com.brxt.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.brxt.dao.ManagerDao;
import com.brxt.model.Manager;
import com.brxt.model.enums.ManagerType;

@Repository("managerDao")
public class ManagerDaoHibernate extends
GenericDaoHibernate<Manager, Long> implements ManagerDao{

    public ManagerDaoHibernate() {
        super(Manager.class);
    }
 
    @Override
    public Manager findByName(String name) {
        List<Manager> managerList =  getSession().createCriteria(Manager.class)
                .add(Restrictions.eq("name", name)).list();
        
        if(managerList != null && !managerList.isEmpty())
        {
            return managerList.get(0);
        }
        return null;
    }

    @Override
    public List<Manager> findByType(ManagerType type) {
        Criteria criteria = getSession().createCriteria(Manager.class);
        if(type != null)
        {
           return criteria
                   .add(Restrictions.eq("managerType", type.getManagerType()))
                   .addOrder(Order.desc("name"))
                   .list();
        }
        return null;
    }

}
