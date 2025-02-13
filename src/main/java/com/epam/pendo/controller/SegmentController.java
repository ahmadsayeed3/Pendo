package com.epam.pendo.controller;

import com.epam.pendo.dto.request.CreateSegmentRequest;
import com.epam.pendo.dto.response.CreateSegmentResponse;
import com.epam.pendo.service.SegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/segment")
public class SegmentController {

    @Autowired
    private SegmentService segmentService;

    @PostMapping("/create")
    public Mono<CreateSegmentResponse> createSegment(@RequestBody CreateSegmentRequest request) {
        return segmentService.create(request);
    }


}
