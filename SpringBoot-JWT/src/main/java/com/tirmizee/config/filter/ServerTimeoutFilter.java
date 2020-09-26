package com.tirmizee.config.filter;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.gson.Gson;
import com.tirmizee.utils.DateUtils;

import lombok.Data;

@Component
public class ServerTimeoutFilter extends OncePerRequestFilter {
	
	public static final Logger LOG = LoggerFactory.getLogger(ServerTimeoutFilter.class);
	
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
			String nowDateStr = DateUtils.FORMAT_DDMMYYYY_HHMM.format(nowDate).substring(0, 8);
			Date startDate = DateUtils.FORMAT_DDMMYYYY_HHMM.parse(nowDateStr.concat(" ").concat(startTime));
			Date endDate = DateUtils.FORMAT_DDMMYYYY_HHMM.parse(nowDateStr.concat(" ").concat(endTime));

			if (DateUtils.between(nowDate, startDate, endDate)) {
				filterChain.doFilter(request, response);
			
			} else {
				ServerTimeOut serverTimeOut = new ServerTimeOut(startDate, endDate);
				response.addHeader(CONTENT_TYPE, APPLICATION_JSON_UTF8_VALUE);
				response.getOutputStream().print(gsonMapper.toJson(serverTimeOut));
				response.getOutputStream().flush();
			}
			
		} catch (Exception exception) {}
		
	}
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return super.shouldNotFilter(request);
	}

	@Data
	private class ServerTimeOut {
		
		private String message = "ER999";
		private String messageDescription = "ขออภัย ขณะนี้อยู่ในช่วงปิดระบบ ระบบจะเปิดทำการในช่วงเวลา ";
		
		public ServerTimeOut(Date start, Date end) {
			this.messageDescription = new StringBuilder(this.messageDescription)
				.append(DateUtils.FORMAT_DDMMYYYY_HHMM.format(start))
				.append(" - ")
				.append(DateUtils.FORMAT_DDMMYYYY_HHMM.format(end))
				.toString();
		}
		
	}

}
