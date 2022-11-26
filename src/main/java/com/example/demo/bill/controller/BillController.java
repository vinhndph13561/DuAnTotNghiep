package com.example.demo.bill.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bill.entity.Bill;
import com.example.demo.bill.entity.CartDto;
import com.example.demo.bill.entity.CartItem;
import com.example.demo.bill.service.imp.BillServiceImp;
import com.example.demo.cart.CartService;
import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.reponsitory.UserRepository;



@Controller
public class BillController {
	@Autowired
	BillServiceImp billService;
	
	@Autowired
	UserRepository userRepository;
	 
	@Autowired
	    private CartService cartService;
	 
	@RequestMapping("/home")
	public String home() {
		return "customer/shop";
	}
	
	@RequestMapping("/bill/list")
	public String list(Model model) {
		List<Bill> list = billService.getAllBill();
		model.addAttribute("items", list);
		return "admin/bill/tables";
	}
	
	@RequestMapping("/billadd")
	 public String addBill(Principal principal) {
	    User user = userRepository.findByUsernameEquals(principal.getName());
		billService.saveBill(user);
		return "redirect:/bill/list";
	}
}
