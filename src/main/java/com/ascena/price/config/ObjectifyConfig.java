package com.ascena.price.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import com.ascena.price.dao.datastore.domain.Price;
import com.google.cloud.datastore.DatastoreOptions;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyFilter;
import com.googlecode.objectify.ObjectifyService;

/**
 * @author BEmani
 *
 */
@Configuration
@RefreshScope
public class ObjectifyConfig {

	@Autowired
	ApplicationConfig appConfig;

	@PostConstruct
	@RefreshScope
	public void init() {
		ObjectifyService.init(new ObjectifyFactory(
				DatastoreOptions.newBuilder().setProjectId(appConfig.getDatastoreProjectID())
						.setNamespace(appConfig.getDatastoreNamespace()).build().getService()));
		registerObjectifyEntities();

	}

	
	@EventListener
	public void onRefreshScopeRefreshed(final RefreshScopeRefreshedEvent event) {
		getClass();
	}

	@Bean
	@ConditionalOnMissingBean
	public ObjectifyFilter objectifyFilter() {
		return new ObjectifyFilter();
	}

	private void registerObjectifyEntities() {
		register(Price.class);
	}

	private void register(Class<?>... entityClasses) {
		Arrays.stream(entityClasses).forEach(ObjectifyService::register);
	}

}
