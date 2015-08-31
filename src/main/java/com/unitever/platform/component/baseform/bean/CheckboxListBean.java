package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CheckboxListBean extends AbstractListBean {

	public CheckboxListBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		if ("false".equals(tableLayout)) {
			return "checkboxlist";
		}
		return "checkboxlist-table";
	}

}
