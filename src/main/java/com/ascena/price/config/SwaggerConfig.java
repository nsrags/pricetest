package com.ascena.price.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
/**
 * Configuration to generate Swagger UI
 * 
 * @author Ascena Micro Services
 *
 */
public class SwaggerConfig {
	@Bean
	/**
	 * 
	 * @return Docket
	 */
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	/**
	 * apiInfo for Swagger
	 * 
	 * @return ApiInfo
	 */
	private ApiInfo getApiInfo() {
		Contact contact = new Contact("Ascena Retail", "www.ascenaretail.com",
				"ABS-Ecomm-Microservices@AscenaRetail.com");

		return new ApiInfo("Price REST API", "REST API for Price by Product,SKU and Bulk requests.", "1.0",
				"Terms of service", contact, "License of API", "API license URL", Collections.emptyList());

	}

}