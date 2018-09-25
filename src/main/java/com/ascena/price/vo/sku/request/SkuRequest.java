
package com.ascena.price.vo.sku.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "skuId" })
public class SkuRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("skuId")
	private String id;
	
	/**
	 * getSkuId
	 * @return String
	 */
	@JsonProperty("skuId")
	public String getSkuId() {
		return id;
	}
	
	/**
	 * setSkuId 
	 * @param id String
	 */
	@JsonProperty("skuId")
	public void setSkuId(String id) {
		this.id = id;
	}
}
