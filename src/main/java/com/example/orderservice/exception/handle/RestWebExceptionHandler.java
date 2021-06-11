package com.example.orderservice.exception.handle;

import com.example.orderservice.exception.BillNotFoundException;
import com.example.orderservice.exception.OrderNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
@Slf4j
public class RestWebExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof OrderNotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            log.error(ex.getMessage());
            return exchange.getResponse().setComplete();
        }
        if (ex instanceof BillNotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            log.error(ex.getMessage());
            return exchange.getResponse().setComplete();
        }
        return Mono.error(ex);
    }
}
