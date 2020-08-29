package com.tirmizee.config.filter;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http. MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tirmizee.api.authen.data.Credential;
import com.tirmizee.api.authen.data.Token;
import com.tirmizee.common.response.ResponseData;
import com.tirmizee.component.JWTProvider;
import com.tirmizee.config.security.UserDetailsImpl;
import com.tirmizee.constant.JWTConstants;
import com.tirmizee.exception.JWTAuthenticationException;
import com.tirmizee.service.JWTService;
import com.tirmizee.utils.CommonUtils;

import io.jsonwebtoken.Claims;

/**
 * @author Pratya Yeekhaday
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

	private ObjectMapper objectMapper;
	
	private JWTProvider jwtProvider;
	
	private JWTService jwtService;
	
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTProvider jwtProvider, JWTService jwtService, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.jwtProvider = jwtProvider;
		this.jwtService = jwtService;
	    this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try { 
		
			String header = request.getHeader(JWTConstants.HEADER_AUTHORIZATION);
			if (header == null) { throw new JWTAuthenticationException("Header invalid"); }
		
			Credential creds = getCredential(header);
			UsernamePasswordAuthenticationToken authenticationToken = 
				new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword());
			return authenticationManager.authenticate(authenticationToken);
			
		} catch (Exception exception) {
			SecurityContextHolder.clearContext();
			throw exception;
		}
		
	}
	
	private Credential getCredential(String header) {
		String usernameAndPassword[] = CommonUtils.decodeBase64ToString(header.substring(JWTConstants.BASIC_TYPE.length())).split(":");
		if (usernameAndPassword.length == 2) {
			return new Credential(usernameAndPassword[0], usernameAndPassword[1]);
		}
		throw new BadCredentialsException("Invalid Username Or Password");
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		try {
			
			UserDetailsImpl details = (UserDetailsImpl) authResult.getPrincipal();
			details.setIpAddress(request.getRemoteAddr());
			
			Token token = new Token(jwtProvider.generateToken(details));
			Claims claims = jwtProvider.getClaim(token.getToken());
			jwtService.addToken(details.getUsername(), claims.getId(), claims.getExpiration());
			
			response.addHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
			response.addHeader(JWTConstants.HEADER_AUTHORIZATION, JWTConstants.BEARER_TYPE + token);
			response.getOutputStream().print(objectMapper.writeValueAsString(ResponseData.success(token)));
		
		} catch (Exception exception) {
			LOGGER.info("{} : {}", exception.getClass().getName(), exception.getMessage());
			throw new ServletException(exception.getMessage());
		}
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		ResponseData<Object> responseData = new ResponseData<Object>();
		responseData.setStatus(false);
		
		if (failed instanceof DisabledException) {
			responseData.setMessageCode("E005");
			responseData.setMessageDesc(failed.getMessage());
		} else if (failed instanceof LockedException) {
			responseData.setMessageCode("E006");
			responseData.setMessageDesc(failed.getMessage());
		} else if (failed instanceof CredentialsExpiredException) {
			responseData.setMessageCode("E007");
			responseData.setMessageDesc(failed.getMessage());
		} else if (failed instanceof AuthenticationServiceException) {
			responseData.setMessageCode("E008");
			responseData.setMessageDesc(failed.getMessage());
		} else if (failed instanceof AccountExpiredException) {
			responseData.setMessageCode("E009");
			responseData.setMessageDesc(failed.getMessage());
		} else if (failed instanceof JWTAuthenticationException) {
			response.setStatus(401);
			responseData.setMessageCode("E009");
			responseData.setMessageDesc(failed.getMessage());
		} else {
			responseData.setMessageCode("E999");
			responseData.setMessageDesc(failed.getMessage());
		}

		response.addHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
		response.getOutputStream().print(objectMapper.writeValueAsString(responseData));
		
	}
    
}
