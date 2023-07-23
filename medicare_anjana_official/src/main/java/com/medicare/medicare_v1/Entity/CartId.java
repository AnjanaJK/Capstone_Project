package com.medicare.medicare_v1.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartId implements Serializable {
	
    private int userId;
    private int productId;
	
// -------------------------------------------
    
    public CartId() {
		// TODO Auto-generated constructor stub
	}

	public CartId(int userId, int prodId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public String toString() {
		return "CartId [userId=" + userId + ", productId=" + productId + "]";
	}

	
}
