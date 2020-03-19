package com.example.Shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Shopping.model.Product;



public interface ProductRepository extends JpaRepository<Product, Integer> {
	List<Product> findByCategoryId(long l);
	List<Product> findAllById(int id);
	@Query("select p from Product p where p.name LIKE %?1%")
	List<Product> searchByNameJpql(String title);
}