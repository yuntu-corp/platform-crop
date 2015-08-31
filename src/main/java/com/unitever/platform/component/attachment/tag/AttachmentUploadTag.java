package com.unitever.platform.component.attachment.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.attachment.bean.AttachmentUpload;
import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.util.ReflectUtil;

public class AttachmentUploadTag extends AbstractUCTag {

	private static final long serialVersionUID = -8186945394938465330L;

	protected Object model = null;

	protected String fieldName = AttachmentConstant.ATTACHMENTTAG_DEFAULT_FIELDNAME;

	protected String configCode = AttachmentConstant.ATTACHMENT_DEFAULTCONFIG_CODE;

	protected String finishCallback;

	protected String deleteCallback;

	protected boolean required = false;
	
	@Override
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new AttachmentUpload(req, res);
	}

	protected void populateParams() {
		super.populateParams();
		String className = "";
		String ownerId="";
		Object obj = model;
		if (obj == null) {
			obj = pageContext.findAttribute(AttachmentConstant.ATTACHMENTTAG_DEFAULT_MODELNAME);
		}
		className = obj.getClass().getName();
		Object ownerIdObj = ReflectUtil.getFieldValue(obj, fieldName);
		if (ownerIdObj != null) {
			ownerId=ownerIdObj.toString();
		}
		((AttachmentUpload) component).setConfigCode(configCode);
		((AttachmentUpload) component).setClassName(className);
		((AttachmentUpload) component).setFieldName(fieldName);
		((AttachmentUpload) component).setOwnerId(ownerId);
		((AttachmentUpload) component).setFinishCallback(StringUtils.isBlank(finishCallback) ? "" : finishCallback);
		((AttachmentUpload) component).setDeleteCallback(StringUtils.isBlank(deleteCallback) ? "" : deleteCallback);
		((AttachmentUpload) component).setRequired(required);

	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getFinishCallback() {
		return finishCallback;
	}

	public void setFinishCallback(String finishCallback) {
		this.finishCallback = finishCallback;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getDeleteCallback() {
		return deleteCallback;
	}

	public void setDeleteCallback(String deleteCallback) {
		this.deleteCallback = deleteCallback;
	}
}
