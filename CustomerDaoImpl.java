package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

	public CustomerDaoImpl() {
		
	}
	
	public List<Customer> getCustomers(PreparedStatement ps) {
		List<Customer> customerList = new LinkedList<Customer>();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setStoreID(rs.getInt("store_id"));
				customer.setLastUpdate(rs.getDate("last_update"));
				customer.setAddressID(rs.getInt("address_id"));
				customer.setID(rs.getInt("customer_id"));
				customerList.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new LinkedList<Customer>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from customer;");
			customerList = getCustomers(ps);
			conn.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	public Customer getCustomer(int Id) {
		Customer customer = new Customer();
		
		Connection conn = ConnectionFactory.getConnection();	
		try {
			PreparedStatement ps = conn.prepareStatement("select * from customer where customer_id=?");
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				customer.setStoreID(rs.getInt("store_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setCreateDate(rs.getDate("create_date"));
				customer.setLastUpdate(rs.getDate("last_update"));
				customer.setAddressID(rs.getInt("address_id"));
				customer.setID(rs.getInt("customer_id"));
			}
			else {
				conn.close();
				ps.close();
				rs.close();
				return null;
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		return false;
	}

	@Override
	public boolean deleteCustomer(int Id) {
		return false;
	}

}
