package com.ascena.price.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.Product;
import com.ascena.price.vo.SkuPrice;
import com.ascena.price.vo.product.response.PrcProductResponsePayload;
import com.ascena.price.vo.product.response.Products;
import com.ascena.price.vo.sku.response.PrcSkuResponsePayload;
import com.ascena.price.vo.sku.response.Skus;

@Component
/**
 * Price Response Payload Generator
 * 
 * @author SMeenavalli
 *
 */
public class PrcRespPayloadGenerator {

	@Autowired
	ErrorPayloadGenerator errorPayloadGenerator;

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;
	
	/**
	 * 
	 * @param sku
	 * @return
	 */
	public PrcSkuResponsePayload generatePrcSkuPayload(final SkuPrice skuPrice,
			final String skuId) {
		PrcSkuResponsePayload skuRespPayld = new PrcSkuResponsePayload();
		List<PrcServiceError> errors = new ArrayList<>();
		Skus skus = new Skus();
		List<SkuPrice> skuPrices = new ArrayList<>();
		
		if(skuPrice == null) {
			PrcServiceError error = new PrcServiceError();
			StringBuilder errorDesc = new StringBuilder(); 
			error.setErrorCode(errorMsgPropsConfig.getNoRecFoundInDbErrorCode());
			errorDesc.append(errorMsgPropsConfig.getNoRecFoundInDbErrorMessage());
			errorDesc.append(skuId);			
			error.setErrorDescription(errorDesc.toString());
			errors.add(error);
		}
		
		if (skuPrice != null) {
			skuPrices.add(skuPrice);
		}
		skus.setSkus(skuPrices);
		skus.setErrors(errors);
		skuRespPayld.setPriceSkuResponsePayload(skus);
		return skuRespPayld;

	}

	/**
	 * 
	 * @param skuPrieMap
	 * @return
	 */
	public PrcSkuResponsePayload genrtePrcSkuBulkPayload(final List<String> skuIds, final Map<String, SkuPrice> skuPrices) {
		PrcSkuResponsePayload prcSkuResponsePayload = new PrcSkuResponsePayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Skus skus = new Skus();
		List<SkuPrice> bulkSkuPrices = new ArrayList<>();
		skuIds.forEach(skuid -> {
			if (!CollectionUtils.isEmpty(skuPrices) && skuPrices.containsKey(skuid)) {
				SkuPrice sku = skuPrices.get(skuid);
				bulkSkuPrices.add(sku);
			} else { // No SKU found
				bulkSkuPrices.add(errorPayloadGenerator.generateSkuErrorPayload(skuid));
			}
		});
		skus.setSkus(bulkSkuPrices);
		skus.setErrors(payLoadErrors);
		prcSkuResponsePayload.setPriceSkuResponsePayload(skus);
		return prcSkuResponsePayload;

	}

	/**
	 * 
	 * @param productIdsList
	 * @param productPrcMap
	 * @return
	 */
	public PrcProductResponsePayload genrtePrcProductBulkPayload(final List<String> productIds,
			final Map<String, Map<String, SkuPrice>> productPrices) {
		Products productsPayLoad = new Products();
		PrcProductResponsePayload payLoad = new PrcProductResponsePayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		List<Product> productsList = new ArrayList<>();
		productIds.forEach(productId -> {
			if (productPrices.containsKey(productId)) {
				Map<String, SkuPrice> skuPrcMap = productPrices.get(productId);
				List<SkuPrice> skuPrcList = skuPrcMap.values().stream().collect(Collectors.toList());
				Product product = new Product();
				product.setProductId(productId);
				product.setSkuList(skuPrcList);
				product.setErrors(new ArrayList<>());
				productsList.add(product);

			} else { // No Product found
				productsList.add(errorPayloadGenerator.generateProductErrorPayload(productId));
			}
		});
		productsPayLoad.setProducts(productsList);
		productsPayLoad.setErrors(payLoadErrors);
		payLoad.setPriceProductResponsePayload(productsPayLoad);
		return payLoad;
	}

	/**
	 * generate Price by Product Payload
	 * 
	 * @param productId
	 * @param prcEntriesMap
	 * @return PrcProductResponsePayload
	 */
	public PrcProductResponsePayload generatePrcProductPayload(final String productId, final Map<String, SkuPrice> skuPrices) {

		Products products = new Products();
		PrcProductResponsePayload payLoad = new PrcProductResponsePayload();
		List<SkuPrice> skus = new ArrayList<>();
		List<Product> productsList = new ArrayList<>();
		Product product = new Product();
		List<PrcServiceError> errors = new ArrayList<>();
		if(CollectionUtils.isEmpty(skuPrices)) {
			PrcServiceError error = new PrcServiceError();
			StringBuilder errorDesc = new StringBuilder(); 
			error.setErrorCode(errorMsgPropsConfig.getNoRecFoundInDbErrorCode());
			errorDesc.append(errorMsgPropsConfig.getNoRecFoundInDbErrorMessage());
			errorDesc.append(productId);			
			error.setErrorDescription(errorDesc.toString());
			errors.add(error);
		}
		product.setErrors(errors);
		product.setProductId(productId);
		skuPrices.forEach((key, value) -> skus.add(value));
		product.setSkuList(skus);
		productsList.add(product);
		products.setProducts(productsList);
		payLoad.setPriceProductResponsePayload(products);
		return payLoad;
	}

}
