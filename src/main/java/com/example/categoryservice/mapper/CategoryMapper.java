package com.example.categoryservice.mapper;

import com.example.categoryservice.dto.CategoryDTO;
import com.example.categoryservice.entity.Category;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Category convertToEntity(CategoryDTO categoryDTO) {
        return  modelMapper.map(categoryDTO, Category.class);
    }

    public static CategoryDTO convertToDTO(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }

    public static List<CategoryDTO> convertToListDTOs(List<Category> posts) {
        return posts.stream().map(CategoryMapper::convertToDTO).collect(Collectors.toList());
    }
}
