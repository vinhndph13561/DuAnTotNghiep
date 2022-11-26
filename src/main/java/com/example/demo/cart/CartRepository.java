package com.example.demo.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.bill.entity.Cart;

import com.example.demo.login.store.entity.User;
import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductDetail;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	List<Cart> findAllByUser(User user);

    List<Cart> deleteByUser(User user);
    
    Cart findByProductAndUser(ProductDetail product, User user);
}
