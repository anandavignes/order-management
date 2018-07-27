package com.ally.order.mgt.domain.exception;

import java.io.Serializable;

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String statusCode;

	private String message;

	private String subStatusCode;

	public ErrorDetails() {
		super();
	}

	public ErrorDetails(String statusCode, String message, String subStatusCode) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.subStatusCode = subStatusCode;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubStatusCode() {
		return subStatusCode;
	}

	public void setSubStatusCode(String subStatusCode) {
		this.subStatusCode = subStatusCode;
	}

	@Override
	public String toString() {
		return "ErrorDetails [statusCode=" + statusCode + ", message=" + message + ", subStatusCode=" + subStatusCode
				+ "]";
	}

}
