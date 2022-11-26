package com.example.demo.product.controllers.category;

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

import com.example.demo.product.beans.CategoryModel;
import com.example.demo.product.entities.Category;
import com.example.demo.product.repositories.CategoryRepository;

@Controller
@RequestMapping("categories")
public class CategoryController {
	@Autowired
	private CategoryRepository categoryRepo;
	
	@GetMapping("/create")
	public String create(@ModelAttribute("categories") CategoryModel category, Model model)
	{	
		return "categories/create";
	}
	
	@PostMapping("/store")
	public String store(CategoryModel model)
	{
		Category a = new Category();
		a.setName(model.getName());
		a.setNote(model.getNote());
		this.categoryRepo.save(a);
		return "redirect:/categories/index";
	}
	
	@PostMapping("/update")
	public String update(Category category)
	{
		this.categoryRepo.save(category);
		return "redirect:/categories/index";
	}
	
	@RequestMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Integer id) {
		Category category = categoryRepo.findById(id).get();
		model.addAttribute("category", category);
		return "categories/edit";
	}
	
	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model)
	{
		this.categoryRepo.deleteById(id);
		return "categories/index";
	}
	
	@GetMapping("index")
	public String index(
		@RequestParam(name="page", defaultValue="0") Integer page,
		@RequestParam(name="size", defaultValue="10") Integer size,
		Model model
	) {	
		Pageable pageable = PageRequest.of(page, size);
		Page<Category> data = this.categoryRepo.findAll(pageable);
		model.addAttribute("data", data);
		return "categories/index";
	}
}
