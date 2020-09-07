package com.tirmizee.config.filter;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;

import lombok.Data;

@Component
public class ServerTimeoutFilter extends OncePerRequestFilter {
	
	public static final Logger LOG = LoggerFactory.getLogger(ServerTimeoutFilter.class);
	
	private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance("ddMMyyyy HH:mm", new Locale("TH", "th"));
	
	@Value("${app.timeout.start}")
	private String startTime;
	
	@Value("${app.timeout.end}")
	private String endTime;
	
	@Autowired
	private Gson gsonMapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("ServerTimeoutFilter Process");

		try {
			
			Date nowDate = new Date();
			String nowDateStr = FAST_DATE_FORMAT.format(nowDate).substring(0, 8);
			Date startDate = FAST_DATE_FORMAT.parse(nowDateStr.concat(" ").concat(startTime));
			Date endDate = FAST_DATE_FORMAT.parse(nowDateStr.concat(" ").concat(endTime));
			
			LOG.info("nowDate : {}", nowDate);
			LOG.info("startDate : {}", startDate);
			LOG.info("endDate : {}", endDate);
			
			if (nowDate.compareTo(startDate) >= 0 && nowDate.compareTo(endDate) <= 0) {
				filterChain.doFilter(request, response);

			} else {
				ServerTimeOut serverTimeOut = new ServerTimeOut(startDate, endDate);
				response.addHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
				response.getOutputStream().print(gsonMapper.toJson(serverTimeOut));
				response.getOutputStream().flush();
			}
			
		} catch (Exception exception) {}
		
	}
	
	@Data
	private class ServerTimeOut {
		
		private String message = "ER999";
		private String messageDescription = "ขออภัย ขณะนี้อยู่ในช่วงปิดระบบ ระบบจะเปิดทำการในช่วงเวลา ";
		
		public ServerTimeOut(Date start, Date end) {
			this.messageDescription = new StringBuilder(this.messageDescription)
				.append(FAST_DATE_FORMAT.format(start))
				.append(" - ")
				.append(FAST_DATE_FORMAT.format(end))
				.toString();
		}
		
	}

}
