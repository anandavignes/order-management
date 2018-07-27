package com.ally.order.mgt.rest;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ally.order.mgt.domain.Order;
import com.ally.order.mgt.domain.OrderSummary;
import com.ally.order.mgt.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Order> createOrder(@RequestBody Order order) throws Exception {
		logger.info("Order Submission operation begins for {} ", order.toString());
		return new ResponseEntity<>(orderService.submitOrder(order), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> findOrderById(@PathVariable String id) throws Exception {
		logger.info("Order Retrieval operation for Order id {} - begins ", id);
		return new ResponseEntity<>(orderService.retrieveOrder(id), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	public ResponseEntity<OrderSummary> findOrderSummary() throws Exception {
		logger.info("Retrieve Order Summary operation - begins");
		return new ResponseEntity<>(orderService.retriveOrderSummary(), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Order>> findAllOrders() throws Exception {
		logger.info("Retrieve All Orders operation - begins");
		return new ResponseEntity<>(orderService.retrieveAllOrders(), HttpStatus.OK);
	}
	

}
