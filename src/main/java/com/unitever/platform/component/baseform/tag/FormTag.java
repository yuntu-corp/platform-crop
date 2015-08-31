package com.unitever.platform.component.baseform.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.baseform.bean.FormBean;

public class FormTag extends AbstractUCTag {

	private static final long serialVersionUID = 8028077765679865870L;

	@Override
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new FormBean(req, res);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		FormBean form = (FormBean) component;
		form.setOnsubmit(onsubmit);
		form.setAction(action);
		form.setTarget(target);
		form.setEnctype(enctype);
		form.setMethod(method);
	}

	protected String onsubmit;

	protected String action;

	protected String target;

	protected String enctype;

	protected String method;

	public String getOnsubmit() {
		return onsubmit;
	}

	public void setOnsubmit(String onsubmit) {
		this.onsubmit = onsubmit;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getEnctype() {
		return enctype;
	}

	public void setEnctype(String enctype) {
		this.enctype = enctype;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
}
