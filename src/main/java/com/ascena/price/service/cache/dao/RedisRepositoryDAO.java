package com.ascena.price.service.cache.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository

/**
 * Repository for Redis Cache
 * 
 * @author smeenavalli
 *
 */
public class RedisRepositoryDAO  {

	
	private RedisTemplate<String, String> redisTemplate;
	private ValueOperations<String, String> valueOperations;

	@Autowired
	public RedisRepositoryDAO(RedisTemplate<String, String> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		valueOperations = redisTemplate.opsForValue();
	}
	/**
	 * create
	 * @param key String
	 * @param value String
	 * @param ttl int
	 */
	public void create(String key, String value, int ttl) {
		valueOperations.set(key, value, ttl, TimeUnit.SECONDS);
		
	}
	/**
	 * find
	 * @param key String
	 * @return String
	 */
	public String find(String key) {
		return valueOperations.get(key);
	}
	
	/**
	 * findAll
	 * @param keys Collection<String>
	 * @return List<String>
	 */
	public List<String> findAll(Collection<String> keys) {
		return valueOperations.multiGet(keys);
	}
	
	/**
	 * update 
	 * @param key String
	 * @param value String
	 * @param ttl int
	 */
	public void update(String key, String value, int ttl) {
		valueOperations.set(key, value, ttl, TimeUnit.SECONDS);
	}
	
	/**
	 * delete
	 * @param key String
	 */
	public void delete(String key) {
		valueOperations.getOperations().delete(key);
	}
	
	/**
	 * create
	 * @param key String
	 * @param value String
	 */
	public void create(String key, String value) {
		valueOperations.set(key, value);

	}
	
	/**
	 * 	create
	 * @param entries Map<String,String>
	 */
	public void create(Map<String,String> entries) {
		valueOperations.multiSet(entries);

	}
	
	/**
	 * update 
	 * @param key String
	 * @param value String
	 */
	public void update(String key, String value) {
		valueOperations.set(key, value);
	}
	
	/**
	 * getValueOperations
	 * @return ValueOperations<String, String>
	 */
	public ValueOperations<String, String> getValueOperations() {
		return valueOperations;
	}
	
	/**
	 * setValueOperations
	 * @param valueOperations ValueOperations<String, String>
	 */
	public void setValueOperations(ValueOperations<String, String> valueOperations) {
		this.valueOperations = valueOperations;
	}
	
	

}
