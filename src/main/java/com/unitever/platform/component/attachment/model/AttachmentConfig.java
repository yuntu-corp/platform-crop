package com.unitever.platform.component.attachment.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class AttachmentConfig implements Serializable {
	private static final long serialVersionUID = -380529852759725260L;
	private String id;
	private String code;
	private String saveDir;
	private Integer dirLevel;
	private Integer fileSize;
	private Integer fileCount;
	private String extension;
	private String description;

	private String ft;
	private String lt;
	private String fu;
	private String lu;

	private String flexFileFilterStr;

	public AttachmentConfig() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getFileCount() {
		return fileCount;
	}

	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getDirLevel() {
		return dirLevel;
	}

	public void setDirLevel(Integer dirLevel) {
		this.dirLevel = dirLevel;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFt() {
		return ft;
	}

	public void setFt(String ft) {
		this.ft = ft;
	}

	public String getLt() {
		return lt;
	}

	public void setLt(String lt) {
		this.lt = lt;
	}

	public String getFu() {
		return fu;
	}

	public void setFu(String fu) {
		this.fu = fu;
	}

	public String getLu() {
		return lu;
	}

	public void setLu(String lu) {
		this.lu = lu;
	}

	public String getFlexFileFilterStr() {
		if (flexFileFilterStr == null) {
			flexFileFilterStr = "";
		}

		// swfupload接收的格式：*.jpg;*.bmp;*.gif;*.png;*.txt;*.doc;*.docx;*.ppt

		if (StringUtils.isNotBlank(extension)) {
			flexFileFilterStr = extension;
			if (extension.indexOf(extension.length() - 1) == ';') {
				flexFileFilterStr = flexFileFilterStr.substring(0, flexFileFilterStr.length() - 1);
			}
			flexFileFilterStr = flexFileFilterStr.replaceAll(";", ";*.");
			flexFileFilterStr = "*." + flexFileFilterStr;
			flexFileFilterStr += ":" + description;
		}
		return flexFileFilterStr;
	}

	public void setFlexFileFilterStr(String flexFileFilterStr) {
		this.flexFileFilterStr = flexFileFilterStr;
	}

}
