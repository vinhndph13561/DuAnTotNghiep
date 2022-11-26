package com.example.demo.discount.controller;

import com.example.demo.discount.entity.Discount;
import com.example.demo.discount.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @RequestMapping("/discount/list")
    public String listDiscount(Model model){
        List<Discount> lstDiscount = discountService.getAllDiscount();
        model.addAttribute("listDiscount",lstDiscount);
        return "admin/discount/tables";
    }

    @RequestMapping("/save")
    public String insertDiscount(Model model){
        Discount discount = new Discount();

        model.addAttribute("insertDiscount",discountService.saveDiscount(discount));

        return "admin/discount/tables";
    }
    
    
    
    
}
