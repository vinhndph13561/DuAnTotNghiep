package com.example.demo.product.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductDetail;

public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long>{
	@Query("SELECT pd FROM ProductDetail pd WHERE pd.product = :product")
	List<ProductDetail> findByProduct(@Param("product") Product product);
}
