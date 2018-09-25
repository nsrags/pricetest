package com.ascena.price.service.integration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ApplicationConfig;
import com.ascena.price.exceptions.PriceIntegrationException;
import com.ascena.price.service.integration.vo.ActiveSkuList;
import com.ascena.price.service.integration.vo.ProductSkuList;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
/**
 * Service gives active sku list for a product
 * @author smeenavalli
 *
 */
public class PrcProductInfoIntg {

	private static Logger logger = LoggerFactory.getLogger(PrcProductInfoIntg.class);

	@Autowired
	ApplicationConfig appConfig;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * getActiveSkus
	 * 
	 * @param productId
	 * @return
	 * @throws PriceIntegrationException
	 */
	@HystrixCommand(fallbackMethod = "prcPrdInfoConsumerFallback", groupKey = "PrcPrdInfoServiceGroup", commandKey = "PrcPrdInfoConsumerCmd", threadPoolKey = "PrcPrdInfoConsumerThreadPool", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "10"),
					@HystrixProperty(name = "maxQueueSize", value = "101"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000") })
	public List<String> getActiveSkus(final String productId) throws PriceIntegrationException {
		List<String> skuList = new ArrayList<>();

		ProductSkuList productSkus = null;
		List<ActiveSkuList> activeSkus = null;
		// Call Product Info service to get Active SKU's
		StringBuilder url = new StringBuilder(appConfig.getProductInfoServiceUrl());
		url.append(appConfig.getPrdInfoResourcePath());
		url.append(productId);
		if (logger.isDebugEnabled()) {
			logger.debug(String.format(" Product Info service by Active SKU List :  %s", url));
		}
		productSkus = restTemplate.getForObject(url.toString(), ProductSkuList.class);
		if (productSkus == null) {
			return skuList;
		}
		// Active Sku's List for a Product
		activeSkus = productSkus.getActiveSkuList();
		if (!CollectionUtils.isEmpty(activeSkus)) {
			activeSkus.forEach(sku -> skuList.add(sku.getSkuId()));
		}
		return skuList;
	}

	/**
	 * prcPrdInfoConsumerFallback
	 * 
	 * @param productId
	 *            String
	 * @param error
	 *            Throwable
	 * @return List<String>
	 * @throws PriceIntegrationException
	 */
	@HystrixCommand(groupKey = "PrcPrdInfoServiceGroup", ignoreExceptions = {
			PriceIntegrationException.class }, raiseHystrixExceptions = { HystrixException.RUNTIME_EXCEPTION })

	public List<String> prcPrdInfoConsumerFallback(final String productId, final Throwable error)
			throws PriceIntegrationException {
		if (logger.isErrorEnabled()) {
			logger.error(String.format(
					"Server Failures for Calling Product Info service  - Product Id  %s Error Details: %s", productId,
					error.getMessage()));
		}
		StringBuilder errorMessage = new StringBuilder();
		errorMessage.append("Server Failures for Calling Product Info service ");
		errorMessage.append(error.getMessage());
		throw new PriceIntegrationException(errorMessage.toString(),
				PriceConstants.PROD_INFO_SERVICE_SYS_EXP.toString());

	}
}
