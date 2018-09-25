package com.ascena.price.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.ascena.price.common.constant.PriceConstants;

@Configuration
@EnableWebSecurity
/**
 * authorize requests to permit all requests 
 * @author SMeenavalli
 *
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers(PriceConstants.FORWARD_SLASH.toString()).permitAll();
	}
}
