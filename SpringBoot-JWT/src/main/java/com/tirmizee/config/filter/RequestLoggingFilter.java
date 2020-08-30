package com.tirmizee.config.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

@Component
public class RequestLoggingFilter extends AbstractRequestLoggingFilter {

	public static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);
	
	@Override
	protected void afterRequest(HttpServletRequest request, String message) {
	}

	@Override
	protected void beforeRequest(HttpServletRequest request, String message) {
		logger.info(message);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("Process RequestLoggingFilter");
		MDC.put("accessIp", request.getRemoteAddr());
		MDC.put("logId", UUID.randomUUID().toString());
		super.doFilterInternal(request, response, filterChain);
	}

	@Override
	protected boolean shouldLog(HttpServletRequest request) {
		return super.shouldLog(request);
	}

	@Override
	protected boolean shouldNotFilterAsyncDispatch() {
		return super.shouldNotFilterAsyncDispatch();
	}

}
