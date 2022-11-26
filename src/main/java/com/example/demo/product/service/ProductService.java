package com.example.demo.product.service;

import java.util.List;


import com.example.demo.login.store.entity.User;
import com.example.demo.product.entities.Product;


public interface ProductService {
	Product saveProduct(Product product);

    List<Product> getAllProduct();

    Product updateProduct(Product product);

    String removeProductById(Long id);

    Product getProductById(Long id);

    Product getProductByName(String name);

    void reductionQuantity(User user);

    List<Product> getAllProductByCategoryId(Long id);
}
