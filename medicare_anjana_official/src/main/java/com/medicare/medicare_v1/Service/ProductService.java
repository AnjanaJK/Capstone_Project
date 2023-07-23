package com.medicare.medicare_v1.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.medicare.medicare_v1.Entity.Category;
import com.medicare.medicare_v1.Entity.Product;
import com.medicare.medicare_v1.Repository.IProductRepo;

@Service
public class ProductService {
	
	@Autowired
	private IProductRepo productRepo;

	
	public List<Product> displayAllProducts() {
		return productRepo.findAll();
	}
	
	public List<Product> displayAllProducts(String keyword) {
		if (keyword  != null) {
			return productRepo.search(keyword);
		}
		else {
			return (List<Product>) productRepo.findAll();
		}
	}
	
	// Add new Product to DB
	public void addNewProduct(MultipartFile file, String prodName, String prodBrand, String prodDesc,
			Category selectedCategory, double prodPrice, int prodQty) 
	{
		Product p = new Product();
		
		// -- for image
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// -- for others
		p.setProductName(prodName);
		p.setProductBrand(prodBrand);
		p.setProductDescription(prodDesc);
		p.setCategory(selectedCategory);
		p.setProductPrice(prodPrice);
		p.setProductQuantity(prodQty);
	
		
		productRepo.save(p);
	}

	// Delete selected product by ID
	public void deleteProductById(int id) {
		productRepo.deleteById(id);
	}
	
	// Fetch product details of selected product by ID
	public Product getProductById(int id) {
		Optional<Product> p = productRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		}
		return null;
	}

	// update Product in the DB
	public void addUpdatedProduct(MultipartFile file, int prod_id, String prodName, String prodBrand, String prodDesc,
			Category selectedCategory, double prodPrice, int prodQty) {
		Product p = new Product();
		
		// -- for image
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {
			p.setProductImage(Base64.getEncoder().encodeToString(file.getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// -- for others
		p.setProduct_id(prod_id);
		p.setProductName(prodName);
		p.setProductBrand(prodBrand);
		p.setProductDescription(prodDesc);
		p.setCategory(selectedCategory);
		p.setProductPrice(prodPrice);
		p.setProductQuantity(prodQty);
		
		// Update product status based on product quantity
	    if (prodQty <= 0) {
	        p.setProductStatus(0); // Out of stock
	    } else {
	        p.setProductStatus(1); // In stock
	    }
	    
		productRepo.save(p);		
	}

	public List<Product> findByCategoryId(int id) {
		return productRepo.findByCategoryCategoryId(id);
		
	}

	// Filter - Category
	public List<Object[]> getProductsByCategoryId(int categoryId) {
		return productRepo.findByCategory(categoryId);
	}

	

	
}
