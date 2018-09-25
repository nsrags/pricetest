
package com.ascena.price.vo.product.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PriceProductResponsePayload"
})
public class PrcProductResponsePayload implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("PriceProductResponsePayload")
    private Products products;

    @JsonProperty("PriceProductResponsePayload")
    public Products getPriceProductResponsePayload() {
        return products;
    }

    @JsonProperty("PriceProductResponsePayload")
    public void setPriceProductResponsePayload(Products products) {
        this.products = products;
    }

}
