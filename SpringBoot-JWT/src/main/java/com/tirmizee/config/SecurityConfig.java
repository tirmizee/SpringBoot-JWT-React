package com.tirmizee.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tirmizee.component.JWTProvider;
import com.tirmizee.config.filter.JWTAuthenticationFilter;
import com.tirmizee.config.filter.JWTAuthorizationFilter;
import com.tirmizee.config.filter.HeaderFilter;
import com.tirmizee.config.filter.RequestLoggingFilter;
import com.tirmizee.config.filter.ServerTimeoutFilter;
import com.tirmizee.config.security.JWTAccessDeniedHandler;
import com.tirmizee.config.security.JWTAuthenticationEntryPoint;
import com.tirmizee.config.security.JWTAuthenticationProvider;
import com.tirmizee.service.JWTService;

/**
 * @author Pratya Yeekhaday
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	prePostEnabled = true,
	jsr250Enabled = true, 
	securedEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTProvider jwtProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private HeaderFilter jwtHeaderFilter;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private ServerTimeoutFilter serverTimeoutFilter;
	
	@Autowired
	private RequestLoggingFilter requestLoggingFilter;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
	@Autowired
	private JWTAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private JWTAuthenticationEntryPoint authenticationEntryPointImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.authenticationProvider(authenticationProvider())
        	.authenticationEventPublisher(authenticationEventPublisher());
    }

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/**","/swagger**/**","/webjars/**");
	}
	
	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/*
	 * https://github.com/spring-projects/spring-security/pull/7802
	 */
	private DefaultAuthenticationEventPublisher authenticationEventPublisher() {
        return new DefaultAuthenticationEventPublisher(applicationEventPublisher);
    }
	
	private AuthenticationProvider authenticationProvider() {
		JWTAuthenticationProvider jwtAuthenticationProvider = new JWTAuthenticationProvider();
		jwtAuthenticationProvider.setHideUserNotFoundExceptions(false);
		jwtAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		jwtAuthenticationProvider.setUserDetailsService(userDetailsService);
		return jwtAuthenticationProvider;
	}
	
	private JWTAuthenticationFilter jtwAuthenticationFilter() throws Exception {
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter.JWTAuthenticationFilterBuilder()
			.authenticationManager(authenticationManagerBean())
			.jwtProvider(jwtProvider)
			.jwtService(jwtService)
			.mapper(objectMapper)
			.build();
		authenticationFilter.setFilterProcessesUrl("/auth/token");
		return authenticationFilter;
	}
	
	private JWTAuthorizationFilter jtwAuthorizationFilter() throws Exception {
		return new JWTAuthorizationFilter(jwtProvider, jwtService, objectMapper);
	}

	@Bean
    public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setExposedHeaders(Arrays.asList("Token-Renew"));
        
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.csrf()
				.disable()
			.cors()
				.configurationSource(corsConfigurationSource())
				.and()
//				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.authorizeRequests()
				.antMatchers("/auth/token", "/event/**")
				.permitAll()
				.anyRequest().authenticated()
				.and()
			.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler)
				.authenticationEntryPoint(authenticationEntryPointImpl)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.addFilter(jtwAuthenticationFilter())
			.addFilterAfter(jtwAuthorizationFilter(), JWTAuthenticationFilter.class)
			.addFilterBefore(jwtHeaderFilter, JWTAuthenticationFilter.class)
			.addFilterBefore(serverTimeoutFilter, HeaderFilter.class)
			.addFilterBefore(requestLoggingFilter, ServerTimeoutFilter.class);
	}
	
}
