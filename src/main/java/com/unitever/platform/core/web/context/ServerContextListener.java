package com.unitever.platform.core.web.context;

import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.unitever.platform.core.spring.SpringContextHolder;

public class ServerContextListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		Map<String, ServletContextMonitor> map = SpringContextHolder.getBeansOfType(ServletContextMonitor.class);
		for (ServletContextMonitor monitor : map.values()) {
			monitor.init(sce.getServletContext());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		Map<String, ServletContextMonitor> map = SpringContextHolder.getBeansOfType(ServletContextMonitor.class);
		for (ServletContextMonitor monitor : map.values()) {
			monitor.destroyed(sce.getServletContext());
		}
	}

}
