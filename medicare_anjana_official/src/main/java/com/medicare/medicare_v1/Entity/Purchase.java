package com.medicare.medicare_v1.Entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

// PAYMENT
@Entity
public class Purchase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int purch_id;
	
	@Column(name="amount")
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
//	------------------------------------------------------------------
	
	public Purchase() {
		// TODO Auto-generated constructor stub
	}

	public Purchase(int purch_id, double amount, User user, Date purchaseDate) {
		super();
		this.purch_id = purch_id;
		this.amount = amount;
		this.user = user;
		this.purchaseDate = purchaseDate;
	}

	public int getPurch_id() {
		return purch_id;
	}

	public void setPurch_id(int purch_id) {
		this.purch_id = purch_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	@Override
	public String toString() {
		return "Purchase [purch_id=" + purch_id + ", amount=" + amount + ", user=" + user + ", purchaseDate="
				+ purchaseDate + "]";
	}
}
