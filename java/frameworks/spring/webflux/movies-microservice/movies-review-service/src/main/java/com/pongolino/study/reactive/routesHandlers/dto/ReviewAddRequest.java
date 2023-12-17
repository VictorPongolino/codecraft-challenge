package com.pongolino.study.reactive.routesHandlers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
public class ReviewAddRequest {
    @Max(value = 250)
    private String comment;
    @Positive @Max(value = 10)
    private Double rating;
}
