package com.tirmizee.config.inceptor;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * @author Pratya Yeekhaday
 *
 */
public class LocaleHeaderChangeInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LocaleHeaderChangeInterceptor.class);
	
	public static final String DEFAULT_HEADER_NAME = "locale";
	
	private String headerName = DEFAULT_HEADER_NAME;
	
	private boolean ignoreInvalidLocale = false;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("LocaleHeaderChangeInterceptor PreHandle");

		String newLocale = request.getHeader(this.headerName);
		
		if (newLocale != null) {
			
			LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
			
			if (localeResolver == null) {
				throw new IllegalStateException("No LocaleResolver found: not in a DispatcherServlet request?");
			}
			
			try {
				localeResolver.setLocale(request, response, parseLocaleValue(newLocale));
			} catch (Exception ex) {
				if (isIgnoreInvalidLocale()) {
					LOGGER.debug("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
				} else throw ex;
			}
			
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("LocaleHeaderChangeInterceptor PostHandle : {}", LocaleContextHolder.getLocale());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		LOGGER.info("LocaleHeaderChangeInterceptor AfterCompletion");
	}
	
	protected boolean isIgnoreInvalidLocale() {
		return this.ignoreInvalidLocale;
	}
	
	protected Locale parseLocaleValue(String locale) {
		return StringUtils.parseLocaleString(locale);
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public void setIgnoreInvalidLocale(boolean ignoreInvalidLocale) {
		this.ignoreInvalidLocale = ignoreInvalidLocale;
	}
	
}
