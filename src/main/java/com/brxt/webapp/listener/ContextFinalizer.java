package com.brxt.webapp.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;

public class ContextFinalizer implements ServletContextListener{

	
    public void contextInitialized(ServletContextEvent sce) {
    }

    public void contextDestroyed(ServletContextEvent sce) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver d = null;
        while(drivers.hasMoreElements()) {
            try {
                d = drivers.nextElement();
                DriverManager.deregisterDriver(d);
                //LOGGER.warn(String.format("Driver %s deregistered", d));
            } catch (SQLException ex) {
                //LOGGER.warn(String.format("Error deregistering driver %s", d), ex);
            	ex.printStackTrace();
            }
        }
        
        try {
            AbandonedConnectionCleanupThread.shutdown();
        } catch (InterruptedException e) {
            //logger.warn("SEVERE problem cleaning up: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
