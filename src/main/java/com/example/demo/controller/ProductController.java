package com.example.demo.controller;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.LongUnaryOperator;

@RestController
@RequestMapping("/api/v1/products")
@Slf4j
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductDTO productDTO) {
        productService.createProduct(productDTO);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity> getProductById(@PathVariable String id) {
        return productService.getById(id)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body(product))
                .cast(ResponseEntity.class)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(HttpStatus.NOT_FOUND.getReasonPhrase()));
    }

    @PutMapping(produces = "application/json")
    public Mono<ResponseEntity> editProduct(@RequestBody ProductDTO productDTO) {
        return productService.editProduct(productDTO)
                .map(product -> ResponseEntity.status(HttpStatus.OK).body(product));
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Flux<ProductDTO> getAll() {
        return productService.getAll();
    }

    @GetMapping(params = "product_name", produces = "application/json")
    public Flux<ProductDTO> getByProductName(@RequestParam("product_name") String productName) {
        return productService.getByProductName(productName);
    }

    @GetMapping(params = "publisher_name", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Flux<ProductDTO> getByPublisherName(@RequestParam("publisher_name") String publisherName) {
        return productService.getByPublisherName(publisherName);
    }

    @GetMapping("/price")
    public Flux<ProductDTO> getByPrice(@RequestParam(name = "price_min") long priceMin,
                                       @RequestParam(name = "price_max") long priceMax){
        log.info("{}",priceMin +" "+priceMax);
        return productService.getByPrice(priceMin,priceMax);
    }

    @GetMapping(params = "uid", produces = "application/json")
    public Flux<ProductDTO> getByUid(@RequestParam String uid) {
        return productService.getByUid(uid);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity> deleteById(@PathVariable String id) {

        return productService.deleteById(id)
                .map(productDTOs -> ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.getReasonPhrase()))
                .cast(ResponseEntity.class);
    }
}
