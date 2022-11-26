package com.example.demo.discount.restcontroller;


import com.example.demo.discount.entity.Discount;
import com.example.demo.discount.service.DiscountService;
import com.example.demo.discount.serviceImp.DiscountServiceImp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class DiscountRestController   {

	@Autowired
	DiscountService discountService;
	
	
    @Autowired
    private DiscountServiceImp discountServiceImp ;

//    @GetMapping("/")
//    public List<Discount> getAllDiscount(){
//        return discountServiceImp.getAllDiscount();
//    }

    @RequestMapping(value = "/api/discount/save",method = RequestMethod.POST)
    public String insertDiscount(@ModelAttribute Discount newDiscount, Model model){
        ObjectMapper mapper =new ObjectMapper();
        try {
			String jsonString = mapper.writeValueAsString(newDiscount);
			Discount discount = mapper.readValue(jsonString, Discount.class);
			model.addAttribute("discount", newDiscount);
			model.addAttribute("discountName", newDiscount.getDiscountName());
			model.addAttribute("decreasePercent", newDiscount.getDecreasePercent());
			model.addAttribute("starDay", newDiscount.getStartDay());
			model.addAttribute("endDay", newDiscount.getEndDay());

			model.addAttribute("insertDiscount",discountService.saveDiscount(discount));
			
			return "redirect:/discount/list";
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return "admin/discount/tables";
    }
    
    @RequestMapping("/api/discount/update/{id}")
    public String editDiscount(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("discount",discountService.getDiscountById(id));
    	
    	return "admin/discount/update";
    }
    
    @RequestMapping(value = "/api/discount/{id}",method = RequestMethod.POST)
    public String updateDiscount(@ModelAttribute("discounts") Discount newDiscount,@PathVariable("id") Long id, Model model) {
    	model.addAttribute("discountName", newDiscount.getDiscountName());
		model.addAttribute("decreasePercent", newDiscount.getDecreasePercent());
		model.addAttribute("starDay", newDiscount.getStartDay());
		model.addAttribute("endDay", newDiscount.getEndDay());
		Discount _discountExisting =  discountService.getDiscountById(id);
		_discountExisting.setDiscountName(newDiscount.getDiscountName());
		_discountExisting.setDecreasePercent(newDiscount.getDecreasePercent());
		_discountExisting.setStartDay(newDiscount.getStartDay());
		_discountExisting.setEndDay(newDiscount.getEndDay());
    	discountService.updateDiscountById(newDiscount);
    	
    	
    	return "redirect:/discount/list";
    }
}
