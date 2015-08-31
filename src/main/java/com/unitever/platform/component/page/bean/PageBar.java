package com.unitever.platform.component.page.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;


public class PageBar extends AbstractUCBean {
	final public static String TEMPLATE = "pagebar";
	private String pageInfo;
	private String formId;
	private String containerId;
	private String type;
	private boolean changePageSize;
	private String changePageSizeNumber;

	public PageBar(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}

	public String getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(String pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChangePageSize() {
		return String.valueOf(changePageSize);
	}

	public void setChangePageSize(boolean changePageSize) {
		this.changePageSize = changePageSize;
	}

	public String getChangePageSizeNumber() {
		return changePageSizeNumber;
	}

	public void setChangePageSizeNumber(String changePageSizeNumber) {
		this.changePageSizeNumber = changePageSizeNumber;
	}
	
	
}
