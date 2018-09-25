
package com.ascena.price.vo.sku.request;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PrcSkuRequestPayload"
})
public class PrcSkuRequestPayload implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("PrcSkuRequestPayload")
    private List<SkuRequest> skuRequest;

    @JsonProperty("PrcSkuRequestPayload")
    public List<SkuRequest> getPriceSkuRequest() {
        return skuRequest;
    }

    @JsonProperty("PrcSkuRequestPayload")
    public void setPriceSkuRequest(List<SkuRequest> skuRequest) {
        this.skuRequest = skuRequest;
    }

}
