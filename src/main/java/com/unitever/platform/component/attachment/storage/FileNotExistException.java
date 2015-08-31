package com.unitever.platform.component.attachment.storage;

import com.unitever.platform.core.exception.BusinessException;

/**
 * 文件不存在异常，下载文件时可能抛出此异常
 * 
 * @author tianyl
 * 
 */
public class FileNotExistException extends BusinessException {

	private static final long serialVersionUID = 8884584577088905437L;

	public FileNotExistException() {
		super();
	}

	public FileNotExistException(String errorCode, String... errorArgs) {
		super(errorCode, errorArgs);
	}

	public FileNotExistException(String errorCode, Throwable t, String... errorArgs) {
		super(errorCode, t, errorArgs);
	}

	public FileNotExistException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}

	public FileNotExistException(String errorCode) {
		super(errorCode);
	}

}
