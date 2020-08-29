package com.tirmizee.config.scheduler;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.tirmizee.service.JWTService;

public class RedisScheduler {

	public static final Logger LOGGER = LoggerFactory.getLogger(RedisScheduler.class);
	
	@Autowired
	private JWTService jwtService;
	
	@Scheduled(fixedRate = 5000, initialDelay = 1000)
	public void processDeleteExpiredKey() {
		LOGGER.info("Redis process delete expired key");

		Map<String, Long> entries = jwtService.allBlackList();
		entries.forEach((key, value) -> {
			
			Date nowDate = new Date();
			Date expiredDate = new Date(value);
			
			if (expiredDate.before(nowDate)) {
				jwtService.deleteBlackList(key);
				LOGGER.info("Redis delete jwt expired {} : {} ", key, expiredDate);
			}
			
		});
	}
	
}
