package com.ally.order.mgt.domain.exception;

import java.io.Serializable;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private ErrorDetails error;

	public ErrorResponse(ErrorDetails error) {
		super();
		this.error = error;
	}

	public ErrorDetails getError() {
		return error;
	}

	public void setError(ErrorDetails error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ErrorResponse [error=" + error + "]";
	}

	
}
