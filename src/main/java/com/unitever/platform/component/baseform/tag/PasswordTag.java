package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.PasswordBean;

public class PasswordTag extends ValidateSupportTag {

	private static final long serialVersionUID = 164345045486940138L;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new PasswordBean(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		PasswordBean bean = (PasswordBean) component;
		bean.setPlaceholder(placeholder);
		bean.setMaxlength(maxlength);
		bean.setSize(size);
	}

	protected String placeholder;

	protected String maxlength;

	protected String size;

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getMaxlength() {
		return maxlength;
	}

	public void setMaxlength(String maxlength) {
		this.maxlength = maxlength;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
