package com.example.orderservice.service.impl;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(OrderDTO orderDTO) {
        Order order = OrderMapper.convertToEntity(orderDTO);
        orderRepository.save(order).subscribe();
    }

    public Flux<OrderDTO> findAllByUid(String uid) {
        Flux<Order> orders = orderRepository.findAllByUidOrderByCreatedAtDesc(uid)
                .switchIfEmpty(Mono.error(new OrderNotFoundException("")));
        return OrderMapper.convertToDTOs(orders);
    }

    public Mono<OrderDTO> editOrder(OrderDTO orderDTO) {
        Mono<Order> updateOrder = orderRepository.findById(orderDTO.getId())
                .map(order -> {
                    order.setQuantity(orderDTO.getQuantity());
                    order.setProduct(orderDTO.getProduct());
                    order.setUpdatedAt(Instant.now());
                    return order;
                }).flatMap(order -> orderRepository.save(order))
                .switchIfEmpty(Mono.error(new OrderNotFoundException(orderDTO.getId())));
        return OrderMapper.convertToDTO(updateOrder);
    }

    public Mono<Void> deleteById(String id){
        return orderRepository.deleteById(id);
    }
}
