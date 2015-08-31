package com.unitever.platform.component.baseform.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;

public class HiddenBean extends AbstractUCBean {

	public HiddenBean(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return "hidden";
	}

}
