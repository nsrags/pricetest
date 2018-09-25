package com.ascena.price.payload;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.common.util.PriceUtils;
import com.ascena.price.exceptions.ProductPrcBadRequestEx;
import com.ascena.price.exceptions.ProductPrcException;
import com.ascena.price.exceptions.SkuPrcBadRequestEx;
import com.ascena.price.exceptions.SkuPrcException;
import com.ascena.price.vo.PrcServiceError;

@ControllerAdvice
public class PriceRespEntityHandler extends ResponseEntityExceptionHandler {

	private HttpHeaders commonHeaders;
	
	@Autowired
	private ErrorPayloadGenerator errorPayloadGenerator;
	
	public PriceRespEntityHandler() {
		super();
	}
	
	@Autowired
	PriceUtils priceUtils;
	
	@PostConstruct
	public void init() {
		commonHeaders = priceUtils.setCommonResponseHeaders();
	}
	

	/**
	 * handle Http bad request(400) for getPriceBySku
	 * @param spbrex SkuPrcBadRequestEx
	 * @param request WebRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ExceptionHandler({ SkuPrcBadRequestEx.class })
	public ResponseEntity<Object> handleBadRequest(final SkuPrcBadRequestEx spbrex, final WebRequest request) {
		final String skuId = (String) request.getAttribute(PriceConstants.SKU_ID.toString(),
				RequestAttributes.SCOPE_REQUEST);
		final List<PrcServiceError> errors = (List<PrcServiceError>) request
				.getAttribute(PriceConstants.ERRORS.toString(), RequestAttributes.SCOPE_REQUEST);
		return handleExceptionInternal(spbrex, errorPayloadGenerator.generateSkuErrorPayload(skuId, errors),
				commonHeaders, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * handle Http bad request(400) for getPriceByProductId
	 * @param ppbrex ProductPrcBadRequestEx
	 * @param request WebRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ExceptionHandler({ ProductPrcBadRequestEx.class })
	public ResponseEntity<Object> handleBadRequest(final ProductPrcBadRequestEx ppbrex, final WebRequest request) {
		final String productId = (String) request.getAttribute(PriceConstants.PRODUCT_ID.toString(),
				RequestAttributes.SCOPE_REQUEST);
		final List<PrcServiceError> errors = (List<PrcServiceError>) request
				.getAttribute(PriceConstants.ERRORS.toString(), RequestAttributes.SCOPE_REQUEST);
		return handleExceptionInternal(ppbrex, errorPayloadGenerator.generateProductErrorPayload(productId, errors),
				commonHeaders, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Handle Http Internal Server Error(500) getPriceByProductId  
	 * @param ppex ProductPrcException
	 * @param request WebRequest
	 * @return
	 */
	@ExceptionHandler({ ProductPrcException.class })
	public ResponseEntity<Object> handleInternalServerError(final ProductPrcException ppex, final WebRequest request) {
		final String productId = (String) request.getAttribute(PriceConstants.PRODUCT_ID.toString(),
				RequestAttributes.SCOPE_REQUEST);
		return handleExceptionInternal(ppex, errorPayloadGenerator.generateProductErrorPayload(productId, ppex),
				commonHeaders, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
 
	/**
	 * 	Handle Http Internal Server Error(500) for  getPriceBySku
	 * @param spex SkuPrcException
	 * @param request WebRequest
	 * @return
	 */
		@ExceptionHandler({ SkuPrcException.class })
		public ResponseEntity<Object> handleInternalServerError(final SkuPrcException spex, final WebRequest request) {
			final String skuId = (String) request.getAttribute(PriceConstants.SKU_ID.toString(),
					RequestAttributes.SCOPE_REQUEST);
			return handleExceptionInternal(spex, errorPayloadGenerator.generateSkuErrorPayload(skuId, spex),
					commonHeaders, HttpStatus.INTERNAL_SERVER_ERROR, request);
		}
}
