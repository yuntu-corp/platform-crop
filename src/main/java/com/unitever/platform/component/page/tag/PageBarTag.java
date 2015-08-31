package com.unitever.platform.component.page.tag;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.page.bean.PageBar;

public class PageBarTag extends AbstractUCTag {
	private static final long serialVersionUID = 8879861012655180717L;
	private String pageInfo;
	private String formId;
	private String containerId;
	private String type = "normal";
	private boolean changePageSize = false;
	private String changePageSizeNumber;

	@Override
	public Component getBean(HttpServletRequest request, HttpServletResponse response) {
		return new PageBar(request, response);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		PageBar component_ = (PageBar) component;
		if(StringUtils.isBlank(id)){
			component_.setId(UUID.randomUUID().toString());
		}else{
			component_.setId(id);
		}
		component_.setPageInfo(pageInfo);
		component_.setFormId(formId);
		component_.setContainerId(containerId);
		component_.setType(type);
		component_.setChangePageSize(changePageSize);
		component_.setChangePageSizeNumber(changePageSizeNumber);
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

	public boolean isChangePageSize() {
		return changePageSize;
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
