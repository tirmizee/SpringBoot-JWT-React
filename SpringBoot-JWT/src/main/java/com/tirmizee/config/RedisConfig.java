package com.tirmizee.config;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
public class RedisConfig {
	
	@Value("${spring.redis.host}")
	private String REDIS_HOSTNAME;
	
	@Value("${spring.redis.password}")
	private String REDIS_PASSWORD;
	
	@Value("${spring.redis.port}")
	private int REDIS_PORT;
	
	public GenericObjectPoolConfig poolConfig() {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxTotal(10);
		poolConfig.setMinIdle(5);
		poolConfig.setMaxIdle(10);
		return poolConfig;
	}
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME);
		configuration.setPassword(RedisPassword.of(REDIS_PASSWORD));
		configuration.setPort(REDIS_PORT);
		
		JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
			.connectTimeout(Duration.ofSeconds(3))
			.readTimeout(Duration.ofSeconds(3))
			.usePooling() //.poolConfig(poolConfig())
			.build();
		
        return  new JedisConnectionFactory(configuration, jedisClientConfiguration);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}

}
