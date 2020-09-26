package com.tirmizee.config.security;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tirmizee.common.response.ResponseData;

@Component
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

	public static final Logger logger = LoggerFactory.getLogger(JWTAccessDeniedHandler.class);
	
	private ObjectMapper objectMapper;
	
	public JWTAccessDeniedHandler(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		logger.debug("JWTAccessDeniedHandler : {}", accessDeniedException.getMessage());
		response.addHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
		response.setStatus(HttpServletResponse.SC_FORBIDDEN);
		response.getOutputStream().print(objectMapper.writeValueAsString(ResponseData.failure(null)));
	}

}
