package com.example.demo.service.impl;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;


@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public void createProduct(ProductDTO productDTO) {
        Product product = ProductMapper.convertToEntity(productDTO);
        productRepository.save(product).subscribe();
        log.info("Product: Created successful", product);
    }

    @Override
    public Mono<ProductDTO> getById(String id) {
        return ProductMapper.convertToDTO(productRepository.findById(id));
    }

    public Mono<ProductDTO> editProduct(ProductDTO productDTO) {
        Mono<Product> updatedProduct =productRepository.findById(productDTO.getId())
                .switchIfEmpty(Mono.error(new ProductNotFoundException(productDTO.getId())))
                .map(product -> {
                    product.setPrice(productDTO.getPrice());
                    product.setProductName(productDTO.getProductName());
//                    product.setDescription(productDTO.getDescription());
                    product.setRating(productDTO.getRating());
                    product.setUpdatedDate(LocalDateTime.now());
                    product.setImageURL(productDTO.getImageURL());
                    product.setPublisherName(productDTO.getPublisherName());
                    return product;
                }).flatMap(product->productRepository.save(product));
        log.info("Product: ","Updated successful");
        return ProductMapper.convertToDTO(updatedProduct);
    }

    @Override
    public Flux<ProductDTO> getAll() {
        Flux<Product> products = productRepository.findAll()
                .switchIfEmpty(Flux.error(new ProductNotFoundException("")));
        log.info("Product: ", "Get all successful");
        return ProductMapper.convertToDTOs(products);
    }

    @Override
    public Flux<ProductDTO> getByProductName(String productName) {
        Flux<Product> products = productRepository.findAllByProductName(productName)
                .switchIfEmpty(Flux.error(new ProductNotFoundException(productName)));
        log.info("Product: ", "Get by " + productName + " successful");
        return ProductMapper.convertToDTOs(products);
    }

    @Override
    public Flux<ProductDTO> getByPrice(long priceMin, long priceMax) {
        Flux<Product> products = productRepository.findByPriceBetweenOrderByPriceDesc(priceMin, priceMax)
                .switchIfEmpty(Flux.error(new ProductNotFoundException("")));
        log.info("Product: ", "Get by to " + priceMin + "from" + priceMax  + " successful");
        return ProductMapper.convertToDTOs(products);
    }

    @Override
    public Flux<ProductDTO> getByUid(String uid) {
        Flux<Product> products = productRepository.findAllByUidOrderByCreatedDateDesc(uid)
                .switchIfEmpty(Flux.error(new ProductNotFoundException(uid)));
        log.info("Product: ", "Get by " + uid + " successful");
        return ProductMapper.convertToDTOs(products);
    }

    @Override
    public Flux<ProductDTO> getByPublisherName(String publisherName) {
        Flux<Product> products = productRepository.findAllByPublisherName(publisherName)
                .switchIfEmpty(Flux.error(new ProductNotFoundException(publisherName)));
        log.info("Product: ", "Get by " + publisherName + " successful");
        return ProductMapper.convertToDTOs(products);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return productRepository.deleteById(id);
    }
}
