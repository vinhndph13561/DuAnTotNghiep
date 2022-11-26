package com.example.demo.product.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="product_details")
@Data
public class ProductDetail implements Serializable{
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="size")
	private String size;
	
	@Column(name="color")
	private String color;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="thumnail")
	private String thumnail;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
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
}
