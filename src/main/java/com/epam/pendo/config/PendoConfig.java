package com.epam.pendo.config;

import com.epam.pendo.dto.PendoIntegration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PendoConfig {

    @Value("${pendo.api.url}")
    private String pendoApiUrl;

    @Value("${pendo.integration.key}")
    private String pendoIntegrationKey; // Add the Pendo integration key

    @Bean
    public PendoIntegration apiVariable(){
        return new PendoIntegration(pendoApiUrl, pendoIntegrationKey);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .build();
    }
}
