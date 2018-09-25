package com.ascena.price.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(ignoreUnknownFields = true)
@RefreshScope
/**
 * 
 * Holds the necessary kill switches and environment specific configurations for
 * Price service
 * 
 * @author smeenavalli
 *
 */
public class ApplicationConfig {

	@Value("${price.enableCachePrefix}")
	private String isCachePrefixEnabled;

	@Value("${price.enableRedisPassword}")
	private String isRedisPasswordEnabled;

	@Value("${price.redis.host}")
	private String redisHost;

	@Value("${price.redis.port}")
	private String redisPort;

	@Value("${price.redis.database}")
	private String redisDatabase;

	@Value("${price.redis.password}")
	private String redisPassword;

	@Value("${price.redis.maxIdle}")
	private String redisMaxIdle;

	@Value("${price.redis.maxTotal}")
	private String redisMaxTotal;

	@Value("${price.redis.minIdle}")
	private String redisMinIdle;

	@Value("${price.redis.connectTimeout}")
	private String redisConnectTimeout;

	@Value("${price.http.requestTimeout}")
	private String requestTimeout;

	@Value("${price.http.connectTimeout}")
	private String connectTimeout;

	@Value("${price.http.maxTotal}")
	private String maxTotal;

	@Value("${price.http.defaultMaxPerRoute}")
	private String defaultMaxPerRoute;

	@Value("${price.productinfo.service.url}")
	private String productInfoServiceUrl;

	@Value("${price.productinfo.resourcePath}")
	private String prdInfoResourcePath;

	@Value("${price.bulk.sku.allowedcount}")
	private String bulkSkuAlwdCount;

	@Value("${price.bulk.product.allowedcount}")
	private String bulkPrdAlwdCount;

	@Value("${price.http.expires.hours}")
	private String expiresHours;

	@Value("${price.cloud.datastore.namespace}")
	private String datastoreNamespace;

	@Value("${price.cloud.datastore.projectID}")
	private String datastoreProjectID;

	/**
	 * get Is Cache Prefix Enabled
	 * 
	 * @return String
	 */
	public String getIsCachePrefixEnabled() {
		return isCachePrefixEnabled;
	}

	/**
	 * set Is Cache Prefix Enabled
	 *  
	 * @param isCachePrefixEnabled String
	 */
	public void setIsCachePrefixEnabled(final String isCachePrefixEnabled) {
		this.isCachePrefixEnabled = isCachePrefixEnabled;
	}

	/**
	 * getIsRedisPasswordEnabled
	 * @return String
	 */
	public String getIsRedisPasswordEnabled() {
		return isRedisPasswordEnabled;
	}

	/**
	 * set redisPasswordEnabled
	 * 
	 * @param isRedisPasswordEnabled String
	 */
	public void setIsRedisPasswordEnabled(final String isRedisPasswordEnabled) {
		this.isRedisPasswordEnabled = isRedisPasswordEnabled;
	}

	/**
	 * get Redis Host
	 * 
	 * @return String
	 */
	public String getRedisHost() {
		return redisHost;
	}

	/**
	 * set RedisHost
	 * 
	 * @param redisHost String
	 */
	public void setRedisHost(final String redisHost) {
		this.redisHost = redisHost;
	}

	/**
	 * getRedisPort
	 * 
	 * @return String
	 */
	public String getRedisPort() {
		return redisPort;
	}

	/**
	 * setRedisPort
	 * 
	 * @param redisPort
	 *            - String
	 */
	public void setRedisPort(final String redisPort) {
		this.redisPort = redisPort;
	}

	/**
	 * getRedisDatabase
	 * 
	 * @return String
	 */
	public String getRedisDatabase() {
		return redisDatabase;
	}

	/**
	 * set RedisD atabase
	 * 
	 * @param redisDatabase
	 */
	public void setRedisDatabase(final String redisDatabase) {
		this.redisDatabase = redisDatabase;
	}

	/**
	 * get Redis Password
	 * 
	 * @return String
	 */
	public String getRedisPassword() {
		return redisPassword;
	}

	/**
	 * set Redis Password
	 * 
	 * @param redisPassword String
	 */
	public void setRedisPassword(final String redisPassword) {
		this.redisPassword = redisPassword;
	}

	/**
	 * get Redis Max Idle
	 * 
	 * @return String
	 */
	public String getRedisMaxIdle() {
		return redisMaxIdle;
	}

	/**
	 * set Redis Max Idle
	 * 
	 * @param redisMaxIdle String
	 */
	public void setRedisMaxIdle(final String redisMaxIdle) {
		this.redisMaxIdle = redisMaxIdle;
	}

	/**
	 * get Redis Max Total
	 * 
	 * @return String
	 */
	public String getRedisMaxTotal() {
		return redisMaxTotal;
	}

	/**
	 * set Redis Max Total
	 * 
	 * @param redisMaxTotal
	 *            - String
	 */
	public void setRedisMaxTotal(final String redisMaxTotal) {
		this.redisMaxTotal = redisMaxTotal;
	}

	/**
	 * get Redis Min Idle
	 * 
	 * @return String
	 */
	public String getRedisMinIdle() {
		return redisMinIdle;
	}

	/**
	 * set Redis Min Idle
	 * 
	 * @param redisMinIdle String
	 */
	public void setRedisMinIdle(final String redisMinIdle) {
		this.redisMinIdle = redisMinIdle;
	}

	/**
	 * get Redis Connect Timeout
	 * 
	 * @return String
	 */
	public String getRedisConnectTimeout() {
		return redisConnectTimeout;
	}

	/**
	 * set Redis Connect Timeout
	 * 
	 * @param redisConnectTimeout String
	 */
	public void setRedisConnectTimeout(final String redisConnectTimeout) {
		this.redisConnectTimeout = redisConnectTimeout;
	}

	/**
	 * get Request Timeout
	 * 
	 * @return String
	 */
	public String getRequestTimeout() {
		return requestTimeout;
	}

	/**
	 * set Request Timeout
	 * 
	 * @param requestTimeout 
	 *            String
	 */
	public void setRequestTimeout(final String requestTimeout) {
		this.requestTimeout = requestTimeout;
	}

	/**
	 * get Connect Timeout
	 * 
	 * @return String
	 */
	public String getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * set Connect Timeout
	 * 
	 * @param connectTimeout
	 *            String
	 */
	public void setConnectTimeout(final String connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * getMaxTotal
	 * 
	 * @return String
	 */
	public String getMaxTotal() {
		return maxTotal;
	}

	/**
	 * setMaxTotal
	 * 
	 * @param maxTotal
	 *            - String
	 */
	public void setMaxTotal(final String maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * getDefaultMaxPerRoute
	 * 
	 * @return -String
	 */
	public String getDefaultMaxPerRoute() {
		return defaultMaxPerRoute;
	}

	/**
	 * set Default Max Per Route
	 * 
	 * @param defaultMaxPerRoute
	 *            - String
	 */
	public void setDefaultMaxPerRoute(final String defaultMaxPerRoute) {
		this.defaultMaxPerRoute = defaultMaxPerRoute;
	}

	/**
	 * get Product Info Service Url
	 * 
	 * @return String
	 */
	public String getProductInfoServiceUrl() {
		return productInfoServiceUrl;
	}

	/**
	 * set Product Info ServiceUrl
	 * 
	 * @param productInfoServiceUrl
	 *            - String
	 */
	public void setProductInfoServiceUrl(final String productInfoServiceUrl) {
		this.productInfoServiceUrl = productInfoServiceUrl;
	}

	/**
	 * get Product Info Resource Path
	 * 
	 * @return String
	 */
	public String getPrdInfoResourcePath() {
		return prdInfoResourcePath;
	}

	/**
	 * set Product Info Resource Path
	 * 
	 * @param prdInfoResourcePath
	 *            - String
	 */
	public void setPrdInfoResourcePath(final String prdInfoResourcePath) {
		this.prdInfoResourcePath = prdInfoResourcePath;
	}

	/**
	 * getBulkSkuAlwdCount
	 * 
	 * @return String
	 */
	public String getBulkSkuAlwdCount() {
		return bulkSkuAlwdCount;
	}

	/**
	 * setBulkSkuAlwdCount
	 * 
	 * @param bulkSkuAlwdCount
	 *            String
	 */
	public void setBulkSkuAlwdCount(final String bulkSkuAlwdCount) {
		this.bulkSkuAlwdCount = bulkSkuAlwdCount;
	}

	/**
	 * getBulkPrdAlwdCount
	 * 
	 * @return String
	 */
	public String getBulkPrdAlwdCount() {
		return bulkPrdAlwdCount;
	}

	/**
	 * setBulkPrdAlwdCount
	 * 
	 * @param bulkPrdAlwdCount
	 *            String
	 */
	public void setBulkPrdAlwdCount(final String bulkPrdAlwdCount) {
		this.bulkPrdAlwdCount = bulkPrdAlwdCount;
	}

	/**
	 * getExpiresHours
	 * 
	 * @return String
	 */
	public String getExpiresHours() {
		return expiresHours;
	}

	/**
	 * setExpiresHours
	 * 
	 * @param expiresHours
	 *            String
	 */
	public void setExpiresHours(final String expiresHours) {
		this.expiresHours = expiresHours;
	}

	/**
	 * getDatastoreNamespace
	 * 
	 * @return String
	 */
	public String getDatastoreNamespace() {
		return datastoreNamespace;
	}

	/**
	 * setDatastoreNamespace
	 * 
	 * @param datastoreNamespace
	 *            String
	 */
	public void setDatastoreNamespace(String datastoreNamespace) {
		this.datastoreNamespace = datastoreNamespace;
	}

	/**
	 * getDatastoreProjectID
	 * 
	 * @return String
	 */
	public String getDatastoreProjectID() {
		return datastoreProjectID;
	}

	/**
	 * setDatastoreProjectID
	 * 
	 * @param datastoreProjectID
	 *            String
	 */
	public void setDatastoreProjectID(String datastoreProjectID) {
		this.datastoreProjectID = datastoreProjectID;
	}

	@Override
	public String toString() {

		return "isCachePrefixEnabled: " + this.isCachePrefixEnabled + "\n" + "isRedisPasswordEnabled: "
				+ this.isRedisPasswordEnabled + "\n" + "redisHost: " + this.redisHost + "\n"
				+ "redisPort: " + this.redisPort + "\n" + "\n" + "redisDatabase: " + this.redisDatabase + "\n" + "\n"
				+ "redisMaxIdle: " + this.redisMaxIdle + "\n" + "\n" + "redisMaxTotal: " + this.redisMaxTotal + "\n"
				+ "\n" + "redisMinIdle: " + this.redisMinIdle + "\n" + "\n" + "redisConnectTimeout: "
				+ this.redisConnectTimeout + "\n" + "\n" + "requestTimeout: " + this.requestTimeout + "\n" + "\n"
				+ "connectTimeout: " + this.connectTimeout + "\n" + "maxTotal: " + this.maxTotal + "\n"
				+ "defaultMaxPerRoute: " + this.defaultMaxPerRoute + "\n" + "productInfoServiceUrl: "
				+ this.productInfoServiceUrl + "\n" + "prdInfoResourcePath: " + this.prdInfoResourcePath + "\n"
				+ "bulkSkuAlwdCount: " + this.bulkSkuAlwdCount + "\n" + "bulkPrdAlwdCount: " + this.bulkPrdAlwdCount
				+ "\n" + ":expiresHours " + this.expiresHours + "\n" + ":datastoreNamespace " + this.datastoreNamespace
				+ "\n" + ":datastoreProjectID " + this.datastoreProjectID + "\n";
	}

}
