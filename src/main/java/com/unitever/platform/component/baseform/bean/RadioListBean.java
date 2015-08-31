package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RadioListBean extends AbstractListBean {

	public RadioListBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		if ("false".equals(tableLayout)) {
			return "radiolist";
		}
		return "radiolist-table";
	}

}
