package com.ally.order.mgt.domain;

import java.io.Serializable;

public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	private String sku;
	private String price;
	private String quantity;
	
	public Product() {}
	
	public Product(String sku, String price, 
			String quantity) {
		this.sku = sku;
		this.price = price;
		this.quantity = quantity;
	}

	public String getSku() {
		return sku;
	}

	public String getPrice() {
		return price;
	}

	public String getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Product [sku=" + sku + ", price=" + price + ", quantity=" + quantity + ", getSku()=" + getSku()
				+ ", getPrice()=" + getPrice() + ", getQuantity()=" + getQuantity();
	}
	

}
