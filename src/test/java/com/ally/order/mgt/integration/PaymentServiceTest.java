package com.ally.order.mgt.integration;

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
import com.ally.order.mgt.domain.Payment;
import com.ally.order.mgt.domain.Product;
import com.ally.order.mgt.exception.OrderNotFoundException;
import com.ally.order.mgt.exception.UnderPaymentException;
import com.ally.order.mgt.service.OrderService;
import com.ally.order.mgt.service.PaymentService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentServiceTest {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	OrderService orderService;
	
	String existingOrderId = null;
	
	@Before
	public void setUp() throws Exception {
		Product prodXYZ = new Product("xyz","11.3","1");
		List<Product> productsXYZ = new ArrayList<Product>();
		productsXYZ.add(prodXYZ);
		existingOrderId = orderService.submitOrder(new Order(productsXYZ)).getOrdernumber();
	}

	@After
	public void tearDown() throws Exception {
		existingOrderId = null;
	}

	@Test(expected=UnderPaymentException.class)
	public void testSubmitPaymentUnderPaymentScenario() throws Exception {
		Payment payment = new Payment(existingOrderId, "0.0");
		Assert.assertTrue(paymentService.submitPayment(payment));
	}

	@Test(expected=Exception.class)
	public void testSubmitPaymentOverPaymentScenario() throws Exception {
		Payment payment = new Payment(existingOrderId, "100.0");
		Assert.assertTrue(paymentService.submitPayment(payment));
	}

	@Test(expected=OrderNotFoundException.class)
	public void testSubmitPaymentInvalidOrderIdScenario() throws Exception {
		Payment payment = new Payment("000000", "11.3");
		Assert.assertTrue(paymentService.submitPayment(payment));
	}
	
	@Test
	public void testSubmitPaymentSuccessScenario() throws Exception {
		Payment payment = new Payment(existingOrderId, "11.3");
		Assert.assertTrue(paymentService.submitPayment(payment));
	}

}
