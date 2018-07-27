package com.ally.order.mgt.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OrderSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String numberWaitingForPayment;
	private String numberShipped;
	
	public OrderSummary() {}
	
	public OrderSummary(String numberWaitingForPayment, String numberShipped) {
		this.numberWaitingForPayment = numberWaitingForPayment;
		this.numberShipped = numberShipped;
	}

	public String getNumberWaitingForPayment() {
		return numberWaitingForPayment;
	}

	public String getNumberShipped() {
		return numberShipped;
	}


}
