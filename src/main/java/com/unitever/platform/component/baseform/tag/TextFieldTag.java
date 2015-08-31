package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.TextFieldBean;

public class TextFieldTag extends ValidateSupportTag {

	private static final long serialVersionUID = -8004674629380627839L;

	@Override
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new TextFieldBean(req, res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();

		TextFieldBean textField = ((TextFieldBean) component);
		textField.setPlaceholder(placeholder);
		textField.setMaxlength(maxlength);
		textField.setReadonly("true".equalsIgnoreCase(readonly) || "readonly".equalsIgnoreCase(readonly));
		textField.setSize(size);
	}

	protected String placeholder;
	protected String maxlength;
	protected String readonly;
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

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
