package com.tirmizee.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
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
import com.tirmizee.config.filter.JWTHeaderFilter;
import com.tirmizee.config.filter.RequestLoggingFilter;
import com.tirmizee.config.filter.ServerTimeoutFilter;
import com.tirmizee.config.security.AuthenticationEntryPointImpl;
import com.tirmizee.config.security.UserDetailsAuthenticationProvider;
import com.tirmizee.service.JWTService;

/**
 * @author Pratya Yeekhaday
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JWTProvider jwtProvider;
	
	@Autowired
	private JWTService jwtService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTHeaderFilter jwtHeaderFilter;
	
	@Autowired
	private ServerTimeoutFilter serverTimeoutFilter;
	
	@Autowired
	private RequestLoggingFilter requestLoggingFilter;

	@Autowired
	private AuthenticationEntryPointImpl authenticationEntryPointImpl;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
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

	private AuthenticationProvider authenticationProvider() {
		UserDetailsAuthenticationProvider userDetailsAuthenticationProvider = new UserDetailsAuthenticationProvider();
		userDetailsAuthenticationProvider.setHideUserNotFoundExceptions(false);
		userDetailsAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		userDetailsAuthenticationProvider.setUserDetailsService(userDetailsService);
		return userDetailsAuthenticationProvider;
	}
	
	private JWTAuthenticationFilter jtwAuthenticationFilter() throws Exception {
		JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(
			authenticationManagerBean(), jwtProvider, jwtService, objectMapper);
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
				.authenticationEntryPoint(authenticationEntryPointImpl)
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.addFilter(jtwAuthenticationFilter())
			.addFilterAfter(jtwAuthorizationFilter(), JWTAuthenticationFilter.class)
			.addFilterBefore(jwtHeaderFilter, JWTAuthenticationFilter.class)
			.addFilterBefore(serverTimeoutFilter, JWTHeaderFilter.class)
			.addFilterBefore(requestLoggingFilter, ServerTimeoutFilter.class);
	}
	
}
