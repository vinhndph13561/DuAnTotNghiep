package com.example.demo.cart;

import com.example.demo.bill.entity.AddToCart;
import com.example.demo.bill.entity.Cart;
import com.example.demo.bill.entity.CartDto;

import com.example.demo.login.store.entity.User;
import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductDetail;


public interface CartService {
	 Cart addToCart(AddToCart addToCart, ProductDetail product, User user);

	 CartDto listCartItem(User user);

	 Cart updateCart(AddToCart addToCart, Long id);


	 void removeCartItem(Long id, Long userId);

	 void deleteUserCartItems(User user);
}
