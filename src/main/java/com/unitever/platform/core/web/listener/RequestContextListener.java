package com.unitever.platform.core.web.listener;

import java.util.Map;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import com.unitever.platform.core.spring.SpringContextHolder;

public class RequestContextListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		if (!req.getRequestURI().endsWith(".do")) {
			return;
		}
		Map<String, RequestContextMonitor> map = SpringContextHolder.getBeansOfType(RequestContextMonitor.class);
		for (RequestContextMonitor init : map.values()) {
			init.init(sre);
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		if (!req.getRequestURI().endsWith(".action")) {
			return;
		}
		Map<String, RequestContextMonitor> map = SpringContextHolder.getBeansOfType(RequestContextMonitor.class);
		for (RequestContextMonitor requestDestroyed : map.values()) {
			requestDestroyed.destroyed(sre);
		}
	}

}
