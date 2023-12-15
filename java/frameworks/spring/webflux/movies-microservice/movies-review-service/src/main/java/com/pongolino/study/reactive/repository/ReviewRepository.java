package com.pongolino.study.reactive.repository;

import com.pongolino.study.reactive.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
    Flux<Review> findByMovieInfoId(String movieInfoId);
}
