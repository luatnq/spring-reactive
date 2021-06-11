//package com.example.categoryservice.config;
//
//import com.example.categoryservice.entity.Category;
//import com.example.categoryservice.service.CategoryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//import static org.springframework.http.MediaType.APPLICATION_JSON;
//import static org.springframework.web.reactive.function.server.RequestPredicates.*;
//
//@Configuration
//public class Router {
//    @Autowired
//    private CategoryService categoryService;
//
//    @Bean
//    public RouterFunction<ServerResponse> categoryRoute() {
//        return RouterFunctions
//                .route(POST("/api/v1/categories").and(accept(APPLICATION_JSON)), categoryService::createCategory)
//                .andRoute(GET("/api/v1/categories").and(accept(APPLICATION_JSON)), categoryService::findAll);
//    }
//}
