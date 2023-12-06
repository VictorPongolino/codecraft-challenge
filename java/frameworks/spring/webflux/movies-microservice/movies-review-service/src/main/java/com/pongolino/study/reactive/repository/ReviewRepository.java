package com.pongolino.study.reactive.repository;

import com.pongolino.study.reactive.domain.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
}
