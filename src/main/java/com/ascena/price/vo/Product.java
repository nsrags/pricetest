package com.ascena.price.vo;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "productId",
    "skuList",
    "errors"
})
public class Product implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Property for productId
	 */
	@JsonProperty("productId")
    private String productId;
    
	
    
    /**
	 * Property for skuList
	 */
    @JsonProperty("skuList")
    private List<SkuPrice> skuList;
    
    /**
	 * Property for errors
	 */
    @JsonProperty("errors")
    private List<PrcServiceError> errors ;
    
    /**
	 * Property for productId
	 */
    @JsonProperty("productId")
    public String getProductId() {
        return productId;
    }

    /**
     * setProductId
     * @param productId
     */
    @JsonProperty("productId")
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
   
   
    /**
     * getSkuList
     * @return
     */
    @JsonProperty("skuList")
    public List<SkuPrice> getSkuList() {
        return skuList;
    }
    
    /**
     * setSkuList
     * @param skuList
     */
    @JsonProperty("skuList")
    public void setSkuList(List<SkuPrice> skuList) {
        this.skuList = skuList;
    }
    
    /**
     * getErrors
     * @return
     */
    @JsonProperty("errors")
    public List<PrcServiceError> getErrors() {
        return errors;
    }
    
    /**
     * setErrors
     * @param errors
     */
    @JsonProperty("errors")
    public void setErrors(List<PrcServiceError> errors) {
        this.errors = errors;
    }

}
