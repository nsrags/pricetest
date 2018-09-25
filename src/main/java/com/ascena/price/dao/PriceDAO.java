package com.ascena.price.dao;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.dao.datastore.domain.Price;
import com.ascena.price.dao.datastore.repository.DataRepository;
import com.ascena.price.exceptions.SysException;
import com.googlecode.objectify.ObjectifyService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
/**
 * Price DAO to get data from Google data store
 * 
 * @author SMeenavalli
 *
 * @param <T>
 */
public class PriceDAO<T> extends DataRepository<Price> {
	private static Logger logger = LoggerFactory.getLogger(PriceDAO.class);

	public PriceDAO() {
		super(Price.class);
	}

	/**
	 * getById 
	 * @param id
	 * @param priceStartDate
	 * @param priceEndDate
	 * @return
	 * @throws SysException
	 */
	@SuppressWarnings("unchecked")
	@HystrixCommand(fallbackMethod = "dataStorePriceGetByIdFallback", groupKey = "PriceDaoGroup", commandKey = "PriceDataStoreGetByIdCmd", threadPoolKey = "PricedataStoreGetByIdPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "10"), @HystrixProperty(name = "maxQueueSize", value = "101"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000")

	}, commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") })
	public T getByPrcForSkuId(final String id,Date priceStartDate,Date priceEndDate) throws SysException {
		List<Price> dbPrices = null;
		if (logger.isDebugEnabled()) {
			logger.debug(String.format(" Data store Input %s", id));
		}
		try {
			ObjectifyService.begin();
			dbPrices = ofy().load().type(Price.class).filter(PriceConstants.SKU_ID.toString(), id)
					.filter(PriceConstants.PRICE_START_DATE.toString() + " <=", priceStartDate)
					.list();
			
			List<Price> endDateConPrices = ofy().load().type(Price.class)
					.filter(
							PriceConstants.PRICE_END_DATE.toString() + " >=", priceEndDate)
					.filter(
							PriceConstants.PRICE_END_DATE.toString() + " =", null)
					.list();
			if(!CollectionUtils.isEmpty(endDateConPrices)) {
				dbPrices.retainAll(endDateConPrices);
			}
			if (!CollectionUtils.isEmpty(dbPrices) && dbPrices.get(0) != null) {
				return (T) dbPrices.get(0);
			}
		} catch (Exception ex) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format(
						"Exception occurred while getting data from Google Data Store for %s Details :%s  ", id, ex));
			}
			throw new SysException(ex.getMessage(), PriceConstants.DATA_STORE_SYS_EXP.toString());
		}

		return null;
	}
	
	/**
	 * dataStoreGetByIdFallback
	 * 
	 * @param id
	 *            String
	 * @param error
	 *            Throwable
	 * @return Price
	 */	
	public T dataStorePriceGetByIdFallback(final String id, Date priceStartDate,Date priceEndDate,final Throwable error) throws SysException {

		if (logger.isErrorEnabled()) {
			logger.error(String.format("Google Data store failures for getById %s priceStartDate%s priceEndDate%s : Error Details: %s", id,priceStartDate,priceEndDate,
					error.getMessage()));
		}
		throw new SysException(error.getMessage(), PriceConstants.DATA_STORE_SYS_EXP.toString());
	}

	/**
	 * 
	 * @param idsList
	 * @return
	 * @throws SysException
	 */
	@HystrixCommand(fallbackMethod = "dataStorePriceGetByIdListFallback", groupKey = "PriceDaoGroup", commandKey = "PriceDataStoreGetByIdListCmd", threadPoolKey = "PricedataStoreGetByIdPool", threadPoolProperties = {
			@HystrixProperty(name = "coreSize", value = "10"), @HystrixProperty(name = "maxQueueSize", value = "101"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "30000")

	}, commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "6000") })
	
	public List<Price> getById(final List<String> skuIds)  {

		List<Price> skuPrices = new ArrayList<>();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format(" Data store Input %s for Get By Id List", skuIds));
		}

		skuIds.forEach(skuid -> {

			try {
				Price price = (Price) this.getByPrcForSkuId(skuid,new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
				if (price != null) {
					skuPrices.add(price);
				}
			} catch (SysException se) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format(
							"Exception occurred while getting data from Google Data Store for %s Details :%s  ", skuid,
							se));
				}
			}

		});

		return skuPrices;

	}
	
	/**
	 * dataStoreGetByIdListFallback
	 * 
	 * @param skuPrices
	 *            Map<String, SkuPrice>
	 * @param skuIds
	 *            List<String>
	 * @param error
	 *            Throwable
	 * @return Price
	 */
	public List<Price> dataStorePriceGetByIdListFallback(final List<String> skuIds,
			final Throwable error) throws SysException {

		if (logger.isErrorEnabled()) {
			logger.error(String.format("Google Data store failures for getById  List %s : Error Details: %s", skuIds,
					error.getMessage()));
		}
		
		throw new SysException(error.getMessage(), PriceConstants.DATA_STORE_SYS_EXP.toString());
	}
}
