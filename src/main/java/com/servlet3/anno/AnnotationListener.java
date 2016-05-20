package com.servlet3.anno;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by tancw on 2016/5/20.
 */
@WebListener("this is only demo listen")
public class AnnotationListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("content init");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("content destroy");
	}
}
