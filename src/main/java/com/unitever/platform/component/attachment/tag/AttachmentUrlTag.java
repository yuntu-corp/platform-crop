package com.unitever.platform.component.attachment.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.component.attachment.model.Attachment;
import com.unitever.platform.component.attachment.service.AttachmentService;
import com.unitever.platform.component.attachment.util.AttachmentUtil;
import com.unitever.platform.component.attachment.vo.AttachmentVO;
import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.util.ReflectUtil;


public class AttachmentUrlTag extends BodyTagSupport {

	private static final long serialVersionUID = -3542837477792137255L;

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_BUFFERED;
	}

	@Override
	public int doEndTag() throws JspException {
		Object obj = model;
		if (obj == null) {
			obj = pageContext.findAttribute(AttachmentConstant.ATTACHMENTTAG_DEFAULT_MODELNAME);
		}
		Object ownerId = ReflectUtil.getFieldValue(obj, fieldName);
		List<AttachmentVO> attachmentVOs = new ArrayList<AttachmentVO>();
		if (ownerId != null) {
			AttachmentService attachmentService = SpringContextHolder.getBean(AttachmentService.class);
			List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ownerId.toString());
			if (attachments.size() > 0) {
				for (Attachment att : attachments) {
					AttachmentVO vo = new AttachmentVO();
					vo.setName(att.getSourceFilename());
					String downloadUrl = AttachmentUtil.getDownloadUrl(att, checkUser, period);
					String picUrl = AttachmentUtil.getPicUrl(att, checkUser, period);
					String audioUrl = AttachmentUtil.getAudioUrl(att, checkUser, period);
					vo.setDownloadUrl(downloadUrl);
					vo.setPicUrl(picUrl);
					vo.setAudioUrl(audioUrl);
					vo.setId(att.getId());
					attachmentVOs.add(vo);
				}
			}
		}
		pageContext.getRequest().setAttribute(var, attachmentVOs);
		return EVAL_PAGE;
	}

	private Object model = null;

	private String fieldName = AttachmentConstant.ATTACHMENTTAG_DEFAULT_FIELDNAME;


	private boolean checkUser = false;

	private String var;

	private String period = AttachmentConstant.ATTACHMENTTAG_DEFAULT_PERIOD;

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

	public boolean isCheckUser() {
		return checkUser;
	}

	public void setCheckUser(boolean checkUser) {
		this.checkUser = checkUser;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

}
