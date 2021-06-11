package com.example.categoryservice.controller;

import com.example.categoryservice.dto.CategoryDTO;
import com.example.categoryservice.entity.Category;
import com.example.categoryservice.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;

@Controller
@RequestMapping("/api/v1/categories")
@CrossOrigin
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void createCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.createCategory(categoryDTO);
//        return new ResponseEntity<>(categoryDTOMono, HttpStatus.CREATED);
    }

    @GetMapping(produces = APPLICATION_JSON)
    @ResponseBody
    public Flux<CategoryDTO> getAll(){
        Flux<CategoryDTO > categories = categoryService.findAll();
        return categories;
    }

    @GetMapping(params = "category_name",produces = "application/json")
    @ResponseBody
    public Mono<CategoryDTO> getByCategoryName(@RequestParam("category_name") String categoryName){
        return categoryService.findByCategoryName(categoryName);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Void> deleteById(@PathVariable String id){
        return categoryService.deleteById(id);
    }
}
