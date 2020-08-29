package com.tirmizee.config.inceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.tirmizee.component.JWTProvider;
import com.tirmizee.constant.JWTConstants;

@Component
public class JWTRenewInceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTRenewInceptor.class);
	
	@Autowired
	private JWTProvider jwtProvider;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("JWTRenewInceptor preHandle : {}", Thread.currentThread().getName());
		
		String token = (String) request.getAttribute(JWTConstants.TOKEN_ATTRIBUTE);
		
		if (token != null) {
			String newToken = jwtProvider.refreshToken(token);
			response.setHeader("Token-Renew", newToken);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("JWTRenewInceptor postHandle : {}", Thread.currentThread().getName());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.info("JWTRenewInceptor afterCompletion : {}", Thread.currentThread().getName());
	}

}
