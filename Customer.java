package com.iandonaldson.data;

import java.util.Date;

public class Customer implements Comparable<Customer>{
	private int storeID;
	private int id;
	private int addressID;
	private String firstName;
	private String lastName;
	private String email;
	private Date createDate;
	private Date lastUpdate;
	private Location location;
	
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
		return this.id;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getAddressID() {
		return this.addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public int compareTo(Customer c) {
		return this.getLastName().compareTo(c.getLastName());
	}
}
