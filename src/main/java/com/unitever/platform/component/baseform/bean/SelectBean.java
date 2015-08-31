package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SelectBean extends AbstractListBean {

	public SelectBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return "select";
	}

	protected String headerKey;
	protected String headerValue;
	protected Boolean multiple;
	protected Boolean editEnable;

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public Boolean getMultiple() {
		return multiple;
	}

	public void setMultiple(Boolean multiple) {
		this.multiple = multiple;
	}

	public Boolean getEditEnable() {
		return editEnable;
	}

	public void setEditEnable(Boolean editEnable) {
		this.editEnable = editEnable;
	}

}
