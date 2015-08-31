package com.unitever.platform.component.attachment.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import com.unitever.platform.component.attachment.annotation.AttachmentAnnotation;
import com.unitever.platform.component.attachment.annotation.AttachmentField;
import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.component.attachment.model.Attachment;
import com.unitever.platform.component.attachment.model.AttachmentConfig;
import com.unitever.platform.component.attachment.service.AttachmentConfigService;
import com.unitever.platform.component.attachment.service.AttachmentService;
import com.unitever.platform.component.attachment.storage.StorageFactory;
import com.unitever.platform.core.dao.ModelHelper;
import com.unitever.platform.core.external.UserUtil;
import com.unitever.platform.core.spring.SpringContextHolder;
import com.unitever.platform.core.spring.SpringMVCUtil;
import com.unitever.platform.util.DateUtil;
import com.unitever.platform.util.EncodeUtil;
import com.unitever.platform.util.FileUtil;
import com.unitever.platform.util.ReflectUtil;
import com.unitever.platform.util.UUID;

public class AttachmentUtil {

	/**
	 * 获取文件扩展名
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		if (fileName.indexOf(".") != -1) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return "";
	}

	/**
	 * 获取临时目录
	 * 
	 * @return
	 */
	public static String getTempDir() {
		return System.getProperty("java.io.tmpdir");
	}

	/**
	 * 获取文件应保存的路径
	 * 
	 * @param attachmentConfig
	 * @return
	 */
	public static String getSaveFilePath(AttachmentConfig attachmentConfig) {
		String savePath = "";
		// 绝对地址
		savePath += attachmentConfig.getSaveDir();
		if (!savePath.substring(savePath.length() - 1).equals("/")) {
			savePath += "/";
		}

		// 相对地址
		savePath += getRelativePath(attachmentConfig);

		return savePath.replaceAll("\\\\", "/");
	}

	/**
	 * 获取附件的文件存储路径
	 * 
	 * @param attachment
	 * @param attachmentConfig
	 * @return
	 */
	public static String getAttachmentFilePath(Attachment attachment) {
		AttachmentConfig attachmentConfig = SpringContextHolder.getBeanOneOfType(AttachmentConfigService.class).getAttachmentConfigWithCode(attachment.getConfigCode());
		String filePath = "";
		filePath += attachmentConfig.getSaveDir();
		if (!filePath.substring(filePath.length() - 1).equals("/")) {
			filePath += "/";
		}
		filePath += attachment.getPath() + "/" + attachment.getServerFilename();
		return filePath;
	}

	/**
	 * 获取文件
	 * 
	 * @param attachment
	 * @param attachmentConfig
	 * @return
	 */
	public static File getAttachmentFile(Attachment attachment) {
		String filePath = getAttachmentFilePath(attachment);
		return StorageFactory.getStorageService().getFile(filePath);
	}

	/**
	 * 获取附件存储相对路径
	 * 
	 * @param attachmentConfig
	 * @return
	 */
	public static String getRelativePath(AttachmentConfig attachmentConfig) {
		String relativePath = "";
		// 模块名未定
		String dateStr = DateUtil.getCurrDateString().replaceAll("-", "");
		if (attachmentConfig.getDirLevel() >= AttachmentConstant.ATTACHMENTCONFIG_DIRLAYER_YEAR) {
			relativePath += dateStr.substring(0, 4) + "/";
		}
		if (attachmentConfig.getDirLevel() >= AttachmentConstant.ATTACHMENTCONFIG_DIRLAYER_MONTH) {
			relativePath += dateStr.substring(4, 6) + "/";
		}
		if (attachmentConfig.getDirLevel() >= AttachmentConstant.ATTACHMENTCONFIG_DIRLAYER_DAY) {
			relativePath += dateStr.substring(6, 8) + "/";
		}
		return relativePath;
	}

	/**
	 * 获取下载地址
	 * 
	 * @param attachment
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static String getDownloadUrl(Attachment attachment, boolean checkUser, String period) {
		String downloadUrl = "";
		HttpServletRequest request = SpringMVCUtil.getRequest();
		downloadUrl += request.getContextPath() + "/";
		downloadUrl += AttachmentConstant.ATTACHMENT_DOWNLOAD_RELATIVE_URL;
		downloadUrl += "?checkUser=" + checkUser;
		downloadUrl += "&period=" + period;
		downloadUrl += "&downloadToken=" + getAttachmentToken(attachment.getId(), period, checkUser);
		return downloadUrl;
	}

	/**
	 * 获取图片显示地址
	 * 
	 * @param attachment
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static String getPicUrl(Attachment attachment, boolean checkUser, String period) {
		String picUrl = "";
		HttpServletRequest request = SpringMVCUtil.getRequest();
		picUrl += request.getContextPath() + "/";
		picUrl += AttachmentConstant.ATTACHMENT_PIC_RELATIVE_URL;
		picUrl += "?checkUser=" + checkUser;
		picUrl += "&period=" + period;
		picUrl += "&downloadToken=" + getAttachmentToken(attachment.getId(), period, checkUser);
		return picUrl;
	}

	/**
	 * 获取图片显示地址，不包含应用名
	 * 
	 * @param obj
	 * @return
	 */
	public static List<String> getPicUrlWithoutContextPath(Object obj) {
		return getPicUrlWithoutContextPath(obj, true, AttachmentConstant.ATTACHMENTTAG_DEFAULT_PERIOD);
	}

	/**
	 * 获取图片显示地址，不包含应用名
	 * 
	 * @param obj
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static List<String> getPicUrlWithoutContextPath(Object obj, boolean checkUser, String period) {
		AttachmentService attachmentService = SpringContextHolder.getApplicationContext().getBean(AttachmentService.class);
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ReflectUtil.getSimpleProperty(obj, "id"));
		List<String> result = new ArrayList<String>();
		for (Attachment att : attachments) {
			result.add(getPictureShowUrlWithoutContextPath(att, checkUser, period));
		}
		return result;
	}

	/**
	 * 获取附件下载地址，不包含应用名
	 * 
	 * @param obj
	 * @param fieldName
	 * @param checkUser
	 * @param period
	 * @return
	 */
	public static List<String> getDownloadUrlWithoutContextPath(Object obj, String fieldName, boolean checkUser, String period) {
		AttachmentService attachmentService = SpringContextHolder.getApplicationContext().getBean(AttachmentService.class);
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ReflectUtil.getSimpleProperty(obj, fieldName));
		List<String> result = new ArrayList<String>();
		for (Attachment att : attachments) {
			result.add(getDownloadUrlWithoutContextPath(att, checkUser, period));
		}
		return result;
	}

	/**
	 * 获取附件下载地址，不包含应用名
	 * 
	 * @param obj
	 * @param configCode
	 * @param fieldName
	 * @return
	 */
	public static List<String> getDownloadUrlWithoutContextPath(Object obj, String fieldName) {
		return getDownloadUrlWithoutContextPath(obj, fieldName, true, AttachmentConstant.ATTACHMENTTAG_DEFAULT_PERIOD);
	}

	/**
	 * 获取视频播放地址
	 * 
	 * @param attachment
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static String getAudioUrl(Attachment attachment, boolean checkUser, String period) {
		String audioUrl = "";
		HttpServletRequest request = SpringMVCUtil.getRequest();
		audioUrl += request.getContextPath() + "/";
		audioUrl += AttachmentConstant.ATTACHMENT_AUDIO_RELATIVE_URL;
		audioUrl += "?checkUser=" + checkUser;
		audioUrl += "&period=" + period;
		audioUrl += "&downloadToken=" + getAttachmentToken(attachment.getId(), period, checkUser);
		return audioUrl;
	}

	/**
	 * 获取附件的token
	 * 
	 * @param id
	 * @param period
	 * @param checkUser
	 * @return
	 */
	public static String getAttachmentToken(String id, String period, boolean checkUser) {
		String downloadToken = "";
		String pattern = "";
		if (AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_YEAR.equals(period)) {
			pattern = "yyyy";
		}
		if (AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_MONTH.equals(period)) {
			pattern = "yyyyMM";
		}
		if (AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_DAY.equals(period)) {
			pattern = "yyyyMMdd";
		}
		if (AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_HOUR.equals(period)) {
			pattern = "yyyyMMddmm";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String dateStr = "";
		if (!AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_FOREVER.equals(period)) {
			dateStr = sdf.format(new Date());
		}
		String encodeStr = "";
		if (checkUser) {
			encodeStr += UserUtil.getCurrUserID();
		}
		encodeStr += AttachmentConstant.ATTACHMENT_TOKEN_KEY + dateStr;
		downloadToken = id + EncodeUtil.encodeByMd5(encodeStr);
		return downloadToken;
	}

	/**
	 * 获取打包下载地址
	 * 
	 * @param ownerId
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static String getZipDownloadUrl(Object ownerId, boolean checkUser, String period) {
		String downloadUrl = "";
		HttpServletRequest request = SpringMVCUtil.getRequest();
		downloadUrl += request.getContextPath() + "/";
		downloadUrl += AttachmentConstant.ATTACHMENT_DOWNLOAD_RELATIVE_URL;
		downloadUrl += "?checkUser=" + checkUser;
		downloadUrl += "&period=" + period;
		downloadUrl += "&downloadToken=" + getAttachmentToken(ownerId.toString(), period, checkUser);
		downloadUrl += "&isZip=true";
		return downloadUrl;
	}

	/**
	 * 获取多个owner的打包下载地址
	 * 
	 * @param ownerAndConfigCodeMap
	 * @return
	 */
	public static String getZipDownloadUrl(Map<String, String> ownerAndConfigCodeMap) {
		String downloadUrl = "";
		HttpServletRequest request = SpringMVCUtil.getRequest();
		downloadUrl += request.getContextPath() + "/";
		downloadUrl += AttachmentConstant.ATTACHMENT_DOWNLOAD_MULITOWNER_RELATIVE_URL;
		downloadUrl += "?param=" + getParamStringFromMap(ownerAndConfigCodeMap);
		return downloadUrl;
	}

	public static String getParamStringFromMap(Map<String, String> map) {
		String result = "";
		for (String key : map.keySet()) {
			result += key + ":" + map.get(key) + ",";
		}
		return result;
	}

	public static Map<String, String> parseParamStringToMap(String strs) {
		Map<String, String> result = new HashMap<String, String>();
		for (String str : strs.split(",")) {
			result.put(str.split(":")[0], str.split(":")[1]);
		}
		return result;
	}

	/**
	 * 生成zip文件
	 * 
	 * @param files
	 * @param fileNames
	 * @param zipFile
	 */
	public static void getZip(List<File> files, List<String> fileNames, File zipFile) {
		List<String> existFileNames = new ArrayList<String>();
		byte[] buffer = new byte[AttachmentConstant.ATTACHMENT_ZIP_BUFFER_SIZE];
		try {
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFile));
			out.setEncoding("gbk");
			int i = 0;
			for (File f : files) {
				FileInputStream fis = new FileInputStream(f);
				String fileName = fileNames.get(i);
				String origFileName = fileName;
				while (true) {// 防止重名
					if (!existFileNames.contains(fileName)) {
						break;
					}
					String extend = FileUtil.getFileExtention(fileName);
					fileName = origFileName.substring(0, origFileName.lastIndexOf(extend) - 1) + UUID.getUUID() + (StringUtils.isBlank(extend) ? "" : ".") + extend;
				}
				existFileNames.add(fileName);
				out.putNextEntry(new ZipEntry(fileName));
				// out.putNextEntry(new ZipEntry(new
				// String(fileNames[i].getBytes("utf-8"),"gbk")));
				int len;
				while ((len = fis.read(buffer)) != -1) {
					out.write(buffer, 0, len);
				}
				out.closeEntry();
				fis.close();
				i++;
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取下载地址，不包含应用名
	 * 
	 * @param attachment
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static String getDownloadUrlWithoutContextPath(Attachment attachment, boolean checkUser, String period) {
		String downloadUrl = "";
		downloadUrl += AttachmentConstant.ATTACHMENT_DOWNLOAD_RELATIVE_URL;
		downloadUrl += "?checkUser=" + checkUser;
		downloadUrl += "&period=" + period;
		downloadUrl += "&downloadToken=" + getAttachmentToken(attachment.getId(), period, checkUser);
		return downloadUrl;
	}

	/**
	 * 获取图片显示地址，不包含应用名
	 * 
	 * @param attachment
	 * @param checkUser
	 * @param period
	 * @param configCode
	 * @return
	 */
	public static String getPictureShowUrlWithoutContextPath(Attachment attachment, boolean checkUser, String period) {
		String showUrl = "";
		showUrl += AttachmentConstant.ATTACHMENT_PIC_RELATIVE_URL;
		showUrl += "?checkUser=" + checkUser;
		showUrl += "&period=" + period;
		showUrl += "&downloadToken=" + getAttachmentToken(attachment.getId(), period, checkUser);
		return showUrl;
	}

	/**
	 * 获取第一个显示地址，调用者需保证业务只能上传一个
	 * 
	 * @param obj
	 * @param configCode
	 * @param fieldName
	 * @return
	 */
	public static String getFirstShowUrlWithoutContextPath(Object obj, String fieldName) {
		List<String> result = getShowUrlWithoutContextPath(obj, false, AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_FOREVER, fieldName);
		return result.size() > 0 ? result.get(0) : null;
	}

	public static List<String> getShowUrlWithoutContextPath(Object obj, String fieldName) {
		return getShowUrlWithoutContextPath(obj, false, AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_FOREVER, fieldName);
	}

	public static List<String> getShowUrlWithoutContextPath(Object obj) {
		return getShowUrlWithoutContextPath(obj, false, AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_FOREVER, AttachmentConstant.ATTACHMENTTAG_DEFAULT_FIELDNAME);
	}

	public static List<String> getShowUrlWithoutContextPath(Object obj, boolean checkUser, String period, String fieldName) {
		AttachmentService attachmentService = SpringContextHolder.getApplicationContext().getBean(AttachmentService.class);
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ReflectUtil.getSimpleProperty(obj, fieldName));
		List<String> result = new ArrayList<String>();
		for (Attachment att : attachments) {
			result.add(getShowUrlWithoutContextPath(att, checkUser, period));
		}
		return result;
	}

	public static String getShowUrlWithoutContextPath(Attachment att, boolean checkUser, String period) {
		String showUrl = "";
		showUrl += AttachmentConstant.ATTACHMENT_SHOW_RELATIVE_URL;
		showUrl += "?checkUser=" + checkUser;
		showUrl += "&period=" + period;
		showUrl += "&downloadToken=" + getAttachmentToken(att.getId(), period, checkUser);
		return showUrl;
	}

	/**
	 * 获取第一个显示地址，调用者需保证业务只能有一个
	 * 
	 * @param obj
	 * @param configCode
	 * @return
	 */
	public static String getFirstShowUrlWithoutContextPath(Object obj) {
		return getFirstShowUrlWithoutContextPath(obj, AttachmentConstant.ATTACHMENTTAG_DEFAULT_FIELDNAME);
	}

	/**
	 * 根据附件id获取文件
	 * 
	 * @param id
	 * @param configCode
	 * @return
	 */
	public static File getFileWithAttachmentId(String id) {
		AttachmentService attachmentService = SpringContextHolder.getBeanOneOfType(AttachmentService.class);
		return getAttachmentFile(attachmentService.get(id));
	}

	/**绑定附件
	 * @param obj 绑定附件的对象
	 * @param ids 附件ID集合
	 * @param delIds 删除的附件ID集合
	 * @param configCode 附件编码
	 */
	public static void bindAttachment(Object obj, String ids, String delIds, String configCode) {
		AttachmentService attachmentService = SpringContextHolder.getBeanOneOfType(AttachmentService.class);
		attachmentService.bindAttachment(obj, ids, delIds, configCode);
	}
	
	/**绑定附件
	 * @param obj 绑定附件的对象
	 * @param ids 附件ID集合
	 * @param delIds 删除的附件ID集合
	 * @param configCode 附件编码
	 * @param fieldName 绑定到对象的属性名称
	 */
	public static void bindAttachment(Object obj, String ids, String delIds, String configCode, String fieldName){
		AttachmentService attachmentService = SpringContextHolder.getBeanOneOfType(AttachmentService.class);
		attachmentService.bindAttachment(obj, ids, delIds, configCode, fieldName);
	}
	/**绑定附件
	 * @param obj 绑定附件的对象
	 */
	public static void bindAttachment(Object obj){
		if (obj == null) {
			return;
		}
		AttachmentService attachmentService = SpringContextHolder.getBeanOneOfType(AttachmentService.class);
		AttachmentConfigService attachmentConfigService = SpringContextHolder.getBeanOneOfType(AttachmentConfigService.class);
		HttpServletRequest request = SpringMVCUtil.getRequest();
		if (request == null) {// 在计划任务里执行会为空
			return;
		}
		@SuppressWarnings("unchecked")
		Map<String, String[]> map = request.getParameterMap();
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String str = it.next();
			if (StringUtils.isBlank(str) || !str.startsWith("attachmentList[")) {
				continue;
			}
			String val = request.getParameter(str);
			String modelName = val.split(",")[0];
			String fieldName = val.split(",")[1];
			String attIds = request.getParameter(val);
			String delIds = request.getParameter(val + "DelIds");
			String configId = request.getParameter(val + "ConfigId");
			if (attIds == null) {
				attIds = "";
			}
			if (delIds == null) {
				delIds = "";
			}
			if(attIds.equals("")&& delIds.equals("")){
				continue;
			}
			if (obj.getClass().getName().equals(modelName)) {
				AttachmentConfig attachmentConfig = attachmentConfigService.get(configId);
				attachmentService.bindAttachment(obj, attIds, delIds, fieldName,attachmentConfig);
			}
		}
		
	}
	
	/**解除绑定附件
	 * @param obj 解除绑定附件的对象
	 */
	public static void unbindAttachment(Object obj){
		if (obj == null) {
			return;
		}
		AttachmentAnnotation attachmentAnnotation = obj.getClass().getAnnotation(AttachmentAnnotation.class);
		if (attachmentAnnotation != null) {
			unbindAttachment(ModelHelper.getIdValue(obj));
			for (Field f : obj.getClass().getDeclaredFields()) {
				if (!f.getName().equals("id")) {
					AttachmentField attachmentField = f.getAnnotation(AttachmentField.class);
					if (attachmentField != null) {
						unbindAttachment(ReflectUtil.getFieldValue(obj, f.getName()));
					}
				}
			}
		}
	}
	
	/**解除绑定附件
	 * @param obj 解除绑定附件的对象
	 */
	public static void unbindAttachment(String ownerId){
		if (ownerId == null) {
			return;
		}
		AttachmentService attachmentService = SpringContextHolder.getBeanOneOfType(AttachmentService.class);
		List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(ownerId);
		if(attachments==null){
			return;
		}
		for (Attachment att : attachments) {
			attachmentService.logicDelete(att.getId());
		}
	}
	
	/**
	 * 自动绑定附件，特殊模块调用
	 * 
	 * @param obj
	 */
	public static void autoBindAttachment(Object obj) {
		AttachmentService attachmentService = SpringContextHolder.getBeanOneOfType(AttachmentService.class);
		AttachmentConfigService attachmentConfigService = SpringContextHolder.getBeanOneOfType(AttachmentConfigService.class);
		HttpServletRequest request = SpringMVCUtil.getRequest();
		if (request == null) {// 在计划任务里执行会为空
			return;
		}
		@SuppressWarnings("unchecked")
		Map<String, String[]> map = request.getParameterMap();
		Set<String> keySet = map.keySet();
		Iterator<String> it = keySet.iterator();
		while (it.hasNext()) {
			String str = it.next();
			if (StringUtils.isNotBlank(str) && str.startsWith("attachmentList[")) {
				String val = request.getParameter(str);
				String modelName = val.split(",")[0];
				String fieldName = val.split(",")[1];
				String attIds = request.getParameter(val);
				String delIds = request.getParameter(val + "DelIds");
				String configId = request.getParameter(val + "ConfigId");
				AttachmentConfig attachmentConfig = attachmentConfigService.get(configId);
				if (attIds == null) {
					attIds = "";
				}
				if (delIds == null) {
					delIds = "";
				}
				if (obj.getClass().getName().equals(modelName)) {
					// 删除旧的
					if (StringUtils.isNotBlank(delIds)) {
						for (String delId : delIds.split(",")) {
							if (StringUtils.isBlank(delId)) {
								continue;
							}
							attachmentService.logicDelete(delId);
						}
					}
					// 新增的
					// 需优化
					int i = attachmentService.getAttachmentsWithOwnerId(ReflectUtil.getFieldValue(obj, fieldName).toString()).size();
					for (String attId : attIds.split(",")) {
						if (StringUtils.isBlank(attId)) {
							continue;
						}
						Attachment attachment = attachmentService.get(attId);
						attachmentService.saveAttachment(attachment, i, attachmentConfig, obj, fieldName);
						i++;
					}
				}
			}
		}
	}
}