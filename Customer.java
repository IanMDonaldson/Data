package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.sql.Statement;
import java.util.Date;
import java.sql.Connection;
import java.sql.ResultSet;

public class Customer {
	private int storeID;
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
}
