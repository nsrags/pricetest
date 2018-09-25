package com.ascena.price.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.common.util.PriceUtils;
import com.ascena.price.exceptions.ProductPrcBadRequestEx;
import com.ascena.price.exceptions.ProductPrcException;
import com.ascena.price.exceptions.SkuPrcBadRequestEx;
import com.ascena.price.exceptions.SkuPrcException;
import com.ascena.price.payload.PrcRespPayloadGenerator;
import com.ascena.price.service.PriceService;
import com.ascena.price.validations.ProductPrcValidator;
import com.ascena.price.validations.SkuPrcValidator;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.SkuPrice;
import com.ascena.price.vo.product.response.PrcProductResponsePayload;
import com.ascena.price.vo.sku.response.PrcSkuResponsePayload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST Controller of Price by Product Id and SKU Id API's
 * 
 * @author Smeenavalli
 *
 */
@RestController

@Api(value = "Price Service API")
@RequestMapping("/price")
public class PriceController {

	private static Logger logger = LoggerFactory.getLogger(PriceController.class);

	@Autowired
	private SkuPrcValidator skuPrcValidator;

	@Autowired
	private ProductPrcValidator productPrcValidator;

	@Autowired
	private PriceService priceService;

	@Autowired
	private PrcRespPayloadGenerator prcRespPayloadGenerator;

	@Autowired
	PriceUtils priceUtils;

	private HttpHeaders commonHeaders;

	@PostConstruct
	public void init() {
		commonHeaders = priceUtils.setCommonResponseHeaders();
	}

	@GetMapping("/")
	@ApiOperation(value = "Ping", response = String.class)
	public String welcome() {
		return "Welcome to Price Service ";
	}

	@GetMapping(value = "/product/{productId}",  produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Gives Price of a product", response = PrcProductResponsePayload.class)
	/**
	 * get Price By ProductId
	 * 
	 * @param productId
	 *            - String
	 * @return PrcProductResponsePayload
	 */
	public ResponseEntity<PrcProductResponsePayload> getPriceByProductId(@PathVariable("productId") String productId,
			HttpServletRequest request) throws ProductPrcBadRequestEx, ProductPrcException {

		Map<String, SkuPrice> prcEntries = new HashMap<>();
		List<PrcServiceError> errors = new ArrayList<>();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Trace Id=%s", MDC.get("X-B3-TraceId")));
			logger.debug(String.format("Product  Id=%s", productId));
		}
		if (StringUtils.isEmpty(productId)) {
			return new ResponseEntity<>(prcRespPayloadGenerator.generatePrcProductPayload(productId, prcEntries),
					commonHeaders, HttpStatus.OK); // Default Http Response
		}

		// Validate Input Request
		productPrcValidator.validateRequest(productId, errors);
		if (!CollectionUtils.isEmpty(errors)) {
			// If any validation errors exists - 400 bad request
			request.setAttribute(PriceConstants.PRODUCT_ID.toString(), productId);
			request.setAttribute(PriceConstants.ERRORS.toString(), errors);
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("getPriceByProductId - Validation Failed for Product ");
			errorMessage.append(productId);
			throw new ProductPrcBadRequestEx(errorMessage.toString(), 
					PriceConstants.PRC_INVALID_INPUT_ERR.toString());
		} else {
			// Price Service Call
			// get Price By ProductId Service Call
			try {
				prcEntries = priceService.getPriceByProduct(productId);
			} catch (Exception ex) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format(" Exception occurred in getPriceByProductId Service%s", ex.getMessage()));
				}
				request.setAttribute(PriceConstants.PRODUCT_ID.toString(), productId);
				throw new ProductPrcException(ex.getMessage(), PriceConstants.PRC_SYS_EXP_PRD.toString());
			}
			return new ResponseEntity<>(prcRespPayloadGenerator.generatePrcProductPayload(productId, prcEntries),
					commonHeaders, HttpStatus.OK); // Success 200 Http Code
		}

	}

	@GetMapping(value = "/sku/{skuId}", produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Gives Price of a SKU", response = PrcSkuResponsePayload.class)
	/**
	 * get Price By Sku
	 * 
	 * @param skuId
	 *            String  @RequestParam("name") Optional<
	 * @return PrcSkuResponsePayload
	 */
	public ResponseEntity<PrcSkuResponsePayload> getPriceBySku(@PathVariable("skuId") String skuId,			
			HttpServletRequest request) throws SkuPrcBadRequestEx, SkuPrcException {
		SkuPrice sku = new SkuPrice();
		List<PrcServiceError> errors = new ArrayList<>();

		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Trace Id=%s", MDC.get("X-B3-TraceId")));
			logger.debug(String.format("sku Id=%s", skuId));
		}
		if (StringUtils.isEmpty(skuId)) {
			return new ResponseEntity<>(prcRespPayloadGenerator.generatePrcSkuPayload(sku, skuId), commonHeaders,
					HttpStatus.OK);
		}
		
		// Validate Input Request
		skuPrcValidator.validateRequest(skuId, errors);
		// Price Service Call
		if (!CollectionUtils.isEmpty(errors)) {
			// Validation failures - 400 bad request
			request.setAttribute(PriceConstants.SKU_ID.toString(), skuId);
			request.setAttribute(PriceConstants.ERRORS.toString(), errors);
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("getPriceBySku - Validation Failed for SKU ");
			errorMessage.append(skuId);
			throw new SkuPrcBadRequestEx(errorMessage.toString(), 
					PriceConstants.PRC_INVALID_INPUT_ERR.toString());
		} else {
			// No validation failures
			// get Price By Sku Service Call
			try {
				sku = priceService.getPriceBySku(skuId);
			} catch (Exception ex) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format(" Exception occurred in getPriceBySku%s", ex.getMessage()));
				} // Exceptions occurred
				request.setAttribute(PriceConstants.SKU_ID.toString(), skuId);
				throw new SkuPrcException(ex.getMessage(), PriceConstants.PRC_SYS_EXP_SKU.toString());
			}
			// Success response
			return new ResponseEntity<>(prcRespPayloadGenerator.generatePrcSkuPayload(sku, skuId), commonHeaders,
					HttpStatus.OK);

		}
	}

}
