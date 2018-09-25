package com.ascena.price.payload;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.common.constant.PriceConstants;
import com.ascena.price.config.ErrorMsgPropsConfig;
import com.ascena.price.exceptions.PriceIntegrationException;
import com.ascena.price.exceptions.SkuPrcException;
import com.ascena.price.exceptions.SysException;
import com.ascena.price.vo.PrcServiceError;
import com.ascena.price.vo.SkuPrice;
import com.ascena.price.vo.product.response.PrcProductResponsePayload;
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;
import com.ascena.price.vo.sku.response.PrcSkuResponsePayload;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class PriceErrorPayldGnrtrTest {

	@Autowired
	ErrorPayloadGenerator errorPayloadGenerator;

	@Autowired
	ErrorMsgPropsConfig errorMsgPropsConfig;

	@Before
	public void init() {
		errorPayloadGenerator.setErrorMessage(errorMsgPropsConfig);
	}

	@Test
	public void testGnrtePrdErrPayload() {
		String productId = "6862291";
		List<PrcServiceError> errors = new ArrayList<>();
		PrcServiceError serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(errorMsgPropsConfig.getPrdPrcMissingErrorMessage());
		message.append(productId);
		serviceError.setErrorCode(errorMsgPropsConfig.getPrdPrcMissingErrorCode());
		serviceError.setErrorDescription(message.toString());
		serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_PRD.toString());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		PrcProductResponsePayload respPayLoad = errorPayloadGenerator.generateProductErrorPayload(productId, errors);
		assertNotNull(" Unable to generate Product respPayLoad  ", respPayLoad);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceProductResponsePayload().getProducts().get(0).getErrors().size() > 0);
	}

	@Test
	public void testGnrtePrdErrPayloadonException() {
		String productId = "6862291";
		SysException sysException = new SysException(" System exception", PriceConstants.DATA_STORE_SYS_EXP.toString());

		PrcProductResponsePayload respPayLoad = errorPayloadGenerator.generateProductErrorPayload(productId,
				sysException);
		assertNotNull(" Unable to generate Product respPayLoad ", respPayLoad);

		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceProductResponsePayload().getProducts().get(0).getErrors().size() > 0);
	}

	@Test
	public void testGnrteSkuErrPayloadWithErrsInput() {
		String skuId = "12207710";
		List<PrcServiceError> errors = new ArrayList<>();
		PrcServiceError serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message.append(skuId);
		serviceError.setErrorCode(errorMsgPropsConfig.getSkuPrcMissingErrorCode());
		serviceError.setErrorDescription(message.toString());
		serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_SKU.toString());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		PrcSkuResponsePayload respPayLoad = errorPayloadGenerator.generateSkuErrorPayload(skuId, errors);
		assertNotNull(" Unable to generate Sku respPayLoad ", respPayLoad);
		assertTrue(" Unable to generate Sku respPayLoad ",
				respPayLoad.getPriceSkuResponsePayload().getSkus().get(0).getErrors().size() > 0);
	}

	@Test
	public void testGnrteSkuErrPayload() {
		String skuId = "12207710";
		SkuPrice skuPrice = errorPayloadGenerator.generateSkuErrorPayload(skuId);
		assertNotNull(" Unable to generate Sku respPayLoad ", skuPrice);
		assertTrue(" Unable to generate Sku respPayLoad ", skuPrice.getErrors().size() > 0);
	}

	@Test
	public void testGnrtePrdErrPayloadWithException() {
		String skuId = "12207710";
		List<PrcServiceError> errors = new ArrayList<>();
		PrcServiceError serviceError = new PrcServiceError();
		StringBuilder message = new StringBuilder();
		message.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message.append(skuId);
		serviceError.setErrorCode(errorMsgPropsConfig.getSkuPrcMissingErrorCode());
		serviceError.setErrorDescription(message.toString());
		serviceError.setErrorCode(PriceConstants.PRC_SYS_EXP_SKU.toString());
		serviceError.setErrorDescription(message.toString());
		errors.add(serviceError);
		PrcSkuResponsePayload respPayLoad = errorPayloadGenerator.generateSkuErrorPayload(skuId, errors);
		assertNotNull(" Unable to generate Product respPayLoad  ", respPayLoad);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceSkuResponsePayload().getSkus().get(0).getErrors().size() > 0);
	}

	@Test
	public void genrteBulkSkuErrorPayload() {
		PrcSkuRequestPayload requestBulk = new PrcSkuRequestPayload();
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> skuErrorsMap = new HashMap<>();
		List<PrcServiceError> errors1 = new ArrayList<>();
		List<PrcServiceError> errors2 = new ArrayList<>();
		PrcServiceError serviceError1 = new PrcServiceError();
		StringBuilder message1 = new StringBuilder();
		message1.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message1.append("12207710");
		serviceError1.setErrorCode(errorMsgPropsConfig.getSkuPrcMissingErrorCode());
		serviceError1.setErrorDescription(message1.toString());
		serviceError1.setErrorCode(PriceConstants.PRC_SYS_EXP_SKU.toString());
		serviceError1.setErrorDescription(message1.toString());
		errors1.add(serviceError1);

		PrcServiceError serviceError2 = new PrcServiceError();
		StringBuilder message2 = new StringBuilder();
		message2.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message2.append("12207765");
		serviceError2.setErrorCode(errorMsgPropsConfig.getSkuPrcMissingErrorCode());
		serviceError2.setErrorDescription(message2.toString());
		serviceError2.setErrorCode(PriceConstants.PRC_SYS_EXP_SKU.toString());
		serviceError2.setErrorDescription(message2.toString());
		errors2.add(serviceError2);
		skuErrorsMap.put("12207710", errors1);
		skuErrorsMap.put("12207765", errors1);

		PrcSkuResponsePayload respPayLoad = errorPayloadGenerator.genrteBulkSkuErrorPayload(requestBulk, payLoadErrors,
				skuErrorsMap);
		assertNotNull(" Unable to generate Product respPayLoad  ", respPayLoad);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceSkuResponsePayload().getSkus().get(0).getErrors().size() > 0);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceSkuResponsePayload().getSkus().get(1).getErrors().size() > 0);
	}

	@Test
	public void genrteBulkSkuErrPldwithException() {
		List<String> skuIdsList = new ArrayList<>();
		PriceIntegrationException intgException = new PriceIntegrationException(" Integration Exception",
				PriceConstants.PROD_INFO_SERVICE_SYS_EXP.toString());

		PrcSkuResponsePayload respPayLoad = errorPayloadGenerator.genrteBulkSkuErrorPayload(skuIdsList, intgException);
		assertNotNull(" Unable to generate Product respPayLoad  ", respPayLoad);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceSkuResponsePayload().getErrors().size() > 0);

	}

	@Test
	public void genrteBulkPrdErrorPayload() {
		List<PrcServiceError> payLoadErrors = new ArrayList<>();
		Map<String, List<PrcServiceError>> prdErrorsMap = new HashMap<>();
		List<PrcServiceError> errors1 = new ArrayList<>();
		List<PrcServiceError> errors2 = new ArrayList<>();
		PrcServiceError serviceError1 = new PrcServiceError();
		StringBuilder message1 = new StringBuilder();
		message1.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message1.append("6862291");
		serviceError1.setErrorCode(errorMsgPropsConfig.getPrdPrcMissingErrorCode());
		serviceError1.setErrorDescription(message1.toString());
		serviceError1.setErrorCode(PriceConstants.PRC_SYS_EXP_PRD.toString());
		serviceError1.setErrorDescription(message1.toString());
		errors1.add(serviceError1);

		PrcServiceError serviceError2 = new PrcServiceError();
		StringBuilder message2 = new StringBuilder();
		message2.append(errorMsgPropsConfig.getSkuPrcMissingErrorMessage());
		message2.append("6862245");
		serviceError2.setErrorCode(errorMsgPropsConfig.getSkuPrcMissingErrorCode());
		serviceError2.setErrorDescription(message2.toString());
		serviceError2.setErrorCode(PriceConstants.PRC_SYS_EXP_PRD.toString());
		serviceError2.setErrorDescription(message2.toString());
		errors2.add(serviceError2);
		prdErrorsMap.put("6862291", errors1);
		prdErrorsMap.put("6862245", errors1);

		PrcProductResponsePayload respPayLoad = errorPayloadGenerator.genrteBulkProductErrorPayload(payLoadErrors,
				prdErrorsMap);
		assertNotNull(" Unable to generate Product respPayLoad  ", respPayLoad);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceProductResponsePayload().getProducts().get(0).getErrors().size() > 0);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceProductResponsePayload().getProducts().get(1).getErrors().size() > 0);
	}

	@Test
	public void genrteBulkProdErrPldwithException() {

		PriceIntegrationException intgException = new PriceIntegrationException(" Integration Exception",
				PriceConstants.PROD_INFO_SERVICE_SYS_EXP.toString());

		Map<String, Exception> exceptions = new HashMap<>();
		exceptions.put("6862245", intgException);

		SysException sysException = new SysException(" System exception",
				PriceConstants.PROD_INFO_SERVICE_SYS_EXP.toString());
		exceptions.put("6862291", sysException);

		PrcProductResponsePayload respPayLoad = errorPayloadGenerator.genrteBulkProductErrorPayload(exceptions);
		assertNotNull(" Unable to generate Product respPayLoad  ", respPayLoad);
		assertTrue(" Unable to generate Product respPayLoad ",
				respPayLoad.getPriceProductResponsePayload().getProducts().get(0).getErrors().size() > 0);

	}
	
	@Test
	public void testGenerateSkuErrorPayload() {
		PrcSkuResponsePayload  prcSkuResponsePayload  =  errorPayloadGenerator.generateSkuErrorPayload("12207765",
				new SkuPrcException("Exception occurred in getPriceBySku", PriceConstants.PRC_SYS_EXP_SKU.toString()));
		assertNotNull(" Unable to generate Product respPayLoad  ", errorPayloadGenerator);
		assertTrue(" Unable to generate Sku respPayLoad ",
				prcSkuResponsePayload.getPriceSkuResponsePayload().getSkus().get(0).getErrors().size() > 0);
	}

}
