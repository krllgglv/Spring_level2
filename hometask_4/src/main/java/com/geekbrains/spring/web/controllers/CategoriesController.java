package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.converters.CategoryConverter;
import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.services.CategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;


    @GetMapping
    public List<CategoryDto> findAllCategories(){
        return categoriesService.findAllCategories().stream().map(CategoryConverter::categoryToDto).collect(Collectors.toList());
    }
}
