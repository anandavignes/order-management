package com.ally.order.mgt.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ally.order.mgt.config.ErrorCodesResponseMap;
import com.ally.order.mgt.domain.exception.ErrorDetails;
import com.ally.order.mgt.domain.exception.ErrorResponse;
import com.ally.order.mgt.exception.ApiClientSideException;
import com.ally.order.mgt.exception.ApiServerSideException;
import com.ally.order.mgt.exception.BadRequestException;
import com.ally.order.mgt.exception.MissingProductsException;
import com.ally.order.mgt.exception.NoOrdersFoundException;
import com.ally.order.mgt.exception.OrderNotFoundException;
import com.ally.order.mgt.exception.OverPaymentException;
import com.ally.order.mgt.exception.UnderPaymentException;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Autowired
	private ErrorCodesResponseMap errorCodesResponseMap;

	@ExceptionHandler(BadRequestException.class)
	protected ResponseEntity<ErrorResponse> badRequest(BadRequestException badRequestException) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCodesResponseMap.getMap().get(4001)),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(OrderNotFoundException.class)
	protected ResponseEntity<ErrorResponse> orderNotFoundException(OrderNotFoundException orderNotFoundException) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCodesResponseMap.getMap().get(4002)),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UnderPaymentException.class)
	protected ResponseEntity<ErrorResponse> underPaymentException(UnderPaymentException underPaymentException) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCodesResponseMap.getMap().get(4003)),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(OverPaymentException.class)
	protected ResponseEntity<ErrorResponse> overPaymentException(OverPaymentException overPaymentException) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCodesResponseMap.getMap().get(4004)),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(MissingProductsException.class)
	protected ResponseEntity<ErrorResponse> missingProductsException(MissingProductsException missingProductsException) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCodesResponseMap.getMap().get(4005)),
				HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(NoOrdersFoundException.class)
	protected ResponseEntity<ErrorResponse> noOrdersFoundException(NoOrdersFoundException noOrdersFoundException) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(errorCodesResponseMap.getMap().get(4006)),
				HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler(ApiClientSideException.class)
	protected ResponseEntity<ErrorDetails> returnClientException(ApiClientSideException apiClientSideException) {
		ErrorDetails errorDetails = errorCodesResponseMap.getMap().get(apiClientSideException.getStatusCode());
		logger.debug(" Error Response Body Is {}", errorDetails.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(Integer.parseInt(errorDetails.getStatusCode())));
	}

	@ExceptionHandler(value = {ApiServerSideException.class})
	protected ResponseEntity<ErrorDetails> returnServerSideException(ApiServerSideException apiServerSideException) {
		ErrorDetails errorDetails = errorCodesResponseMap.getMap().get(500);
		logger.debug(" Error Response Body Is {}", errorDetails.toString());
		return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(Integer.parseInt(errorDetails.getStatusCode())));
	}

}
