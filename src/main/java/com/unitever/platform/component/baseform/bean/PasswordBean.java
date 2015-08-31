package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordBean extends ValidateBean {

	public PasswordBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return "password";
	}

	protected String placeholder;

	protected String maxlength;

	protected String size;

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
