package com.ascena.price.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.json.simple.JSONValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.service.cache.dao.RedisRepositoryDAO;
import com.ascena.price.service.cache.vo.PriceCacheVo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ai.grakn.redismock.RedisServer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)

/**
 * Test class for Redis Repository
 * 
 * @author Smeenavalli
 *
 */
public class PriceRedisRepositoryTest {

	private RedisServer server;
	private RedisRepositoryDAO redisRepository;
	private static Logger logger = LoggerFactory.getLogger(PriceRedisRepositoryTest.class);

	@Autowired
	ObjectMapper objectMapper;

	@Before
	public void init() {

		try {

			server = RedisServer.newRedisServer(16379);// redis port
			// Start redis mock server
			server.start();

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

		if (logger.isInfoEnabled()) {
			logger.info(String.format("Redis-mock running on Port %d", port));
		}
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

	@Test
	public void testCreateAndFind() throws JsonProcessingException {
		PriceCacheVo priceCache = new PriceCacheVo();
		priceCache.setCacheCreatedBy("Batch Admin");
		priceCache.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache.setSkuId("30064566");
		priceCache.setSalePrice("10.40");
		priceCache.setListPrice("14.90");
		priceCache.setMsrp("14.90");
		priceCache.setCurrency("$");
		priceCache.setGwpEligible("false");
		priceCache.setIsOnClerance("false");
		priceCache.setPomoId("Prom1232");
		priceCache.setPomoPrice("10.40");
		priceCache.setPriceId("10001");
		priceCache.setPricePoint("");
		priceCache.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		
		priceCache.setPromoMessage("Limited time 30% off!");
		priceCache.setCacheUpdatedBy("Price Service");
		priceCache.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");

		String priceStr = objectMapper.writeValueAsString(priceCache);
		redisRepository.create(priceCache.getSkuId(), JSONValue.escape(priceStr));
		String cacheKey = priceCache.getSkuId();
		String cacheStr = redisRepository.find(priceCache.getSkuId());
		assertNotNull("No cache found for Key : " + cacheKey, cacheStr);

	}

	@Test
	public void testCreatewithTTL() throws JsonProcessingException {
		PriceCacheVo priceCache = new PriceCacheVo();
		priceCache.setCacheCreatedBy("Batch Admin");
		priceCache.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache.setSkuId("30064566");
		priceCache.setSalePrice("10.40");
		priceCache.setListPrice("14.90");
		priceCache.setMsrp("14.90");
		priceCache.setCurrency("$");
		priceCache.setGwpEligible("false");
		priceCache.setIsOnClerance("false");
		priceCache.setPomoId("Prom1232");
		priceCache.setPomoPrice("10.40");
		priceCache.setPriceId("10001");
		priceCache.setPricePoint("");
		priceCache.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		priceCache.setPromoMessage("Limited time 30% off!");
		priceCache.setCacheUpdatedBy("Price Service");
		priceCache.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");
		String priceStr = objectMapper.writeValueAsString(priceCache);
		redisRepository.create(priceCache.getSkuId(), JSONValue.escape(priceStr), 60);
		String cacheKey = priceCache.getSkuId();
		String cacheStr = redisRepository.find(priceCache.getSkuId());
		assertNotNull("No cache found for Key : " + cacheKey, cacheStr);

	}

	@Test
	public void testFindAll() throws JsonProcessingException {
		List<PriceCacheVo> cacheList = new ArrayList<>();
		PriceCacheVo priceCache1 = new PriceCacheVo();
		priceCache1.setCacheCreatedBy("Batch Admin");
		priceCache1.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache1.setSkuId("30064566");
		priceCache1.setSalePrice("10.40");
		priceCache1.setListPrice("14.90");
		priceCache1.setMsrp("14.90");
		priceCache1.setCurrency("$");
		priceCache1.setGwpEligible("false");
		priceCache1.setIsOnClerance("false");
		priceCache1.setPomoId("Prom1232");
		priceCache1.setPomoPrice("10.40");
		priceCache1.setPriceId("10001");
		priceCache1.setPricePoint("");
		priceCache1.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache1.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
  	    
		priceCache1.setPromoMessage("Limited time 30% off!");
		priceCache1.setCacheUpdatedBy("Price Service");
		priceCache1.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");
		cacheList.add(priceCache1);

		PriceCacheVo priceCache2 = new PriceCacheVo();
		priceCache2.setSkuId("30064111");
		priceCache2.setSalePrice("30.00");
		priceCache2.setListPrice("30.00");
		priceCache2.setMsrp("30.00");
		priceCache2.setCurrency("$");
		priceCache2.setGwpEligible("false");
		priceCache2.setIsOnClerance("false");
		priceCache2.setPriceId("10002");
		priceCache2.setPricePoint("");
		priceCache2.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache2.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
  	    
		cacheList.add(priceCache2);
		Map<String, String> cacheEntriesMap = new HashMap<>();
		List<String> keysList = new ArrayList<>();
		cacheList.forEach(cacheVo -> {
			try {
				String priceStr = objectMapper.writeValueAsString(cacheVo);
				cacheEntriesMap.put(cacheVo.getSkuId(), JSONValue.escape(priceStr));
				keysList.add(cacheVo.getSkuId());
			} catch (JsonProcessingException e) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format("Server Failures to create Cache %s ", cacheVo));
				}
			}
		});
		redisRepository.create(cacheEntriesMap);

		List<String> cacheEntries = redisRepository.findAll(keysList);
		assertNotNull("No cache found for Key : " + keysList, cacheEntries);
		assertTrue("Test case failed due to input provided :" + keysList, keysList.size() > 0);

	}

	@Test
	public void testUpdate() throws JsonProcessingException {
		redisRepository.create("30064566", "cacheCreated");
		String cacheStr = redisRepository.find("30064566");
		assertNotNull("No cache found for Key : " + cacheStr);
		assertTrue("No cache found for Key : ", cacheStr.contains("cacheCreated"));
		redisRepository.update("30064566", "cacheUpdated");
		cacheStr = redisRepository.find("30064566");
		assertFalse("No cache found for Key : ", cacheStr.contains("cacheCreated"));
		assertTrue("No cache found for Key : ", cacheStr.contains("cacheUpdated"));

	}

	@Test
	public void testDelete() throws JsonProcessingException {
		PriceCacheVo priceCache = new PriceCacheVo();
		priceCache.setCacheCreatedBy("Batch Admin");
		priceCache.setCacheCreatedOn("2018-06-20T16:35:40.102Z");
		priceCache.setSkuId("30064566");
		priceCache.setSalePrice("10.40");
		priceCache.setListPrice("14.90");
		priceCache.setMsrp("14.90");
		priceCache.setCurrency("$");
		priceCache.setGwpEligible("false");
		priceCache.setIsOnClerance("false");
		priceCache.setPomoId("Prom1232");
		priceCache.setPomoPrice("10.40");
		priceCache.setPriceId("10001");
		priceCache.setPricePoint("");
		priceCache.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		priceCache.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
  	    
		priceCache.setPromoMessage("Limited time 30% off!");
		priceCache.setCacheUpdatedBy("Price Service");
		priceCache.setCacheUpdatedOn("2018-06-20T16:35:40.102Z");
		String priceStr = objectMapper.writeValueAsString(priceCache);
		redisRepository.create(priceCache.getSkuId(), JSONValue.escape(priceStr));
		String cacheKey = priceCache.getSkuId();
		String cacheStr = redisRepository.find(cacheKey);
		assertNotNull("No cache found for Key : " + cacheKey, cacheStr);
		redisRepository.delete(priceCache.getSkuId());
		String noCacheFnd = redisRepository.find(priceCache.getSkuId());
		assertNull("No cache found for Key : " + cacheKey, noCacheFnd);

	}

	@After
	public void destroy() {
		server.stop();
	}

}
