package com.example.authenticationservice.config;

import com.example.authenticationservice.repository.UserRepository;
import com.example.authenticationservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterFunction {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Bean
    public org.springframework.web.reactive.function.server.RouterFunction<ServerResponse> authRoute() {
        return RouterFunctions
                .route(POST("/api/v1/users/login").and(accept(APPLICATION_JSON)), userService::login)
                .andRoute(POST("/api/v1/users/signup").and(accept(APPLICATION_JSON)), userService::signUp)
                .andRoute(GET("/api/v1/users").and(accept(APPLICATION_JSON)), userService::getAllUser);
    }
}
