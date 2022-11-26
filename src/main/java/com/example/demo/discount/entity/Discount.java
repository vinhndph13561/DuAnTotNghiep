package com.example.demo.discount.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "discounts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "condition")
    private Integer condition;

    @Column(name = "decrease_percent")
    private Integer decreasePercent;

    @Column(name = "discount_name" , nullable = false)
    private String discountName;

    @Column(name = "start_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDay;

    @Column(name = "end_day")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDay;


}