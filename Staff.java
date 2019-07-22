package com.iandonaldson.data;

import java.util.Date;
import java.util.List;

public class Staff extends Person{
	private int staffID;
	private int storeID;
	private int addressID;
	private String email;
	private String username;
	private String password;
	private Date createDate;
	private List<Store> worksAt;
	private Location location;
	
	public Staff() {
		
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<Store> getWorksAt() {
		return worksAt;
	}
	public void setWorksAt(List<Store> worksAt) {
		this.worksAt = worksAt;
	}
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}

}
