package com.ascena.price.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.ascena.price.app.PriceApplication;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PriceApplication.class)
/**
 * Test class for Price Controller( Get By Sku Id & Product Id)
 * 
 * @author Smeenavalli
 *
 */
public class PriceControllerTest {

	private Closeable session;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));


	@Before
	public void init() {
		session = ObjectifyService.begin();
	}

	@InjectMocks
	private PriceController priceController;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void initTests() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	public void testWelcome() throws Exception {
		mockMvc.perform(get("/price/")).andExpect(status().isOk());
	}

	@Test
	public void testSkuValidationBadRequest() throws Exception {
		// SKU must be number
		RequestBuilder requestBuilder = get("/price/sku/abc").contentType(contentType);
		ResultActions resultActions = mockMvc.perform(requestBuilder);
		resultActions.andExpect(status().isBadRequest());// 400 bad request

	}

	@Test
	public void testProductValidationBadRequest() throws Exception {
		// SKU must be number
		RequestBuilder requestBuilder = get("/price/product/1123abc").contentType(contentType);
		ResultActions resultActions = mockMvc.perform(requestBuilder);
		resultActions.andExpect(status().isBadRequest());// 400 bad request

	}

	@Test
	public void testProductValidationSuccess() throws Exception {
		// SKU must be number
		RequestBuilder requestBuilder = get("/price/sku/111323232").contentType(contentType);
		ResultActions resultActions = mockMvc.perform(requestBuilder);
		MockHttpServletResponse response = resultActions.andReturn().getResponse();
		resultActions.andExpect(status().isInternalServerError());// 500 internal server error

	}

	@After
	public void destroy() {
		session.close();
	}
}
