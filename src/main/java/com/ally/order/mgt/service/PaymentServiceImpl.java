package com.ally.order.mgt.service;

import java.nio.channels.OverlappingFileLockException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ally.order.mgt.domain.Order;
import com.ally.order.mgt.domain.Payment;
import com.ally.order.mgt.exception.UnderPaymentException;
import com.ally.order.mgt.repository.OrderRepository;
import com.ally.order.mgt.util.OrderUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	private static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public boolean submitPayment(Payment payment) throws Exception {
		logger.info("Payment Submission starts for order number - {} and payment of {}", payment.getOrdernumber(),
				payment.getPayment());
		Order order = orderRepository.getOrderById(payment.getOrdernumber());
		double totalDueAmount = Double.valueOf(OrderUtil.calculateTotalDueAmount(order.getProducts()));
		double paymentAmount = Double.valueOf(payment.getPayment());
		if(paymentAmount < totalDueAmount) {
			throw new UnderPaymentException();
		} else if(paymentAmount > totalDueAmount) {
			throw new OverlappingFileLockException();
		}else {
			orderRepository.updateOrderStatus(payment.getOrdernumber(), "PAYMENT_SUCCESSFULL");
			logger.info("Payment was successfull and the order number {} is SHIPPED", payment.getOrdernumber());
			return true;
		}
	}

}
