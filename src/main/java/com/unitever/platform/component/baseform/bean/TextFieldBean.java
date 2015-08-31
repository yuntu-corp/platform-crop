package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TextFieldBean extends ValidateBean {

	public TextFieldBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	protected String placeholder;
	protected String maxlength;
	protected boolean readonly;
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

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	protected String getTemplate() {
		return "textField.ftl";
	}

}
