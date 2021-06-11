package com.example.reviewservice.service.impl;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.entity.Review;
import com.example.reviewservice.exception.ReviewNotFoundException;
import com.example.reviewservice.mapper.ReviewMapper;
import com.example.reviewservice.repository.ReviewRepository;
import com.example.reviewservice.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(ReviewDTO reviewDTO){
        Review review = ReviewMapper.convertToEntity(reviewDTO);
        reviewRepository.save(review).subscribe();
    }

    public Mono<ReviewDTO> editReview(ReviewDTO reviewDTO){
        Mono<Review> updatedReview =reviewRepository.findById(reviewDTO.getId())
                .switchIfEmpty(Mono.error(new ReviewNotFoundException(reviewDTO.getId())))
                .map(review -> {
                    review.setContent(reviewDTO.getContent());
                    review.setRating(reviewDTO.getRating());
                    review.setUpdateAt(Instant.now());
                    return review;
                }).flatMap(review -> reviewRepository.save(review));
        return ReviewMapper.convertToDTO(updatedReview);
    }

    public Flux<ReviewDTO> findAllByProductId(String productId){
        Flux<Review> reviews = reviewRepository
                .findByProductIdOrderByCreatedAtDesc(productId)
                .switchIfEmpty(Flux.error(new ReviewNotFoundException("")));
        return ReviewMapper.convertToDTOs(reviews);
    }

    public Flux<ReviewDTO> findAll(){
        Flux<Review> reviews = reviewRepository.findAllByOrderByCreatedAtDesc()
                .switchIfEmpty(Flux.error(new ReviewNotFoundException("")));
        return ReviewMapper.convertToDTOs(reviews);
    }

    @Override
    public Mono<Void> delete(String id) {
        return reviewRepository.deleteById(id);
    }
}
