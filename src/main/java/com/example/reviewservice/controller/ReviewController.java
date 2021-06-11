package com.example.reviewservice.controller;

import com.example.reviewservice.dto.ReviewDTO;
import com.example.reviewservice.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin
@Slf4j
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createReview(@RequestBody ReviewDTO reviewDTO){
        reviewService.addReview(reviewDTO);
    }

    @PutMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<ReviewDTO> updateReview(@RequestBody ReviewDTO reviewDTO){
        return reviewService.editReview(reviewDTO);
    }

    @GetMapping(produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    @ResponseBody
    public Flux<ReviewDTO> findALl(){
        return reviewService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable String id){
        return reviewService.delete(id);
    }
}
