
package com.ascena.price.vo.sku.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PriceSkuResponsePayload"
})
public class PrcSkuResponsePayload implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@JsonProperty("PriceSkuResponsePayload")
    private Skus skus;

    @JsonProperty("PriceSkuResponsePayload")
    public Skus getPriceSkuResponsePayload() {
        return skus;
    }

    @JsonProperty("PriceSkuResponsePayload")
    public void setPriceSkuResponsePayload(Skus skus) {
        this.skus = skus;
    }

}
