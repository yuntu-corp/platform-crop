package com.unitever.platform.core.exception;

public class DeleteException extends BusinessException {
	private static final long serialVersionUID = 3411959600698110088L;

	public DeleteException() {
		super();
	}

	public DeleteException(String errorCode, String... errorArgs) {
		super(errorCode, errorArgs);
	}

	public DeleteException(String errorCode, Throwable t, String... errorArgs) {
		super(errorCode, t, errorArgs);
	}

	public DeleteException(String errorMessage, Throwable t) {
		super(errorMessage, t);
	}

	public DeleteException(String errorCode) {
		super(errorCode);
	}
}
