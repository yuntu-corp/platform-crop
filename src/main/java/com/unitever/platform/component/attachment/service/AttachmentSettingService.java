package com.unitever.platform.component.attachment.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unitever.platform.component.attachment.dao.manual.AttachmentSettingDAO;
import com.unitever.platform.component.attachment.model.AttachmentConfig;
import com.unitever.platform.component.attachment.model.AttachmentSetting;
import com.unitever.platform.core.exception.BusinessException;
import com.unitever.platform.util.FtpUtil;

@Service
public class AttachmentSettingService {

	@Autowired
	private AttachmentSettingDAO attachmentSettingDao;

	@Autowired
	private AttachmentConfigService attachmentConfigService;

	public AttachmentSetting getAttachmentSetting() {
		return attachmentSettingDao.getAttachmentSetting();
	}

	public void save(AttachmentSetting attachmentSetting, AttachmentConfig attachmentConfig) {
		attachmentSettingDao.saveOrUpdate(attachmentSetting);
		attachmentConfigService.save(attachmentConfig);
	}

	public void save(AttachmentSetting attachmentSetting) {
		attachmentSettingDao.saveOrUpdate(attachmentSetting);
	}

	public String testConfig(AttachmentSetting model) {
		String result = "";
		if (model.isFtp()) {
			FTPClient client = null;
			try {
				client = FtpUtil.login(model.getFtpAddress(), model.getFtpPort(), model.getFtpUserName(), model.getFtpPassword());
			} catch (BusinessException e) {
				result = e.getMessage();
			}
			if (client == null) {
				if (StringUtils.isBlank(result)) {
					result = "登录失败，可能用户名或密码错误！";
				}
			}
			if (client != null) {
				boolean flag = FtpUtil.checkReadAndWritePermission(client, StringUtils.isBlank(model.getFtpPath()) ? "/" : model.getFtpPath());
				if (!flag) {
					result = "Ftp服务器权限配置有问题，需要读、写、删除权限！";
				}
			}
			FtpUtil.logout(client);
		}
		return result;
	}
}
