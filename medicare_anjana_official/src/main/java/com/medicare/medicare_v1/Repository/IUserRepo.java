package com.medicare.medicare_v1.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.medicare.medicare_v1.Entity.User;


@Repository
public interface IUserRepo extends JpaRepository<User, Integer> {
	// Login Query
	Optional<User> findByUserNameAndUserPassword(@Param("user_name") String userName, @Param("user_password") String userPassword);
}
