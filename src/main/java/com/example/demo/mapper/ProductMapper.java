package com.example.demo.mapper;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class ProductMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static Product convertToEntity(ProductDTO productDTO) {
        return  modelMapper.map(productDTO, Product.class);
    }


    public static Mono<ProductDTO> convertToDTO(Mono<Product> product) {
        return product.map(r -> ProductDTO.builder()
                .id(r.getId())
                .uid(r.getUid())
                .createdDate(r.getCreatedDate())
                .rating(r.getRating())
                .price(r.getPrice())
                .imageURL(r.getImageURL())
                .productName(r.getProductName())
                .publisherName(r.getPublisherName())
                .updatedDate(r.getUpdatedDate())
                .build());
    }
    public static Flux<ProductDTO> convertToDTOs(Flux<Product> products){
        return products.map(r -> ProductDTO.builder()
                .id(r.getId())
                .uid(r.getUid())
                .createdDate(r.getCreatedDate())
                .rating(r.getRating())
                .price(r.getPrice())
                .imageURL(r.getImageURL())
                .productName(r.getProductName())
                .publisherName(r.getPublisherName())
                .updatedDate(r.getUpdatedDate())
                .build());
    }
}
