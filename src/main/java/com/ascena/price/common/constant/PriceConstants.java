package com.ascena.price.common.constant;

/**
 *  Price constants
 * 
 * @author smeenavalli
 *
 */
public enum PriceConstants {

	NUMBER_PATTERN("\\d+"), 
	PRICE_SERVICE("Price Service"),
	KEEP_ALIVE("keep-alive"),
	ACCEPT_ENCODING("Accept-Encoding"), 
	GZIP("gzip"),
	SPRING_PROFILE_DEV("dev"),
	SPRING_PROFILE_LOCAL("local"),
	FORWARD_SLASH("/"),
	NUMBER_VALIDATOR("NumberValidator"),
	DATE_VALIDATOR("DateValidator"),	
	PRC_CACHE_PREFIX("Prc:"),
	HTTP_BAD_REQUEST("400"),
	HTTP_INSTER_SERVER("500"),
	CONTENT_TYPE("content-type"),
	CONTENT_JSON("application/json; charset=UTF-8"),
	EXPIRES("expires"),
	APP_ID("X-APP-ID"),
	TRACE_ID("X-B3-TraceId"),	
	EXPIRES_DATE_FORMAT("EEE, d MMM yyyy HH:mm:ss zzz"),
	GMT_TIME_ZONE("GMT"),
	DATA_STORE_SYS_EXP("PRC-SYS-EXP-DATASTORE"),
	PROD_INFO_SERVICE_SYS_EXP("PRC-SYS-EXP-PRDINFO-SKULIST"),
	PRC_PRD_ERROR_MSG ("Error occurred while getting Price for Product :"),
	DETAILS(" Details: "),
	PRC_SYS_EXP_PRD("PRC-SYS-EXP-PRD"),
	PRC_SKU_ERROR_MSG ("Error occurred while getting Price for Sku :"),
	PRC_SKU_LIST_ERROR_MSG("Error occurred while getting Price for SKU List:"),
	PRC_SYS_EXP_SKU("PRC-SYS-EXP-SKU"),
	SKU_ID("skuId"),
	SKU_IDS("skuIds"),
	ERRORS("errors"),
	PRODUCT_ID("productId"),
	PAY_LOAD_ERRORS("payLoadErrors"),
	SKU_BULK_REQUEST("skuBulkRequest"),
	SYS_EXCEPTIONS("sysExceptions"),
	PRC_INVALID_INPUT_ERR("PRC-INVALID-INPUT-ERR"),
	PRICE_START_DATE("priceStartDate"),
	PRICE_END_DATE("priceEndDate"),
	DATE_FORMAT("yyyy-MM-dd");
	
	private final String value;
	
	/**
	 * 
	 * @param constant
	 */
	PriceConstants(final String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
	
}
