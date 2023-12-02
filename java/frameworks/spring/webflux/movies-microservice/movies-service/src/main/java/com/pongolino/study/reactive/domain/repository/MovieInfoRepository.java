package com.pongolino.study.reactive.domain.repository;

import com.pongolino.study.reactive.domain.entity.Movie;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieInfoRepository extends ReactiveMongoRepository<Movie, String> {
}
