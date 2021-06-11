package com.example.reviewservice.mapper;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.entity.Review;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ReviewMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static Review convertToEntity(ReviewDTO reviewDTO) {
        return modelMapper.map(reviewDTO, Review.class);
    }

    public static Mono<ReviewDTO> convertToDTO(Mono<Review> reviewMono) {
        return reviewMono.map(review -> ReviewDTO.builder()
                .id(review.getId())
                .username(review.getUsername())
                .content(review.getContent())
                .productId(review.getProductId())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updateAt(review.getUpdateAt())
                .build());
    }

    public static Flux<ReviewDTO> convertToDTOs(Flux<Review> reviewFlux) {
        return reviewFlux.map(review -> ReviewDTO.builder()
                .id(review.getId())
                .username(review.getUsername())
                .content(review.getContent())
                .productId(review.getProductId())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt())
                .updateAt(review.getUpdateAt())
                .build());
    }
}
