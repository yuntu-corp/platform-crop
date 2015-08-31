package com.unitever.platform.component.baseform.tag;

import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.baseform.bean.ValidateBean;

public abstract class ValidateSupportTag extends AbstractUCTag {

	private static final long serialVersionUID = -253316087103701924L;
	protected String required;

	public ValidateSupportTag() {
		super();
	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	protected void populateParams() {
		super.populateParams();
		ValidateBean validatebean = ((ValidateBean) component);
		validatebean.setRequired(Boolean.valueOf(required));
	}
}
