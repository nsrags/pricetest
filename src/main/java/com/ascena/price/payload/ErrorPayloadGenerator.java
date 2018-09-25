package com.ascena.price.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.Product;
import com.ascena.price.vo.SkuPrice;
import com.ascena.price.vo.product.response.PrcProductResponsePayload;
import com.ascena.price.vo.product.response.Products;
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;
import com.ascena.price.vo.sku.response.PrcSkuResponsePayload;
import com.ascena.price.vo.sku.response.Skus;

@Component
/**
 * Error Pay load Generator
 * 
 * @author SMeenavalli
 *
 */
public class ErrorPayloadGenerator {

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;

	/**
	 * generateProductErrorPayload
	 * @param productId String
	 * @param errors List<PrcServiceError>
	 * @return PrcProductResponsePayload
	 */
	public PrcProductResponsePayload generateProductErrorPayload(final String productId,final List<PrcServiceError> errors) {
		Products productPayLoad = new Products();
		PrcProductResponsePayload payLoad = new PrcProductResponsePayload();
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		product.setErrors(errors);
		product.setProductId(productId);
		product.setSkuList(new ArrayList<>());
		products.add(product);
		productPayLoad.setProducts(products);
		payLoad.setPriceProductResponsePayload(productPayLoad);
		return payLoad;
	}

	/**
	 * generateProductErrorPayload
	 * @param productId String
	 * @param ex Exception
	 * @return PrcProductResponsePayload
	 */
	public PrcProductResponsePayload generateProductErrorPayload(final String productId, final Exception ex) {
		Products productPayLoad = new Products();
		PrcProductResponsePayload payload = new PrcProductResponsePayload();
		List<Product> products = new ArrayList<>();
		Product product = new Product();
		List<PrcServiceError> errors = new ArrayList<>();
		PrcServiceError serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(PriceConstants.PRC_PRD_ERROR_MSG.toString());
		message.append(productId);
		message.append(PriceConstants.DETAILS.toString());
		message.append(ex.getMessage());
		serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_PRD.toString());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		product.setErrors(errors);
		product.setProductId(productId);
		product.setSkuList(new ArrayList<>());
		products.add(product);
		productPayLoad.setProducts(products);
		payload.setPriceProductResponsePayload(productPayLoad);
		return payload;
	}

	/**
	 * generateSkuErrorPayload 
	 * @param skuId String
	 * @param errors List<PrcServiceError>
	 * @return PrcSkuResponsePayload
	 */
	public PrcSkuResponsePayload generateSkuErrorPayload(final String skuId, final List<PrcServiceError> errors) {
		PrcSkuResponsePayload prcSkuResponsePayload = new PrcSkuResponsePayload();
		Skus skuPayLoad = new Skus();
		List<SkuPrice> skus = new ArrayList<>();
		SkuPrice skuPrice = new SkuPrice();
		skuPrice.setSkuId(skuId);
		skuPrice.setErrors(errors);
		skus.add(skuPrice);
		skuPayLoad.setSkus(skus);
		prcSkuResponsePayload.setPriceSkuResponsePayload(skuPayLoad);
		return prcSkuResponsePayload;

	}

	/**
	 * generateSkuErrorPayload
	 * @param skuid
	 * @return SkuPrice
	 */
	public SkuPrice generateSkuErrorPayload(final String skuid) {
		List<PrcServiceError> errors = null;
		PrcServiceError serviceError = null;
		SkuPrice skuPrice = null;
		errors = new ArrayList<>();
		serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message.append(skuid);
		serviceError.setErrorCode(errorMsgPropsConfig.getSkuPrcMissingErrorCode());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		skuPrice = new SkuPrice();
		skuPrice.setSkuId(skuid);
		skuPrice.setErrors(errors);
		return skuPrice;

	}

	/**
	 * generateSkuErrorPayload 
	 * @param skuid String
	 * @param ex Exception
	 * @return PrcSkuResponsePayload
	 */
	public PrcSkuResponsePayload generateSkuErrorPayload(final String skuid, final Exception ex) {
		List<PrcServiceError> errors = null;
		PrcServiceError serviceError = null;
		PrcSkuResponsePayload prcSkuResponsePayload = new PrcSkuResponsePayload();
		List<SkuPrice> skuPrices = new ArrayList<>();
		Skus skus = new Skus();
		SkuPrice skuPrice = null;
		errors = new ArrayList<>();
		serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(PriceConstants.PRC_SKU_ERROR_MSG.toString());
		message.append(skuid);
		message.append(PriceConstants.DETAILS.toString());
		message.append(ex.getMessage());
		serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_SKU.toString());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		skuPrice = new SkuPrice();
		skuPrice.setSkuId(skuid);
		skuPrice.setErrors(errors);
		skuPrices.add(skuPrice);
		skus.setSkus(skuPrices);
		prcSkuResponsePayload.setPriceSkuResponsePayload(skus);
		return prcSkuResponsePayload;
	}

	/**
	 * generateProductErrorPayload
	 * @param productId String
	 * @return  Product
	 */
	public Product generateProductErrorPayload(final String productId) {
		List<PrcServiceError> errors = null;
		PrcServiceError serviceError = null;
		Product product = new Product();
		errors = new ArrayList<>();
		serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(errorMsgPropsConfig.getPrdPrcMissingErrorMessage());
		message.append(productId);
		serviceError.setErrorCode(errorMsgPropsConfig.getPrdPrcMissingErrorCode());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		product.setProductId(productId);
		product.setErrors(errors);
		return product;

	}

	/**
	 * genrteBulkSkuErrorPayload
	 * @param requestBulk PrcSkuRequestPayload
	 * @param payLoadErrors List<PrcServiceError>
	 * @param skuErrorsMap Map<String, List<PrcServiceError>>
	 * @return PrcSkuResponsePayload
	 */
	public PrcSkuResponsePayload genrteBulkSkuErrorPayload(final PrcSkuRequestPayload requestBulk,
			final List<PrcServiceError> payLoadErrors, final Map<String, List<PrcServiceError>> skuErrors) {
		PrcSkuResponsePayload payLoad = new PrcSkuResponsePayload();
		Skus skus = new Skus();
		List<SkuPrice> skuPrices = new ArrayList<>();

		if (!CollectionUtils.isEmpty(skuErrors)) {
			skuErrors.forEach((key, value) -> {
				SkuPrice skuPrice = new SkuPrice();
				skuPrice.setSkuId(key);
				skuPrice.setErrors(value);
				skuPrices.add(skuPrice);
			});
		}
		skus.setSkus(skuPrices);
		skus.setErrors(payLoadErrors);
		payLoad.setPriceSkuResponsePayload(skus);
		return payLoad;

	}

	/**
	 * genrteBulkSkuErrorPayload
	 * @param uniqueSkuIdsList uniqueSkuIds
	 * @param ex Exception
	 * @return Exception
	 */
	public PrcSkuResponsePayload genrteBulkSkuErrorPayload(final List<String> uniqueSkuIds, final Exception ex) {
		PrcSkuResponsePayload payLoad = new PrcSkuResponsePayload();
		Skus skusPayLoad = new Skus();
		List<SkuPrice> skus = new ArrayList<>();
		List<PrcServiceError> errors = new ArrayList<>();
		PrcServiceError serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(PriceConstants.PRC_SKU_LIST_ERROR_MSG.toString());
		message.append(uniqueSkuIds);
		message.append(PriceConstants.DETAILS.toString());
		message.append(ex.getMessage());
		serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_SKU.toString());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		skusPayLoad.setErrors(errors);
		skusPayLoad.setSkus(skus);
		payLoad.setPriceSkuResponsePayload(skusPayLoad);
		return payLoad;

	}

	/**
	 * genrteBulkProductErrorPayload
	 * @param payLoadErrors List<PrcServiceError>
	 * @param productErrors Map<String, List<PrcServiceError>>
	 * @return
	 */
	public PrcProductResponsePayload genrteBulkProductErrorPayload(final List<PrcServiceError> payLoadErrors,
			final Map<String, List<PrcServiceError>> productErrors) {
		Products productsPayLoad = new Products();
		PrcProductResponsePayload payLoad = new PrcProductResponsePayload();
		List<Product> products = new ArrayList<>();
		productsPayLoad.setErrors(payLoadErrors);
		if (!CollectionUtils.isEmpty(productErrors)) {
			productErrors.forEach((key, value) -> {
				Product product = new Product();
				product.setProductId(key);
				product.setErrors(value);
				products.add(product);
			});
		}
		productsPayLoad.setProducts(products);
		payLoad.setPriceProductResponsePayload(productsPayLoad);
		return payLoad;
	}

	/**
	 * genrteBulkProductErrorPayload
	 * @param sysExceptions Map<String, Exception>
	 * @return PrcProductResponsePayload
	 */
	public PrcProductResponsePayload genrteBulkProductErrorPayload(final Map<String, Exception> sysExceptions) {
		PrcProductResponsePayload payLoad = new PrcProductResponsePayload();
		Products productPayLoad = new Products();
		List<Product> products = new ArrayList<>();
		productPayLoad.setErrors(new ArrayList<>());
		if (!CollectionUtils.isEmpty(sysExceptions)) {
			sysExceptions.forEach((key, value) -> {
				Product product = new Product();
				product.setProductId(key);
				List<PrcServiceError> errors = new ArrayList<>();
				PrcServiceError serviceError = new PrcServiceError();
				StringBuilder message = new StringBuilder();
				message.append(PriceConstants.PRC_PRD_ERROR_MSG.toString());
				message.append(key);
				message.append(PriceConstants.DETAILS.toString());
				message.append(value);
				serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_PRD.toString());
				serviceError.setErrorDescription(message.toString());
				errors.add(serviceError);
				product.setErrors(errors);
				products.add(product);
			});
		}
		productPayLoad.setProducts(products);
		payLoad.setPriceProductResponsePayload(productPayLoad);
		return payLoad;

	}

	/**
	 * getErrorMessage
	 * 
	 * @return ErrorMessageProps
	 */
	public ErrorMsgPropsConfig getErrorMessage() {
		return errorMsgPropsConfig;
	}

	/**
	 * setErrorMessage
	 * 
	 * @param errorMessage
	 */
	public void setErrorMessage(ErrorMsgPropsConfig errorMsgPropsConfig) {
		this.errorMsgPropsConfig = errorMsgPropsConfig;
	}

}
