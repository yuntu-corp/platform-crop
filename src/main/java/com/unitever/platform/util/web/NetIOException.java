package com.unitever.platform.util.web;

import com.unitever.platform.core.exception.BusinessException;

/**
 * 网络相关的业务异常
 * 
 * @author tianyl
 * 
 */
public class NetIOException extends BusinessException {

	private static final long serialVersionUID = 3772089145982669733L;

	public NetIOException(String errorMessage, Throwable t) {
		super("UNKNOW_ERROR", t);
	}

}
