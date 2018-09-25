package com.ascena.price.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:errormessages.properties")
@ConfigurationProperties(ignoreUnknownFields = true)
/**
 * Error codes and messages Properties
 * 
 * @author smeenavalli
 *
 */
public class ErrorMsgPropsConfig {

	@Value("${error.invalid.number.errorcode}")
	private String invldNmbrErrorCode;

	@Value("${error.invalid.number.errormessage}")
	private String invldNmbrErrorMessage;

	@Value("${error.invalid.date.errorcode}")
	private String invldDateErrorCode;

	@Value("${error.invalid.date.errormessage}")
	private String invldDateErrorMessage;

	@Value("${error.noprice.sku.errorcode}")
	private String skuPrcMissingErrorCode;

	@Value("${error.noprice.sku.errormessage}")
	private String skuPrcMissingErrorMessage;

	@Value("${error.noprice.product.errorcode}")
	private String prdPrcMissingErrorCode;

	@Value("${error.noprice.product.errormessage}")
	private String prdPrcMissingErrorMessage;

	@Value("${error.bulk.sku.entries.exceeded.errorcode}")
	private String skuBulkExcdErrorCode;

	@Value("${error.bulk.sku.entries.exceeded.errormessage}")
	private String skuBulkExcdErrorMessage;

	@Value("${error.bulk.product.entries.exceeded.errorcode}")
	private String prdBulkExcdErrorCode;

	@Value("${error.bulk.product.entries.exceeded.errormessage}")
	private String prdBulkExcdErrorMessage;

	@Value("${error.norecord.found.datastore.price.errorcode}")
	private String noRecFoundInDbErrorCode;

	@Value("${error.norecord.found.datastore.price.errormessage}")
	private String noRecFoundInDbErrorMessage;

	@Value("${error.bulk.sku.entries.invalidinput.errorcode}")
	private String skuBulkInvldInputErrorCode;

	@Value("${error.bulk.sku.entries.invalidinput.errormessage}")
	private String skuBulkInvldInputErrorMsg;

	@Value("${error.bulk.product.entries.invalidinput.errorcode}")
	private String prdBulkInvldInputErrorCode;

	@Value("${error.bulk.product.entries.invalidinput.errormessage}")
	private String prdBulkInvldInputErrorMsg;

	/**
	 * getInvldNmbrErrorCode
	 * 
	 * @return String
	 */
	public String getInvldNmbrErrorCode() {
		return invldNmbrErrorCode;
	}

	/**
	 * setInvldNmbrErrorCode
	 * 
	 * @param invldNmbrErrorCode
	 *            String
	 */
	public void setInvldNmbrErrorCode(final String invldNmbrErrorCode) {
		this.invldNmbrErrorCode = invldNmbrErrorCode;
	}

	/**
	 * getInvldNmbrErrorMessage
	 * 
	 * @return String
	 */
	public String getInvldNmbrErrorMessage() {
		return invldNmbrErrorMessage;
	}

	/**
	 * setInvldNmbrErrorMessage
	 * 
	 * @param invldNmbrErrorMessage
	 *            - String
	 */
	public void setInvldNmbrErrorMessage(final String invldNmbrErrorMessage) {
		this.invldNmbrErrorMessage = invldNmbrErrorMessage;
	}

	/**
	 * getSkuInvMissingErrorCode
	 * 
	 * @return String
	 */
	public String getSkuPrcMissingErrorCode() {
		return skuPrcMissingErrorCode;
	}

	/**
	 * getSkuPrcMissingErrorMessage
	 * 
	 * @return String
	 */
	public String getSkuPrcMissingErrorMessage() {
		return skuPrcMissingErrorMessage;
	}

	/**
	 * setSkuPrcMissingErrorMessage
	 * 
	 * @param skuPrcMissingErrorMessage
	 *            String
	 */
	public void setSkuPrcMissingErrorMessage(final String skuPrcMissingErrorMessage) {
		this.skuPrcMissingErrorMessage = skuPrcMissingErrorMessage;
	}

	/**
	 * setSkuPrcMissingErrorCode
	 * 
	 * @param skuPrcMissingErrorCode
	 */
	public void setSkuPrcMissingErrorCode(final String skuPrcMissingErrorCode) {
		this.skuPrcMissingErrorCode = skuPrcMissingErrorCode;
	}

	/**
	 * skuPrcMissingErrorMessage
	 * 
	 * @param skuPrcMissingErrorMessage
	 *            - String
	 */
	public void skuPrcMissingErrorMessage(final String skuPrcMissingErrorMessage) {
		this.skuPrcMissingErrorMessage = skuPrcMissingErrorMessage;
	}

	/**
	 * getPrdPrcMissingErrorCode
	 * 
	 * @return String
	 */
	public String getPrdPrcMissingErrorCode() {
		return prdPrcMissingErrorCode;
	}

	/**
	 * setPrdPrcMissingErrorCode
	 * 
	 * @param prdPrcMissingErrorCode
	 *            - String
	 */
	public void setPrdPrcMissingErrorCode(final String prdPrcMissingErrorCode) {
		this.prdPrcMissingErrorCode = prdPrcMissingErrorCode;
	}

	/**
	 * getPrdPrcMissingErrorMessage
	 * 
	 * @return String
	 */
	public String getPrdPrcMissingErrorMessage() {
		return prdPrcMissingErrorMessage;
	}

	/**
	 * setPrdPrcMissingErrorMessage
	 * 
	 * @param prdPrcMissingErrorMessage
	 *            String
	 */
	public void setPrdPrcMissingErrorMessage(final String prdPrcMissingErrorMessage) {
		this.prdPrcMissingErrorMessage = prdPrcMissingErrorMessage;
	}

	/**
	 * getSkuBulkExcdErrorCode
	 * 
	 * @return String
	 */
	public String getSkuBulkExcdErrorCode() {
		return skuBulkExcdErrorCode;
	}

	/**
	 * setSkuBulkExcdErrorCode
	 * 
	 * @param skuBulkExcdErrorCode
	 *            - String
	 */
	public void setSkuBulkExcdErrorCode(final String skuBulkExcdErrorCode) {
		this.skuBulkExcdErrorCode = skuBulkExcdErrorCode;
	}

	/**
	 * getSkuBulkExcdErrorMessage
	 * 
	 * @return String
	 */
	public String getSkuBulkExcdErrorMessage() {
		return skuBulkExcdErrorMessage;
	}

	/**
	 * setSkuBulkExcdErrorMessage
	 * 
	 * @param skuBulkExcdErrorMessage
	 *            - String
	 */
	public void setSkuBulkExcdErrorMessage(final String skuBulkExcdErrorMessage) {
		this.skuBulkExcdErrorMessage = skuBulkExcdErrorMessage;
	}

	/**
	 * getPrdBulkExcdErrorCode
	 * 
	 * @return String
	 */
	public String getPrdBulkExcdErrorCode() {
		return prdBulkExcdErrorCode;
	}

	/**
	 * setPrdBulkExcdErrorCode
	 * 
	 * @param prdBulkExcdErrorCode
	 *            String
	 */
	public void setPrdBulkExcdErrorCode(final String prdBulkExcdErrorCode) {
		this.prdBulkExcdErrorCode = prdBulkExcdErrorCode;
	}

	/**
	 * getPrdBulkExcdErrorMessage
	 * 
	 * @return String
	 */
	public String getPrdBulkExcdErrorMessage() {
		return prdBulkExcdErrorMessage;
	}

	/**
	 * setPrdBulkExcdErrorMessage
	 * 
	 * @param prdBulkExcdErrorMessage
	 *            String
	 */
	public void setPrdBulkExcdErrorMessage(final String prdBulkExcdErrorMessage) {
		this.prdBulkExcdErrorMessage = prdBulkExcdErrorMessage;
	}

	/**
	 * getNoRecFoundInDbErrorCode
	 * 
	 * @return String
	 */
	public String getNoRecFoundInDbErrorCode() {
		return noRecFoundInDbErrorCode;
	}

	/**
	 * setNoRecFoundInDbErrorCode
	 * 
	 * @param noRecFoundInDbErrorCode
	 *            String
	 */
	public void setNoRecFoundInDbErrorCode(final String noRecFoundInDbErrorCode) {
		this.noRecFoundInDbErrorCode = noRecFoundInDbErrorCode;
	}

	/**
	 * getNoRecFoundInDbErrorMessage
	 * 
	 * @return String
	 */
	public String getNoRecFoundInDbErrorMessage() {
		return noRecFoundInDbErrorMessage;
	}

	/**
	 * setNoRecFoundInDbErrorMessage
	 * 
	 * @param noRecFoundInDbErrorMessage
	 *            - String
	 */
	public void setNoRecFoundInDbErrorMessage(final String noRecFoundInDbErrorMessage) {
		this.noRecFoundInDbErrorMessage = noRecFoundInDbErrorMessage;
	}

	/**
	 * getSkuBulkInvldInputErrorCode
	 * 
	 * @return String
	 */
	public String getSkuBulkInvldInputErrorCode() {
		return skuBulkInvldInputErrorCode;
	}

	/**
	 * setSkuBulkInvldInputErrorCode
	 * 
	 * @param skuBulkInvldInputErrorCode
	 *            String
	 */
	public void setSkuBulkInvldInputErrorCode(final String skuBulkInvldInputErrorCode) {
		this.skuBulkInvldInputErrorCode = skuBulkInvldInputErrorCode;
	}

	/**
	 * getSkuBulkInvldInputErrorMsg
	 * 
	 * @return String
	 */
	public String getSkuBulkInvldInputErrorMsg() {
		return skuBulkInvldInputErrorMsg;
	}

	/**
	 * setSkuBulkInvldInputErrorMsg
	 * 
	 * @param skuBulkInvldInputErrorMsg
	 *            String
	 */
	public void setSkuBulkInvldInputErrorMsg(final String skuBulkInvldInputErrorMsg) {
		this.skuBulkInvldInputErrorMsg = skuBulkInvldInputErrorMsg;
	}

	/**
	 * getPrdBulkInvldInputErrorCode
	 * 
	 * @return String
	 */
	public String getPrdBulkInvldInputErrorCode() {
		return prdBulkInvldInputErrorCode;
	}

	/**
	 * setPrdBulkInvldInputErrorCode
	 * 
	 * @param prdBulkInvldInputErrorCode
	 */
	public void setPrdBulkInvldInputErrorCode(final String prdBulkInvldInputErrorCode) {
		this.prdBulkInvldInputErrorCode = prdBulkInvldInputErrorCode;
	}

	/**
	 * getPrdBulkInvldInputErrorMsg
	 * 
	 * @return String
	 */
	public String getPrdBulkInvldInputErrorMsg() {
		return prdBulkInvldInputErrorMsg;
	}

	/**
	 * getInvldDateErrorCode
	 * 
	 * @return String
	 */
	public String getInvldDateErrorCode() {
		return invldDateErrorCode;
	}

	/**
	 * setInvldDateErrorCode
	 * 
	 * @param invldDateErrorCode
	 *            String
	 */
	public void setInvldDateErrorCode(String invldDateErrorCode) {
		this.invldDateErrorCode = invldDateErrorCode;
	}

	/**
	 * getInvldDateErrorMessage
	 * 
	 * @return String
	 */
	public String getInvldDateErrorMessage() {
		return invldDateErrorMessage;
	}

	/**
	 * setInvldDateErrorMessage
	 * 
	 * @param invldDateErrorMessage
	 *            String
	 */
	public void setInvldDateErrorMessage(String invldDateErrorMessage) {
		this.invldDateErrorMessage = invldDateErrorMessage;
	}

	/**
	 * setPrdBulkInvldInputErrorMsg
	 * 
	 * @param prdBulkInvldInputErrorMsg
	 *            String
	 */
	public void setPrdBulkInvldInputErrorMsg(final String prdBulkInvldInputErrorMsg) {
		this.prdBulkInvldInputErrorMsg = prdBulkInvldInputErrorMsg;
	}

	@Override
	public String toString() {

		return "invldNmbrErrorCode: " + this.invldNmbrErrorCode + "\n" + "invldNmbrErrorMessage: "
				+ this.invldNmbrErrorMessage + "\n" + "skuPrcMissingErrorCode: " + this.skuPrcMissingErrorCode + "\n"
				+ "invldDateErrorCode: " + this.invldDateErrorCode + "\n" + "invldDateErrorMessage: "
				+ "invldDateErrorMessage: " + "skuPrcMissingErrorMessage: " + this.skuPrcMissingErrorMessage + "\n"
				+ "\n" + "prdPrcMissingErrorCode: " + this.prdPrcMissingErrorCode + "\n" + "\n"
				+ "skuBulkExcdErrorCode: " + this.skuBulkExcdErrorCode + "\n" + "\n" + "skuBulkExcdErrorMessage: "
				+ this.skuBulkExcdErrorMessage + "\n" + "\n" + "prdBulkExcdErrorCode: " + this.prdBulkExcdErrorCode
				+ "\n" + "\n" + "prdBulkExcdErrorMessage: " + this.prdBulkExcdErrorMessage + "\n" + "\n"
				+ "noRecFoundInDbErrorCode: " + this.noRecFoundInDbErrorCode + "\n" + "\n"
				+ "noRecFoundInDbErrorMessage: " + this.noRecFoundInDbErrorMessage + "\n"
				+ "prdPrcMissingErrorMessage: " + this.prdPrcMissingErrorMessage + "\n" + "skuBulkInvldInputErrorCode: "
				+ this.skuBulkInvldInputErrorCode + "\n" + "skuBulkInvldInputErrorMsg: "
				+ this.skuBulkInvldInputErrorMsg + "\n" + "prdBulkInvldInputErrorCode: "
				+ this.prdBulkInvldInputErrorCode + "\n" + "prdBulkInvldInputErrorMsg: "
				+ this.prdBulkInvldInputErrorMsg + "\n";
	}
}
