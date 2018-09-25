package com.ascena.price.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.ascena.price.app.PriceApplication;
import com.ascena.price.vo.product.request.PrcProductRequestPayload;
import com.ascena.price.vo.product.request.ProductRequest;
import com.ascena.price.vo.sku.request.PrcSkuRequestPayload;
import com.ascena.price.vo.sku.request.SkuRequest;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
public class BulkPriceControllerTest {
	private Closeable session;

	@Before
	public void init() {
		session = ObjectifyService.begin();
	}

	@InjectMocks
	private BulkPriceController bulkPriceController;

	private MockMvc mockMvc;

	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext context;

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@Test
	public void testProductBulkBadRequest() throws Exception {

		PrcProductRequestPayload bulkRequest = new PrcProductRequestPayload();
		List<ProductRequest> productRequestList = new ArrayList<>();
		ProductRequest product1 = new ProductRequest();
		product1.setProductId("Prd-122211");
		ProductRequest product2 = new ProductRequest();
		product1.setProductId("1111");
		productRequestList.add(product1);
		productRequestList.add(product2);
		bulkRequest.serPrcProductRequestPayload(productRequestList);

		RequestBuilder requestBuilder = post("/price/products").content(this.json(bulkRequest))
				.contentType(contentType);

		ResultActions resultActions = mockMvc.perform(requestBuilder);
		resultActions.andExpect(status().isBadRequest());// 400 bad request

	}

	@Test
	public void testSkuBulkBadRequest() throws Exception {

		PrcSkuRequestPayload skuBulkRequest = new PrcSkuRequestPayload();
		List<SkuRequest> skuRequestList = new ArrayList<>();
		SkuRequest sku1 = new SkuRequest();
		sku1.setSkuId("sku122211");
		SkuRequest sku2 = new SkuRequest();
		sku1.setSkuId("3232323");
		skuRequestList.add(sku1);
		skuRequestList.add(sku2);
		skuBulkRequest.setPriceSkuRequest(skuRequestList);

		RequestBuilder requestBuilder = post("/price/skus").content(this.json(skuBulkRequest)).contentType(contentType);

		ResultActions resultActions = mockMvc.perform(requestBuilder);
		resultActions.andExpect(status().isBadRequest());// 400 bad request

	}

	@Test
	public void testSkuBulkSucessRequest() throws Exception {

		PrcSkuRequestPayload skuBulkRequest = new PrcSkuRequestPayload();
		List<SkuRequest> skuRequestList = new ArrayList<>();
		SkuRequest sku1 = new SkuRequest();
		sku1.setSkuId("30064566");
		SkuRequest sku2 = new SkuRequest();
		sku2.setSkuId("30064111");
		skuRequestList.add(sku1);
		skuRequestList.add(sku2);
		skuBulkRequest.setPriceSkuRequest(skuRequestList);

		RequestBuilder requestBuilder = post("/price/skus").content(this.json(skuBulkRequest))
				.contentType(contentType);

		ResultActions resultActions = mockMvc.perform(requestBuilder);
		resultActions.andExpect(status().isOk());// 200 Success
	}

	/**
	 * 
	 * @param o
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	protected String json(Object object) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(object, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}

	@After
	public void destroy() {
		session.close();
	}
}
