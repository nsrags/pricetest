package com.ascena.price.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.ascena.price.common.util.HeaderReqIntrcptrUtils;

@Configuration
@RefreshScope
/**
 * Configuration to create RestTemplate
 * 
 * @author SMeenavalli
 *
 */
public class RestTemplateConfig {

	@Autowired
	ApplicationConfig appConfig;

	/**
	 * 
	 * @return RestTemplate
	 */
	@Bean
	@RefreshScope
	RestTemplate restTemplate() {

		HttpComponentsClientHttpRequestFactory httpRequestFactory = createHttpRequestFactory(
				Integer.parseInt(appConfig.getRequestTimeout()), Integer.parseInt(appConfig.getConnectTimeout()));

		RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderReqIntrcptrUtils());
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

	/**
	 * createHttpRequestFactory
	 * @param requestTimeout int
	 * @param connectTimeout int
	 * @return HttpComponentsClientHttpRequestFactory
	 */
	HttpComponentsClientHttpRequestFactory createHttpRequestFactory(final int requestTimeout, final int connectTimeout) {
		RequestConfig.Builder requestBuilder = RequestConfig.custom();

		// Set Request and Socket timeouts
		requestBuilder = requestBuilder.setConnectTimeout(requestTimeout);
		requestBuilder = requestBuilder.setSocketTimeout(connectTimeout);
		final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		httpClientBuilder.setDefaultRequestConfig(requestBuilder.build());

		// Set Http Client Connection Pooling configurations
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(Integer.parseInt(appConfig.getMaxTotal()));
		connectionManager.setDefaultMaxPerRoute(Integer.parseInt(appConfig.getDefaultMaxPerRoute()));
		httpClientBuilder.setConnectionManager(connectionManager);
		CloseableHttpClient httpClient = httpClientBuilder.build();
		final HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setHttpClient(httpClient);
		return httpRequestFactory;
	}

}
