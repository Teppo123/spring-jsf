package com.example;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
public class AppConfig {

	public AppConfig() {
		// default empty constructor
	}

	@Bean
	@ApplicationScope
	public ClientHttpRequestFactory clientHttpRequestFactory() {
		int timeout = 125;
		int maxTotalConn = 200;
		int maxPerRoute = 100;

		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(maxTotalConn);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout).build();
		CloseableHttpClient httpClient = HttpClientBuilder.create()
				.setConnectionManager(poolingHttpClientConnectionManager).setDefaultRequestConfig(config).build();
		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}
	
	@Bean
	public MessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("MessageBundle");
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}

}
