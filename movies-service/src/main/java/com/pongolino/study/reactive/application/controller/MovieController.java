package com.pongolino.study.reactive.application.controller;

import com.pongolino.study.reactive.application.controller.dto.*;
import com.pongolino.study.reactive.domain.entity.Movie;
import com.pongolino.study.reactive.domain.service.MovieInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/1")
public class MovieController {

    private final MovieInfoService movieInfoService;
    private final MovieFacade movieFacade;

    public MovieController(MovieInfoService movieInfoService, MovieFacade movieFacade) {
        this.movieInfoService = movieInfoService;
        this.movieFacade = movieFacade;
    }

    @GetMapping("/movie")
    public Flux<MovieShowResult> showAllMovieInfo() {
        return movieInfoService.listAll().map(result -> {
            MovieShowResult response = new MovieShowResult();
            response.setId(result.getMovieInfoId());
            response.setName(result.getName());
            response.setYear(result.getYear());
            response.setCast(result.getCast());
            response.setRelease_date(result.getRelease_date());

            return response;
        });
    }

    @GetMapping("/movie/{id}")
    public Mono<MovieShowResult> findByMovieInfoId(@PathVariable("id") String id) {
        return movieInfoService.findById(id).map(result -> {
            MovieShowResult response = new MovieShowResult();
            response.setId(result.getMovieInfoId());
            response.setName(result.getName());
            response.setYear(result.getYear());
            response.setCast(result.getCast());
            response.setRelease_date(result.getRelease_date());

            return response;
        });
    }

    @PutMapping("/movie/{id}")
    public Mono<MovieUpdateResult> updateMovie(@RequestBody MovieUpdate movieUpdate, @PathVariable("id") String id) {
        return movieFacade.updateMovieInfo(movieUpdate, id);
    }

    @DeleteMapping("/movie/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteMovie(@RequestParam("id") String id) {
        return movieInfoService.deleteById(id);
    }

    @PostMapping("/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<MovieCreationResponse> addMovieInfo(@RequestBody MovieCreationRequest request) {
        Movie movie = new Movie(null, request.getName(), request.getYear(), request.getCast(), request.getRelease_date());
        return movieInfoService.save(movie).map(result -> {
            MovieCreationResponse response = new MovieCreationResponse();
            response.setName(result.getName());
            response.setYear(result.getYear());
            response.setCast(result.getCast());
            response.setRelease_date(result.getRelease_date());

            return response;
        });
    }
}

