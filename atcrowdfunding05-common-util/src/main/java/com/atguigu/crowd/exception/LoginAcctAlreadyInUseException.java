package com.atguigu.crowd.exception;


/**
 * 保存Admin时如果检测到登陆账号抛出的异常
 * @author tornado4651
 *
 */
public class LoginAcctAlreadyInUseException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public LoginAcctAlreadyInUseException() {
		super();
	}

	public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginAcctAlreadyInUseException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginAcctAlreadyInUseException(String message) {
		super(message);
	}

	public LoginAcctAlreadyInUseException(Throwable cause) {
		super(cause);
	}
	
	

}
