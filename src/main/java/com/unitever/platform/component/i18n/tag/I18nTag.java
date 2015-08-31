package com.unitever.platform.component.i18n.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.unitever.platform.core.i18n.util.I18nUtil;

public class I18nTag extends TagSupport {

	private static final long serialVersionUID = -1327917521729742659L;

	private String code;

	private List<String> args = new ArrayList<String>();

	public void addArgs(String arg) {
		args.add(arg);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public int doStartTag() throws JspException {
		String[] strs = args.toArray(new String[] {});
		args.clear();// tag为单例模式必须清除
		String msg = I18nUtil.getI18nString(code, strs);
		try {
			pageContext.getOut().write(msg);
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException(e);
		}
		return EVAL_BODY_INCLUDE;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}
