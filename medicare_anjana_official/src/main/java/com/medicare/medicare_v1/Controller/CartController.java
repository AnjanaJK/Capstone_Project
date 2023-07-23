package com.medicare.medicare_v1.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.medicare.medicare_v1.Entity.Product;
import com.medicare.medicare_v1.Entity.User;
import com.medicare.medicare_v1.Service.CartService;
import com.medicare.medicare_v1.Service.ProductService;
import com.medicare.medicare_v1.Service.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@GetMapping("/myCart")
	public String myCartPage(HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("uid");
		if(userId != null) {
			List<Object[]> showingProductsInCart = cartService.showingProductsInCart(userId);
			model.addAttribute("showingProductsInCart", showingProductsInCart);
			Double totalPrice = cartService.getTotalPriceByUserId(userId);
			model.addAttribute("totalPrice", totalPrice);
			int totalItems = cartService.getTotalItemsInCart(userId);
			model.addAttribute("totalItems", totalItems);
			return "myCart";
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
		}
	}
	
	@PostMapping("/saveToCart")
	public String saveToCart(HttpSession session, @RequestParam("product_id") Product prod_id, 
							 @RequestParam("productPrice") double prodPrice) {
		Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	User user_id = userService.getUserById(uid);
	    	cartService.addToCart(prod_id, user_id, prodPrice);
			session.setAttribute("msg", "Product Added To Cart!");
			return "redirect:/cart/myCart";
	    }
	    else {
	    	session.setAttribute("msg", "Please Login first to add product to cart!");
	        return "redirect:/login";
	    }
	}
	
	// Get Details to Remove Selected Product
	@GetMapping("/removeFromCartPage/{id}")
	public String removeFromCartPage(HttpSession session, Model model, @PathVariable int id) {
	    Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Product selectedCartItem = productService.getProductById(id);	// to display product details
	    	model.addAttribute("selectedCartItem", selectedCartItem);
	    	int totalItems = cartService.getTotalItemsInCart(uid);
			model.addAttribute("totalItems", totalItems);
	    	return "removeFromCart";
	    }
	    else {
	        return "redirect:/";
	    }
	}
	
	@PostMapping("/removeItemFromCart")
	public String removeItemFromCart(HttpSession session, @RequestParam("_method") String method, @RequestParam("product_id") int productId) {
		if ("DELETE".equals(method)) {
			Integer user_id = (Integer) session.getAttribute("uid");
			int userId = (int) user_id;
		    if(user_id != null) {
		    	cartService.deleteProductFromCart(userId, productId);
		    	session.setAttribute("msg", "Item Removed from Cart");
		    	return "redirect:/cart/myCart";
		    }
		    else {
		    	session.setAttribute("msg", "You have been Logged out");
		        return "redirect:/";
		    }
		}
		else {
			session.setAttribute("msg", "You have been Logged out");
			return "redirect:/";
	    }
	}
	
	@GetMapping("/checkout")
	public String checkoutPaymentPage(HttpSession session, Model model) {
		Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	Double totalPrice = cartService.getTotalPriceByUserId(uid);
			model.addAttribute("totalPrice", totalPrice);
	    	int totalItems = cartService.getTotalItemsInCart(uid);
			model.addAttribute("totalItems", totalItems);
	    	return "payment";
	    }
	    else {
	    	session.setAttribute("msg", "Something went wrong, please try again.");
	    	return "redirect:/cart/myCart";
	    }
	}
	
	
}
