package com.pongolino.study.reactive.routes.routesHandlers;

import com.pongolino.study.reactive.domain.Review;
import com.pongolino.study.reactive.routesHandlers.ReviewHandler;
import com.pongolino.study.reactive.routesHandlers.dto.ReviewAddRequest;
import com.pongolino.study.reactive.routesHandlers.dto.ReviewCreationResponse;
import com.pongolino.study.reactive.service.ReviewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;


@WebFluxTest
@ContextConfiguration(classes = {ReviewHandler.class, ReviewsService.class})
@AutoConfigureWebTestClient
class ReviewHandlerTest {

    private final String REVIEWS_ENDPOINT = "/1/reviews";

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ReviewsService reviewsService;

    @Test
    void createReview() {
        final String EXPECTED_ID = "abc";
        final String EXPECTED_COMMENT = "Good";
        ReviewAddRequest request = new ReviewAddRequest(EXPECTED_COMMENT, 10.0);

        when(reviewsService.save(isA(Review.class))).thenReturn(
                Mono.just(new Review(EXPECTED_ID, 123L, request.getComment(), request.getRating()))
        );

        webTestClient
            .post()
                .uri(REVIEWS_ENDPOINT)
                .body(request, ReviewAddRequest.class)
            .exchange()
            .expectStatus()
                .isCreated()
            .expectBody(ReviewCreationResponse.class)
            .consumeWith(result -> {
                ReviewCreationResponse responseBody = result.getResponseBody();
                assertNotNull(responseBody);
                assertNotNull(responseBody.getReviewId());
                assertNotNull(responseBody.getMovieInfoId());
                assertEquals(EXPECTED_ID, responseBody.getReviewId());
                assertEquals(EXPECTED_COMMENT, responseBody.getComment());
            });
    }
}