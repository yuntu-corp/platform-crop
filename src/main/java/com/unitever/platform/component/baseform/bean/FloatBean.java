package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FloatBean extends TextFieldBean {

	public FloatBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	protected String maxValue;

	protected String minValue;

	protected String decimalScale;

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

	public String getDecimalScale() {
		return decimalScale;
	}

	public void setDecimalScale(String decimalScale) {
		this.decimalScale = decimalScale;
	}

	@Override
	protected String getTemplate() {
		return "float";
	}
}
