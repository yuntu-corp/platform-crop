package com.unitever.platform.component.attachment.storage.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.unitever.platform.component.attachment.storage.FileNotExistException;
import com.unitever.platform.component.attachment.storage.IAttachmentStorage;
import com.unitever.platform.core.exception.BusinessException;

public class LocalStorageImpl implements IAttachmentStorage {

	@Override
	public void storage(File sourceFile, File targetFile) {
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(sourceFile, targetFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BusinessException("attachment.file.save.fail");
		}
	}

	@Override
	public File getFile(File file) {
		if (!file.exists()) {
			throw new FileNotExistException("文件不存在！");
		}
		return file;
	}

	@Override
	public File getFile(String filePath) {
		return new File(filePath);
	}

	@Override
	public void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

}
