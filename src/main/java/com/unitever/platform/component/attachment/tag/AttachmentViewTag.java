package com.unitever.platform.component.attachment.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.unitever.platform.component.abstractUC.bean.Component;
import com.unitever.platform.component.abstractUC.tag.AbstractUCTag;
import com.unitever.platform.component.attachment.bean.AttachmentView;
import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.component.attachment.model.Attachment;
import com.unitever.platform.component.attachment.service.AttachmentService;
import com.unitever.platform.component.attachment.util.AttachmentUtil;
import com.unitever.platform.component.attachment.vo.AttachmentVO;
import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.util.ReflectUtil;


public class AttachmentViewTag extends AbstractUCTag {


	@Override
	public Component getBean(HttpServletRequest req, HttpServletResponse res) {
		return new AttachmentView(req, res);
	}
	
	protected void populateParams() {
		super.populateParams();
		Object obj = model;
		if (obj == null) {
			obj = pageContext.findAttribute(AttachmentConstant.ATTACHMENTTAG_DEFAULT_MODELNAME);
		}
		Object ownerId = ReflectUtil.getFieldValue(obj, fieldName);
		if (ownerId != null) {
			AttachmentService attachmentService = SpringContextHolder.getBean(AttachmentService.class);
			List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ownerId.toString());
			List<AttachmentVO> attachmentVOs = new ArrayList<AttachmentVO>();
			String zipUrl = "";
			if (showAll) {
				if (attachments.size() > 0) {
					for (Attachment att : attachments) {
						AttachmentVO vo = new AttachmentVO();
						vo.setName(att.getSourceFilename());
						String downloadUrl = AttachmentUtil.getDownloadUrl(att, checkUser, period);
						vo.setDownloadUrl(downloadUrl);
						attachmentVOs.add(vo);
					}
				}
			}
			((AttachmentView) component).setAttachmentVOs(attachmentVOs);
			if (showZipUrl) {
				if (attachments.size() > 0) {
					zipUrl = AttachmentUtil.getZipDownloadUrl(ownerId, checkUser, period);
				}
				((AttachmentView) component).setZipUrl(zipUrl);
			}
		}
	}

	private static final long serialVersionUID = -8186945394938465330L;

	private Object model;

	private String fieldName = AttachmentConstant.ATTACHMENTTAG_DEFAULT_FIELDNAME;

	private boolean checkUser;

	private String period = AttachmentConstant.ATTACHMENTTAG_DEFAULT_PERIOD;

	private Boolean showAll = AttachmentConstant.ATTACHMENTTAG_DEFAULT_SHOWALL;

	private Boolean showZipUrl = true;

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

	public Boolean getShowAll() {
		return showAll;
	}

	public void setShowAll(Boolean showAll) {
		this.showAll = showAll;
	}

	public Boolean getShowZipUrl() {
		return showZipUrl;
	}

	public void setShowZipUrl(Boolean showZipUrl) {
		this.showZipUrl = showZipUrl;
	}

}
