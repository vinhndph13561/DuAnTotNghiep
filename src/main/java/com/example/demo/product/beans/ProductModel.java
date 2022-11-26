package com.example.demo.product.beans;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.product.entities.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable{
	private Integer id;
	private String name;
	private float price;
	private String note;
	private Category category;
}
