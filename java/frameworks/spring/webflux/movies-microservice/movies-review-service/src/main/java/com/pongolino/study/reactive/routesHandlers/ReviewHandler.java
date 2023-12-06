package com.pongolino.study.reactive.routesHandlers;

import com.pongolino.study.reactive.domain.Review;
import com.pongolino.study.reactive.routesHandlers.dto.ReviewAddRequest;
import com.pongolino.study.reactive.routesHandlers.dto.ReviewCreationResponse;
import com.pongolino.study.reactive.routesHandlers.dto.ReviewResponse;
import com.pongolino.study.reactive.service.ReviewsService;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ReviewHandler {

    private final ReviewsService reviewsService;


    public Mono<ServerResponse> createReview(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ReviewAddRequest.class)
            .flatMap(request -> {
                Review review = new Review();
                review.setComment(request.getComment());
                review.setRating(request.getRating());
                return reviewsService.save(review);
            }).flatMap(response -> {
                    ReviewCreationResponse creationResponse = ReviewCreationResponse.builder()
                            .withMovieInfoId(response.getMovieInfoId())
                            .withReviewId(response.getReviewId())
                            .withComment(response.getComment())
                            .withRating(response.getRating())
                            .build();

                return ServerResponse.created(serverRequest.uriBuilder().path("/{id}").build(creationResponse.getReviewId())).build();
            });
    }

    public Mono<ServerResponse> getAllReviews(ServerRequest serverRequest) {
        Mono<List<ReviewResponse>> serverResponseFlux = reviewsService.findAll().flatMap(response -> {
            ReviewResponse reviewResponse = ReviewResponse.builder()
                    .withReviewId(response.getReviewId())
                    .withMovieInfoId(response.getMovieInfoId())
                    .withComment(response.getComment())
                    .withRating(response.getRating())
                    .build();
            return Mono.just(reviewResponse);
        }).collectList();

        return serverResponseFlux.flatMap(result -> ServerResponse.ok().body(result, ReviewResponse.class));
    }



}

