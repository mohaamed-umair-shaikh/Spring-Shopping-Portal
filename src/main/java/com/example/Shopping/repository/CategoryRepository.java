package com.example.Shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shopping.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
