package com.unitever.platform.component.attachment.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.unitever.platform.component.attachment.constant.AttachmentConstant;
import com.unitever.platform.component.attachment.model.Attachment;
import com.unitever.platform.component.attachment.model.AttachmentConfig;
import com.unitever.platform.component.attachment.service.AttachmentConfigService;
import com.unitever.platform.component.attachment.service.AttachmentService;
import com.unitever.platform.component.attachment.storage.StorageFactory;
import com.unitever.platform.component.attachment.util.AttachmentUtil;
import com.unitever.platform.component.attachment.vo.AttachmentVO;
import com.unitever.platform.core.dataSource.helper.DataSourceHolder;
import com.unitever.platform.core.web.controller.SpringController;
import com.unitever.platform.util.FileUtil;
import com.unitever.platform.util.JsonUtil;
import com.unitever.platform.util.UUID;

@Controller
@RequestMapping("/sys/attachment")
public class AttachmentController extends SpringController {

	@Autowired
	private AttachmentConfigService attachmentConfigService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 保存flex提交的文件
	 * 
	 * @return
	 */
	@RequestMapping("/saveUploadFile")
	@ResponseBody
	public String saveUploadFile(@RequestParam(value = "filedata") MultipartFile file) {
		try {
			File dir = new File(AttachmentUtil.getTempDir());
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String newFileName = UUID.getUUID() + "." + AttachmentUtil.getFileExtension(file.getOriginalFilename());
			File newFile = new File(dir.getAbsolutePath() + "/" + newFileName);
			file.transferTo(newFile);
			Attachment attachment = new Attachment();
			attachment.setSourceFilename(file.getOriginalFilename());
			attachment.setStatus(AttachmentConstant.ATTACHMENT_STATUS_TEMP);
			attachment.setServerFilename(newFileName);
			attachment.setFileSize("" + file.getSize());
			attachmentService.save(attachment);
			this.print(attachment.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/ajaxDeleteUploadFile")
	@ResponseBody
	public void ajaxDeleteUploadFile(String attachmentId) {
		if (StringUtils.isNotBlank(attachmentId)) {
			attachmentService.delete(attachmentId);
			this.print("true");
		}
	}

	@RequestMapping("/saveUEditorFile")
	@ResponseBody
	public void saveUEditorFile(@RequestParam(value = "upfile") MultipartFile file) {
		String url = "";
		String state = "SUCCESS";
		try {
			File tempFile = new File(FileUtil.getTempDir() + UUID.getUUID());
			file.transferTo(tempFile);
			AttachmentConfig attachmentConfig = attachmentConfigService.getAttachmentConfigWithCode(AttachmentConstant.ATTACHMENT_UEDITOR_CODE);
			String path = AttachmentUtil.getSaveFilePath(attachmentConfig);
			File dir = new File(path);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			List<Attachment> list = attachmentService.getAttachmentsWithOwnerId(AttachmentConstant.ATTACHMENT_UEDITOR_OWNER_ID);
			String newFileName = UUID.getUUID() + "." + AttachmentUtil.getFileExtension(file.getOriginalFilename());
			File newFile = new File(dir.getAbsolutePath() + "/" + newFileName);
			StorageFactory.getStorageService().storage(tempFile, newFile);
			Attachment attachment = new Attachment();
			attachment.setSourceFilename(file.getOriginalFilename());
			attachment.setStatus(AttachmentConstant.ATTACHMENT_STATUS_USE);
			attachment.setServerFilename(newFileName);
			attachment.setFileNum(list.size());
			attachment.setId_owner(AttachmentConstant.ATTACHMENT_UEDITOR_OWNER_ID);
			attachment.setPath(AttachmentUtil.getRelativePath(attachmentConfig));
			attachment.setConfigCode(AttachmentConstant.ATTACHMENT_UEDITOR_CODE);
			attachment.setFileSize("" + file.getSize());
			attachmentService.save(attachment);
			url = AttachmentUtil.getPicUrl(attachment, false, AttachmentConstant.ATTACHMENT_DOWNLOAD_PERIOD_FOREVER);
			// url带上dataSourceName，共其他平台（android,ios）查看
			Object dsName = DataSourceHolder.getCurDataSourceKey();
			if (dsName != null && StringUtils.isNotBlank(dsName.toString())) {
				url += "&dataSourceName=" + dsName.toString();
			}
			tempFile.delete();
		} catch (Exception e) {
			e.printStackTrace();
			state = e.getMessage();
		}
		Map<String, String> tempMap = new HashMap<String, String>();
		tempMap.put("url", url);
		tempMap.put("state", state);
		tempMap.put("size", file.getSize() + "");
		tempMap.put("type", "." + AttachmentUtil.getFileExtension(file.getOriginalFilename()));
		tempMap.put("original", file.getOriginalFilename());
		tempMap.put("name", file.getOriginalFilename());
		this.print(JsonUtil.map2Json(tempMap));
	}

	/**
	 * 下载地址
	 * 
	 * @return
	 */
	@RequestMapping("/download")
	@ResponseBody
	public String download() {
		// 内网，外网
		String checkUser = this.getRequest().getParameter("checkUser");
		String period = this.getRequest().getParameter("period");
		String token = this.getRequest().getParameter("downloadToken");
		String isZip = this.getRequest().getParameter("isZip");
		String fileName = this.getRequest().getParameter("fileName");
		attachmentService.download(token, Boolean.parseBoolean(checkUser), period, StringUtils.isNotBlank(isZip), fileName);
		return null;
	}

	/**
	 * 下载多个owner对应的附件
	 * 
	 * @return
	 */
	@RequestMapping("/downloadMulitOwner")
	@ResponseBody
	public String downloadMulitOwner() {
		String param = this.getRequest().getParameter("param");
		Map<String, String> map = AttachmentUtil.parseParamStringToMap(param);
		attachmentService.downloadMulitOwner(map);
		return null;
	}

	/**
	 * 显示图片
	 * 
	 * @return
	 */
	@RequestMapping("/showPic")
	@ResponseBody
	public String showPic() {
		// 内网，外网
		String checkUser = this.getRequest().getParameter("checkUser");
		String period = this.getRequest().getParameter("period");
		String token = this.getRequest().getParameter("downloadToken");
		attachmentService.showPic(token, Boolean.parseBoolean(checkUser), period);
		return null;
	}

	/**
	 * 显示视频
	 * 
	 * @return
	 */
	@RequestMapping("/showAudio")
	@ResponseBody
	public String showAudio() {
		// 内网，外网
		String checkUser = this.getRequest().getParameter("checkUser");
		String period = this.getRequest().getParameter("period");
		String token = this.getRequest().getParameter("downloadToken");
		attachmentService.showAudio(token, Boolean.parseBoolean(checkUser), period);
		return null;
	}

	@RequestMapping("/show")
	@ResponseBody
	public String show() {
		String checkUser = this.getRequest().getParameter("checkUser");
		String period = this.getRequest().getParameter("period");
		String token = this.getRequest().getParameter("downloadToken");
		attachmentService.show(token, Boolean.parseBoolean(checkUser), period);
		return null;
	}

	/**
	 * 上传完后临时显示图片地址
	 * 
	 * @return
	 */
	@RequestMapping("/showTempPic")
	@ResponseBody
	public String showTempPic() {
		String attachmentId = this.getRequest().getParameter("attachmentId");
		attachmentService.showTemp(attachmentId, AttachmentConstant.CONTENTTYPE_IMAGE);
		return null;
	}

	/**
	 * 上传完后临时显示视频地址
	 * 
	 * @return
	 */
	@RequestMapping("/showTempVideo")
	@ResponseBody
	public String showTempVideo() {
		String attachmentId = this.getRequest().getParameter("attachmentId");
		attachmentService.showTemp(attachmentId, AttachmentConstant.CONTENTTYPE_AUDIO);
		return null;
	}

	@RequestMapping("/ajaxGetAttachment")
	@ResponseBody
	public void ajaxGetAttachment(String idOwner) {
		List<AttachmentVO> existAttachments = new ArrayList<AttachmentVO>(0);
		if (idOwner != null) {
			List<Attachment> attachments = attachmentService.getAttachmentsWithOwnerId(idOwner);
			if (attachments.size() > 0) {
				for (Attachment att : attachments) {
					AttachmentVO attachmentVO = new AttachmentVO();
					attachmentVO.setId(att.getId());
					attachmentVO.setName(att.getSourceFilename());
					existAttachments.add(attachmentVO);
				}
			}
		}
		this.print(JsonUtil.collection2Json(existAttachments));
	}
}