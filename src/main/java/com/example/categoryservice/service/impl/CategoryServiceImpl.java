package com.example.categoryservice.service.impl;

import com.example.categoryservice.dto.CategoryDTO;
import com.example.categoryservice.dto.DataResponseDTO;
import com.example.categoryservice.entity.Category;
import com.example.categoryservice.exception.CategoryNotFoundException;
import com.example.categoryservice.mapper.CategoryMapper;
import com.example.categoryservice.repository.CategoryRepository;
import com.example.categoryservice.service.CategoryService;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void createCategory(CategoryDTO categoryDTO) {
        Category category = CategoryMapper.convertToEntity(categoryDTO);
        categoryRepository.save(category).subscribe();
    }
    @Override
    public Flux<CategoryDTO> findAll(){
       return categoryRepository.findAll().map(category
               -> CategoryDTO.builder().categoryName(category.getCategoryName()).build());
    }

    @Override
    public Mono<CategoryDTO> findByCategoryName(String categoryName){
        return categoryRepository.findByCategoryName(categoryName).map(category
                -> CategoryDTO.builder()
                .categoryName(category.getCategoryName())
                .build())
                .switchIfEmpty(Mono.error(new CategoryNotFoundException("")));
    }

    @Override
    public Mono<Void> deleteById(String id){
        return categoryRepository.deleteById(id);
    }
}
