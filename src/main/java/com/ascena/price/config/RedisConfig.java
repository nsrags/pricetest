package com.ascena.price.config;

import java.time.Duration;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RefreshScope
public class RedisConfig {

	@Autowired
	ApplicationConfig appConfig;
	
	/**
	 * jedis Connection Factory
	 * @return JedisConnectionFactory
	 */
	@Bean
	@RefreshScope
	JedisConnectionFactory jedisConnectionFactory() {
		//Redis instance connection properties
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(appConfig.getRedisHost());//host
		redisStandaloneConfiguration.setPort(Integer.valueOf(appConfig.getRedisPort())); //port
		redisStandaloneConfiguration.setDatabase(Integer.valueOf(appConfig.getRedisDatabase()));
		if (Boolean.valueOf(appConfig.getIsRedisPasswordEnabled())) {
			redisStandaloneConfiguration.setPassword(RedisPassword.of(appConfig.getRedisPassword()));//password
		}
		//Connection pool configurations
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(Integer.valueOf(appConfig.getRedisMaxIdle()));
		poolConfig.setMaxTotal(Integer.valueOf(appConfig.getRedisMinIdle()));
		poolConfig.setMinIdle(Integer.valueOf(appConfig.getRedisMinIdle()));

		JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
		JedisClientConfiguration clientConfiguration = jedisClientConfiguration.usePooling().build();
		// connection timeout
		jedisClientConfiguration
				.connectTimeout(Duration.ofSeconds(Integer.valueOf(appConfig.getRedisConnectTimeout())));
		return new JedisConnectionFactory(redisStandaloneConfiguration, clientConfiguration);

	}
	
	/**
	 * redis Template with Serializers
	 * 
	 * @return RedisTemplate<String, String>
	 */
	@Bean
	@RefreshScope
	public RedisTemplate<String, String> redisTemplate() {
		final RedisTemplate<String, String> template = new RedisTemplate<>();
		//set Serializer to RedisTemplate
		template.setConnectionFactory(jedisConnectionFactory());
		template.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

		return template;
	}
	
	/**
	 * getAppConfig
	 * @return ApplicationConfig
	 */
	public ApplicationConfig getAppConfig() {
		return appConfig;
	}
	
	/**
	 * setAppConfig 
	 * @param appConfig ApplicationConfig
	 */
	public void setAppConfig(ApplicationConfig appConfig) {
		this.appConfig = appConfig;
	}
	
	
}
