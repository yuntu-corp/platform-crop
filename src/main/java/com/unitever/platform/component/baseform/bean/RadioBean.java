package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RadioBean extends ValidateBean {

	protected String fieldValue;
	protected String label;
	protected boolean labelEnableI18n;

	public RadioBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return "radio";
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isLabelEnableI18n() {
		return labelEnableI18n;
	}

	public void setLabelEnableI18n(boolean labelEnableI18n) {
		this.labelEnableI18n = labelEnableI18n;
	}

}
