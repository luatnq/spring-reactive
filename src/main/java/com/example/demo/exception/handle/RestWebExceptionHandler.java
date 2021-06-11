package com.example.demo.exception.handle;

import com.example.demo.exception.ProductNotFoundException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Order(-2)
@Slf4j
public class RestWebExceptionHandler implements WebExceptionHandler {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        if (ex instanceof ProductNotFoundException) {
            exchange.getResponse().setStatusCode(HttpStatus.NOT_FOUND);
            log.error(ex.getMessage());
            return exchange.getResponse().setComplete();
        }
        return Mono.error(ex);
    }
}
