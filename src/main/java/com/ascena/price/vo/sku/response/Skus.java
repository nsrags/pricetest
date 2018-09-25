
package com.ascena.price.vo.sku.response;

import java.io.Serializable;
import java.util.List;

import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.SkuPrice;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "skus","errors"
})
public class Skus implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("skus")
    private List<SkuPrice> skuList;

    @JsonProperty("errors")
	private List<PrcServiceError> errors;
	
    @JsonProperty("skus")
    public List<SkuPrice> getSkus() {
        return skuList;
    }

    @JsonProperty("skus")
    public void setSkus(List<SkuPrice> skuList) {
        this.skuList = skuList;
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


}
