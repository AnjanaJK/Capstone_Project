package com.medicare.medicare_v1.Entity;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

// ORDER
@Entity
public class PurchaseDetails {
	
	@EmbeddedId
    private PurchaseDetailsId orderId;
	
	@Column(name="order_date")
	private Date orderDate;

//	@ManyToOne
//	@JoinColumn(name="user_id")
//	private User user;
	
	@Column(name="amount")
	private double amount;
	
	@Column(name="quantity")
	private int quantity;
	
	@ManyToOne
	@MapsId("purchaseId")
    @JoinColumn(name = "purch_id")
    private Purchase purchase;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;
// -------------------------------------------
    
    public PurchaseDetails() {
		// TODO Auto-generated constructor stub
	}
	public PurchaseDetails(PurchaseDetailsId orderId, Date orderDate, double amount, int quantity, Purchase purchase,
			Product product) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.amount = amount;
		this.quantity = quantity;
		this.purchase = purchase;
		this.product = product;
	}
	
	public PurchaseDetailsId getOrderId() {
		return orderId;
	}
	public void setOrderId(PurchaseDetailsId orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Purchase getPurchase() {
		return purchase;
	}
	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@Override
	public String toString() {
		return "PurchaseDetails [orderId=" + orderId + ", orderDate=" + orderDate + ", amount=" + amount + ", quantity="
				+ quantity + ", purchase=" + purchase + ", product=" + product + "]";
	}
}
