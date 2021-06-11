package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Order;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    public void addOrder(OrderDTO orderDTO);

    public Flux<OrderDTO> findAllByUid (String uid);

    public Mono<OrderDTO> editOrder(OrderDTO orderDTO);

    public Mono<Void> deleteById(String id);
}
