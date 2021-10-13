package com.douzone.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextLoadListener implements ServletContextListener {
   
	public void contextInitialized(ServletContextEvent sce)  { 
		ServletContext sc = sce.getServletContext();
		String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
		
//		new WebXmlApplicationContext(contextConfigLocation);
		
		System.out.println("Application Starts.....:" + contextConfigLocation);
   }

    public void contextDestroyed(ServletContextEvent sce)  { 

    }


	
}
