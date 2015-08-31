package com.unitever.platform.component.attachment.bean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.AbstractUCBean;


public class AttachmentUpload extends AbstractUCBean {

	final public static String TEMPLATE = "attachmentUpload";

	protected String configCode;
	
	protected String className;

	protected String fieldName;

	protected String ownerId;
	
	protected String finishCallback;
	
	protected String deleteCallback;
	
	protected Boolean required;

	public AttachmentUpload(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
	}

	@Override
	protected String getTemplate() {
		return TEMPLATE;
	}
	
	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFinishCallback() {
		return finishCallback;
	}

	public void setFinishCallback(String finishCallback) {
		this.finishCallback = finishCallback;
	}

	public String getRequired() {
		return String.valueOf(required);
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getDeleteCallback() {
		return deleteCallback;
	}

	public void setDeleteCallback(String deleteCallback) {
		this.deleteCallback = deleteCallback;
	}

}
