package com.unitever.platform.component.attachment.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

public class AttachmentSetting implements Serializable {

	private static final long serialVersionUID = 3672885435736955348L;
	private String id;
	private String limitFileExtention;

	private String ft;
	private String lt;
	private String fu;
	private String lu;

	private String type;
	private String ftpAddress;
	private String ftpPort;
	private String ftpPath;
	private String ftpUserName;
	private String ftpPassword;

	public AttachmentSetting() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLimitFileExtention() {
		return limitFileExtention;
	}

	public void setLimitFileExtention(String limitFileExtention) {
		this.limitFileExtention = limitFileExtention;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFtpAddress() {
		return ftpAddress;
	}

	public void setFtpAddress(String ftpAddress) {
		this.ftpAddress = ftpAddress;
	}

	public String getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(String ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpPath() {
		return ftpPath;
	}

	public void setFtpPath(String ftpPath) {
		this.ftpPath = ftpPath;
	}

	public String getFtpUserName() {
		return ftpUserName;
	}

	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public boolean isLocal() {
		return StringUtils.isBlank(type) || "0".equals(type);
	}

	public boolean isFtp() {
		return "1".equals(type);
	}
}
