package com.medicare.medicare_v1.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.medicare.medicare_v1.Entity.Category;
import com.medicare.medicare_v1.Entity.Product;
import com.medicare.medicare_v1.Repository.ICategoryRepo;
import com.medicare.medicare_v1.Service.CartService;
import com.medicare.medicare_v1.Service.CategoryService;
import com.medicare.medicare_v1.Service.ProductService;
import com.medicare.medicare_v1.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService catService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ICategoryRepo catRepo;
	
	
	@GetMapping("/searchFilter")
	public String searchFilter(Model model, @Param("keyword") String keyword, HttpSession session) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			if(keyword != null) {
				List<Category> listCategory = catService.displayAllCategory();
				List<Product> listProduct = productService.displayAllProducts(keyword);
				int totalItems = cartService.getTotalItemsInCart(id);
				model.addAttribute("listProduct", listProduct);
				model.addAttribute("keyword", keyword);
				model.addAttribute("listCategory", listCategory);
				model.addAttribute("totalItems", totalItems);
				return "dashboard_customer";
			}
			else {
				List<Category> listCategory = catService.displayAllCategory();
				List<Product> listProduct = productService.displayAllProducts();
				int totalItems = cartService.getTotalItemsInCart(id);
				model.addAttribute("listProduct", listProduct);
				model.addAttribute("listCategory", listCategory);
				model.addAttribute("totalItems", totalItems);
				return "dashboard_customer";
			}
			
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/sorted/{id}")
	public String catSearch(HttpSession session, Model model, @PathVariable("id") int id){
//		Integer uid = (Integer) session.getAttribute("uid");
//	    if(uid != null) {
	    	List<Category> listCategory = catService.displayAllCategory();
	    	int totalItems = cartService.getTotalItemsInCart(id);
	    	List<Product> listProduct = productService.findByCategoryId(id);
	    	String categoryName = catService.getCategoryNameById(id);
	        model.addAttribute("categoryName", categoryName);
	    	model.addAttribute("listProduct", listProduct);
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("totalItems", totalItems);
			Integer uid = (Integer) session.getAttribute("uid");
		    if(uid != null) {
		    	return "dashboard_customer";
		    }
		    return "home_medicare";
	    	
//	    }
//	    else {
//	    	session.setAttribute("msg", "You have been Logged out");
//	        return "redirect:/";
//	    }
		
	}
	
//	@GetMapping("/adminFilter")
//	public String filterProductsByCategory(@RequestParam(name = "category", required = false) int categoryId, Model model) {
//	    if (categoryId == 0) {
//	    	return "redirect:/dashboardAdmin";
//	    } else {
//	    	Category selectedCategory = catService.getCategoryById(categoryId);
//			model.addAttribute("selectedCategory", selectedCategory);
//	        List<Object[]> filteredProducts = productService.getProductsByCategoryId(categoryId);		// Filter products based on the selected category
//	        List<Category> listCat = catService.displayAllCategory();
//			model.addAttribute("listCat", listCat);
//		    model.addAttribute("listProduct", filteredProducts);
//		    return "dashboard_admin";
//	    } 
//	}
	
	@PostMapping("/addNewProduct")
	public String addingProduct(HttpSession session, @RequestParam("productImage") MultipartFile file, @RequestParam("productName") String prodName,
								@RequestParam("productBrand") String prodBrand, @RequestParam("productDescription") String prodDesc, 
								@RequestParam("category.id") int categoryId, @RequestParam("productPrice") double prodPrice, 
								@RequestParam("productQuantity") int prodQty) 
	{
		Integer id = (Integer) session.getAttribute("uid");
	    if(id != null) {
	    	Optional<Category> optionalCategory = catService.findCatId(categoryId);
			if(optionalCategory.isEmpty()) {
				throw new IllegalArgumentException("Invalid Category ID");
			}
			Category selectedCategory = optionalCategory.get();
			productService.addNewProduct(file, prodName, prodBrand, prodDesc, selectedCategory, prodPrice, prodQty);
			session.setAttribute("msg", "New Product Added Successfully");
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
		return "redirect:/dashboardAdmin";
		
	}
	
	
	// Delete Product from DB
	@GetMapping("/delete/{id}")
	public String deleteProduct(HttpSession session, @PathVariable("id") int id) {
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	productService.deleteProductById(id);
	    	session.setAttribute("msg", "Product Deleted Successfully");
			return "redirect:/dashboardAdmin";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
	}
	
	// Edit Selected Product
		@GetMapping("/edit/{id}")
		public String editProductInfoPage(HttpSession session, Model model, @PathVariable int id) {
		    Integer uid = (Integer) session.getAttribute("uid");
		    if(uid != null) {
		    	Product selectedProduct = productService.getProductById(id);
				List<Category> listCat = catService.displayAllCategory();
				model.addAttribute("selectedProduct", selectedProduct);
				model.addAttribute("listCat", listCat);
				return "editProduct";
		    }
		    else {
		    	session.setAttribute("msg", "You have been Logged out");
		        return "redirect:/";
		    }
		}
		
	// onClick of update btn
	@PostMapping("/updateProductInfo")
	public String updateProductInfo(HttpSession session, @RequestParam("productImage") MultipartFile file, @RequestParam("product_id") int prod_id, 
									@RequestParam("productName") String prodName, @RequestParam("productBrand") String prodBrand,
									@RequestParam("productDescription") String prodDesc, @RequestParam("category.id") int categoryId, 
									@RequestParam("productPrice") double prodPrice, @RequestParam("productQuantity") int prodQty) 
	{
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Optional<Category> optionalCategory = catService.findCatId(categoryId);
			if(optionalCategory.isEmpty()) {
				throw new IllegalArgumentException("Invalid Category ID");
			}

			Category selectedCategory = optionalCategory.get();
			productService.addUpdatedProduct(file, prod_id, prodName, prodBrand, prodDesc, selectedCategory, prodPrice, prodQty);
			session.setAttribute("msg", "Product Updated Successfully");
			return "redirect:/dashboardAdmin";
	    }
	    else {
	    	session.setAttribute("msg", "You have been Logged out");
	        return "redirect:/";
	    }
	}
	
	// View Product Details
	@GetMapping("/viewProduct/{id}")
	public String viewProduct(HttpSession session, Model model, @PathVariable int id) {
		
			
	    	Product selectedProduct = productService.getProductById(id);
			model.addAttribute("selectedProduct", selectedProduct);
			
			Integer uid = (Integer) session.getAttribute("uid");
		    if(uid != null) {
				int totalItems = cartService.getTotalItemsInCart(uid);
				model.addAttribute("totalItems", totalItems);
		    }
			return "viewProduct";
	    
	}
	
}
