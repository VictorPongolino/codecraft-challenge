package com.pongolino.study.reactive.routesHandlers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewUpdateRequest {
    private Long movieInfoId;
    private String comment;
    private Double rating;
}
