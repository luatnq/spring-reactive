package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDTO;
import com.example.orderservice.entity.Order;
import com.example.orderservice.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(produces = "application/json")
    public void createOrder(@RequestBody OrderDTO orderDTO){
        orderService.addOrder(orderDTO);
    }

    @GetMapping(params = "uid",produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Flux<OrderDTO> getAllByUid(@RequestParam("uid") String uid){
        return orderService.findAllByUid(uid);
    }

    @PutMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Mono<OrderDTO> updateOrder(@RequestBody OrderDTO orderDTO){
        return orderService.editOrder(orderDTO);
    }

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @DeleteMapping("/{id}")
    public Mono<Void> deleteById(@PathVariable String id){
        return orderService.deleteById(id);
    }
}
