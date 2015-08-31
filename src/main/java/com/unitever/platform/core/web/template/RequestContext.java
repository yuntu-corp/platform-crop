package com.unitever.platform.core.web.template;

import javax.servlet.http.HttpServletRequest;

public class RequestContext {
	private String contextPath;
	
	public RequestContext(HttpServletRequest request) {
		super();
		this.contextPath = request.getContextPath();
	}

	public String getContextPath() {
		return contextPath;
	}
}
