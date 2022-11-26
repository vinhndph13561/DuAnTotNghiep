package com.example.demo.product.controllers.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.product.entities.Category;
import com.example.demo.product.entities.Product;
import com.example.demo.product.entities.ProductDetail;
import com.example.demo.product.repositories.CategoryRepository;
import com.example.demo.product.repositories.ProductDetailRepository;
import com.example.demo.product.repositories.ProductRepository;

@Controller
@RequestMapping("product")
public class ProductController {
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ProductDetailRepository productDetailRepo;

	//product Create 
	@RequestMapping("create")
	public String create(Model model) {
		List<Category> list = categoryRepo.findAll();
		model.addAttribute("listCategory", list);
		return "product/create";
	}

	//create --> productDetail index
	@PostMapping("/store")
	public String store(@ModelAttribute("product") Product product, Model model) {
		Product p = product;
		this.productRepo.save(p);

		Product prod = productRepo.findByName(p.getName());
		model.addAttribute("product", product);

		List<ProductDetail> listPD = productDetailRepo.findByProduct(prod);
		model.addAttribute("listProductDetail", listPD);

		return "productDetail/index";
	}

	//update product --> productDetail index
	@PostMapping("/update/{id}")
	public String update(@ModelAttribute("product") Product product, Model model, @PathVariable("id") Long id) {
		Product p = product;
		p.setId(id);
		this.productRepo.save(p);

		Product prod = productRepo.findByName(p.getName());
		model.addAttribute("product", product);

		List<ProductDetail> listPD = productDetailRepo.findByProduct(prod);
		model.addAttribute("listProductDetail", listPD);

		return "productDetail/index";
	}

	//edit product
	@RequestMapping("edit/{id}")
	public String editProduct(Model model, @PathVariable("id") Long id) {
		Product product = productRepo.findById(id).get();
		model.addAttribute("product", product);

		List<Category> list = categoryRepo.findAll();
		model.addAttribute("listCategory", list);

		return "product/edit";
	}

	//delete product
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		Product p = productRepo.findById(id).get();
		List<ProductDetail> listProD = productDetailRepo.findByProduct(p);
		if(listProD.size()!=0) {
			for(ProductDetail proD : listProD) {
				this.productDetailRepo.deleteById(proD.getId());
			}
		}
		this.productRepo.deleteById(id);
		return "redirect:/product/index";
	}

	//index product
	@GetMapping("index")
	public String index(Model model, @ModelAttribute("product") Product product,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {
		model.addAttribute("create", true);
		List<Category> list = categoryRepo.findAll();
		model.addAttribute("listCategory", list);

		Pageable pageable = PageRequest.of(page, size);
		Page<Product> data = this.productRepo.findAll(pageable);
		model.addAttribute("data", data);
		return "product/index";
	}
	
	@GetMapping("index2")
	public String index2(Model model, @ModelAttribute("product") Product product,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {
		model.addAttribute("create", true);
		List<Category> list = categoryRepo.findAll();
		model.addAttribute("listCategory", list);

		Pageable pageable = PageRequest.of(page, size);
		Page<ProductDetail> data = this.productDetailRepo.findAll(pageable);
		model.addAttribute("data", data);
		return "customer/product";
	}


	// Product Detail

	//create productDetail
	@PostMapping("/productDetail/store/{id}")
	public String storeDetail(@ModelAttribute("productDetail") ProductDetail productDetail, Model model,
			@PathVariable("id") Long id) {
		ProductDetail proDt = productDetail;
		
		Product product = productRepo.findById(id).get();
		proDt.setProduct(product);

		this.productDetailRepo.save(proDt);

		model.addAttribute("product", product);

		List<ProductDetail> listPD = productDetailRepo.findByProduct(product);
		model.addAttribute("listProductDetail", listPD);

		return "customer/productdetail";
	}

	//productDetail index
	@GetMapping("/productDetail/index/{id}")
	public String indexDetail(@ModelAttribute("productDetail") ProductDetail productDetail, Model model,
			@PathVariable("id") Long id) {
		Product product = productRepo.findById(id).get();
		System.out.println(product);
		model.addAttribute("product", product);

		List<ProductDetail> listPD = productDetailRepo.findByProduct(product);
		model.addAttribute("listProductDetail", listPD);
		System.out.println(listPD);
		return "customer/productdetail";
	}

	//delete productDetail
	@GetMapping("/productDetail/delete/{id}")
	public String deleteDetail(@ModelAttribute("productDetail") ProductDetail productDetail, Model model, @PathVariable("id") Long id) {
		ProductDetail proDt = productDetailRepo.getById(id);
		Product product = proDt.getProduct();
		model.addAttribute("product", product);
		productDetailRepo.deleteById(id);

		List<ProductDetail> listPD = productDetailRepo.findByProduct(product);
		model.addAttribute("listProductDetail", listPD);

		return "productDetail/index";
	}
}
