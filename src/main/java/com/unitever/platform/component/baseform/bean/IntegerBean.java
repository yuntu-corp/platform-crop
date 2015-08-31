package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IntegerBean extends TextFieldBean {

	public IntegerBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	protected String maxValue;

	protected String minValue;

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	@Override
	protected String getTemplate() {
		return "integer";
	}
}
