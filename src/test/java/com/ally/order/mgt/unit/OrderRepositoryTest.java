package com.ally.order.mgt.unit;

import static org.junit.Assert.assertNotNull;

import java.security.SecureRandom;
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
import com.ally.order.mgt.domain.Product;
import com.ally.order.mgt.exception.OrderNotFoundException;
import com.ally.order.mgt.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest {

	Order order =  null;
	List<Product> products = null;
	String existingOrderId = null;
	@Autowired
	private OrderRepository repository;

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
		existingOrderId = repository.insertOrderEntry(new Order(productsXYZ)).getOrdernumber();
	}

	@After
	public void tearDown() throws Exception {
		products = null;
		order =  null;
		existingOrderId = null;
	}

	@Test
	public void testInsertOrderWhenThereNoProducts() throws Exception {
		Order inserted = repository.insertOrderEntry(new Order());
		assertNotNull(inserted.getOrdernumber());
		Assert.assertEquals("0.00", inserted.getTotaldue());
	}
	
	@Test
	public void testInsertOrderSuccessScenario() throws Exception {
		Order inserted = repository.insertOrderEntry(order);
		assertNotNull(inserted.getOrdernumber());
		Assert.assertEquals("66.30", inserted.getTotaldue());
	}

	@Test(expected = OrderNotFoundException.class)
	public void testgetOrderByIdWhenWithNonExistentId() throws Exception {
		repository.getOrderById(String.valueOf(new SecureRandom().nextInt(100)+1));
	}
	
	@Test
	public void testgetOrderByIdWhenWithValidOrderId() throws Exception {
		Order retrived = repository.getOrderById(existingOrderId);
		assertNotNull(retrived);
		Assert.assertEquals("WAITING_FOR_PAYMENT", retrived.getStatus());
	}

	@Test
	public void testUpdateOrderByIdWhenWithValidOrderId() throws Exception {
		repository.updateOrderStatus(existingOrderId, "PAYMENT_SUCCESSFULL");
		Order retrived = repository.getOrderById(existingOrderId);
		assertNotNull(retrived);
		Assert.assertEquals("PAYMENT_SUCCESSFULL", retrived.getStatus());
	}
	
	@Test
	public void testGetAllOrderIdsNonEmptyScenario() throws Exception {
		List<String> orderIds = repository.getAllOrderIds();
		assertNotNull(orderIds);
		Assert.assertTrue(orderIds.size() > 0);
	}

}
