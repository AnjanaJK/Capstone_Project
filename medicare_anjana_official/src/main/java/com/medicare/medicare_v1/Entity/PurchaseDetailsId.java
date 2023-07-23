package com.medicare.medicare_v1.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class PurchaseDetailsId implements Serializable {
	
	private int purchaseId;
	private int productId;
	
// -------------------------------------------
	
	public PurchaseDetailsId() {
		// TODO Auto-generated constructor stub
	}

	public PurchaseDetailsId(int purchaseId, int prodId) {
		super();
		this.purchaseId = purchaseId;
		this.productId = productId;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	
	

}

