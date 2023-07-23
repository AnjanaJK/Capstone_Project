package com.medicare.medicare_v1.Entity;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_id;
	
	@Column(name="user_name")
	private String userName;		// username
	
	@Column(name="user_fname")
	private String userFname;		// user first name
	
	@Column(name="user_lname")
	private String userLname;		// user last name
	
	@Column(name="user_address")
	private String userAddress;		// apt no.
	
	@Column(name="user_location")
	private String userLocation;	// state
	
	@Column(name="user_mail_id")
	private String userEmail;
	
	@Column(name="user_password")
	private String userPassword;
	
	@Column(name="user_phone_no")
	private String userPhoneNo;
	
	@Column(name="user_role")
	private String userRole;		// cust. or admin.
	
	@OneToMany(mappedBy = "user")
    private List<Cart> cart;
	
	@OneToMany(mappedBy = "user")
    private List<Purchase> purchase;

//----------------------------------------------------------
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int user_id, String userName, String userFname, String userLname, String userAddress,
			String userLocation, String userEmail, String userPassword, String userPhoneNo, String userRole,
			List<Cart> cart, List<com.medicare.medicare_v1.Entity.Purchase> purchase) {
		super();
		this.user_id = user_id;
		this.userName = userName;
		this.userFname = userFname;
		this.userLname = userLname;
		this.userAddress = userAddress;
		this.userLocation = userLocation;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhoneNo = userPhoneNo;
		this.userRole = userRole;
		this.cart = cart;
		this.purchase = purchase;
	}

	public List<Purchase> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<Purchase> purchase) {
		this.purchase = purchase;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFname() {
		return userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserLocation() {
		return userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserPhoneNo() {
		return userPhoneNo;
	}

	public void setUserPhoneNo(String userPhoneNo) {
		this.userPhoneNo = userPhoneNo;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<Cart> getCart() {
		return cart;
	}

	public void setCart(List<Cart> cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", userName=" + userName + ", userFname=" + userFname + ", userLname="
				+ userLname + ", userAddress=" + userAddress + ", userLocation=" + userLocation + ", userEmail="
				+ userEmail + ", userPassword=" + userPassword + ", userPhoneNo=" + userPhoneNo + ", userRole="
				+ userRole + ", cart=" + cart + ", purchase=" + purchase + "]";
	}

}
