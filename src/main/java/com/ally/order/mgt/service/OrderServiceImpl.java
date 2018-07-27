package com.ally.order.mgt.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ally.order.mgt.domain.Order;
import com.ally.order.mgt.domain.OrderSummary;
import com.ally.order.mgt.exception.BadRequestException;
import com.ally.order.mgt.exception.MissingProductsException;
import com.ally.order.mgt.exception.NoOrdersFoundException;
import com.ally.order.mgt.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private static Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order submitOrder(Order order) throws Exception {
		logger.info("New Order Submission starts");
		if(null != order) {
			if(null == order.getProducts() || order.getProducts().isEmpty()) {
				throw new MissingProductsException();
			}
		}
		order = orderRepository.insertOrderEntry(order);
		
		logger.info("Order Submission was successfull and the order number is {} and the total due is {}",
				order.getOrdernumber(), order.getTotaldue());
		
		return order;
	}

	@Override
	public Order retrieveOrder(String orderId) throws Exception {
		logger.info("Retrive Order - OrderNumber {}", orderId);
		if(StringUtils.isEmpty(orderId)) {
			throw new BadRequestException();
		}
		return orderRepository.getOrderById(orderId);
	}

	@Override
	public List<Order> retrieveAllOrders() throws Exception {
		logger.info("Retrieve All Orders - Starts");
		List<Order> allOrders = new ArrayList<Order>();
		List<String> orderIds = orderRepository.getAllOrderIds();
		if(null == orderIds || orderIds.isEmpty()) {
			throw new NoOrdersFoundException();
		}
		
		logger.info("Total number of orders: {}", allOrders.size());
		for(String orderId : orderIds) {
			allOrders.add(retrieveOrder(orderId));
		}
		return allOrders;
	}

	@Override
	public OrderSummary retriveOrderSummary() throws Exception {
		logger.info("Retrieve Order Summary - Starts");
		
		OrderSummary orderSummary = new OrderSummary(orderRepository.getOrderCountByStatus("WAITING_FOR_PAYMENT"),
				orderRepository.getOrderCountByStatus("PAYMENT_SUCCESSFULL"));
		
		logger.info("Retrieve Order Summary: Payment-Waiting: {} , Order-Shipped: {}",
				orderSummary.getNumberWaitingForPayment(), orderSummary.getNumberShipped());
		
		return orderSummary;
	}


}
