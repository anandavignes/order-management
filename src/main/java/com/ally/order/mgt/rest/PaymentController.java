package com.ally.order.mgt.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ally.order.mgt.domain.Payment;
import com.ally.order.mgt.domain.PaymentStatus;
import com.ally.order.mgt.service.PaymentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/payment", produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PaymentStatus> submitPayment(@RequestBody Payment payment) throws Exception {
		logger.info("Payment Submission operation - beings for {}",  payment.toString());
		boolean paymentSuccess = paymentService.submitPayment(payment);
		
		if (paymentSuccess) {
			PaymentStatus paymentStatus = new PaymentStatus(payment.getOrdernumber(), "SUCCESS");
			return new ResponseEntity<>(paymentStatus, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
}
