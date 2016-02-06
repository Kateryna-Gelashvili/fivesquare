package org.k;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
       System.setProperty("org.jboss.logging.provider","slf4j");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
