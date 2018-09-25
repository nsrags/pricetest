package com.ascena.price.payload;

import java.util.List;
import java.util.Map;

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
import com.ascena.price.exceptions.BulkProductPrcBadRequestEx;
import com.ascena.price.exceptions.BulkProductPrcException;
import com.ascena.price.exceptions.BulkSkuPrcBadRequestEx;
import com.ascena.price.exceptions.BulkSkuPrcException;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;

@ControllerAdvice
public class BulkPriceRespEntityHandler extends ResponseEntityExceptionHandler {

	private HttpHeaders commonHeaders;
	
	@Autowired
	private ErrorPayloadGenerator errorPayloadGenerator;
	
	public BulkPriceRespEntityHandler() {
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
	 * @param bsibrex BulkSkuPrcBadRequestEx
	 * @param request WebRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ExceptionHandler({ BulkSkuPrcBadRequestEx.class })
	public ResponseEntity<Object> handleBadRequest(final BulkSkuPrcBadRequestEx bsibrex, final WebRequest request) {
		
		PrcSkuRequestPayload requestBulk = (PrcSkuRequestPayload)request.getAttribute(PriceConstants.SKU_BULK_REQUEST.toString(), 
				 RequestAttributes.SCOPE_REQUEST);		
		final List<PrcServiceError> payLoadErrors = (List<PrcServiceError>) request.getAttribute(PriceConstants.PAY_LOAD_ERRORS.toString(), RequestAttributes.SCOPE_REQUEST); 			
		final Map<String, List<PrcServiceError>> skuErrors = (Map<String, List<PrcServiceError>>) request
				.getAttribute(PriceConstants.ERRORS.toString(), RequestAttributes.SCOPE_REQUEST);
		return handleExceptionInternal(bsibrex, errorPayloadGenerator.genrteBulkSkuErrorPayload(requestBulk, payLoadErrors, skuErrors),
				commonHeaders, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * handle Bulk Http bad request(400) for getPriceByProductId
	 * @param bpibrex BulkProductPrcBadRequestEx
	 * @param request WebRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ExceptionHandler({ BulkProductPrcBadRequestEx.class })
	public ResponseEntity<Object> handleBadRequest(final BulkProductPrcBadRequestEx bpibrex, final WebRequest request) {
		
		final List<PrcServiceError> payLoadErrors = (List<PrcServiceError>) request.getAttribute(PriceConstants.PAY_LOAD_ERRORS.toString(), RequestAttributes.SCOPE_REQUEST); 			
		final Map<String, List<PrcServiceError>> productErrors = (Map<String, List<PrcServiceError>>) request
				.getAttribute(PriceConstants.ERRORS.toString(), RequestAttributes.SCOPE_REQUEST);
		return handleExceptionInternal(bpibrex, errorPayloadGenerator.genrteBulkProductErrorPayload(payLoadErrors, productErrors),
				commonHeaders, HttpStatus.BAD_REQUEST, request);
	}

	/**
	 * Handle Http Bulk Internal Server Error(500) getPriceByProductId  
	 * @param bpiex BulkProductPrcException
	 * @param request WebRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ExceptionHandler({ BulkProductPrcException.class })
	public ResponseEntity<Object> handleInternalServerError(final BulkProductPrcException bpiex, final WebRequest request) {
		final Map<String, Exception> sysExceptions = (Map<String, Exception>) request.getAttribute(PriceConstants.SYS_EXCEPTIONS.toString(),
				RequestAttributes.SCOPE_REQUEST);
		return handleExceptionInternal(bpiex, errorPayloadGenerator.genrteBulkProductErrorPayload(sysExceptions),
				commonHeaders, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	/**
	 * 	Handle Http Bulk Internal Server Error(500) for  getPriceBySku
	 * @param bsiex BulkSkuPrcException
	 * @param request WebRequest
	 * @return
	 */
		@SuppressWarnings("unchecked")
		@ExceptionHandler({ BulkSkuPrcException.class })
		public ResponseEntity<Object> handleInternalServerError(final BulkSkuPrcException bsiex, final WebRequest request) {
			final List<String> uniqueSkuIds = (List<String>) request.getAttribute(PriceConstants.SKU_IDS.toString(),
					RequestAttributes.SCOPE_REQUEST);
			return handleExceptionInternal(bsiex, errorPayloadGenerator.genrteBulkSkuErrorPayload(uniqueSkuIds, bsiex),
					commonHeaders, HttpStatus.INTERNAL_SERVER_ERROR, request);
		}
}
