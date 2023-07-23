package com.medicare.medicare_v1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicare.medicare_v1.Entity.Product;

@Repository
public interface IProductRepo extends JpaRepository<Product, Integer>{

	@Query("SELECT product FROM Product product WHERE CONCAT(product.productName) LIKE %?1%")
	List<Product> search(String keyword);

	
	List<Product> findByCategoryCategoryId(@Param("id") int id);


	@Query("SELECT p.productImage, p.product_id, p.productName, p.productBrand, p.productQuantity, p.productPrice, p.productStatus FROM Product p JOIN p.category c WHERE c.categoryId = :categoryId ORDER BY p.product_id")
	List<Object[]> findByCategory(@Param("categoryId") int categoryId);

	
}
