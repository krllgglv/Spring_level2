package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.entities.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryConverter {
    public CategoryDto categoryToDto(Category category){
        return new CategoryDto(category.getId(), category.getTitle());
    }
}
