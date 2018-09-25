package com.ascena.price.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "priceId", "skuId", "msrp", "salePrice", "listPrice", "currency", "isOnClerance", "pomoId",
		"pomoPrice", "promoMessage", "gwpEligible", "pricePoint", "priceStartDate", "priceEndDate" })
/**
 * Price Cache Value Object
 * 
 * @author smeenavalli
 *
 */
public class SkuPrice implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * skuId Property
	 */
	@JsonProperty("skuId")
	private String skuId;

	/**
	 * listPrice Property
	 */
	@JsonProperty("listPrice")
	private String listPrice;

	/**
	 * currency Property
	 */
	@JsonProperty("currency")
	private String currency;

	/**
	 * price Id Property
	 */
	@JsonProperty("priceId")
	private String priceId;

	
	/**
	 * salePrice Property
	 */
	@JsonProperty("salePrice")
	private String salePrice;
	/**
	 * pricePoint Property
	 */
	@JsonProperty("pricePoint")
	private String pricePoint;

	/**
	 * MSRP Property
	 */
	@JsonProperty("msrp")
	private String msrp;

	
	/**
	 * priceStartDate Property
	 */
	@JsonProperty("priceStartDate")
	private Date priceStartDate;
	
	/**
	 * isOnClerance Property
	 */
	@JsonProperty("isOnClerance")
	private String isOnClerance;

	/**
	 * pomoId Property
	 */
	@JsonProperty("pomoId")
	private String pomoId;

	
	/**
	 * gwpEligible Property
	 */
	@JsonProperty("gwpEligible")
	private String gwpEligible;



	/**
	 * priceEndDate Property
	 */
	@JsonProperty("priceEndDate")
	private Date priceEndDate;

	/**
	 * pomoPrice Property
	 */
	@JsonProperty("pomoPrice")
	private String pomoPrice;

	/**
	 * promoMessage Property
	 */
	@JsonProperty("promoMessage")
	private String promoMessage;

	
	/**
	 * errors Property
	 */
	@JsonProperty("errors")
	private List<PrcServiceError> errors;
	
	/**
	 * getPriceId
	 * @return String
	 */
	@JsonProperty("priceId")
	public String getPriceId() {
		return priceId;
	}

	/**
	 * 
	 * @param priceId
	 */
	@JsonProperty("priceId")
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("skuId")
	public String getSkuId() {
		return skuId;
	}
	/**
	 * 
	 * @param skuId
	 */
	@JsonProperty("skuId")
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("msrp")
	public String getMsrp() {
		return msrp;
	}

	/**
	 * 
	 * @param msrp
	 */
	@JsonProperty("msrp")
	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("salePrice")
	public String getSalePrice() {
		return salePrice;
	}

	/**
	 * 
	 * @param salePrice
	 */
	@JsonProperty("salePrice")
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("listPrice")
	public String getListPrice() {
		return listPrice;
	}

	/**
	 * 
	 * @param listPrice
	 */
	@JsonProperty("listPrice")
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}

	/**
	 * 
	 * @param currency
	 */
	@JsonProperty("currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	/**
	 * 
	 * @return
	 */
	@JsonProperty("isOnClerance")
	public String getIsOnClerance() {
		return isOnClerance;
	}

	/**
	 * 
	 * @param isOnClerance
	 */
	@JsonProperty("isOnClerance")
	public void setIsOnClerance(String isOnClerance) {
		this.isOnClerance = isOnClerance;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("pomoId")
	public String getPomoId() {
		return pomoId;
	}

	/**
	 * 
	 * @param pomoId
	 */
	@JsonProperty("pomoId")
	public void setPomoId(String pomoId) {
		this.pomoId = pomoId;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("pomoPrice")
	public String getPomoPrice() {
		return pomoPrice;
	}

	/**
	 * 
	 * @param pomoPrice
	 */
	@JsonProperty("pomoPrice")
	public void setPomoPrice(String pomoPrice) {
		this.pomoPrice = pomoPrice;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("promoMessage")
	public String getPromoMessage() {
		return promoMessage;
	}

	/**
	 * 
	 * @param promoMessage
	 */
	@JsonProperty("promoMessage")
	public void setPromoMessage(String promoMessage) {
		this.promoMessage = promoMessage;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("gwpEligible")
	public String getGwpEligible() {
		return gwpEligible;
	}

	/**
	 * 
	 * 
	 * @param gwpEligible
	 */
	@JsonProperty("gwpEligible")
	public void setGwpEligible(String gwpEligible) {
		this.gwpEligible = gwpEligible;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("pricePoint")
	public String getPricePoint() {
		return pricePoint;
	}

	/**
	 * 
	 * @param pricePoint
	 */
	@JsonProperty("pricePoint")
	public void setPricePoint(String pricePoint) {
		this.pricePoint = pricePoint;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("priceStartDate")
	public Date getPriceStartDate() {
		return priceStartDate;
	}

	/**
	 * 
	 *  @param priceStartDate
	 */
	@JsonProperty("priceStartDate")
	public void setPriceStartDate(Date priceStartDate) {
		this.priceStartDate = priceStartDate;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("priceEndDate")
	public Date getPriceEndDate() {
		return priceEndDate;
	}
	
	/**
	 * 
	 * @param priceEndDate
	 */
	@JsonProperty("priceEndDate")
	public void setPriceEndDate(Date priceEndDate) {
		this.priceEndDate = priceEndDate;
	}

	/**
	 * get Errors
	 * 
	 * @return List<PrcServiceError>
	 */
	@JsonProperty("errors")
	public List<PrcServiceError> getErrors() {
		return errors;
	}

	/**
	 * set Errors
	 * 
	 * @param errors
	 *            List<PrcServiceError>
	 */
	@JsonProperty("errors")
	public void setErrors(List<PrcServiceError> errors) {
		this.errors = errors;
	}
	
	@Override
	public String toString() {
		return "PriceCacheVo{" + "priceId='" + priceId + '\'' + ", skuId='" + skuId + '\'' + '\'' + ", msrp='"
				+ msrp + '\'' + '\'' + ", salePrice='" + salePrice + '\'' + '\'' + ", listPrice='" + listPrice + '\''
				+ '\'' + ", currency='" + currency + '\'' + '\'' + ", isOnClerance='" + isOnClerance + '\'' + '\''
				+ ", pomoId='" + pomoId + '\'' + '\'' + ", pomoPrice='" + pomoPrice + '\'' + '\'' + ", promoMessage='"
				+ promoMessage + '\'' + '\'' + ", gwpEligible='" + gwpEligible + '\'' + '\'' + ", pricePoint='"
				+ pricePoint + '\'' + '\'' + ", priceStartDate='" + priceStartDate + '\'' + '\'' + ", priceEndDate='"
				+ priceEndDate + '\'' + '\'' + ", cacheCreatedBy='"  + '\'' + '}';
	}
}
