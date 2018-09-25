
package com.ascena.price.service.integration.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "skuId"
})
/**
 * Active Sku List
 * @author SMeenavalli
 *
 */
public class ActiveSkuList implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("skuId")
    private String skuId;
   
    @JsonProperty("skuId")
    /**
     * getSkuId 
     * @return String skuId
     */
    public String getSkuId() {
        return skuId;
    }

    @JsonProperty("skuId")
    /**
     * set SkuId 
     * @param skuId String
     */
    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    

}
