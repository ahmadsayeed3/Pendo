package com.epam.pendo.service;

import com.epam.pendo.dto.request.CreateSegmentRequest;
import com.epam.pendo.dto.response.CreateSegmentResponse;
import reactor.core.publisher.Mono;

public interface SegmentService {

    Mono<CreateSegmentResponse> create(CreateSegmentRequest request);
}
