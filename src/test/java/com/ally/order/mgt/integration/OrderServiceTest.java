package com.ally.order.mgt.integration;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ally.order.mgt.domain.Order;
import com.ally.order.mgt.domain.OrderSummary;
import com.ally.order.mgt.domain.Product;
import com.ally.order.mgt.exception.BadRequestException;
import com.ally.order.mgt.exception.MissingProductsException;
import com.ally.order.mgt.service.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	@Autowired
	OrderService orderService;
	Order order =  null;
	List<Product> products = null;
	String existingOrderId = null;
	
	@Before
	public void setUp() throws Exception {
		products = new ArrayList<Product>();
		Product prod1 = new Product("abcd","10.2","2");
		Product prod2 = new Product("abcd","15.3","3");
		
		products.add(prod1);
		products.add(prod2);
		order = new Order(products);

		Product prodXYZ = new Product("xyz","11.3","1");
		List<Product> productsXYZ = new ArrayList<Product>();
		productsXYZ.add(prodXYZ);
		existingOrderId = orderService.submitOrder(new Order(productsXYZ)).getOrdernumber();
	
	}

	@After
	public void tearDown() throws Exception {
		existingOrderId = null;
	}

	@Test(expected=MissingProductsException.class)
	public void testSubmitOrderWhenNoProductsExists() throws Exception {
		assertNotNull(orderService.submitOrder(new Order()).getOrdernumber());
	}
	@Test
	public void testSubmitOrderSuccessScenario() throws Exception {
		assertNotNull(orderService.submitOrder(order).getOrdernumber());
		assertNotNull(orderService.submitOrder(order).getTotaldue());
	}

	@Test(expected=BadRequestException.class)
	public void testRetreiveOrderWhenThereNoOrderId() throws Exception {
		assertNotNull(orderService.retrieveOrder(null));
	}

	@Test
	public void testRetreiveOrderSuccessScenario() throws Exception {
		assertNotNull(orderService.retrieveOrder(existingOrderId));
	}

	@Test
	public void testRetreiveAllOrdersSuccessScenario() throws Exception {
		List<Order> orders = orderService.retrieveAllOrders();
		assertNotNull(orders);
		Assert.assertTrue(orders.size() > 0);
	}

	@Test
	public void testRetreiveOrderSummaryScenario() throws Exception {
		OrderSummary orderSummary = orderService.retriveOrderSummary();
		assertNotNull(orderSummary);
		Assert.assertTrue(Integer.valueOf(orderSummary.getNumberWaitingForPayment()) > 0);
		Assert.assertEquals("0", orderSummary.getNumberShipped());
	}

}
