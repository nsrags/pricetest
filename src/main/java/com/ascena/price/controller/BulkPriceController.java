package com.ascena.price.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.common.util.PriceUtils;
import com.ascena.price.config.ApplicationConfig;
import com.ascena.price.exceptions.BulkProductPrcBadRequestEx;
import com.ascena.price.exceptions.BulkProductPrcException;
import com.ascena.price.exceptions.BulkSkuPrcBadRequestEx;
import com.ascena.price.exceptions.BulkSkuPrcException;
import com.ascena.price.payload.PrcRespPayloadGenerator;
import com.ascena.price.service.PriceService;
import com.ascena.price.validations.ProductPrcValidator;
import com.ascena.price.validations.SkuPrcValidator;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.SkuPrice;
import com.ascena.price.vo.product.request.PrcProductRequestPayload;
import com.ascena.price.vo.product.request.ProductRequest;
import com.ascena.price.vo.product.response.PrcProductResponsePayload;
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;
import com.ascena.price.vo.sku.request.SkuRequest;
import com.ascena.price.vo.sku.response.PrcSkuResponsePayload;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * REST Controller of Price by Product Id and SKU Id API's
 * 
 * @author smeenavalli
 *
 */
@RestController

@Api(value = "Price API for Bulk Requests")
@RequestMapping("/price")
public class BulkPriceController {

	private static Logger logger = LoggerFactory.getLogger(BulkPriceController.class);

	@Autowired
	private SkuPrcValidator skuPrcValidator;

	@Autowired
	private ProductPrcValidator productPrcValidator;

	@Autowired
	private PriceService priceService;

	@Autowired
	private PrcRespPayloadGenerator prcRespPayloadGenerator;

	@Autowired
	ApplicationConfig appConfig;

	@Autowired
	PriceUtils priceUtils;

	private HttpHeaders commonHeaders;

	@PostConstruct
	public void init() {
		commonHeaders = priceUtils.setCommonResponseHeaders();
	}

	@PostMapping(value = "/products", consumes = "application/json", produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Bulk Price API - Gives Price for given products", response = PrcProductResponsePayload.class)
	/**
	 * Bulk Post Requests to get Price By ProductId
	 * 
	 * @param bulkRequest
	 *            PrcProductRequestPayload
	 * @param request
	 *            HttpServletRequest
	 * @return PrcProductResponsePayload
	 */
	public ResponseEntity<PrcProductResponsePayload> getPriceByProductId(
			@RequestBody @Validated PrcProductRequestPayload bulkRequest, HttpServletRequest request)
			throws BulkProductPrcBadRequestEx, BulkProductPrcException {
		PrcProductResponsePayload respPayload = new PrcProductResponsePayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();

		Map<String, List<PrcServiceError>> productErrors = new HashMap<>();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("bulkRequest  =%s", bulkRequest));
		}
		if (bulkRequest == null) {
			return new ResponseEntity<>(respPayload, commonHeaders, HttpStatus.OK);
		}
		// Validate Input Request
		productPrcValidator.validateBulkRequest(bulkRequest, payLoadErrors, productErrors,
				appConfig.getBulkPrdAlwdCount());
		if (!payLoadErrors.isEmpty() || !productErrors.isEmpty()) {
			// Validation failures - 400 bad Request
			request.setAttribute(PriceConstants.PAY_LOAD_ERRORS.toString(), payLoadErrors);
			request.setAttribute(PriceConstants.ERRORS.toString(), productErrors);
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Bulk getPriceByProductId - Validation Failed for Request ");
			errorMessage.append(bulkRequest);
			throw new BulkProductPrcBadRequestEx(errorMessage.toString(), PriceConstants.PRC_INVALID_INPUT_ERR.toString());
		} else {
			// Service call
			return bulkPriceCall(bulkRequest, commonHeaders, request);
		}

	}

	/**
	 * bulkPriceCall
	 * 
	 * @param bulkRequest
	 *            PrcProductRequestPayload
	 * @param commonHeaders
	 *            HttpHeaders
	 * @param request
	 *            HttpServletRequest
	 * @return ResponseEntity<PrcProductResponsePayload>
	 * @throws BulkProductPrcException
	 */
	private ResponseEntity<PrcProductResponsePayload> bulkPriceCall(final PrcProductRequestPayload bulkRequest,
			final HttpHeaders commonHeaders, final HttpServletRequest request) throws BulkProductPrcException {
		List<String> productIds = new ArrayList<>();
		List<String> uniquePrdIds = null;
		Map<String, Exception> sysExceptions = new HashMap<>();
		Map<String, Map<String, SkuPrice>> productPrices = new HashMap<>();
		// Bulk Price Service Call
		List<ProductRequest> productRequests = bulkRequest.getPrcProductRequestPayload();
		productRequests.forEach(productRequest -> productIds.add(productRequest.getProductId()));
		uniquePrdIds = productIds.stream().distinct().collect(Collectors.toList());
		// get Price By Sku Service Call
		uniquePrdIds.forEach(productId -> {
			Map<String, SkuPrice> skuPriceMap = new HashMap<>();
			try {
				skuPriceMap = priceService.getPriceByProduct(productId);
				productPrices.put(productId, skuPriceMap);
			} catch (Exception ex) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format(" Exception occurred in getPriceBySku %s", ex.getMessage()));
				} // Exceptions occurred
				sysExceptions.put(productId, ex);
			}

		});
		if (!CollectionUtils.isEmpty(sysExceptions)) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Bulk getPriceByProductId - Validation Failed for Request ");
			errorMessage.append(bulkRequest);
			request.setAttribute(PriceConstants.SYS_EXCEPTIONS.toString(), sysExceptions);
			throw new BulkProductPrcException(errorMessage.toString(), 
					PriceConstants.PRC_INVALID_INPUT_ERR.toString());
		}
		// Generate Pay load for success response with 200 http code
		return new ResponseEntity<>(prcRespPayloadGenerator.genrtePrcProductBulkPayload(uniquePrdIds, productPrices),
				commonHeaders, HttpStatus.OK);

	}

	@PostMapping(value = "/skus", consumes = "application/json", produces = "application/json")
	@ResponseBody
	@ApiOperation(value = "Bulk Price API- Gives Price for List of SKU's", response = PrcSkuResponsePayload.class)
	/**
	 * Bulk Post Requests to get Price By Sku Id
	 * 
	 * @param requestBulk
	 *            PrcSkuRequestPayload
	 * @param request
	 *            HttpServletRequest
	 * @return PrcSkuResponsePayload
	 */
	public ResponseEntity<PrcSkuResponsePayload> getPriceBySku(@RequestBody @Validated PrcSkuRequestPayload requestBulk,
			HttpServletRequest request) throws BulkSkuPrcBadRequestEx, BulkSkuPrcException {
		Map<String, SkuPrice> skuPrices = new HashMap<>();
		List<String> skuIds = new ArrayList<>();
		List<String> uniqueSkuIds = new ArrayList<>();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> skuErrorsMap = new HashMap<>();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Trace Id=%s", MDC.get("X-B3-TraceId")));
			logger.debug(String.format("requestBody Id=%s", requestBulk));
		}
		if (requestBulk == null) {
			return new ResponseEntity<>(prcRespPayloadGenerator.genrtePrcSkuBulkPayload(uniqueSkuIds, skuPrices),
					commonHeaders, HttpStatus.OK);
		}

		// Validate Input Request
		skuPrcValidator.validateBulkRequest(requestBulk, payLoadErrors, skuErrorsMap, appConfig.getBulkSkuAlwdCount());
		if (!payLoadErrors.isEmpty() || !skuErrorsMap.isEmpty()) {
			// Validation failures - 400 bad request
			request.setAttribute(PriceConstants.SKU_BULK_REQUEST.toString(), requestBulk);
			request.setAttribute(PriceConstants.PAY_LOAD_ERRORS.toString(), payLoadErrors);
			request.setAttribute(PriceConstants.ERRORS.toString(), skuErrorsMap);
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("Bulk getPriceBySku - Validation Failed for Request ");
			errorMessage.append(requestBulk);
			throw new BulkSkuPrcBadRequestEx(errorMessage.toString(), 
					PriceConstants.PRC_INVALID_INPUT_ERR.toString());
		} else {// No validation failures
			// Bulk Price Service Call
			List<SkuRequest> skuRequests = requestBulk.getPriceSkuRequest();
			skuRequests.forEach(skuRequest -> skuIds.add(skuRequest.getSkuId()));
			uniqueSkuIds = skuIds.stream().distinct().collect(Collectors.toList());
			// get Price By Sku Service Call
			try {
				skuPrices = priceService.getPrice(uniqueSkuIds);
			} catch (Exception ex) {
				if (logger.isErrorEnabled()) {
					logger.error(String.format(" Exception occurred in getPriceBySku %s", ex.getMessage()));
				} // Exceptions occurred
				request.setAttribute(PriceConstants.SKU_IDS.toString(), uniqueSkuIds);
				throw new BulkSkuPrcException(ex.getMessage(), PriceConstants.PRC_SYS_EXP_SKU.toString());
			}
			// Generate Pay load
			return new ResponseEntity<>(prcRespPayloadGenerator.genrtePrcSkuBulkPayload(uniqueSkuIds, skuPrices),
					commonHeaders, HttpStatus.OK);
		}
	}

}
