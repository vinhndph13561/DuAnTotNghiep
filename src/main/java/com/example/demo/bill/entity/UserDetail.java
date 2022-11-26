package com.example.demo.bill.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name="user_detail")
public class UserDetail {
	@Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "province_city")
    private String provinceCity;

    @Column(name = "district")
    private String district;
    
    @Column(name = "ward")
    private String ward;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "total_spending")
    private double totalSpending;
    
    @Column(name = "tb_coin")
    private double tbCoin;
    
    @Column(name = "payment_account")
    private String paymentAccount;
}
