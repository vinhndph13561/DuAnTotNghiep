package com.example.demo.discount.service;


import com.example.demo.discount.entity.Discount;

import java.util.List;

public interface DiscountService {
    List<Discount> getAllDiscount();
    Discount getDiscountById(Long id);
    Discount updateDiscountById(Discount newDiscount);
    Discount saveDiscount(Discount newDiscount);

}
