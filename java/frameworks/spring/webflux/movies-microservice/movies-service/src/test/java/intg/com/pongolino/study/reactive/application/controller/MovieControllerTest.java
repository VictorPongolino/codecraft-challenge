package com.pongolino.study.reactive.application.controller;

import com.pongolino.study.reactive.application.controller.dto.MovieCreationRequest;
import com.pongolino.study.reactive.application.controller.dto.MovieShowResult;
import com.pongolino.study.reactive.domain.entity.Movie;
import com.pongolino.study.reactive.domain.repository.MovieInfoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebTestClient
class MovieControllerTest {

    @Autowired
    private MovieInfoRepository movieInfoRepository;
    @Autowired
    private WebTestClient webTestClient;

    private String lastResultId;

    @BeforeEach
    @Transactional(readOnly = false)
    void setUp() {
        var standard = List.of(
                new Movie(null, "Avatar 2", 2023, Arrays.asList("Someone"), LocalDate.of(2022,11,0)),
                new Movie(null, "Avatar 1", 2012, Arrays.asList("Again Someone"), LocalDate.of(2012,11,0)),
                new Movie(null, "Super Mario", 2023, Arrays.asList("Nitendo"), LocalDate.of(2023,1,0))
        );

        lastResultId = movieInfoRepository.saveAll(standard).blockLast().getMovieInfoId();
    }

    @AfterEach
    void tearDown() {
        movieInfoRepository.deleteAll();
    }

    @Test
    void showAll() {
        webTestClient
            .get()
            .uri("/v1/movie")
            .exchange()
                .expectStatus()
                .isOk()
            .expectBodyList(MovieShowResult.class)
                .hasSize(3);
    }

    @Test
    void showById() {
        webTestClient
            .get()
            .uri("/1/movie/{id}", lastResultId)
            .exchange()
                .expectStatus()
                .isOk()
            .expectBody(MovieShowResult.class)
            .consumeWith(result -> {
                MovieShowResult responseBody = result.getResponseBody();
                assert responseBody != null;
                assert responseBody.getId().equals(lastResultId);
            });
    }

    @Test
    @DisplayName("Create a movie info")
    void addMovieInfo() {
        MovieCreationRequest request = new MovieCreationRequest();
        request.setName("Avatar");
        request.setCast(List.of("Someone"));
        request.setYear(2023);
        request.setRelease_date(LocalDate.of(2023, 11, 28));

        webTestClient
            .post()
                .uri("/1/movie")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
            .exchange()
                .expectStatus()
                .isCreated()
            .expectBody(Movie.class)
            .consumeWith(result -> {
                Movie body = result.getResponseBody();
                assert body != null;
                assert body.getMovieInfoId() != null;
            });
    }
}