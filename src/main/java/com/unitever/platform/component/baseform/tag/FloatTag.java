package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.FloatBean;

public class FloatTag extends TextFieldTag {

	private static final long serialVersionUID = -4421818718246058539L;

	private String maxValue;

	private String minValue;

	private String decimalScale;

	public FloatTag() {

	}

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
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new FloatBean(req, res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		FloatBean bean = (FloatBean) component;
		bean.setMinValue(minValue);
		bean.setMaxValue(maxValue);
		bean.setDecimalScale(decimalScale);
	}
}
