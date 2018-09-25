package com.ascena.price.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ApplicationConfig;


/**
 * Common utility for Price Service
 * 
 * @author SMeenavalli
 *
 */
@Component
public class PriceUtils {

	private static Logger logger = LoggerFactory.getLogger(PriceUtils.class);
	
	@Autowired
	ApplicationConfig appConfig;

	/**
	 * creates Cache Key based on a flag
	 * 
	 * @param skuId String
	 * @return String
	 */
	public String createCacheKey(final String skuId) {
		Boolean enableCachePrefix = Boolean.valueOf(appConfig.getIsCachePrefixEnabled());
		StringBuilder cacheKeyPrefix = new StringBuilder(PriceConstants.PRC_CACHE_PREFIX.toString());
		if (enableCachePrefix) {
			cacheKeyPrefix.append(skuId);
			return cacheKeyPrefix.toString();
		} else {
			return skuId;
		}
	}

	/**
	 * Setting the common response headers
	 * 
	 * @return HttpHeaders
	 */
	public HttpHeaders setCommonResponseHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();

		responseHeaders.setConnection(PriceConstants.KEEP_ALIVE.toString());
		responseHeaders.set(PriceConstants.CONTENT_TYPE.toString(), PriceConstants.CONTENT_JSON.toString());
		responseHeaders.set(PriceConstants.APP_ID.toString(), PriceConstants.PRICE_SERVICE.toString());
		if(!StringUtils.isEmpty(MDC.get(PriceConstants.TRACE_ID.toString()))) {
			responseHeaders.set(PriceConstants.TRACE_ID.toString(), MDC.get(PriceConstants.TRACE_ID.toString()));	
		}		
		responseHeaders.set(PriceConstants.EXPIRES.toString(), getExpiresHdrValue());
		return responseHeaders;

	}

	/**
	 * get Expires Header Value
	 * 
	 * @return String
	 */
	private String getExpiresHdrValue() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(PriceConstants.EXPIRES_DATE_FORMAT.toString());
		dateFormat.setTimeZone(TimeZone.getTimeZone(PriceConstants.GMT_TIME_ZONE.toString()));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.HOUR, Integer.parseInt(appConfig.getExpiresHours()));
		return dateFormat.format(calendar.getTime());

	}
	/**
	 * parseDate 
	 * @param inputDateStr
	 * @return Date
	 */
	public Date parseDate(String inputDateStr) {
		String pattern = PriceConstants.DATE_FORMAT.toString();
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		try {
			return dateFormat.parse(inputDateStr);
			
		} catch (ParseException pe) {
			if (logger.isErrorEnabled()) {
				logger.error(String.format(
						"Exception occurred while parsing Date  %s Details :%s  ", inputDateStr, pe));
			}
			return null;

		}
	}
	/**
	 * getAppConfig
	 * @return ApplicationConfiguration
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
