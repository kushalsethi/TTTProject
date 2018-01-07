package com.kushals.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfiguration {

	@Value("${fileurl}")
	private String fileUrl;

	@Value("${proxyserver}")
	private String proxyServer;

	@Value("${proxyhost}")
	private int proxyHost;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public String getProxyServer() {
		return proxyServer;
	}

	public int getProxyHost() {
		return proxyHost;
	}
}
