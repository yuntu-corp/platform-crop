package com.unitever.platform.core.exception;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.NestedRuntimeException;

/**
 * 业务异常基类. 带有错误代码与错误信息. 用户在生成异常时既可以直接赋予错误代码与错误信息. 也可以只赋予错误代码与错误信息参数.
 */
public class BusinessException extends NestedRuntimeException {

	private static final long serialVersionUID = -7457274113522059787L;

	/**
	 * 错误代码,默认为未知错误
	 */
	private String errorCode = "UNKNOW_ERROR";

	/**
	 * 错误信息中的参数
	 */
	protected String[] errorArgs = null;

	/**
	 * 兼容纯错误信息，不含error code,errorArgs的情况
	 */
	private String errorMessage = null;

	/**
	 * 错误信息的i18n ResourceBundle. 默认Properties文件名定义于 {@link Constants#ERROR_BUNDLE_KEY}
	 */
	// static private ResourceBundle rb = ResourceBundle.getBundle("i18n/errors", LocaleContextHolder.getLocale());

	public BusinessException() {
		super("UNKNOW_ERROR");
	}

	public BusinessException(String errorCode, String... errorArgs) {
		super(errorCode);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public BusinessException(String errorCode, Throwable t, String... errorArgs) {
		super(errorCode, t);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public BusinessException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public BusinessException(String errorMessage, Throwable t) {
		super("UNKNOW_ERROR", t);
		this.errorMessage = errorMessage;
	}

	/**
	 * 获得出错信息. 读取i18N properties文件中Error Code对应的message,并组合参数获得i18n的出错信息.
	 */
	public String getMessage() {
		if (StringUtils.isNotBlank(this.errorMessage)) {
			return errorMessage;
		}
		// TODO
		// errorMessage = I18nUtil.getI18nString(errorCode, errorArgs);
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String[] getErrorArgs() {
		return errorArgs;
	}

	public void setErrorArgs(String[] errorArgs) {
		this.errorArgs = errorArgs;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
