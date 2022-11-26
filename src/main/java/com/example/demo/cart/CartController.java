package com.example.demo.cart;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bill.entity.AddToCart;
import com.example.demo.bill.entity.Bill;
import com.example.demo.bill.entity.Cart;
import com.example.demo.bill.entity.CartDto;
import com.example.demo.bill.entity.CartItem;
import com.example.demo.login.store.entity.User;
import com.example.demo.login.store.reponsitory.UserRepository;
import com.example.demo.product.entities.Product;
import com.example.demo.product.repositories.ProductDetailRepository;
import com.example.demo.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CartController {
	@Autowired
    private CartServiceImp cartService;

    @Autowired
    private ProductService productService;
    
    @Autowired
    private ProductDetailRepository productDetailRepository;
    
    @Autowired
    UserRepository userRepository;
    
    @RequestMapping("/detail")
    public String detail() {
    	return "customer/shop-single"; 
    }
    
    @RequestMapping("/cartlist")
	 public String list2(Model model, Principal principal) {
    	if(principal==null) {
    		return "redirect:/security/login";
    	}
    	User user = userRepository.findByUsernameEquals(principal.getName());
				CartDto cartDto = cartService.listCartItem(user);
				List<CartItem> list = cartDto.getCartItems();
				model.addAttribute("dto", cartDto);
				model.addAttribute("itemList", list);
		return "customer/cart";
	}
    
    @RequestMapping({"/checkouts"})
	public String checkout(Model model,Principal principal) {
    	if(principal==null) {
    		return "redirect:/security/login";
    	}
    	User user = userRepository.findByUsernameEquals(principal.getName());
		CartDto cartDto = cartService.listCartItem(user);
		List<CartItem> list = cartDto.getCartItems();
		model.addAttribute("dto", cartDto);
		model.addAttribute("itemList", list);
		model.addAttribute("user", user);
		return "customer/checkout";
	}

    @GetMapping("/cart")
    @ResponseBody
    public CartDto getCartItems(Principal principal) {
    	User user = userRepository.findByUsernameEquals(principal.getName());
        CartDto cartDto = cartService.listCartItem(user);
        return cartDto;
    }

    @RequestMapping(value = "/cart/add",method = RequestMethod.POST)
    public String addToCart(@ModelAttribute("addToCart") AddToCart addToCart, Model model, Principal principal) {
    	if(principal==null) {
    		return "redirect:/security/login";
    	}
    	User user = userRepository.findByUsernameEquals(principal.getName());
        
        ObjectMapper mapper =new ObjectMapper();
        try {
			String jsonString = mapper.writeValueAsString(addToCart);
			AddToCart add = mapper.readValue(jsonString, AddToCart.class);
			model.addAttribute("addToCart", addToCart);
			model.addAttribute("quantity",addToCart.getQuantity());
			model.addAttribute("productId", addToCart.getProductId());
			System.out.println("add cart");
			model.addAttribute("insertCart",cartService.addToCart(add, productDetailRepository.findById(add.getProductId()).get(), user));
			System.out.println(cartService.addToCart(add, productDetailRepository.findById(add.getProductId()).get(), user));
			return "redirect:/cartlist";
        } catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return "redirect:/cartlist";
    }
    
   
    
    @PostMapping("/cart/add/{proId}")
    @ResponseBody
    public void addToCart2(@ModelAttribute("addToCart") AddToCart addToCart,@PathVariable Long proId, Model model,Principal principal) {
    	User user = userRepository.findByUsernameEquals(principal.getName());

        ObjectMapper mapper =new ObjectMapper();
        try {
			String jsonString = mapper.writeValueAsString(addToCart);
			AddToCart add = mapper.readValue(jsonString, AddToCart.class);
			model.addAttribute("addToCart", addToCart);
			model.addAttribute("quantity",addToCart.getQuantity());
			model.addAttribute("insertCart",cartService.addToCart(add, productDetailRepository.findById(add.getProductId()).get(), user));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }

    @PutMapping("/update/{cartItemId}")
    public String updateCartItem(@RequestBody AddToCart cartDto,Model model, @PathVariable Long id, Principal principal) {
    	if(principal==null) {
    		return "redirect:/security/login";
    	}
    	User user = userRepository.findByUsernameEquals(principal.getName());
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCart(cartDto, id);
        return "redirect:/cartlist";
    }

    @RequestMapping("/cart/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable("cartItemId") Long itemID,Model model,Principal principal) {
    	if(principal==null) {
    		return "redirect:/security/login";
    	}
    	User user = userRepository.findByUsernameEquals(principal.getName());
    	Long userId= Long.valueOf(user.getId());
        cartService.removeCartItem(itemID, userId);
        return "redirect:/cartlist";
    }
}
