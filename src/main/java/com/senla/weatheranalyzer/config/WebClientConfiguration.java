package com.senla.weatheranalyzer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Value("${api.url}")
    private String url;
    @Value("${api.key}")
    private String key;
    @Value("${api.city}")
    private String city;
    @Value("${api.lang}")
    private String lang;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url + "?key=" + key + "&q=" + city + "&lang=" + lang)
                .build();
    }

}
