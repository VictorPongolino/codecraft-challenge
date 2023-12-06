package com.pongolino.study.reactive.routesHandlers.dto;

import lombok.Builder;

@Builder(setterPrefix = "with")
public class UpdateReviewResponse {
    private String reviewId;
    private Long movieInfoId;
    private String comment;
    private Double rating;
}
