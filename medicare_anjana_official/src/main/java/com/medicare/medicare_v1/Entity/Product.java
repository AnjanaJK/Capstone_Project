package com.medicare.medicare_v1.Entity;


import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int product_id;
	
	@Column(name="prod_name")
	private String productName;
	
	@Column(name="prod_brand")
	private String productBrand;
	
	@Column(name="prod_price")
	private double productPrice;
	
	@Column(name="prod_quantity")
	private int productQuantity;
	
	@Column(name="prod_status")		// availability of the product
	private int productStatus = 1;	// 1 - available || 0 - out of stock
	
	@Column(name="prod_desc", length=500)
	private String productDescription;
	
	@ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
	
	@Lob
	@Column(name="prod_image", columnDefinition = "MEDIUMBLOB")
	private String productImage;
	
	@OneToMany(mappedBy = "product")
	private List<Cart> carts;
	
//	----------------------------------------------------------------
	
	public Product() {
		// TODO Auto-generated constructor stub
	}

	public Product(int product_id, String productName, String productBrand, double productPrice, int productQuantity,
			int productStatus, String productDescription, Category category, String productImage, List<Cart> carts) {
		super();
		this.product_id = product_id;
		this.productName = productName;
		this.productBrand = productBrand;
		this.productPrice = productPrice;
		this.productQuantity = productQuantity;
		this.productStatus = productStatus;
		this.productDescription = productDescription;
		this.category = category;
		this.productImage = productImage;
		this.carts = carts;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	

	public int getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(int productStatus) {
		this.productStatus = productStatus;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	@Override
	public String toString() {
		return "Product [product_id=" + product_id + ", productName=" + productName + ", productBrand=" + productBrand
				+ ", productPrice=" + productPrice + ", productQuantity=" + productQuantity + ", productStatus="
				+ productStatus + ", productDescription=" + productDescription + ", category=" + category
				+ ", productImage=" + productImage + ", carts=" + carts + "]";
	}

}
