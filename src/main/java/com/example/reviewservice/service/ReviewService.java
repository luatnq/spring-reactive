package com.example.reviewservice.service;

import com.example.reviewservice.dto.ReviewDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ReviewService {

    public void addReview(ReviewDTO reviewDTO);

    public Mono<ReviewDTO> editReview(ReviewDTO reviewDTO);

    public Flux<ReviewDTO> findAllByProductId(String productId);

    public Flux<ReviewDTO> findAll();

    public Mono<Void> delete(String id);
}
