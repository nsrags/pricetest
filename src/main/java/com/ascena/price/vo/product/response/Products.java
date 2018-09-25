
package com.ascena.price.vo.product.response;

import java.io.Serializable;
import java.util.List;

import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "products"
})
public class Products implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("products")
    private List<Product> productLists;

    /**
	 * errors
	 */
	@JsonProperty("errors")
	private List<PrcServiceError> errors;

	
    @JsonProperty("products")
    public List<Product> getProducts() {
        return productLists;
    }

    @JsonProperty("products")
    public void setProducts(List<Product> productLists) {
        this.productLists = productLists;
    }

    /**
	 * getErrors
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
