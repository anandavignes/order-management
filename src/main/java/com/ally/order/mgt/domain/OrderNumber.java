package com.ally.order.mgt.domain;

import java.io.Serializable;

public class OrderNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ordernumber;
	
	public OrderNumber() {}
	
	public OrderNumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

}
