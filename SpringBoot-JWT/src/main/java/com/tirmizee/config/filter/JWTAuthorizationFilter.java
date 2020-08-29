package com.tirmizee.config.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tirmizee.component.JWTProvider;
import com.tirmizee.config.security.UserDetailsImpl;
import com.tirmizee.constant.JWTConstants;
import com.tirmizee.exception.JWTAuthenticationException;
import com.tirmizee.exception.JWTExpiredException;
import com.tirmizee.exception.JWTMalformedException;
import com.tirmizee.exception.JWTSignatureException;
import com.tirmizee.exception.JWTUnsupportedException;
import com.tirmizee.exception.MessageError;
import com.tirmizee.service.JWTService;
import com.tirmizee.utils.GrantedAuthorityUtils;

import io.jsonwebtoken.Claims;

/**
 * @author Pratya Yeekhaday
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	private ObjectMapper objectMapper;
	private JWTProvider jwtProvider;
	private JWTService jwtService;
	 
	public JWTAuthorizationFilter(JWTProvider jwtProvider, JWTService jwtService, ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
		this.jwtProvider = jwtProvider;
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		LOGGER.info("Init JWTAuthorizationFilter ");
		try {
			 
			 String header = request.getHeader(JWTConstants.HEADER_AUTHORIZATION);
			 if (header == null || !header.startsWith(JWTConstants.BEARER_TYPE)) {
	            chain.doFilter(request, response);
			 }
			 
			 String accessIP = request.getRemoteAddr();
			 String token = header.replace(JWTConstants.BEARER_TYPE, "");
			 UserDetailsImpl principal = getPrincipal(token);
			 
			 boolean isAccessIpEqualLoginIp = accessIP.equals(principal.getIp());
			 if (principal != null && isAccessIpEqualLoginIp) {
				 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
				 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			 }
			 
			 request.setAttribute(JWTConstants.TOKEN_ATTRIBUTE, token);
			 chain.doFilter(request, response);
		     
		 } catch (Exception exception) {
			 LOGGER.info(exception.getMessage());
			 onUnsuccessfulAuthentication(request, response, exception);
		 }
		 
	}
	
	private UserDetailsImpl getPrincipal(String token) {
		
		Claims claims = jwtProvider.getClaim(token);
		
		if (claims != null) {
    		
			jwtService.validateToken(claims);
			
    		@SuppressWarnings("unchecked")
			List<String> authoritiesString = (List<String>) claims.get("authorities");
			List<GrantedAuthority> authorities = GrantedAuthorityUtils.createAuthorityList(authoritiesString);
			
			UserDetailsImpl principal = new UserDetailsImpl(claims.getSubject(), null, authorities);
			principal.setJti(claims.getId());
			principal.setExp(claims.getExpiration().getTime());
			principal.setIat(claims.getIssuedAt().getTime());
			principal.setIp((String) claims.get("ip"));
            return principal;
        }
		
		return null;
	}

	protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			Exception failed) throws IOException {
		LOGGER.info("{} : {} ", failed.getClass().getName(), failed.getMessage());
		SecurityContextHolder.clearContext();
		
		MessageError messageError = new MessageError();
		
		if (failed instanceof JWTExpiredException) {
			messageError.setErrorCode("E001");
			messageError.setErrorDesc(failed.getMessage());
		} else if (failed instanceof JWTSignatureException) {
			messageError.setErrorCode("E002");
			messageError.setErrorDesc(failed.getMessage());
		} else if (failed instanceof JWTUnsupportedException) {
			messageError.setErrorCode("E003");
			messageError.setErrorDesc(failed.getMessage());
		} else if (failed instanceof JWTMalformedException) {
			messageError.setErrorCode("E004");
			messageError.setErrorDesc(failed.getMessage());
		} else if (failed instanceof JWTAuthenticationException) {
			response.setStatus(401);
			messageError.setErrorCode("E009");
			messageError.setErrorDesc(failed.getMessage());
		}  else {
			response.setStatus(401);
			messageError.setErrorCode("E999");
			messageError.setErrorDesc(failed.getMessage());
		}
		
		response.addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
		response.getOutputStream().print(objectMapper.writeValueAsString(messageError));	
		response.getOutputStream().flush();
		
	}

}
