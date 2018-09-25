
package com.ascena.price.vo.product.request;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "PrcProductRequestPayload"
})
public class PrcProductRequestPayload implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("PrcProductRequestPayload")
    private List<ProductRequest> payLoad;

    @JsonProperty("PrcProductRequestPayload")
    public List<ProductRequest> getPrcProductRequestPayload() {
        return payLoad;
    }

    @JsonProperty("PrcProductRequestPayload")
    public void serPrcProductRequestPayload(List<ProductRequest> payLoad) {
        this.payLoad = payLoad;
    }

}
