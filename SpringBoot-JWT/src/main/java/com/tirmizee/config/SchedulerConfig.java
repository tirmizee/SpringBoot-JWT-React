package com.tirmizee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.tirmizee.config.scheduler.RedisScheduler;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	
	@Bean
	public RedisScheduler serverTimeScheduler() {
		return new RedisScheduler();
	}

}
