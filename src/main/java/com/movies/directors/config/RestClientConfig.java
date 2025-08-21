package com.movies.directors.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 *
 * @author feres
 */
@Configuration
public class RestClientConfig {
    @Bean
    RestClient restClient(RestClient.Builder builder,@Value("${movies.api.base-url:https://wiremock.dev.eroninternational.com}") String baseUrl) {
        return builder.baseUrl(baseUrl).build();
    }
}
