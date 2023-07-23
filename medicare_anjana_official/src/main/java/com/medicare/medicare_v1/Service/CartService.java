package com.medicare.medicare_v1.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.medicare_v1.Entity.Cart;
import com.medicare.medicare_v1.Entity.CartId;
import com.medicare.medicare_v1.Entity.Product;
import com.medicare.medicare_v1.Entity.Purchase;
import com.medicare.medicare_v1.Entity.PurchaseDetails;
import com.medicare.medicare_v1.Entity.PurchaseDetailsId;
import com.medicare.medicare_v1.Entity.User;
import com.medicare.medicare_v1.Repository.ICartRepo;
import com.medicare.medicare_v1.Repository.IPurchaseDetailsRepo;
import com.medicare.medicare_v1.Repository.IPurchaseRepo;
import com.medicare.medicare_v1.Repository.IUserRepo;

import jakarta.transaction.Transactional;

@Service
public class CartService {

	@Autowired
	private ICartRepo cartRepo;
	@Autowired
	private IUserRepo userRepo;
	@Autowired
	private IPurchaseRepo purchaseRepo;
	@Autowired
	private IPurchaseDetailsRepo purchaseDetailsRepo;
	
	public void addToCart(Product prod_id, User user_id, double prodPrice) {
		Cart cart = new Cart();
		CartId cartId = new CartId();
		
		cartId.setUserId(user_id.getUser_id());
	    cartId.setProductId(prod_id.getProduct_id());
	    
	    
	    cart.setCartId(cartId);
	    cart.setProduct(prod_id); // Set the product object
	    cart.setUser(user_id); // Set the user object
	    cart.setPrice(prodPrice);
	    cart.setQuantity(1);
		
		cartRepo.save(cart);
		
	}

	// Cart page product display
	public List<Object[]> showingProductsInCart(int userId) {
		return cartRepo.showingProductsInCart(userId);
	}

	public Double getTotalPriceByUserId(int userId) {
		return cartRepo.getTotalPriceByUserId(userId);
	}

	public int getTotalItemsInCart(int userId) {
		return cartRepo.getTotalItemsInCart(userId);
	}

	@Transactional
	public void deleteProductFromCart(int userId, int productId) {
		cartRepo.deleteByCartIdUserIdAndCartIdProductId(userId, productId);
	}

	public void checkout(int uid) {
		User user = userRepo.findById(uid).orElse(null);
		List<Cart> cartItem = cartRepo.findByCartIdUserId(uid);
		
		System.out.println("PRINTING...");
		
		for (Cart cartItems : cartItem) {
			System.out.println("PRINTING...");
			// store to purchase
			Purchase purchase= new Purchase();
			
			purchase.setUser(user);
			purchase.setPurchaseDate(new Date());
			purchase.setAmount(cartItems.getPrice());

			purchaseRepo.save(purchase);

			// store to purchaseDetails
			PurchaseDetails purchaseDetails = new PurchaseDetails();
		    PurchaseDetailsId purchaseDetailsId = new PurchaseDetailsId();
		    
		    purchaseDetailsId.setPurchaseId(purchase.getPurch_id());
		    purchaseDetailsId.setProductId(cartItems.getProduct().getProduct_id());
	        purchaseDetails.setOrderId(purchaseDetailsId);
		    
			purchaseDetails.setPurchase(purchase);
		    purchaseDetails.setProduct(cartItems.getProduct());
		    purchaseDetails.setAmount(cartItems.getPrice());
		    purchaseDetails.setQuantity(cartItems.getQuantity());
		    purchaseDetails.setOrderDate(new Date());
		    
		    // for Stock
		    Product product = cartItems.getProduct();
            int quantityInCart = cartItems.getQuantity();
            
        	// Update product quantity and set the "outOfStock" flag
            int updatedQuantity = product.getProductQuantity() - quantityInCart;
            product.setProductQuantity(updatedQuantity);
            if(updatedQuantity <= 0) {
            	product.setProductStatus(0);
            }
            
			
			purchaseDetailsRepo.save(purchaseDetails);
			
			cartRepo.delete(cartItems);
		
		}
	}

}
