package com.iandonaldson.data;

import java.util.Date;

public class Customer {
	private int storeID;
	private int ID;
	private int addressID;
	private String firstName;
	private String lastName;
	private String email;
	private Date createDate;
	private Date lastUpdate;
	
	public Customer() {
		
	}
		
	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getStoreID() {
		return this.storeID;
		}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public Date getLastUpdateDate() {
		return this.lastUpdate;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
}
