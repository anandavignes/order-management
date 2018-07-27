package com.ally.order.mgt.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Order extends OrderNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Product> products;
	private String totaldue;
	private String status;
	
	public Order() {}

	public Order(List<Product> products) {
		this.products = products;
	}

	public Order(String ordernumber, List<Product> products, 
			String totaldue, String status) {
		super(ordernumber);
		this.products = products;
		this.totaldue = totaldue;
		this.status = status;
	}

	public List<Product> getProducts() {
		return products;
	}

	public String getTotaldue() {
		return totaldue;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Order [products=" + products + ", totaldue=" + totaldue + ", status=" + status + ", getProducts()="
				+ getProducts() + ", getTotaldue()=" + getTotaldue() + ", getStatus()=" + getStatus()
				+ ", getOrdernumber()=" + getOrdernumber();
	}


}
