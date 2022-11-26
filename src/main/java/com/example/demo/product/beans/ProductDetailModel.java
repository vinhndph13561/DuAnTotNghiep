package com.example.demo.product.beans;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.product.entities.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailModel implements Serializable{
	private String size;
	private String color;
	private int quantity;
	private String thumnail;
	private Product product;
}
