package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.IntegerBean;

public class IntegerTag extends TextFieldTag {

	private static final long serialVersionUID = 1046262765888547710L;

	private String minValue;

	private String maxValue;

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new IntegerBean(req, res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		IntegerBean bean = (IntegerBean) component;
		bean.setMinValue(minValue);
		bean.setMaxValue(maxValue);
	}
}
