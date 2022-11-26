package com.example.demo.product.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name="products")
@Data
public class Product implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="product_name")
	private String name;
	
	@Column(name="unit_price")
	private float price;
	
	@Column(name="note")
	private String note;
	
	@Column(name="material")
	private String material;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	List<ProductDetail> productDetails;
	
	@Column(name="create_day")
	private Date createDay;
	
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="modify_day")
	private Date modifyDay;
	
	@Column(name="modified_by")
	private Date modifiedBy;
	
	@Column(name="status")
	private int status;
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(Long id, String name, float price, String note, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.note = note;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}
	
	
}
