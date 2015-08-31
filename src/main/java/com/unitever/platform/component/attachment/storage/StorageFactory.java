package com.unitever.platform.component.attachment.storage;

import com.unitever.platform.component.attachment.model.AttachmentSetting;
import com.unitever.platform.component.attachment.service.AttachmentSettingService;
import com.unitever.platform.component.attachment.storage.impl.FtpStorageImpl;
import com.unitever.platform.component.attachment.storage.impl.LocalStorageImpl;
import com.unitever.platform.core.spring.SpringContextHolder;

public class StorageFactory {

	private static final LocalStorageImpl LOCAL_STORAGE_IMPL = new LocalStorageImpl();

	private static final FtpStorageImpl FTP_STORAGE_IMPL = new FtpStorageImpl();

	public static IAttachmentStorage getStorageService() {
		AttachmentSettingService attachmentSettingService = SpringContextHolder.getBeanOneOfType(AttachmentSettingService.class);
		AttachmentSetting attachmentSetting = attachmentSettingService.getAttachmentSetting();
		return attachmentSetting.isFtp() ? FTP_STORAGE_IMPL : LOCAL_STORAGE_IMPL;
	}

}
