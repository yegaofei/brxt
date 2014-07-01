package com.brxt.webapp.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInitServlet extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -918033350926897221L;

	public void service(ServletRequest req, ServletResponse rep) throws ServletException, IOException
	{
		
	}
	
	public void init() throws ServletException
	{
		System.setProperty("webappRoot", getServletContext().getRealPath("/"));
		//For tomcat only
		String log4jXml = getServletContext().getRealPath("/") + getInitParameter("configfile");		
		File log4jXmlFile = new File(log4jXml);
		if(log4jXmlFile.exists())
		{
			PropertyConfigurator.configure(log4jXml);			
		}
	}
}
