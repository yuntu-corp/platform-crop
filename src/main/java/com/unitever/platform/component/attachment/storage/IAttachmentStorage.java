package com.unitever.platform.component.attachment.storage;

import java.io.File;

public interface IAttachmentStorage {

	/**
	 * 保存文件
	 * 
	 * @param sourceFile
	 *            本地已存在的文件
	 * @param targetFile
	 *            要保存到的文件，可以是远程，远程会去掉盘符（如：d:）
	 */
	void storage(File sourceFile, File targetFile);

	/**
	 * 获取文件的本地路径，如果在远程服务器上需下载。
	 * 
	 * @param file
	 * @return
	 */
	File getFile(File file);

	/**
	 * 获取文件的本地路径，如果在远程服务器上需下载。
	 * 
	 * @param filePath
	 * @return
	 */
	File getFile(String filePath);

	/**
	 * 删除文件
	 * 
	 * @param filePath
	 */
	void deleteFile(String filePath);

}
