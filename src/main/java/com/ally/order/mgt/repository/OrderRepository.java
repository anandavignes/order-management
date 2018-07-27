package com.ally.order.mgt.repository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ally.order.mgt.domain.Order;
import com.ally.order.mgt.domain.Product;
import com.ally.order.mgt.exception.OrderNotFoundException;
import com.ally.order.mgt.util.OrderUtil;

@Repository
public class OrderRepository {

	private static Logger logger = LoggerFactory.getLogger(OrderRepository.class);

	private static String INSERT_ORDER_ENTRY = "INSERT INTO ORDERS(ID, STATUS, LAST_UPD_DT) VALUES (:id, :status, SYSDATE)";
	private static String INSERT_PRODUCT_ENTRY = "INSERT INTO PRODUCTS(ID, ORDER_ID, SKU, PRICE, QUANTITY ) VALUES (:id, :orderId, :sku, :price, :quantity)";
	private static String UPDATE_ORDER_STATUS = "UPDATE ORDERS  SET STATUS = :status WHERE ID = :orderId";
	private static String FIND_PRODUCTS_BY_ORDER_ID = "SELECT sku, price, quantity FROM PRODUCTS WHERE order_id = :orderId";
	private static String FIND_ORDER_STATUS_BY_ID = "SELECT status FROM ORDERS WHERE id = :orderId";
	private static String FIND_ALL_ORDER_IDS = "SELECT id FROM ORDERS";
	private static String FIND_ORDER_COUNT_BY_STATUS = "SELECT count(*) as order_count FROM ORDERS WHERE status = :status";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public Order insertOrderEntry(Order order) throws Exception {
		logger.info(" New order creation starts..");
		long orderId = new SecureRandom().nextInt(99999999)+1;
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("id", orderId);
		namedParameters.put("status", "WAITING_FOR_PAYMENT");
		jdbcTemplate.update(INSERT_ORDER_ENTRY, namedParameters);
		
		if(null != order.getProducts()) {
			for(Product p: order.getProducts()) {
				addProductEntry(orderId, p);
			}
		}

		logger.info("New Order Entry created successfully!");
		
		return new Order(String.valueOf(orderId), null, OrderUtil.calculateTotalDueAmount(order.getProducts()), null);
	}

	public void updateOrderStatus(String orderId, String status) throws Exception {
		logger.info(" Update the status of OrderId and the Status {} {}", orderId, status);
		try {
			Map<String, Object> namedParameters = new HashMap<String, Object>();
			namedParameters.put("orderId", orderId);
			namedParameters.put("status",status);
			jdbcTemplate.update(UPDATE_ORDER_STATUS, namedParameters);
		}catch (EmptyResultDataAccessException emptyException) {
			throw new OrderNotFoundException();
		}
		logger.info("Order Status updated successfully");
	}
	
	public Order getOrderById(String orderId) throws Exception {
		String status = "";
		try {
			status = jdbcTemplate.queryForObject(FIND_ORDER_STATUS_BY_ID,
					new MapSqlParameterSource("orderId", orderId), String.class);
		}catch (EmptyResultDataAccessException emptyException) {
			throw new OrderNotFoundException();
		}

		List<Product> products = (List<Product>) jdbcTemplate.query(FIND_PRODUCTS_BY_ORDER_ID, 
				new MapSqlParameterSource("orderId", orderId), new ProductMapper());
		
		Order order = new Order(orderId, products, OrderUtil.calculateTotalDueAmount(products), status);
		
		return order;
	}

	public List<String> getAllOrderIds() throws Exception {
		List<String> orderIds = new ArrayList<String>();
		orderIds = (List<String>) jdbcTemplate.query(FIND_ALL_ORDER_IDS, new OrderNumberMapper());
		return orderIds;
	}
	
	public String getOrderCountByStatus(String status) throws Exception {
		int count = 0;
		try {
			count = jdbcTemplate.queryForObject(FIND_ORDER_COUNT_BY_STATUS,
					new MapSqlParameterSource("status", status), Integer.class);
		}catch (EmptyResultDataAccessException emptyException) {
			throw new OrderNotFoundException();
		}
		return String.valueOf(count);
	}
	
	private void addProductEntry(long orderId, Product p) {
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("id", new SecureRandom().nextInt(10000)+1);
		namedParameters.put("orderId", orderId);
		namedParameters.put("sku", p.getSku());
		if(!StringUtils.isEmpty(p.getPrice())) {
			namedParameters.put("price", Double.valueOf(p.getPrice()));
		}
		if(!StringUtils.isEmpty(p.getQuantity())) {
			namedParameters.put("quantity", Integer.valueOf(p.getQuantity()));
		}
		jdbcTemplate.update(INSERT_PRODUCT_ENTRY, namedParameters);
	}

}
