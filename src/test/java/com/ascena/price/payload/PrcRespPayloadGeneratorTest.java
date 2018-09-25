package com.ascena.price.payload;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.vo.SkuPrice;
import com.ascena.price.vo.product.response.PrcProductResponsePayload;
import com.ascena.price.vo.sku.response.PrcSkuResponsePayload;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class PrcRespPayloadGeneratorTest {

	@Autowired
	PrcRespPayloadGenerator prcRespPayloadGenerator;

	@Test
	public void testGenrtePrcSkuPyld() {
		SkuPrice price = new SkuPrice();
		price.setSkuId("30064508");
		price.setSalePrice("10.43");
		price.setListPrice("14.90");
		price.setMsrp("14.90");
		price.setCurrency("$");
		price.setGwpEligible("false");
		price.setIsOnClerance("false");
		price.setPomoId("Prom1232");
		price.setPomoPrice("10.43");
		price.setPriceId("10001");
		price.setPricePoint("");
		price.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		price.setPromoMessage("Limited time 30% off!");

		PrcSkuResponsePayload skuRespPayLd = prcRespPayloadGenerator.generatePrcSkuPayload(price, "30064508");
		assertNotNull(" Unable to generate SKU respPayLoad  ", skuRespPayLd);
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getSkuId());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getSalePrice());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getListPrice());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getMsrp());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getPriceStartDate());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getPriceEndDate());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getPromoMessage());

		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getPomoId());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getPomoPrice());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				skuRespPayLd.getPriceSkuResponsePayload().getSkus().get(0).getPriceId());
	}

	@Test
	public void testGenrtePrcbulkSkuPyld() {
		List<String> skuIdsList = new ArrayList<>();
		skuIdsList.add("30064508");
		skuIdsList.add("30064566");

		Map<String, SkuPrice> skuPrices = new HashMap<>();
		SkuPrice price1 = new SkuPrice();
		price1.setSkuId("30064508");
		price1.setSalePrice("10.40");
		price1.setListPrice("14.90");
		price1.setMsrp("14.90");
		price1.setCurrency("$");
		price1.setGwpEligible("false");
		price1.setIsOnClerance("false");
		price1.setPomoId("Prom1232");
		price1.setPomoPrice("10.40");
		price1.setPriceId("10001");
		price1.setPricePoint("");
		price1.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price1.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		price1.setPromoMessage("Limited time 30% off!");
		skuPrices.put("30064508", price1);

		SkuPrice price2 = new SkuPrice();
		price2.setSkuId("30064566");
		price2.setSalePrice("28.00");
		price2.setListPrice("28.00");
		price2.setMsrp("28.00");
		price2.setCurrency("$");
		price2.setGwpEligible("false");
		price2.setIsOnClerance("false");
		price2.setPriceId("10002");
		price2.setPricePoint("");
		price2.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price2.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
  	    
		skuPrices.put("30064566", price2);
		PrcSkuResponsePayload prcSkuRespPyld = prcRespPayloadGenerator.genrtePrcSkuBulkPayload(skuIdsList, skuPrices);
		assertNotNull(" Unable to generate SKU respPayLoad  ", prcSkuRespPyld);
		assertNotNull(" Unable to generate SKU respPayLoad  ", prcSkuRespPyld.getPriceSkuResponsePayload());
		assertNotNull(" Unable to generate SKU respPayLoad  ", prcSkuRespPyld.getPriceSkuResponsePayload().getSkus());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getSkuId());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getSalePrice());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getListPrice());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getMsrp());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getPriceStartDate());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getPriceEndDate());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getPromoMessage());

		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getPomoId());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getPomoPrice());
		assertNotNull(" Unable to generate SKU respPayLoad ",
				prcSkuRespPyld.getPriceSkuResponsePayload().getSkus().get(0).getPriceId());
	}

	@Test
	public void genrtePrcPrdBulkPayload() {
		List<String> productIds = new ArrayList<>();
		productIds.add("6862291");
		productIds.add("6862287");
		Map<String, Map<String, SkuPrice>> productPrcs = new HashMap<>();
		Map<String, SkuPrice> skusPrc1 = new HashMap<>();
		SkuPrice price1 = new SkuPrice();
		price1.setSkuId("30064508");
		price1.setSalePrice("10.40");
		price1.setListPrice("14.90");
		price1.setMsrp("14.90");
		price1.setCurrency("$");
		price1.setGwpEligible("false");
		price1.setIsOnClerance("false");
		price1.setPomoId("Prom1232");
		price1.setPomoPrice("10.40");
		price1.setPriceId("10001");
		price1.setPricePoint("");
		price1.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price1.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		price1.setPromoMessage("Limited time 30% off!");
		skusPrc1.put("30064508", price1);

		SkuPrice price2 = new SkuPrice();
		price2.setSkuId("30064566");
		price2.setSalePrice("30.00");
		price2.setListPrice("30.00");
		price2.setMsrp("30.00");
		price2.setCurrency("$");
		price2.setGwpEligible("false");
		price2.setIsOnClerance("false");
		price2.setPriceId("10002");
		price2.setPricePoint("");
		price2.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price2.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		skusPrc1.put("30064566", price2);
		productPrcs.put("6862291", skusPrc1);

		Map<String, SkuPrice> skusPrc2 = new HashMap<>();
		SkuPrice price3 = new SkuPrice();
		price3.setSkuId("12207723");
		price3.setSalePrice("43.00");
		price3.setListPrice("43.00");
		price3.setMsrp("43.00");
		price3.setCurrency("$");
		price3.setGwpEligible("false");
		price3.setIsOnClerance("false");
		price3.setPriceId("10003");
		price3.setPricePoint("");
		price3.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price3.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		skusPrc2.put("12207723", price3);
		SkuPrice price4 = new SkuPrice();
		price4.setSkuId("12207765");
		price4.setSalePrice("43.00");
		price4.setListPrice("43.00");
		price4.setMsrp("43.00");
		price4.setCurrency("$");
		price4.setGwpEligible("false");
		price4.setIsOnClerance("false");
		price4.setPriceId("10003");
		price4.setPricePoint("");
		price4.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price4.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		skusPrc2.put("12207765", price4);
		productPrcs.put("6862291", skusPrc1);
		productPrcs.put("6862287", skusPrc2);
		PrcProductResponsePayload bulkResp = prcRespPayloadGenerator.genrtePrcProductBulkPayload(productIds,
				productPrcs);
		assertNotNull(" Unable to generate SKU respPayLoad  ", bulkResp);
		assertNotNull(" Unable to generate SKU respPayLoad  ", bulkResp.getPriceProductResponsePayload());
		assertNotNull(" Unable to generate SKU respPayLoad  ", bulkResp.getPriceProductResponsePayload().getProducts());
		assertTrue(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().size() > 0);
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(0));
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(1));
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(0).getSkuList());
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(1).getSkuList());
		assertTrue(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(0).getSkuList().size() > 0);
		assertTrue(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(1).getSkuList().size() > 0);

	}

	@Test
	public void genrtePrcPrdPayload() {
		List<String> productIds = new ArrayList<>();
		productIds.add("6862291");
		productIds.add("6862287");
		Map<String, Map<String, SkuPrice>> productPrcs = new HashMap<>();
		Map<String, SkuPrice> skusPrc1 = new HashMap<>();
		SkuPrice price1 = new SkuPrice();
		price1.setSkuId("30064508");
		price1.setSalePrice("10.40");
		price1.setListPrice("14.90");
		price1.setMsrp("14.90");
		price1.setCurrency("$");
		price1.setGwpEligible("false");
		price1.setIsOnClerance("false");
		price1.setPomoId("Prom1232");
		price1.setPomoPrice("10.40");
		price1.setPriceId("10001");
		price1.setPricePoint("");
		price1.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price1.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		price1.setPromoMessage("Limited time 30% off!");
		skusPrc1.put("30064508", price1);

		SkuPrice price2 = new SkuPrice();
		price2.setSkuId("30064566");
		price2.setSalePrice("30.00");
		price2.setListPrice("30.00");
		price2.setMsrp("30.00");
		price2.setCurrency("$");
		price2.setGwpEligible("false");
		price2.setIsOnClerance("false");
		price2.setPriceId("10002");
		price2.setPricePoint("");
		price2.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price2.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		skusPrc1.put("30064566", price2);
		productPrcs.put("6862291", skusPrc1);

		Map<String, SkuPrice> skusPrc2 = new HashMap<>();
		SkuPrice price3 = new SkuPrice();
		price3.setSkuId("12207723");
		price3.setSalePrice("43.00");
		price3.setListPrice("43.00");
		price3.setMsrp("43.00");
		price3.setCurrency("$");
		price3.setGwpEligible("false");
		price3.setIsOnClerance("false");
		price3.setPriceId("10003");
		price3.setPricePoint("");
		price3.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price3.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		skusPrc2.put("12207723", price3);
		SkuPrice price4 = new SkuPrice();
		price4.setSkuId("12207765");
		price4.setSalePrice("43.00");
		price4.setListPrice("43.00");
		price4.setMsrp("43.00");
		price4.setCurrency("$");
		price4.setGwpEligible("false");
		price4.setIsOnClerance("false");
		price4.setPriceId("10003");
		price4.setPricePoint("");
		price4.setPriceStartDate(new Date(System.currentTimeMillis()-24*30*60*60*1000));
		price4.setPriceEndDate(new Date(System.currentTimeMillis()+24*30*60*60*1000));
  	    
		
		skusPrc2.put("12207765", price4);
		productPrcs.put("6862291", skusPrc1);
		productPrcs.put("6862287", skusPrc2);
		PrcProductResponsePayload bulkResp = prcRespPayloadGenerator.genrtePrcProductBulkPayload(productIds,
				productPrcs);
		assertNotNull(" Unable to generate SKU respPayLoad  ", bulkResp);
		assertNotNull(" Unable to generate SKU respPayLoad  ", bulkResp.getPriceProductResponsePayload());
		assertNotNull(" Unable to generate SKU respPayLoad  ", bulkResp.getPriceProductResponsePayload().getProducts());
		assertTrue(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().size() > 0);
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(0));
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(1));
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(0).getSkuList());
		assertNotNull(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(1).getSkuList());
		assertTrue(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(0).getSkuList().size() > 0);
		assertTrue(" Unable to generate SKU respPayLoad  ",
				bulkResp.getPriceProductResponsePayload().getProducts().get(1).getSkuList().size() > 0);

	}

	@Test
	public void testPrcPrdPayldWithEmptyVals() {
		Map<String, SkuPrice> skusPrices = new HashMap<>();
		PrcProductResponsePayload prdRespLoad = prcRespPayloadGenerator.generatePrcProductPayload("6862291",
				skusPrices);
		assertTrue(" Unable to generate SKU respPayLoad  ",
				prdRespLoad.getPriceProductResponsePayload().getProducts().get(0).getErrors().size() == 1);

	}
}
