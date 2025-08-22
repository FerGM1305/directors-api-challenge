package com.movies.directors.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * This class is responsible for configuring the RestClient bean
 * that will be used across the application to communicate with
 * the external Movie API.
 * @author feres
 */
@Configuration
public class RestClientConfig {
	//Creates and registers a RestClient bean with Spring's context.
    @Bean
    RestClient restClient(RestClient.Builder builder,@Value("${movies.api.base-url:https://wiremock.dev.eroninternational.com}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }
}
