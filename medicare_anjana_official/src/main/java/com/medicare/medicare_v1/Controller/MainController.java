package com.medicare.medicare_v1.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicare.medicare_v1.Entity.Category;
import com.medicare.medicare_v1.Entity.Product;
import com.medicare.medicare_v1.Entity.User;
import com.medicare.medicare_v1.Service.CartService;
import com.medicare.medicare_v1.Service.CategoryService;
import com.medicare.medicare_v1.Service.PdService;
import com.medicare.medicare_v1.Service.ProductService;
import com.medicare.medicare_v1.Service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService catService;
	
	@Autowired
	private ProductService prodService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private PdService pdService;
	
	@GetMapping("/")
	public String mainPage(Model model) {
		List<Category> listCategory = catService.displayAllCategory();
		List<Product> listProduct = prodService.displayAllProducts();
		
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("listProduct", listProduct);
		return "home_medicare";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/logout")
	public String userLogout(HttpSession session) {
		session.removeAttribute("uid");
		session.removeAttribute("username");
	    session.setAttribute("msg", "You have been Logged out");
	    return "redirect:/";
	}
	
	@GetMapping("/register")
	public String registerPage() {
		return "register";
	}
	
	// onClick of login btn
	@PostMapping("/index")
	public String index(@RequestParam("user_name") String userName, @RequestParam("user_password") String userPassword, 
			HttpSession session)  {
		User u = userService.findUser(userName, userPassword);
		if (u != null) {
			// check if role is admin
			if(u.getUserRole().equals("Admin")) {
				session.setAttribute("uid", u.getUser_id());
				session.setAttribute("username", u.getUserName());
				return "redirect:/dashboardAdmin";
			}
			if(u.getUserRole().equals("Customer")) {
				session.setAttribute("uid", u.getUser_id());
				session.setAttribute("username", u.getUserName());
				return "redirect:/dashboardCustomer";
			}
			else {
				session.setAttribute("msg", "Invalid Username or Password");
				return "redirect:/login";
			}
		} else {
			session.setAttribute("msg", "Invalid Username or Password");
			return "redirect:/login";
		}
	}
	
	// onClick of Register User btn
	@PostMapping("/registerUser")
	public String resgistering(HttpSession session, @RequestParam("userName") String userName, @RequestParam("userFname") String userFname, 
							   @RequestParam("userLname") String userLname, @RequestParam("userAddress") String userAddress, 
							   @RequestParam("userLocation") String userLocation, @RequestParam("userEmail") String userEmail, 
							   @RequestParam("userPassword") String userPassword, @RequestParam("userPhoneNo") String userPhoneNo) 
	{
		userService.addNewUser(userName, userFname, userLname, userAddress, userLocation, userEmail, userPassword, userPhoneNo);
		session.setAttribute("msg_success", "Account Created! Please Login.");
		return "redirect:/login";
	}
	
	@GetMapping("/about")
	public String aboutPage(HttpSession session, Model model) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			int totalItems = cartService.getTotalItemsInCart(id);
			model.addAttribute("totalItems", totalItems);
		}
		return "about";
	}
	
	@GetMapping("/contact")
	public String contactPage(HttpSession session, Model model) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			int totalItems = cartService.getTotalItemsInCart(id);
			model.addAttribute("totalItems", totalItems);
		}
		return "contact";
	}
//	-------------------------------
	
	@GetMapping("/dashboardAdmin")
	public String adminDashPage(HttpSession session, Model model, @RequestParam(name = "category", required = false) Integer categoryId) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCat = catService.displayAllCategory();
			model.addAttribute("listCat", listCat);
			
			List<Product> listProduct = prodService.displayAllProducts();
			
			
//			Category selectedCategory = catService.getCategoryById(categoryId);
//			model.addAttribute("selectedCategory", selectedCategory);
			
//	        List<Object[]> filteredProducts = prodService.getProductsByCategoryId(categoryId);		// Filter products based on the selected category
	        
	        
	        if (categoryId != null && categoryId > 0) {
	        	
	            Category selectedCategory = catService.getCategoryById(categoryId);
	            model.addAttribute("selectedCategory", selectedCategory);
	            
	            List<Object[]> filteredProducts = prodService.getProductsByCategoryId(categoryId); // Filter products based on the selected category
	            model.addAttribute("filteredProductList", filteredProducts);
	        } else {
	            model.addAttribute("listProduct", listProduct);
	        }
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
		return "dashboard_admin";
	}
	
	@GetMapping("/dashboardCustomer")
	public String customerDashPage(HttpSession session, Model model) {
		Integer id = (Integer) session.getAttribute("uid");
		if(id != null) {
			List<Category> listCategory = catService.displayAllCategory();
			List<Product> listProduct = prodService.displayAllProducts();
			int totalItems = cartService.getTotalItemsInCart(id);
			
			model.addAttribute("listCategory", listCategory);
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("totalItems", totalItems);
			return "dashboard_customer";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@GetMapping("/myorders")
	public String allOrdersPage(HttpSession session, Model model) {
		Integer uid = (Integer) session.getAttribute("uid");
		if(uid != null) {
			int totalItems = cartService.getTotalItemsInCart(uid);
			model.addAttribute("totalItems", totalItems);
			
			List<Object[]> showPurchaseReport = pdService.showPurchaseReport(uid);
			model.addAttribute("showPurchaseReport", showPurchaseReport);
			return "allOrders";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	
	

}
