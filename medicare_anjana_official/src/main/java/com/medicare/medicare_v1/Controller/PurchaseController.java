package com.medicare.medicare_v1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.medicare.medicare_v1.Service.CartService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/orderComplete")
	public String orderSuccessPage(HttpSession session) {
		Integer uid = (Integer) session.getAttribute("uid");
		if(uid != null) {
	    	return "orderSummary"; 
	    }
		else {
	    	session.setAttribute("msg", "Something went wrong, please try again.");
	    	return "redirect:/cart/myCart";
	    }
	}
	
	@PostMapping("/orderSummary")
	public String checkoutPaymentPage(HttpSession session, Model model) {
		Integer uid = (Integer) session.getAttribute("uid");
	    if(uid != null) {
	    	cartService.checkout(uid);
	    	session.setAttribute("msg", "Payment successful! Order placed.");
	    	return "redirect:/purchase/orderComplete"; 
	    }
	    else {
	    	session.setAttribute("msg", "Something went wrong, please try again.");
	    	return "redirect:/cart/myCart";
	    }
	}
}
