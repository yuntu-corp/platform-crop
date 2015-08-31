package com.unitever.platform.core.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@SuppressWarnings("deprecation")
public class CustomAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

//	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		//未登录时，受权限控制的路径这里都会拦截到，然后处理redirect的路径，可以控制不同用户的登录页，好像是这个意思
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//		System.out.println(httpRequest.getServletPath());
//		
//		String redirectUrl = buildRedirectUrlToLoginPage(httpRequest, httpResponse, authException);
//		redirectUrl = "http://localhost:9997/courseplatform/bd/welcome/login";
//				
//      request.getSession().setAttribute("redirectUrl", redirectUrl);
//      redirectStrategy.sendRedirect(httpRequest, httpResponse, redirectUrl);
        super.commence(request, response, authException);
	}

	
	
}