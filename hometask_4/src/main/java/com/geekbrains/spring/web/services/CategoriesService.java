package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.entities.CategoryEntity;
import com.geekbrains.spring.web.repositories.CategoriesRepository;
import com.geekbrains.spring.web.soap.categories.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoriesService {
private final CategoriesRepository categoriesRepository;

public List<CategoryEntity> findAllCategories(){
    return categoriesRepository.findAll();
}
public Optional<CategoryEntity> findOneById(Integer id){
    return categoriesRepository.findById(id);
}


}
