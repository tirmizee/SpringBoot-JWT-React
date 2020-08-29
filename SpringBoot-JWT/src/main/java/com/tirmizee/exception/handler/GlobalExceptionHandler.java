package com.tirmizee.exception.handler;

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

	@ExceptionHandler(value = RequestRejectedException.class)
	ResponseEntity<?> handleException(RequestRejectedException exception){
		ResponseData<Void> responseData = new ResponseData<Void>();
		responseData.setStatus(false);
		responseData.setMessageDesc(exception.getMessage());
		return ResponseEntity.badRequest().body(responseData);
	}
	
}
