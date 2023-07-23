package com.medicare.medicare_v1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.medicare.medicare_v1.Entity.Category;
import com.medicare.medicare_v1.Entity.Product;
import com.medicare.medicare_v1.Service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private CategoryService catService;
	
	@GetMapping("/addCategoryPage")
	public String addCategoryPage(Model model, HttpSession session) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCategory = catService.displayAllCategory();
			model.addAttribute("listCategory", listCategory);
			return "addCategory";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/addProductPage")
	public String addProductPage(Model model, HttpSession session) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCat = catService.displayAllCategory();
			model.addAttribute("listCat", listCat);
			model.addAttribute("product", new Product());
			return "addProduct";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/viewAllProductsPage")
	public String viewAllProductsPage() {
		return "viewAllProducts";
	}
	
	//----------------------------------------------------

	@PostMapping("/addNewCategory")
	public String addNewCat(HttpSession session,  @ModelAttribute Category category) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			Category cat = catService.addingCat(category);
			session.setAttribute("msg", "New Category Added Successfully");
			return "redirect:/admin/addCategoryPage";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
}
