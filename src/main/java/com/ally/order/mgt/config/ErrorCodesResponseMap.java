package com.ally.order.mgt.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ally.order.mgt.domain.exception.ErrorDetails;

@Component
@ConfigurationProperties(prefix = "error")
public class ErrorCodesResponseMap {

	private Map<Integer, ErrorDetails> errorMap;

	public Map<Integer, ErrorDetails> getMap() {
		return errorMap;
	}

	public void setMap(Map<Integer, ErrorDetails> map) {
		this.errorMap = map;
	}

	
}
