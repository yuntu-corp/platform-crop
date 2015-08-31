package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;

public abstract class ValidateBean extends AbstractUCBean {

	protected Boolean required;

	protected boolean validate;


	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public ValidateBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

}
