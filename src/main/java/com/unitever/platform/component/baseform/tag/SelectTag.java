package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.baseform.bean.SelectBean;

public class SelectTag extends AbstractListTag {

	private static final long serialVersionUID = -3900238024571887581L;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new SelectBean(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		SelectBean bean = (SelectBean) component;
		bean.setHeaderKey(headerKey);
		bean.setHeaderValue(headerValue);
		if (StringUtils.isNotBlank(multiple)) {
			bean.setMultiple(Boolean.valueOf(multiple));
		}
		bean.setEditEnable(editEnable);
	}

	protected String headerKey;
	protected String headerValue;
	protected String multiple;
	protected Boolean editEnable;

	public String getHeaderKey() {
		return headerKey;
	}

	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public Boolean getEditEnable() {
		return editEnable;
	}

	public void setEditEnable(Boolean editEnable) {
		this.editEnable = editEnable;
	}

}
