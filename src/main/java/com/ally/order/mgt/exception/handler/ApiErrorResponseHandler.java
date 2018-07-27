package com.ally.order.mgt.exception.handler;

import java.io.IOException;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import com.ally.order.mgt.exception.ApiClientSideException;
import com.ally.order.mgt.exception.ApiServerSideException;

public class ApiErrorResponseHandler implements ResponseErrorHandler {

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		
		if(response.getStatusCode().is4xxClientError()){
			throw new ApiClientSideException(response.getRawStatusCode());
		}
		
		else if(response.getStatusCode().is5xxServerError()){
			throw new ApiServerSideException(response.getRawStatusCode());
		}	
		
	}

	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		
		if(!response.getStatusCode().is2xxSuccessful()) {
			return true;
		}
		
		return false;
	}

}
