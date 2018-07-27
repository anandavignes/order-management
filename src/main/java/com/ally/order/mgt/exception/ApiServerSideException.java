package com.ally.order.mgt.exception;

import java.io.Serializable;

public class ApiServerSideException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final int statusCode;

	public ApiServerSideException(int statusCode) {
		super();
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}

	

}
