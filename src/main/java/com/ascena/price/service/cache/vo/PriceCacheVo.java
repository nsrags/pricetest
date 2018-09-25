package com.ascena.price.service.cache.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "priceId", "skuId", "msrp", "salePrice", "listPrice", "currency", "isOnClerance", "pomoId",
		"pomoPrice", "promoMessage", "gwpEligible", "pricePoint", "priceStartDate", "priceEndDate", "cacheCreatedBy",
		"cacheCreatedOn", "cacheUpdatedBy", "cacheUpdatedOn" })
/**
 * Price Cache Value Object
 * 
 * @author smeenavalli
 *
 */
public class PriceCacheVo implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("priceId")
	private String priceId;

	@JsonProperty("skuId")
	private String skuId;

	@JsonProperty("msrp")
	private String msrp;

	@JsonProperty("salePrice")
	private String salePrice;

	@JsonProperty("listPrice")
	private String listPrice;

	@JsonProperty("currency")
	private String currency;

	@JsonProperty("isOnClerance")
	private String isOnClerance;

	@JsonProperty("pomoId")
	private String pomoId;

	@JsonProperty("pomoPrice")
	private String pomoPrice;

	@JsonProperty("promoMessage")
	private String promoMessage;

	@JsonProperty("gwpEligible")
	private String gwpEligible;

	@JsonProperty("pricePoint")
	private String pricePoint;

	@JsonProperty("priceStartDate")
	private Date priceStartDate;

	@JsonProperty("priceEndDate")
	private Date priceEndDate;

	@JsonProperty("cacheCreatedBy")
	private String cacheCreatedBy;

	@JsonProperty("cacheCreatedOn")
	private String cacheCreatedOn;

	@JsonProperty("cacheUpdatedBy")
	private String cacheUpdatedBy;

	@JsonProperty("cacheUpdatedOn")
	private String cacheUpdatedOn;

	/**
	 * getPriceId
	 * @return String
	 */
	@JsonProperty("priceId")
	public String getPriceId() {
		return priceId;
	}

	/**
	 * setPriceId 
	 * @param priceId String
	 */
	@JsonProperty("priceId")	
	public void setPriceId(final String priceId) {
		this.priceId = priceId;
	}
	
	/**
	 * getSkuId
	 * @return String
	 */
	@JsonProperty("skuId")
	public String getSkuId() {
		return skuId;
	}
	
	/**
	 * setSkuId
	 * @param skuId String
	 */
	@JsonProperty("skuId")
	public void setSkuId(final String skuId) {
		this.skuId = skuId;
	}
	
	/**
	 * getMsrp
	 * @return String
	 */
	@JsonProperty("msrp")
	public String getMsrp() {
		return msrp;
	}
	
	/**
	 * setMsrp
	 * @param msrp String
	 */
	@JsonProperty("msrp")
	public void setMsrp(final String msrp) {
		this.msrp = msrp;
	}
	
	/**
	 * getSalePrice
	 * @return String
	 */
	@JsonProperty("salePrice")
	public String getSalePrice() {
		return salePrice;
	}
	
	/**
	 * setSalePrice
	 * @param salePrice String
	 */
	@JsonProperty("salePrice")
	public void setSalePrice(final String salePrice) {
		this.salePrice = salePrice;
	}
	
	/**
	 * getListPrice
	 * @return String
	 */
	@JsonProperty("listPrice")
	public String getListPrice() {
		return listPrice;
	}
	
	/**
	 * setListPrice
	 * @param listPrice String
	 */
	@JsonProperty("listPrice")
	public void setListPrice(final String listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * getCurrency
	 * @return String
	 */
	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}
	
	/**
	 * setCurrency
	 * @param currency String
	 */
	@JsonProperty("currency")
	public void setCurrency(final String currency) {
		this.currency = currency;
	}
	
	/**
	 * getIsOnClerance
	 * @return String
	 */
	@JsonProperty("isOnClerance")
	public String getIsOnClerance() {
		return isOnClerance;
	}

	/**
	 * setIsOnClerance
	 * @param isOnClerance String
	 */
	@JsonProperty("isOnClerance")
	public void setIsOnClerance(final String isOnClerance) {
		this.isOnClerance = isOnClerance;
	}
	
	/**
	 * getPomoId
	 * @return String
	 */
	@JsonProperty("pomoId")
	public String getPomoId() {
		return pomoId;
	}
	
	/**
	 * setPomoId
	 * @param pomoId String
	 */
	@JsonProperty("pomoId")
	public void setPomoId(final String pomoId) {
		this.pomoId = pomoId;
	}
	
	/**
	 * getPomoPrice
	 * @return String
	 */
	@JsonProperty("pomoPrice")
	public String getPomoPrice() {
		return pomoPrice;
	}
	
	/**
	 * setPomoPrice 
	 * @param pomoPrice String
	 */
	@JsonProperty("pomoPrice")
	public void setPomoPrice(final String pomoPrice) {
		this.pomoPrice = pomoPrice;
	}
	
	/**
	 * getPromoMessage 
	 * @return String
	 */
	@JsonProperty("promoMessage")
	public String getPromoMessage() {
		return promoMessage;
	}
	
	/**
	 * setPromoMessage
	 * @param promoMessage String
	 */
	@JsonProperty("promoMessage")
	public void setPromoMessage(final String promoMessage) {
		this.promoMessage = promoMessage;
	}
	
	/**
	 * getGwpEligible
	 * @return String
	 */
	@JsonProperty("gwpEligible")
	public String getGwpEligible() {
		return gwpEligible;
	}
	
	/**
	 * setGwpEligible
	 * @param gwpEligible String
	 */
	@JsonProperty("gwpEligible")
	public void setGwpEligible(final String gwpEligible) {
		this.gwpEligible = gwpEligible;
	}
	
	/**
	 * getPricePoint 
	 * @return String
	 */
	@JsonProperty("pricePoint")
	public String getPricePoint() {
		return pricePoint;
	}

	/**
	 * 	setPricePoint 
	 * @param pricePoint String
	 */
	@JsonProperty("pricePoint")
	public void setPricePoint(final String pricePoint) {
		this.pricePoint = pricePoint;
	}
	
	/**
	 * getPriceStartDate
	 * @return String
	 */
	@JsonProperty("priceStartDate")
	public Date getPriceStartDate() {
		return priceStartDate;
	}
	
	/**
	 * setPriceStartDate 
	 * @param priceStartDate String
	 */
	@JsonProperty("priceStartDate")
	public void setPriceStartDate(final Date priceStartDate) {
		this.priceStartDate = priceStartDate;
	}
	
	/**
	 * getPriceEndDate
	 * @return String
	 */
	@JsonProperty("priceEndDate")
	public Date getPriceEndDate() {
		return priceEndDate;
	}

	/**
	 * 	setPriceEndDate
	 * @param priceEndDate String
	 */
	@JsonProperty("priceEndDate")
	public void setPriceEndDate(final Date priceEndDate) {
		this.priceEndDate = priceEndDate;
	}
	
	/**
	 * getCacheCreatedBy
	 * @return String
	 */
	@JsonProperty("cacheCreatedBy")
	public String getCacheCreatedBy() {
		return cacheCreatedBy;
	}
	
	/**
	 * setCacheCreatedBy 
	 * @param cacheCreatedBy String
	 */
	@JsonProperty("cacheCreatedBy")
	public void setCacheCreatedBy(final String cacheCreatedBy) {
		this.cacheCreatedBy = cacheCreatedBy;
	}
	
	/**
	 * getCacheCreatedOn
	 * @return String
	 */
	@JsonProperty("cacheCreatedOn")
	public String getCacheCreatedOn() {
		return cacheCreatedOn;
	}
	
	/**
	 * setCacheCreatedOn 
	 * @param cacheCreatedOn String
	 */
	@JsonProperty("cacheCreatedOn")
	public void setCacheCreatedOn(final String cacheCreatedOn) {
		this.cacheCreatedOn = cacheCreatedOn;
	}
	
	/**
	 * getCacheUpdatedBy
	 * @return String
	 */
	@JsonProperty("cacheUpdatedBy")
	public String getCacheUpdatedBy() {
		return cacheUpdatedBy;
	}
	
	/**
	 * setCacheUpdatedBy
	 * @param cacheUpdatedBy String
	 */
	@JsonProperty("cacheUpdatedBy")
	public void setCacheUpdatedBy(final String cacheUpdatedBy) {
		this.cacheUpdatedBy = cacheUpdatedBy;
	}
	
	/**
	 * getCacheUpdatedOn 
	 * @return String
	 */
	@JsonProperty("cacheUpdatedOn")
	public String getCacheUpdatedOn() {
		return cacheUpdatedOn;
	}
	
	/**
	 * setCacheUpdatedOn
	 * @param cacheUpdatedOn String
	 */
	@JsonProperty("cacheUpdatedOn")
	public void setCacheUpdatedOn(final String cacheUpdatedOn) {
		this.cacheUpdatedOn = cacheUpdatedOn;
	}

	@Override
	public String toString() {
		return "PriceCacheVo{" + "priceId='" + priceId + '\'' + ", skuId='" + skuId + '\'' + '\'' + ", msrp='"
				+ msrp + '\'' + '\'' + ", salePrice='" + salePrice + '\'' + '\'' + ", listPrice='" + listPrice + '\''
				+ '\'' + ", currency='" + currency + '\'' + '\'' + ", isOnClerance='" + isOnClerance + '\'' + '\''
				+ ", pomoId='" + pomoId + '\'' + '\'' + ", pomoPrice='" + pomoPrice + '\'' + '\'' + ", promoMessage='"
				+ promoMessage + '\'' + '\'' + ", gwpEligible='" + gwpEligible + '\'' + '\'' + ", pricePoint='"
				+ pricePoint + '\'' + '\'' + ", priceStartDate='" + priceStartDate + '\'' + '\'' + ", priceEndDate='"
				+ priceEndDate + '\'' + '\'' + ", cacheCreatedBy='" + cacheCreatedBy + '\'' + '\''
				+ ", cacheCreatedOn='" + cacheCreatedOn + '\'' + '\'' + ", cacheUpdatedBy='" + cacheUpdatedBy + '\''
				+ '\'' + ", cacheUpdatedOn='" + cacheUpdatedOn + '\'' + '\'' + '}';
	}
}
