package org.zerock.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class BoardListener
 *
 */
//@WebListener
public class BoardListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public BoardListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext app = sce.getServletContext();
        app.setAttribute("root", app.getContextPath());
        
        app.setAttribute("staticPath", "https://objectstorage.ap-seoul-1.oraclecloud.com/n/cnkghg76rfem/b/bucket-20210216-1420/o");
    }
	
}
