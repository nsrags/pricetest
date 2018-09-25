package com.ascena.price.service.cache.dao;

import java.io.IOException;
import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.After;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.ascena.price.service.cache.dao.RedisRepositoryDAO;

import ai.grakn.redismock.RedisServer;

public abstract class MockRedisRepositoryDAO {

	private RedisServer server;
	protected RedisRepositoryDAO redisRepository;
	private static Logger logger = LoggerFactory.getLogger(MockRedisRepositoryDAO.class);

	@Before
	public void init() {
		
		try {

			server = RedisServer.newRedisServer(6379);// Redis port
			// Start redis mock server
			server.start();
			if (logger.isInfoEnabled()) {
				logger.info(String.format("Redis-mock server running on Host %s : Port %d", server.getHost(),
						server.getBindPort()));
			}
			RedisTemplate<String, String> template = new RedisTemplate<>();
			// set Serializer to RedisTemplate
			template.setConnectionFactory(jedisConnectionFactory(server.getHost(), server.getBindPort()));
			template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
			template.setKeySerializer(new StringRedisSerializer());
			template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
			template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
			redisRepository = new RedisRepositoryDAO(template);
			redisRepository.setValueOperations(template.opsForValue());
			template.afterPropertiesSet();

		} catch (IOException ioe) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format(" Exception occurred while running redis mock test cases Details: %s", ioe));
			}
		}

	}

	/**
	 * jedis Connection Factory
	 * 
	 * @param host
	 * @param port
	 * @return JedisConnectionFactory
	 */
	private JedisConnectionFactory jedisConnectionFactory(String host, int port) {
		
		// Redis instance connection properties
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(host);// host
		redisStandaloneConfiguration.setPort(port); // port
		redisStandaloneConfiguration.setDatabase(0);

		// Connection pool configurations
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxTotal(100);
		poolConfig.setMinIdle(10);

		JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		JedisClientConfiguration clientConfiguration = jedisClientConfiguration.usePooling().build();
		// connection timeout
		jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));
		return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);

	}

	@After
	public void destroy() {
		
		if (logger.isInfoEnabled()) {
			//logger.info(String.format("Redis-mock server stopped on Host %s : Port %d", server.getHost(),
				//	server.getBindPort()));
			logger.info(String.format("Redis-mock server stopped "));

		}
		if(server!= null) {
			server.stop();	
		}
		
	}

}
