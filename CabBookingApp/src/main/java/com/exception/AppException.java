package com.exception;

public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -374999354699774869L;
	public AppException() {
		super();
	}
	public AppException(final String message) {
		super(message);
	}
	public AppException(final String message,Throwable cause) {
		super(message,cause);
	}
}
