package com.ally.order.mgt.service;

import com.ally.order.mgt.domain.Payment;

public interface PaymentService {

	public boolean submitPayment(Payment payment) throws Exception;
	
}
