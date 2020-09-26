package com.tirmizee.config.intercepter;

import java.time.Duration;
import java.time.LocalDateTime;

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
import com.tirmizee.utils.DateUtils;

import io.jsonwebtoken.Claims;

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
			
			Claims claims = jwtProvider.getClaim(token);
			
			LocalDateTime nowDate = LocalDateTime.now();
			LocalDateTime expiredDate = DateUtils.toLocalDateTime(claims.getExpiration());
			long renewDuration = Duration.ofMinutes(1).toMillis();
			long expiredDuration = Duration.between(nowDate, expiredDate).toMillis();
			LOGGER.info("renewDuration {}", renewDuration);
			LOGGER.info("expiredDuration {}", expiredDuration);
			
			boolean shouldRenewToken = expiredDuration < renewDuration;
			LOGGER.info("shouldRenewToken {}", shouldRenewToken);
			
			String renewToken = shouldRenewToken ? jwtProvider.refreshToken(claims) : token;
			response.setHeader("Token-Renew", renewToken);
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
