package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository <CategoryEntity, Integer> {

}
