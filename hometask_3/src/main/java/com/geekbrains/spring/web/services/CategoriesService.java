package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.entities.Category;
import com.geekbrains.spring.web.repositories.CategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesService {
private final CategoriesRepository categoriesRepository;

public List<Category> findAllCategories(){
    return categoriesRepository.findAll();
}
public Optional<Category> findOneById(Integer id){
    return categoriesRepository.findById(id);
}

}
