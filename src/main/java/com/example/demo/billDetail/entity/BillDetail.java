package com.example.demo.billDetail.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.bill.entity.Bill;
import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="bill_details")
public class BillDetail {
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bill_id",referencedColumnName = "id")
    private Bill bill;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_detail_id",referencedColumnName = "id")
    private ProductDetail product;
	
	@Column(name="quantity")
    private int quantity;
	
	@Column(name="unit_price")
    private double unitPrice;
	
	@Column(name="total")
    private double total;
}
