package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormBean extends ClosingUCBean {

	public FormBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	protected String onsubmit;

	protected String action;

	protected String target;

	protected String enctype;

	protected String method;

	public String getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	@Override
	protected String getTemplate() {
		return "form-close";
	}

	@Override
	public String getOpenTemplate() {
		return "form";
	}

}
