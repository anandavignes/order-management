package com.ally.order.mgt.exception;

import java.io.Serializable;

public class ApiClientSideException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int statusCode;

	public ApiClientSideException(int status) {
		super();
		this.statusCode = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

}
