package com.example.demo.product.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.product.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT p FROM Product p WHERE p.name = :name")
	Product findByName(@Param("name") String name);
}
