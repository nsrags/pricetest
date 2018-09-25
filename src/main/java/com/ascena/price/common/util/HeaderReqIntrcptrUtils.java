package com.ascena.price.common.util;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.stereotype.Component;

import com.ascena.price.common.constant.PriceConstants;

@Component
/**
 * Header Request Interceptor to add custom headers
 * 
 * @author SMeenavalli
 *
 */
public class HeaderReqIntrcptrUtils implements ClientHttpRequestInterceptor {
	
	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body, final ClientHttpRequestExecution execution)
			throws IOException {
		
		HttpRequestWrapper requestWrapper = new HttpRequestWrapper(request);
		//Add common Http Headers for REST calls 
		HttpHeaders headers = requestWrapper.getHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setConnection(PriceConstants.KEEP_ALIVE.toString());
		headers.add(PriceConstants.ACCEPT_ENCODING.toString(), PriceConstants.GZIP.toString());
		return execution.execute(requestWrapper, body);
	}
}
