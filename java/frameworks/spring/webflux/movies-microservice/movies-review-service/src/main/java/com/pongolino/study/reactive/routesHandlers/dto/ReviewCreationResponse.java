package com.pongolino.study.reactive.routesHandlers.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(setterPrefix = "with")
@Getter
public class ReviewCreationResponse {

    private String reviewId;
    private Long movieInfoId;
    private String comment;
    private Double rating;
}
