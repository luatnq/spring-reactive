package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    void createProduct(ProductDTO productDTO);

    Mono<ProductDTO> getById(String id);

    Mono<ProductDTO> editProduct(ProductDTO productDTO);

    Flux<ProductDTO> getAll();

    Flux<ProductDTO> getByProductName(String productName);

    Flux<ProductDTO> getByPrice(long priceMin, long priceMax);

    Flux<ProductDTO> getByUid(String uid);

    Flux<ProductDTO> getByPublisherName(String publisherName);

    Mono<Void> deleteById(String id);
}
