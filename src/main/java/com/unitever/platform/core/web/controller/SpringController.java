package com.unitever.platform.core.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.util.UUID;

/**
 * 所有Controller的基类
 * 
 * @author tianyl
 * 
 */
public class SpringController {

	public SpringController() {

	}

	/**
	 * 在request中添加pageId参数
	 * 
	 * @return
	 */
	@ModelAttribute("pageId")
	public String populatePageId() {
		return "page" + UUID.getUUID();
	}

	/**
	 * 获取request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		return SpringMVCUtil.getRequest();
	}

	/**
	 * 获取response对象
	 * 
	 * @return
	 */
	public HttpServletResponse getResponse() {
		return SpringMVCUtil.getResponse();
	}

	public void print(String result) {
		render(result);
	}

	public void render(String result) {
		SpringMVCUtil.render(result, "text/plain");
	}

	public void renderText(String result) {
		SpringMVCUtil.render(result, "text/plain");
	}

	public void renderJson(String result) {
		SpringMVCUtil.render(result, "application/json");
	}

	public void renderXml(String result) {
		SpringMVCUtil.render(result, "text/xml");
	}

	public void renderHtml(String result) {
		SpringMVCUtil.render(result, "text/html");
	}

	public void render(String result, String contentType) {
		SpringMVCUtil.render(result, contentType);
	}

	public String redirectTo(String url) {
		return "redirect:" + url;
	}

	public String forwardTo(String url) {
		return "forward:" + url;
	}
	
	@InitBinder
	//此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
	public void initBinder(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	/**
	 * 在request中添加值
	 * 
	 * @param key
	 * @param value
	 */
	public void setAttribute(String key, Object value) {
		getRequest().setAttribute(key, value);
	}
}
