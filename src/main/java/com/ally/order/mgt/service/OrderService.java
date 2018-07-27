package com.ally.order.mgt.service;

import java.util.List;

import com.ally.order.mgt.domain.Order;
import com.ally.order.mgt.domain.OrderSummary;

public interface OrderService {

	public Order submitOrder(Order order) throws Exception;
	public Order retrieveOrder(String orderId) throws Exception;
	public List<Order> retrieveAllOrders() throws Exception;
	public OrderSummary retriveOrderSummary() throws Exception;
	
}
