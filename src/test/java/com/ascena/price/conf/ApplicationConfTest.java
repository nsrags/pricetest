package com.ascena.price.conf;

import java.time.LocalDateTime;

import org.junit.Test;
import org.meanbean.lang.Factory;
import org.meanbean.test.BeanTester;
import org.springframework.test.context.TestPropertySource;

import com.ascena.price.config.ApplicationConfig;

@TestPropertySource(properties = { "enableCachePrefix=false", "enableRedisPassword=false", "bulk.sku.allowedcount=4",
		"bulk.product.allowedcount=4", "redis.host=localhost", "redis.port=6379", "redis.database=0",
		"redis.password=redispassword", "redis.maxIdle=10", "redis.maxTotal=100", "redis.minIdle=10",
		"redis.connectTimeout=60", "http.requestTimeout=2000", "http.connectTimeout=2000", "http.maxTotal=100",
		"http.defaultMaxPerRoute=11", "http.expires.hours=2", "productinfo.service.url=http://localhost:8080",
		"productinfo.resourcePath=/productinfo/skulist/", "cloud.datastore.namespace=Price",
		"cloud.datastore.projectID=newagent-1cf00", "metrics.rollingStats.timeInMilliseconds=30000",
		"getById.thread.timeoutInMilliseconds=2000", "getById.ThreadPool.coreSize=10", "getById.maxQueueSize=101",
		"getByListId.thread.timeoutInMilliseconds=2000", "getByListId.ThreadPool.coreSize=10",
		"getByListId.maxQueueSize=101", "create.thread.timeoutInMilliseconds=2000", "create.ThreadPool.coreSize=10",
		"create.maxQueueSize=101", "delete.thread.timeoutInMilliseconds=2000", "delete.ThreadPool.coreSize=10",
		"delete.maxQueueSize=101" })

public class ApplicationConfTest {

	@Test
	public void getterAndSetterCorrectness() throws Exception {
		final BeanTester beanTester = new BeanTester();
		beanTester.getFactoryCollection().addFactory(LocalDateTime.class, new LocalDateTimeFactory());
		beanTester.testBean(ApplicationConfig.class);
	}

	/**
	 * Concrete Factory that creates a LocalDateTime.
	 */
	class LocalDateTimeFactory implements Factory<Object> {

		@Override
		public LocalDateTime create() {
			return LocalDateTime.now();
		}

	}

}