package com.ascena.price.app;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.ascena.price" })
@EnableHystrixDashboard
@EnableCircuitBreaker
//@EnableConfigServer

/**
 * Spring Boot application for Price Service
 * 
 * @author smeenavalli
 *
 */
public class PriceApplication {

	private static Logger logger = LoggerFactory.getLogger(PriceApplication.class);

	public static void main(String[] args) {
		if (logger.isInfoEnabled()) {
			Instant currentDate = Instant.ofEpochMilli(System.currentTimeMillis());
			logger.info(String.format("Starting Price Micro Service Application at %s ", currentDate));
		}
		SpringApplication.run(PriceApplication.class, args);
		Instant endTime = Instant.ofEpochMilli(System.currentTimeMillis());
		if (logger.isInfoEnabled()) {
			logger.info(String.format(" Price Application Strated at %s ", endTime));
		}
	}
}
