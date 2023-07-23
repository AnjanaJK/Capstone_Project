package com.medicare.medicare_v1.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicare.medicare_v1.Entity.Cart;

@Repository
public interface ICartRepo extends JpaRepository<Cart, Integer>{

	// Cart page product display
	@Query("SELECT p.product_id, p.productImage, p.productName, c.quantity, c.price "
			+ "FROM Product p "
			+ "JOIN Cart c "
			+ "ON p.product_id = c.cartId.productId "
			+ "WHERE c.cartId.userId = :userId")
	List<Object[]> showingProductsInCart(@Param("userId") int userId);

	// Calculate total sum of the items in the cart
	@Query("SELECT SUM(c.price) "
			+ "FROM Cart c "
			+ "WHERE c.cartId.userId = :userId")
    Double getTotalPriceByUserId(@Param("userId") int userId);


	@Query("SELECT COUNT(c) FROM Cart c WHERE c.cartId.userId = :userId")
	int getTotalItemsInCart(@Param("userId") int userId);

	void deleteByCartIdUserIdAndCartIdProductId(int userId, int productId);

	List<Cart> findByCartIdUserId(int user_id);

	
//	@Query("SELECT p.prod_id, p.image, p.prod_name, c.price FROM Product p JOIN Cart c ON p.prod_id = c.cartId.prodId WHERE c.cartId.userId = :userId")
//	List<Object[]> showingProductsInCart(@Param("userId") int userId);

}
