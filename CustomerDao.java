package com.iandonaldson.data;

import java.util.List;

public interface CustomerDao {
	public List<Customer> getAllCustomers();
	public Customer getCustomer(int Id);
	public boolean updateCustomer(Customer customer);
	public boolean deleteCustomer(int Id);
}
