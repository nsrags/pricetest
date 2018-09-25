package com.ascena.price.validations;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;
import com.ascena.price.vo.sku.request.SkuRequest;

/**
 * Sku Validation Util
 * 
 * @author SMeenavalli
 *
 */
@Component
@DependsOn("validatorsFactory")
/**
 * Validation Util for SKU
 * 
 * @author SMeenavalli
 *
 */
public class SkuPrcValidator {

	private static final Map<String,Validator> skuPrcValidators = new HashMap<>();

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;

	@PostConstruct
	public void initSkuValidators() {
		skuPrcValidators.put(PriceConstants.NUMBER_VALIDATOR.toString(),
				ValidatorsFactory.getValidator(PriceConstants.NUMBER_VALIDATOR.toString()));		
		// Add more validators to List if any additional validations are required

	}

	/**
	 * validateRequest 
	 * @param inPutValue Object
	 * @param errors List<PrcServiceError>
	 */
	public void validateRequest(Object skuId , List<PrcServiceError> errors) {
		skuPrcValidators.forEach((key,validator) -> 
		{
			if(key.equals(PriceConstants.NUMBER_VALIDATOR.toString())) {
				validator.isValidRequest((String)skuId, errors);
			}
				
		});
	}

	/**
	 * validateBulkRequest 
	 * @param prcRequest PrcSkuRequestPayload
	 * @param payLoadErrors List<PrcServiceError>
	 * @param skuPriceErrors Map<String, List<PrcServiceError>>
	 * @param bulkSkuAlwdCount String
	 */
	public void validateBulkRequest(PrcSkuRequestPayload prcRequest, List<PrcServiceError> payLoadErrors,
			Map<String, List<PrcServiceError>> skuPriceErrors, String bulkSkuAlwdCount) {

		if (prcRequest != null) {
			List<SkuRequest> skuIds = prcRequest.getPriceSkuRequest();
			if (isInvalidInput(skuIds) || CollectionUtils.isEmpty(skuIds)) {
				addInputErrorsToPayLoad(payLoadErrors);
			} else {
				skuIds.forEach(skuRequest -> skuPrcValidators.forEach((key,validator) -> {
					List<PrcServiceError> errors = new ArrayList<>();
					validator.isValidRequest(skuRequest.getSkuId(), errors);
					if (!CollectionUtils.isEmpty(errors)) {
						skuPriceErrors.put(skuRequest.getSkuId(), errors);
						errors = new ArrayList<>();
					}

				}));
				// Number of entries exceeded in Bulk request
				int alwdMaxReqEntries = Integer.parseInt(bulkSkuAlwdCount);
				if (skuIds.size() > alwdMaxReqEntries) {
					PrcServiceError prcServiceError = new PrcServiceError();
					prcServiceError.setErrorCode(errorMsgPropsConfig.getSkuBulkExcdErrorCode());
					StringBuilder message = new StringBuilder(errorMsgPropsConfig.getSkuBulkExcdErrorMessage());
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
	private void addInputErrorsToPayLoad(List<PrcServiceError> payLoadErrors) {
		PrcServiceError prcServiceError = new PrcServiceError();
		prcServiceError.setErrorCode(errorMsgPropsConfig.getSkuBulkInvldInputErrorCode());
		StringBuilder message = new StringBuilder(errorMsgPropsConfig.getSkuBulkInvldInputErrorMsg());
		prcServiceError.setErrorDescription(message.toString());
		payLoadErrors.add(prcServiceError);
	}

	/**
	 * isInvalidInput
	 * 
	 * @param skuIds
	 *            List<SkuRequest>
	 * @return boolean
	 */
	private boolean isInvalidInput(List<SkuRequest> skuIds) {
		if(CollectionUtils.isEmpty(skuIds) ) {
			return true;
		}
		return skuIds.stream().anyMatch(skuRequest -> skuRequest.getSkuId() == null);

	}
}
