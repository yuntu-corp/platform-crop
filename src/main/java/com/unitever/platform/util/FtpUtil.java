package com.unitever.platform.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.unitever.platform.core.exception.BusinessException;

public class FtpUtil {

	/**
	 * 登录ftp
	 * 
	 * @param ftpAddress
	 * @param portStr
	 * @param userName
	 * @param password
	 * @return
	 */
	public static FTPClient login(String ftpAddress, String portStr, String userName, String password) {
		FTPClient client = null;
		try {
			client = new FTPClient();
			Integer port = 21;
			if (StringUtils.isNotBlank(portStr)) {
				port = Integer.valueOf(portStr);
			}
			client.setConnectTimeout(5 * 1000);
			client.connect(ftpAddress, port);
			int reply = client.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				client.disconnect();
				return null;
			}
			boolean login = client.login(StringUtils.isNotBlank(userName) ? userName.trim() : "anonymous", StringUtils.isNotBlank(password) ? password.trim() : "");
			if (!login) {
				client.disconnect();
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage(), e);
		}
		return client;
	}

	/**
	 * 登出ftp
	 * 
	 * @param client
	 */
	public static void logout(FTPClient client) {
		if (client == null) {
			return;
		}
		try {
			client.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检测是否有读写权限
	 * 
	 * @param client
	 * @param workingDirectory
	 * @return
	 */
	public static boolean checkReadAndWritePermission(FTPClient client, String workingDirectory) {
		boolean result = true;
		try {
			workingDirectory = reCodeString(workingDirectory.trim());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = false;
		}
		if (result) {// 测试进入指定目录
			try {
				checkAndCreatWorkingDirectory(client, workingDirectory);
			} catch (IOException e) {
				e.printStackTrace();
				result = false;
			}
		}
		String ftpTempFile = "/testFile" + UUID.getUUID() + ".txt";
		if (result) {// 测试创建文件
			try {
				client.enterLocalPassiveMode();
				client.setFileType(FTPClient.BINARY_FILE_TYPE);
				client.changeWorkingDirectory(workingDirectory);
				client.setBufferSize(1024);
				client.setControlEncoding("gbk");
				result = client.storeFile(ftpTempFile, new ByteArrayInputStream("this is a test file.".getBytes()));
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		}
		if (result) {// 测试下载文件
			OutputStream local = null;
			File tempFile = new File(FileUtil.getTempDir() + "/" + UUID.getUUID());
			try {
				client.enterLocalPassiveMode();
				client.setFileType(FTPClient.BINARY_FILE_TYPE);
				client.changeWorkingDirectory(workingDirectory);
				client.setBufferSize(1024);
				local = new FileOutputStream(tempFile);
				result = client.retrieveFile(new String(ftpTempFile.getBytes("gbk"), "iso-8859-1"), local);
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			} finally {
				if (local != null) {
					try {
						local.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (tempFile.exists()) {
					tempFile.delete();
				}
			}
		}
		if (result) {// 测试删除文件
			try {
				client.enterLocalPassiveMode();
				client.setFileType(FTPClient.BINARY_FILE_TYPE);
				client.changeWorkingDirectory(workingDirectory);
				result = client.deleteFile(new String(ftpTempFile.getBytes("gbk"), "iso-8859-1"));
			} catch (Exception e) {
				e.printStackTrace();
				result = false;
			}
		}

		return result;
	}

	/**
	 * 上传文件
	 * 
	 * @param client
	 * @param srcFile
	 * @param workingDirectory
	 * @param fileName
	 */
	public static void uploadFile(FTPClient client, File srcFile, String workingDirectory, String fileName) {
		InputStream in = null;
		boolean flag = true;
		Exception e = null;
		try {
			workingDirectory = reCodeString(workingDirectory);
			fileName = reCodeString(fileName);
			client.enterLocalPassiveMode();
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			checkAndCreatWorkingDirectory(client, workingDirectory);
			client.changeWorkingDirectory(workingDirectory);
			client.setBufferSize(1024);
			client.setControlEncoding("gbk");
			in = new FileInputStream(srcFile);
			flag = client.storeFile(fileName, in);
		} catch (Exception e2) {
			e2.printStackTrace();
			e = e2;
			flag = false;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		}
		if (!flag) {
			throw new BusinessException("ftp上传失败，请检查配置。" + (e != null ? "原因：" + e.getMessage() : ""));
		}
	}

	private static String reCodeString(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("gbk"), "iso-8859-1");
	}

	/**
	 * 下载文件
	 * 
	 * @param client
	 * @param workingDirectory
	 * @param fileName
	 * @param targetFile
	 */
	public static void download(FTPClient client, String workingDirectory, String fileName, File targetFile) {
		OutputStream local = null;
		boolean flag = true;
		Exception e = null;
		try {
			workingDirectory = reCodeString(workingDirectory);
			fileName = reCodeString(fileName);
			client.enterLocalPassiveMode();
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			checkAndCreatWorkingDirectory(client, workingDirectory);
			client.changeWorkingDirectory(workingDirectory);
			client.setBufferSize(1024);
			local = new FileOutputStream(targetFile);
			flag = client.retrieveFile(fileName, local);
		} catch (Exception e1) {
			e1.printStackTrace();
			e = e1;
			flag = false;
		} finally {
			if (local != null) {
				try {
					local.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
		if (!flag) {
			if (targetFile.exists()) {
				targetFile.delete();
			}
			throw new BusinessException("ftp下载失败，请检查配置。" + (e != null ? "原因：" + e.getMessage() : ""));
		}
	}

	private static void checkAndCreatWorkingDirectory(FTPClient client, String workingDirectory) throws IOException {
		client.enterLocalPassiveMode();
		boolean flag = client.changeWorkingDirectory(workingDirectory);
		if (!flag) {
			client.changeWorkingDirectory("/");
			for (String subPath : workingDirectory.split("/")) {
				if (StringUtils.isNotBlank(subPath)) {
					boolean changeFlag = client.changeWorkingDirectory(subPath);
					if (!changeFlag) {
						boolean makeFlag = client.makeDirectory(subPath);
						if (makeFlag) {
							client.changeWorkingDirectory(subPath);
						} else {
							throw new BusinessException("ftp无法创建目录，请检查服务器配置。");
						}
					}
				}
			}
			flag = client.changeWorkingDirectory(workingDirectory);
			if (!flag) {
				throw new BusinessException("ftp无法切换目录，请检查服务器配置。");
			}
		}
	}

	public static void delete(FTPClient client, String workingDirectory, String fileName) {
		try {
			client.enterLocalPassiveMode();
			client.setFileType(FTPClient.BINARY_FILE_TYPE);
			client.changeWorkingDirectory(workingDirectory);
			client.deleteFile(new String(fileName.getBytes("gbk"), "iso-8859-1"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("ftp文件删除失败，请检查配置。" + (e != null ? "原因：" + e.getMessage() : ""));
		}
	}

}
