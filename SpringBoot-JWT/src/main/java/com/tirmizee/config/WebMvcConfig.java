package com.tirmizee.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.tirmizee.component.VarargMessageSource;
import com.tirmizee.component.VarargMessageSourceImpl;
import com.tirmizee.config.inceptor.JWTRenewInceptor;
import com.tirmizee.config.inceptor.LocaleHeaderChangeInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private JWTRenewInceptor jwtRenewInceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(new LocaleHeaderChangeInterceptor())
			.addPathPatterns("/**")
			.order(1);
		
		registry.addInterceptor(jwtRenewInceptor)
			.addPathPatterns("/**")
			.order(2);
	
	}
	
	@Bean(name = "localeResolver")
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
	    cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
	    return cookieLocaleResolver;
	}
	
	@Bean(name = "messageSource")
	public VarargMessageSource varargMessageSource() {
		VarargMessageSourceImpl varargMessageSource = new VarargMessageSourceImpl();
		varargMessageSource.setBasenames("classpath:i18n/messages/messages");
		varargMessageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
		varargMessageSource.setUseCodeAsDefaultMessage(true);
		return varargMessageSource;
	}

}
