package com.example.demo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bill.entity.CartDto;
import com.example.demo.bill.entity.CartItem;
import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.reponsitory.UserRepository;

@Controller
public class HomeController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping({"/", "/home/index"})
	public String home() {
		return "customer/home";
	}
	
	
	@RequestMapping({"/admin", "/admin/home/index"})
	public String admin() {
		return "redirect:/assets/admin/index.html";
	}
	
	@RequestMapping({"/products"})
	public String products() {
		return "customer/product";
	}
	
	@RequestMapping({"/details"})
	public String detail() {
		return "customer/chitiet-sanpham";
	}
	
	@RequestMapping({"/contact"})
	public String contact() {
		return "customer/lienhe";
	}
	
	@RequestMapping({"/detail-news"})
	public String detailnews() {
		return "customer/detail-news";
	}
	
	@RequestMapping({"/tintuc"})
	public String tintuc() {
		return "customer/tintuc";
	}
	
	@RequestMapping({"/carts"})
	public String cart() {
		return "customer/cart";
	}
	
	
	@RequestMapping({"/productdetail"})
	public String prodetail() {
		return "customer/productdetail";
	}
}
