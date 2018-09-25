package com.ascena.price.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.product.request.PrcProductRequestPayload;
import com.ascena.price.vo.product.request.ProductRequest;

/**
 * Dedicated Util for Product PriceBulk  Request validations
 * 
 * @author SMeenavalli
 *
 */
@Component
@DependsOn("validatorsFactory")
/**
 * Validation Util for Product
 * 
 * @author SMeenavalli
 *
 */
public class ProductPrcValidator {

	private List<Validator> productValidators = new ArrayList<>();

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;

	@PostConstruct
	public void init() {
		productValidators.add(ValidatorsFactory.getValidator(PriceConstants.NUMBER_VALIDATOR.toString()));
		// Add more validators to List if any additional validations are required
	}

	/**
	 * validateRequest
	 * @param inPutValue Object
	 * @param errors List<PrcServiceError>
	 */
	public void validateRequest(final Object inPutValue, final List<PrcServiceError> errors) {
		productValidators.forEach(item -> item.isValidRequest(inPutValue, errors));

	}

	/**
	 * validateBulkRequest 
	 * @param prcRequest
	 * @param payLoadErrors
	 * @param prdErrors
	 * @param bulkPrdAlwdCoun String
	 */
	public void validateBulkRequest(final PrcProductRequestPayload prcRequest, final List<PrcServiceError> payLoadErrors,
			Map<String, List<PrcServiceError>> prdErrors, String bulkPrdAlwdCoun) {

		if (prcRequest != null) {

			List<ProductRequest> productIds = prcRequest.getPrcProductRequestPayload();
			if (CollectionUtils.isEmpty(productIds) || isInvalidInput(productIds)) {
				addInputErrorsToPayLoad(payLoadErrors);

			} else {
				productIds.forEach(productRequest -> productValidators.forEach(validator -> {
					List<PrcServiceError> productErrors = new ArrayList<>();
					validator.isValidRequest(productRequest.getProductId(), productErrors);
					if (!CollectionUtils.isEmpty(productErrors)) {
						prdErrors.put(productRequest.getProductId(), productErrors);
						productErrors = new ArrayList<>();
					}

				}));
				// Number of entries exceeded
				int alwdMaxReqEntries = Integer.parseInt(bulkPrdAlwdCoun);
				if (productIds.size() > alwdMaxReqEntries) {
					PrcServiceError prcServiceError = new PrcServiceError();
					prcServiceError.setErrorCode(errorMsgPropsConfig.getPrdBulkExcdErrorCode());
					StringBuilder message = new StringBuilder(errorMsgPropsConfig.getPrdBulkExcdErrorMessage());
					message.append(alwdMaxReqEntries);
					prcServiceError.setErrorDescription(message.toString());
					payLoadErrors.add(prcServiceError);
				}
			}

		}

	}

	/**
	 * addInputErrorsToPayLoad
	 * 
	 * @param payLoadErrors
	 *            List<PrcServiceError>
	 */
	private void addInputErrorsToPayLoad(final List<PrcServiceError> payLoadErrors) {
		PrcServiceError prcServiceError = new PrcServiceError();
		prcServiceError.setErrorCode(errorMsgPropsConfig.getPrdBulkInvldInputErrorCode());
		StringBuilder message = new StringBuilder(errorMsgPropsConfig.getPrdBulkInvldInputErrorMsg());
		prcServiceError.setErrorDescription(message.toString());
		payLoadErrors.add(prcServiceError);
	}

	/**
	 * isInvalidInput
	 * 
	 * @param productIds
	 *            List<ProductRequest>
	 * @return boolean
	 */
	private boolean isInvalidInput(final List<ProductRequest> productIds) {
		if(CollectionUtils.isEmpty(productIds) ) {
			return true;
		}
		return productIds.stream().anyMatch(productRequest -> productRequest.getProductId() == null);

	}
}
