package com.geekbrains.spring.web.repositories;

import com.geekbrains.spring.web.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository <Category, Integer> {

}
