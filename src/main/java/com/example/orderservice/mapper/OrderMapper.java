package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Order;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class OrderMapper {
    public static ModelMapper modelMapper = new ModelMapper();

    public static Order convertToEntity(OrderDTO orderDTO){
        return modelMapper.map(orderDTO,Order.class);
    }
    public static  Flux<OrderDTO> convertToDTOs(Flux<Order> orders){
        return orders.map(order -> OrderDTO.builder()
        .id(order.getId())
        .product(order.getProduct())
        .quantity(order.getQuantity())
        .uid(order.getUid())
        .build());
    }

    public static Mono<OrderDTO> convertToDTO(Mono<Order> orderMono){
        return orderMono.map(order -> OrderDTO.builder()
                .id(order.getId())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .uid(order.getUid())
                .build());
    }
}
