package com.tirmizee.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.firewall.RequestRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.tirmizee.common.response.ResponseData;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	public static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(value = RequestRejectedException.class)
	ResponseEntity<?> handleException(RequestRejectedException exception){
		logger.debug("GlobalExceptionHandler RequestRejectedException : {}", exception.getMessage());
		ResponseData<Void> responseData = new ResponseData<Void>();
		responseData.setStatus(false);
		responseData.setMessageDesc(exception.getMessage());
		return createHttpResponse(HttpStatus.BAD_REQUEST, responseData);
	}
	
	private ResponseEntity<ResponseData<?>> createHttpResponse(HttpStatus httpStatus, ResponseData<?> responseData) {
		return new ResponseEntity<ResponseData<?>>(responseData, httpStatus);
	}
	
}
