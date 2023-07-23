package com.medicare.medicare_v1.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class Cart {
	@EmbeddedId
    private CartId cartId;

	@Column(name="price")
	private double price;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
//	------------------------------------------------------------------
    
    public Cart() {
		// TODO Auto-generated constructor stub
	}
    
	public Cart(CartId cartId, double price, int quantity, User user, Product product) {
		super();
		this.cartId = cartId;
		this.price = price;
		this.quantity = quantity;
		this.user = user;
		this.product = product;
	}

	public CartId getCartId() {
		return cartId;
	}

	public void setCartId(CartId cartId) {
		this.cartId = cartId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", price=" + price + ", quantity=" + quantity + ", user=" + user
				+ ", product=" + product + "]";
	}

}
