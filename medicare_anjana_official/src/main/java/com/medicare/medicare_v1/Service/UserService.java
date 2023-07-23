package com.medicare.medicare_v1.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medicare.medicare_v1.Entity.User;
import com.medicare.medicare_v1.Repository.IUserRepo;

@Service
public class UserService {
	
	@Autowired
	private IUserRepo userRepo;
	
	public User findUser(String userName, String userPassword) {
		Optional<User> optionalUser = userRepo.findByUserNameAndUserPassword(userName, userPassword);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
	}

	public void addNewUser(String userName, String userFname, String userLname, String userAddress, String userLocation, String userEmail,
			String userPassword, String userPhoneNo) {
		User user = new User();
		
		user.setUserName(userName);
		user.setUserFname(userFname);
		user.setUserLname(userLname);
		user.setUserAddress(userAddress);
		user.setUserLocation(userLocation);
		user.setUserEmail(userEmail);
		user.setUserPassword(userPassword);
		user.setUserPhoneNo(userPhoneNo);
		user.setUserRole("Customer");
		
		userRepo.save(user);		
	}

	public User getUserById(Integer uid) {
		return userRepo.findById(uid).orElse(null);
	}

}
