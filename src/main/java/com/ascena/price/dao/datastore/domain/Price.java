package com.ascena.price.dao.datastore.domain;



import java.io.Serializable;
import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


@Entity
/**
 * Price entity for Data store CRUD operations
 * 
 * @author SMeenavalli
 *
 */
public class Price implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;	
	private String priceId;
	@Index
	private String skuId;
	private String msrp;
	private String salePrice;
	private String listPrice;
	private String currency;
	private String isOnClerance;
	private String pomoId;
	private String pomoPrice;
	private String promoMessage;
	private String gwpEligible;
	private String pricePoint;
	@Index
	private Date priceStartDate;
	@Index
	private Date priceEndDate;
	private String createdBy;
	private String createdOn;
	private String updatedBy;
	private String updatedOn;

	/**
	 * getPriceId
	 * 
	 * @return String
	 */
	public String getPriceId() {
		return priceId;
	}

	/**
	 * setPriceId
	 * 
	 * @param priceId
	 *            String
	 */
	public void setPriceId(String priceId) {
		this.priceId = priceId;
	}

	/**
	 * getSkuId
	 * 
	 * @return String
	 */
	public String getSkuId() {
		return skuId;
	}

	/**
	 * setSkuId
	 * 
	 * @param skuId
	 *            String
	 */
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	/**
	 * getMsrp
	 * 
	 * @return String
	 */
	public String getMsrp() {
		return msrp;
	}

	/**
	 * setMsrp
	 * 
	 * @param msrp
	 *            String
	 */
	public void setMsrp(String msrp) {
		this.msrp = msrp;
	}

	/**
	 * getSalePrice
	 * 
	 * @return String
	 */
	public String getSalePrice() {
		return salePrice;
	}

	/**
	 * setSalePrice
	 * 
	 * @param salePrice
	 *            String
	 */
	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	/**
	 * getListPrice
	 * 
	 * @return String
	 */
	public String getListPrice() {
		return listPrice;
	}

	/**
	 * setListPrice
	 * 
	 * @param listPrice
	 */
	public void setListPrice(String listPrice) {
		this.listPrice = listPrice;
	}

	/**
	 * getCurrency
	 * 
	 * @return String
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * setCurrency
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * getIsOnClerance
	 * 
	 * @return String
	 */
	public String getIsOnClerance() {
		return isOnClerance;
	}

	/**
	 * setIsOnClerance
	 * 
	 * @param isOnClerance
	 *            String
	 */
	public void setIsOnClerance(String isOnClerance) {
		this.isOnClerance = isOnClerance;
	}

	/**
	 * getPomoId
	 * 
	 * @return String
	 */
	public String getPomoId() {
		return pomoId;
	}

	/**
	 * setPomoId
	 * 
	 * @param pomoId
	 */
	public void setPomoId(String pomoId) {
		this.pomoId = pomoId;
	}

	/**
	 * getPomoPrice
	 * 
	 * @return
	 */
	public String getPomoPrice() {
		return pomoPrice;
	}

	/**
	 * setPomoPrice
	 * 
	 * @param pomoPrice
	 */
	public void setPomoPrice(String pomoPrice) {
		this.pomoPrice = pomoPrice;
	}

	/**
	 * getPromoMessage
	 * 
	 * @return String
	 */
	public String getPromoMessage() {
		return promoMessage;
	}

	/**
	 * setPromoMessage
	 * 
	 * @param promoMessage
	 */
	public void setPromoMessage(String promoMessage) {
		this.promoMessage = promoMessage;
	}

	/**
	 * getGwpEligible
	 * 
	 * @return String
	 */
	public String getGwpEligible() {
		return gwpEligible;
	}

	/**
	 * setGwpEligible
	 * 
	 * @param gwpEligible
	 */
	public void setGwpEligible(String gwpEligible) {
		this.gwpEligible = gwpEligible;
	}

	/**
	 * getPricePoint
	 * 
	 * @return String
	 */
	public String getPricePoint() {
		return pricePoint;
	}

	/**
	 * setPricePoint
	 * 
	 * @param pricePoint
	 */
	public void setPricePoint(String pricePoint) {
		this.pricePoint = pricePoint;
	}

	/**
	 * getPriceStartDate
	 * 
	 * @return String
	 */
	public Date getPriceStartDate() {
		return priceStartDate;
	}

	/**
	 * setPriceStartDate
	 * 
	 * @param priceStartDate
	 *            String
	 */
	public void setPriceStartDate(Date priceStartDate) {
		this.priceStartDate = priceStartDate;
	}

	/**
	 * getPriceEndDate
	 * 
	 * @return Date
	 */
	public Date getPriceEndDate() {
		return priceEndDate;
	}

	/**
	 * setPriceEndDate
	 * 
	 * @param priceEndDate
	 */
	public void setPriceEndDate(Date priceEndDate) {
		this.priceEndDate = priceEndDate;
	}

	/**
	 * getCreatedBy
	 * 
	 * @return String
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * setCreatedBy
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * getCreatedOn
	 * 
	 * @return String
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * setCreatedOn
	 * 
	 * @param createdOn
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * getUpdatedBy
	 * 
	 * @return String
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * setUpdatedBy
	 * 
	 * @param updatedBy
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * getUpdatedOn
	 * 
	 * @return String
	 */
	public String getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * setUpdatedOn
	 * 
	 * @param updatedOn
	 */
	public void setUpdatedOn(String updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	/**
	 * getId
	 * @return String
	 */
	public String getId() {
		return id;
	}
	/**
	 * setId 
	 * @param id - String
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Price{" + "skuId='" + skuId + '\'' + " msrp='" + msrp + '\'' + " salePrice='" + salePrice + '\''
				+ " listPrice='" + listPrice + '\'' + " currency='" + currency + '\'' + " isOnClerance='" + isOnClerance
				+ '\'' + " pomoId='" + pomoId + '\'' + " pomoPrice='" + pomoPrice + '\'' + " promoMessage='"
				+ promoMessage + '\'' + " gwpEligible='" + gwpEligible + '\'' + " pricePoint='" + pricePoint + '\''
				+ " priceStartDate='" + priceStartDate + '\'' + " priceEndDate='" + priceEndDate + '\'' + " createdBy='"
				+ createdBy + '\'' + " createdOn='" + createdOn + '\'' + " updatedBy='" + updatedBy + '\''
				+ " updatedOn='" + updatedOn + '\'' + '}';
	}
}
