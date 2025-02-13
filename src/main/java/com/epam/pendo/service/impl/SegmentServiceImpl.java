package com.epam.pendo.service.impl;

import com.epam.pendo.dto.PendoIntegration;
import com.epam.pendo.dto.request.CreateSegmentRequest;
import com.epam.pendo.dto.response.CreateSegmentResponse;
import com.epam.pendo.service.SegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SegmentServiceImpl implements SegmentService {

    @Autowired
    private WebClient webClient;

    @Autowired
    private PendoIntegration pendoIntegration;

    private static final Logger log = LoggerFactory.getLogger(SegmentServiceImpl.class);

    @Override
    public Mono<CreateSegmentResponse> create(CreateSegmentRequest request) {
        log.info("Initiating segment creation with request: {}", request);

        return webClient.post()
                .uri(pendoIntegration.baseUrl() + "/api/v1//segment/upload")
                .header("Content-Type", "application/json")
                .header("x-pendo-integration-key", pendoIntegration.apiKey())
                .bodyValue(request)  // Pass the request object directly
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        clientResponse -> {
                            // Log error details on status failure
                            log.error("API request failed with status: {}", clientResponse.statusCode());
                            return Mono.error(new RuntimeException("API request failed"));
                        })
                .bodyToMono(CreateSegmentResponse.class)
                .doOnSubscribe(subscription -> log.debug("Sending request to Pendo API to create segment"));

    }
}
