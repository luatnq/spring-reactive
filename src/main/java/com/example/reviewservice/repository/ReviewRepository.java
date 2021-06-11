package com.example.reviewservice.repository;

import com.example.reviewservice.entity.Review;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ReviewRepository extends ReactiveMongoRepository<Review, String> {
    Flux<Review> findByProductIdOrderByCreatedAtDesc(String productId);

    Flux<Review> findAllByOrderByCreatedAtDesc();
}
