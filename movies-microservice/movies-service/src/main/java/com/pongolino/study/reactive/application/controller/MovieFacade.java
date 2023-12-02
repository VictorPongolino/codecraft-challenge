package com.pongolino.study.reactive.application.controller;

import com.pongolino.study.reactive.application.controller.dto.MovieUpdate;
import com.pongolino.study.reactive.application.controller.dto.MovieUpdateResult;
import com.pongolino.study.reactive.domain.service.MovieInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
public class MovieFacade {

    private final MovieInfoService movieInfoService;

    public MovieFacade(MovieInfoService movieInfoService) {
        this.movieInfoService = movieInfoService;
    }

    @Transactional(readOnly = false)
    public Mono<MovieUpdateResult> updateMovieInfo(MovieUpdate movieUpdate, String id) {
        var data = movieInfoService.findById(id).flatMap(result -> {
            result.setName(movieUpdate.getName());
            result.setYear(movieUpdate.getYear());
            result.setCast(movieUpdate.getCast());
            result.setRelease_date(movieUpdate.getRelease_date());
            return movieInfoService.save(result);
        });

        return data.map(result -> new MovieUpdateResult(result.getName(), result.getYear(), result.getCast(), result.getRelease_date()));
    }
}
