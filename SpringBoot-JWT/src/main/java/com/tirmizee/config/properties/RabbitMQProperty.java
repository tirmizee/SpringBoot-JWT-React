package com.tirmizee.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties("rabbitmq")
public class RabbitMQProperty {
	
	private String queueName;
	private String exchangeName;
	private String routingKey;

}
