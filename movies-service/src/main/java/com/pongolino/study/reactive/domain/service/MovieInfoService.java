package com.pongolino.study.reactive.domain.service;

import com.pongolino.study.reactive.domain.entity.Movie;
import com.pongolino.study.reactive.domain.repository.MovieInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieInfoService {

    private final MovieInfoRepository movieInfoRepository;

    public MovieInfoService(MovieInfoRepository movieInfoRepository) {
        this.movieInfoRepository = movieInfoRepository;
    }

    @Transactional(readOnly = false)
    public Mono<Movie> save(Movie movie) {
        return movieInfoRepository.save(movie);
    }

    public Flux<Movie> listAll() {
        return movieInfoRepository.findAll();
    }

    public Mono<Movie> findById(String id) {
        return movieInfoRepository.findById(id);
    }

    @Transactional(readOnly = false)
    public Mono<Void> deleteById(String id) {
        return movieInfoRepository.deleteById(id);
    }
}
