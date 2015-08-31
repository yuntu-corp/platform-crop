package com.unitever.platform.component.attachment.storage.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;

import com.unitever.platform.component.attachment.model.AttachmentSetting;
import com.unitever.platform.component.attachment.service.AttachmentSettingService;
import com.unitever.platform.component.attachment.storage.FileNotExistException;
import com.unitever.platform.component.attachment.storage.IAttachmentStorage;
import com.unitever.platform.component.attachment.util.AttachmentClearFtpCache;
import com.unitever.platform.core.exception.BusinessException;
import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.util.FileUtil;
import com.unitever.platform.util.FtpUtil;

public class FtpStorageImpl implements IAttachmentStorage {

	private static final Log log = LogFactory.getLog(FtpStorageImpl.class);

	@Override
	public void storage(File sourceFile, File targetFile) {
		FTPClient ftpClient = getFtpClient();
		String workingDirectory = getWorkingDirectory(targetFile);
		FtpUtil.uploadFile(ftpClient, sourceFile, workingDirectory, targetFile.getName());
		FtpUtil.logout(ftpClient);
	}

	@Override
	public File getFile(File file) {
		File result = new File(FileUtil.getTempDir() + file.getName());
		if (result.exists()) {// 使用缓存的文件
			return result;
		}
		FTPClient ftpClient = getFtpClient();
		String workingDirectory = getWorkingDirectory(file);
		try {
			FtpUtil.download(ftpClient, workingDirectory, file.getName(), result);
		} catch (BusinessException e) {
			throw new FileNotExistException(e.getMessage());
		} finally {
			try {
				if (result.length() > 5 * 1024 * 1024 || result.getParentFile().getFreeSpace() < 3 * 1024 * 1024 * 1024) {// 如果文件大于5m或磁盘空间小于3g，需要删除缓存文件，每次都从ftp上取
					@SuppressWarnings("unchecked")
					List<String> delFileList = (List<String>) SpringMVCUtil.getRequest().getAttribute(AttachmentClearFtpCache.DEL_FTP_FILE_ATTR_NAME);
					if (delFileList == null) {
						delFileList = new ArrayList<String>();
					}
					delFileList.add(result.getAbsolutePath());
					SpringMVCUtil.getRequest().setAttribute(AttachmentClearFtpCache.DEL_FTP_FILE_ATTR_NAME, delFileList);
				}
			} catch (Exception e) {// 删除缓存失败，无需处理异常，只记录异常
				e.printStackTrace();
				log.error("尝试删除ftp缓存失败：", e);
			}
		}
		FtpUtil.logout(ftpClient);
		return result;
	}

	@Override
	public File getFile(String filePath) {
		return getFile(new File(filePath));
	}

	@Override
	public void deleteFile(String filePath) {
		FTPClient ftpClient = getFtpClient();
		File file = new File(filePath);
		String workingDirectory = getWorkingDirectory(file);
		FtpUtil.delete(ftpClient, workingDirectory, file.getName());
		FtpUtil.logout(ftpClient);
	}

	private FTPClient getFtpClient() {
		AttachmentSettingService attachmentSettingService = SpringContextHolder.getBeanOneOfType(AttachmentSettingService.class);
		AttachmentSetting attachmentSetting = attachmentSettingService.getAttachmentSetting();
		FTPClient ftpClient = FtpUtil.login(attachmentSetting.getFtpAddress(), attachmentSetting.getFtpPort(), attachmentSetting.getFtpUserName(), attachmentSetting.getFtpPassword());
		return ftpClient;
	}

	private String getWorkingDirectory(File file) {
		AttachmentSettingService attachmentSettingService = SpringContextHolder.getBeanOneOfType(AttachmentSettingService.class);
		AttachmentSetting attachmentSetting = attachmentSettingService.getAttachmentSetting();
		String ftpPath = attachmentSetting.getFtpPath();
		if (ftpPath == null) {
			ftpPath = "";
		}
		String path = file.getParentFile().getAbsolutePath();
		if (path.contains(":")) {
			path = path.substring(path.indexOf(":") + 1);
		}
		path = path.replaceAll("\\\\", "/");
		String workingDirectory = "/" + ftpPath.replaceAll("\\\\", "/") + "/" + path;
		workingDirectory = workingDirectory.replaceAll("//", "/").replaceAll("//", "/");
		return workingDirectory;
	}
}
