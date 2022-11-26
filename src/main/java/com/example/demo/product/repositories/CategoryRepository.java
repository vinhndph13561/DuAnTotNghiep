package com.example.demo.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.product.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
}
