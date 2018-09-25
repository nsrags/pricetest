package com.ascena.price.service.integration.vo;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "activeSkuList",
    "error"
})
/**
 * Product Sku List
 * @author SMeenavalli
 *
 */
public class ProductSkuList implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("activeSkuList")
    private List<ActiveSkuList> activeSkuList;
	
    @JsonProperty("error")
    private transient List<Object> error ;
    
    @JsonProperty("activeSkuList")
    /**
     * get Active Sku List
     * @return List<ActiveSkuList>
     */
    public List<ActiveSkuList> getActiveSkuList() {
        return activeSkuList;
    }

    @JsonProperty("activeSkuList")
    /**
     * set Active Sku List
     * @param activeSkuList
     */
    public void setActiveSkuList(List<ActiveSkuList> activeSkuList) {
        this.activeSkuList = activeSkuList;
    }

    @JsonProperty("error")
    /**
     * get Error
     * @return List<Object> 
     */
    public List<Object> getError() {
        return error;
    }

    @JsonProperty("error")
    /**
     * set Error
     * @param error List<Object>
     */
    public void setError(List<Object> error) {
        this.error = error;
    }

    

}
