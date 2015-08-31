package com.unitever.platform.component.attachment.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.component.attachment.dao.manual.AttachmentConfigDAO;
import com.unitever.platform.component.attachment.model.AttachmentConfig;

@Service
public class AttachmentConfigService {

	@Autowired
	private AttachmentConfigDAO attachmentConfigDAO;

	public List<AttachmentConfig> getAttachmentConfigs() {
		return attachmentConfigDAO.getAll();
	}

	public AttachmentConfig getAttachmentConfig(String id) {
		return attachmentConfigDAO.get(id);
	}

	public void save(AttachmentConfig attachmentConfig) {
		attachmentConfigDAO.saveOrUpdate(attachmentConfig);
	}

	public void delete(String[] ids) {
		for (String id : ids) {
			if (StringUtils.isNotBlank(id)) {
				attachmentConfigDAO.delete(id);
			}
		}
	}

	public void delete(String id) {
		attachmentConfigDAO.delete(id);
	}

	public AttachmentConfig getAttachmentConfigDefault() {
		return attachmentConfigDAO.getAttachmentConfigWithCode(AttachmentConstant.ATTACHMENT_DEFAULTCONFIG_CODE);
	}

	public AttachmentConfig getAttachmentConfigWithCode(String code) {
		return attachmentConfigDAO.getAttachmentConfigWithCode(code);
	}

	public AttachmentConfig get(String id) {
		return attachmentConfigDAO.get(id);
	}

	public boolean checkRepeatName(String code, String id) {
		List<AttachmentConfig> attachmentConfigs = attachmentConfigDAO.findAttachmentConfigWithCodeAndNotId(code, id);
		return attachmentConfigs.size() == 0;
	}
}
