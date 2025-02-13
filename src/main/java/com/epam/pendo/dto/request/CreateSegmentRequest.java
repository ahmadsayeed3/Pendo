package com.epam.pendo.dto.request;


import java.util.List;

public record CreateSegmentRequest(String name, List<String> visitors) {
}
