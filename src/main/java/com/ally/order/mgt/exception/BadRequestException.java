package com.ally.order.mgt.exception;

public class BadRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String statusCode;
	private String errorMessage;
	
	public BadRequestException() { }
	
	public BadRequestException(String statusCode, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
