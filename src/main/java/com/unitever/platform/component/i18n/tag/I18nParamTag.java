package com.unitever.platform.component.i18n.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class I18nParamTag extends TagSupport {

	private static final long serialVersionUID = -4018576238518383200L;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int doStartTag() throws JspException {
		I18nTag parent = (I18nTag) getParent();
		parent.addArgs(value);
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
