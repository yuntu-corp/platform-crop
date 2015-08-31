package com.unitever.platform.component.attachment.model;

import java.io.Serializable;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 5584474299743949870L;
	private String id;
	private String id_owner; // 主体ID
	private String path; // 路径
	private String sourceFilename; // 源文件名
	private String serverFilename; // 服务端文件名
	private Integer fileNum; // 附件序号
	private String fileSize; // 文件大小
	private String fileKind; // 文件类型 （后缀）
	private String status;// 状态
	private String configCode;

	private String ft;
	private String lt;
	private String fu;
	private String lu;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId_owner() {
		return id_owner;
	}

	public void setId_owner(String id_onwer) {
		this.id_owner = id_onwer;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getFileNum() {
		return fileNum;
	}

	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getSourceFilename() {
		return sourceFilename;
	}

	public void setSourceFilename(String sourceFilename) {
		this.sourceFilename = sourceFilename;
	}

	public String getServerFilename() {
		return serverFilename;
	}

	public void setServerFilename(String serverFilename) {
		this.serverFilename = serverFilename;
	}

	public String getFileKind() {
		return fileKind;
	}

	public void setFileKind(String fileKind) {
		this.fileKind = fileKind;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

}
