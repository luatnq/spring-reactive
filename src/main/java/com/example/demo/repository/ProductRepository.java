package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
    Flux<Product> findAllByProductName(String productName);

    Flux<Product> findByPriceBetweenOrderByPriceDesc(long priceMin, long priceMax);

    Flux<Product> findAllByPublisherName(String publisherName);

    Flux<Product> findAllByUidOrderByCreatedDateDesc(String uid);

}
