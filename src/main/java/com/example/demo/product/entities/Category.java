package com.example.demo.product.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="categories")
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="category_name")
	private String name;
	
	@Column(name="note")
	private String note;
	
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
