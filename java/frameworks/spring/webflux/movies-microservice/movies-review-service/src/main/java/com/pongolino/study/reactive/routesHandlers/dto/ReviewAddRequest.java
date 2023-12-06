package com.pongolino.study.reactive.routesHandlers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewAddRequest {
    private String comment;
    private Double rating;
}
