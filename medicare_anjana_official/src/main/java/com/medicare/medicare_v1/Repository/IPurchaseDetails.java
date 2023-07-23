package com.medicare.medicare_v1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medicare.medicare_v1.Entity.PurchaseDetails;



public interface IPurchaseDetails extends JpaRepository<PurchaseDetails, Integer> {

// Fetch details from Purchase Table and PurchaseDetails Table
	@Query("SELECT purch.purch_id, purch.purchaseDate, p.product_id, p.productName, u.userName, pd.amount, c.categoryName \r\n"
			+ "FROM Purchase purch\r\n"
			+ "JOIN PurchaseDetails pd\r\n"
			+ "ON purch.purch_id = pd.orderId.purchaseId\r\n"
			+ "JOIN Product p\r\n"
			+ "ON p.product_id = pd.orderId.productId\r\n"
			+ "JOIN User u\r\n"
			+ "ON purch.user.user_id= u.user_id\r\n"
			+ "JOIN Category c\r\n"
			+ "ON c.categoryId = p.category.categoryId\r\n"
			+ "WHERE u.user_id = :userId")
	List<Object[]> displayAllReport(@Param("userId") int userId);

}
