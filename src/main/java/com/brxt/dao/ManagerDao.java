package com.brxt.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.brxt.model.Manager;
import com.brxt.model.enums.ManagerType;

public interface ManagerDao extends GenericDao<Manager, Long> {

    public Manager findByName(String name);
    
    public List<Manager> findByType(ManagerType type);
}
