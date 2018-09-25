package com.ascena.price.service.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import com.ascena.price.app.PriceApplication;
import com.ascena.price.exceptions.PriceIntegrationException;
import com.ascena.price.service.integration.vo.ProductSkuList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class PrcProductInfoIntgTest {
	private MockRestServiceServer mockServer;
	private RestTemplate restTemplate;
	
	@Autowired
	PrcProductInfoIntg productInfoIntg;
	
	@Before
	public void setup() {
		this.restTemplate = new RestTemplate();
		this.mockServer = MockRestServiceServer.bindTo(this.restTemplate).ignoreExpectOrder(true).build();
	}

	@Test
	public void performGet() {

		String responseBody = "{\r\n" + 
				"    \"activeSkuList\": [\r\n" + 
				"        {\r\n" + 
				"            \"skuId\": \"30064508\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"skuId\": \"30064566\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"skuId\": \"30064582\"\r\n" + 
				"        },\r\n" + 
				"        {\r\n" + 
				"            \"skuId\": \"3006460511\"\r\n" + 
				"        }\r\n" + 
				"    ],\r\n" + 
				"    \"error\": []\r\n" + 
				"}";

		this.mockServer.expect(requestTo("/productinfo/skulist/2260951")).andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON));

		
		ProductSkuList skus = this.restTemplate.getForObject("/productinfo/skulist/{id}", ProductSkuList.class,2260951);

		assertNotNull("Failed to call Product Info SKU List service ", skus);
		assertNotNull("Failed to call Product Info SKU List service ", skus.getActiveSkuList());
		assertTrue("Failed to call Product Info SKU List service ", skus.getActiveSkuList().size() > 0);
		
		this.mockServer.verify();
		
		
		
	}
	
	@Test(expected = Exception.class)
	public void testIntegrationException() throws PriceIntegrationException  {
		
			productInfoIntg.getActiveSkus("2260951");
		
	}

}
