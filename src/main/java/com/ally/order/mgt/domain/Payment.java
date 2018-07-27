package com.ally.order.mgt.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Payment extends OrderNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String payment;
	
	public Payment() {}
	
	public Payment(String orderNumber, String payment) {
		super(orderNumber);
		this.payment = payment;
	}

	public String getPayment() {
		return payment;
	}

	@Override
	public String toString() {
		return "Payment [payment=" + payment + ", getPayment()=" + getPayment() + ", getOrdernumber()="
				+ getOrdernumber();
	}


}
