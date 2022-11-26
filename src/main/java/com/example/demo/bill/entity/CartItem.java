package com.example.demo.bill.entity;

import com.example.demo.product.entities.ProductDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {
    private Long id;
    private int quantity;
    private ProductDetail product;
    
    public CartItem() {
		// TODO Auto-generated constructor stub
	}

    public CartItem(Cart cart) {
        this.setId(cart.getId());
        this.setQuantity(cart.getQuantity());
        this.setProduct(cart.getProduct());
    }
}
