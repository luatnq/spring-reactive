package com.example.categoryservice.service;

import com.example.categoryservice.dto.CategoryDTO;
import org.reactivestreams.Publisher;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO);

    Flux<CategoryDTO> findAll();

    Mono<CategoryDTO> findByCategoryName(String categoryName);

    Mono<Void> deleteById(String id);
}
