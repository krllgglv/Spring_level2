package com.geekbrains.spring.web.converters;

import com.geekbrains.spring.web.dto.CategoryDto;
import com.geekbrains.spring.web.entities.CategoryEntity;
import com.geekbrains.spring.web.soap.categories.Category;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class CategoryConverter {
    public CategoryDto categoryToDto(CategoryEntity category){
        return new CategoryDto(category.getId(), category.getTitle());
    }
    public  Category functionEntityToSoap(CategoryEntity categoryEntity)  {
        Category category = new Category();
        category.setId(categoryEntity.getId());
        category.setName(categoryEntity.getTitle());
        return category;
    };
}
