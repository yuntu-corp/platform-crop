package com.unitever.platform.core.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.core.spring.SpringMVCUtil;

public class RequestResponseContextHolderFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		SpringMVCUtil.setRequestAndResponse(req, res);
		try {
			chain.doFilter(request, response);
		} finally {
			SpringMVCUtil.cleanRequestAndResponse();
		}
	}

	@Override
	public void destroy() {

	}

}
