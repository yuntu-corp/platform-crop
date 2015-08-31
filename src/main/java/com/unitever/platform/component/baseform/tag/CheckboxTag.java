package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.CheckboxBean;

public class CheckboxTag extends ValidateSupportTag {

	private static final long serialVersionUID = -7245853998152510690L;

	protected String fieldValue;
	protected String label;
	protected boolean labelEnableI18n;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new CheckboxBean(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		CheckboxBean bean = (CheckboxBean) component;
		bean.setFieldValue(fieldValue);
		bean.setLabel(label);
		bean.setLabelEnableI18n(labelEnableI18n);
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
