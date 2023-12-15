package com.pongolino.study.reactive.service;

import com.pongolino.study.reactive.domain.Review;
import com.pongolino.study.reactive.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = false)
    public Mono<Review> save(Review review) {
        return reviewRepository.save(review);
    }

    public Flux<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Mono<Review> findById(String id) {
        return reviewRepository.findById(id);
    }
    public Flux<Review> findByMovieInfoId(String movieInfoId) {
        return reviewRepository.findByMovieInfoId(movieInfoId);
    }

    @Transactional(readOnly = false)
    public Mono<Void> deleteById(String id) {
        return reviewRepository.deleteById(id);
    }
}
