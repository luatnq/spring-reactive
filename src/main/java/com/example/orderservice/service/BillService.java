package com.example.orderservice.service;


import com.example.orderservice.dto.BillDTO;
import com.example.orderservice.entity.Bill;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BillService {
    public void addBill(BillDTO billDTO);

    public Mono<BillDTO> editBill(BillDTO billDTO);

    public Mono<Void> deleteById(String id);

    public Flux<BillDTO> findAllByUid (String uid);

    public Flux<BillDTO> findALl();
}
