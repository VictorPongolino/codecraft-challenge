package com.pongolino.study.reactive.routesHandlers.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public class ReviewResponse {
    private String reviewId;
    private Long movieInfoId;
    private String comment;
    private Double rating;
}
