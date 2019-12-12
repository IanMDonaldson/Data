package com.iandonaldson.data;

import java.util.List;

public interface CustomerDao {
	public List<Customer> getAllCustomers();
	public List<Customer> getCustomersByName(String customerName);
	public Customer getCustomer(int Id);
	public boolean validCustomerNameSearch(String customerName);
	public boolean updateCustomer(Customer customer);
	public boolean deleteCustomer(int Id);
	public String customerSearchSQLQuery(String names[]);
}
