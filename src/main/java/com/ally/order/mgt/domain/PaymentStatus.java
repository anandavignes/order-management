package com.ally.order.mgt.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PaymentStatus extends OrderNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	
	public PaymentStatus() {}
	
	public PaymentStatus(String orderNumber, String status) {
		super(orderNumber);
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
