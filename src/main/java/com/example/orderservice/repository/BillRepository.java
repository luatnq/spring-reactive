package com.example.orderservice.repository;

import com.example.orderservice.entity.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BillRepository extends ReactiveMongoRepository<Bill, String> {
    Flux<Bill> findAllByUidOrderByCreatedAtDesc(String uid);

    Flux<Bill> findAllByOrderByCreatedAtDesc();
}
