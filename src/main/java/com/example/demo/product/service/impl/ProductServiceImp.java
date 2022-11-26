package com.example.demo.product.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bill.entity.CartDto;
import com.example.demo.bill.entity.CartItem;

import com.example.demo.cart.CartService;
import com.example.demo.login.store.entity.User;
import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductDetail;
import com.example.demo.product.repositories.ProductDetailRepository;
import com.example.demo.product.repositories.ProductRepository;
import com.example.demo.product.service.ProductService;


@Service
@Transactional
public class ProductServiceImp implements ProductService{
	 	@Autowired
	    private ProductDetailRepository productDetailRepository;
	 	
	 	@Autowired
	    private ProductRepository productRepository;
	 	
	    @Autowired
	    private CartService cartService;


	    @Override
	    public void reductionQuantity(User user) {
	        CartDto cartDto = cartService.listCartItem(user);

	        List<CartItem> cartItemList = cartDto.getCartItems();

	        for (CartItem cartItem :
	                cartItemList) {
	            // find product by product id and redution quantity when create order
	            ProductDetail productl = productDetailRepository.findById(cartItem.getProduct().getId()).get();
	            productl.setQuantity(productl.getQuantity() - cartItem.getQuantity());
	            productDetailRepository.save(productl);
	        }

	    }


		@Override
		public Product saveProduct(Product product) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public List<Product> getAllProduct() {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Product updateProduct(Product product) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public Product getProductByName(String name) {
			// TODO Auto-generated method stub
			return null;
		}



		@Override
		public String removeProductById(Long id) {
			// TODO Auto-generated method stub
			return null;
		}


		@Override
		public Product getProductById(Long id) {
			
			return productRepository.findById(id).get();
		}


		@Override
		public List<Product> getAllProductByCategoryId(Long id) {
			// TODO Auto-generated method stub
			return null;
		}

	    
}
