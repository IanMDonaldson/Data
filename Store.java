package com.iandonaldson.data;

import java.util.Date;
import java.util.List;

public class Store {
	private int storeID;
	private int managerStaffID;
	private int addressID;
	private Date lastUpdate;
	private List<Staff> staffList;
	private List<Customer> customerList;//
	private Location location;
	
	public Store() {
		
	}
	
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public int getManagerStaffID() {
		return managerStaffID;
	}
	public void setManagerStaffID(int managerStaffID) {
		this.managerStaffID = managerStaffID;
	}
	public int getAddressID() {
		return addressID;
	}
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	public Date getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Staff> getStaffList() {
		return staffList;
	}

	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
}
