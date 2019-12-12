package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

	public CustomerDaoImpl() {
		
	}
	
	public List<Customer> getCustomers(PreparedStatement ps) {
		List<Customer> customerList = new LinkedList<Customer>();
		CustomerLNameComparer lnameCompare = new CustomerLNameComparer();
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
			Collections.sort(customerList, lnameCompare.lastNameComparator);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new LinkedList<Customer>();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from customer order by ?;");
			ps.setString(1, "last_name");
			customerList = getCustomers(ps);
			
			ps.close();
			conn.close();
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
	public List<Customer> getCustomersByName(String customerName) {
		List<Customer> customerList = new LinkedList<Customer>();
		Connection conn = ConnectionFactory.getConnection();
		
		String[] names = customerName.split(" ");
		String completeStatement = customerSearchSQLQuery(names);
		try {
			PreparedStatement ps = conn.prepareStatement(completeStatement);
			if (names.length == 1) {
				ps.setString(1, names[0]);
				ps.setString(2, names[0]);
			}
			else if (names.length >= 2) {
				ps.setString(1, names[0]);
				ps.setString(2, names[1]);
			}
			customerList = getCustomers(ps);
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customerList;
	}
	@Override
	public boolean validCustomerNameSearch(String customerName) {
		boolean validSearch = false;
		Connection conn = ConnectionFactory.getConnection();
		String[] names = customerName.split(" ");
		//if 1 word, query first OR last, if 2 search terms, query first AND last, if >2 searchterms, use first two terms
		String completeStatement = customerSearchSQLQuery(names);//create query based on amount of search terms
		try {
			PreparedStatement ps = conn.prepareStatement(completeStatement);
			if (names.length == 1) {
				ps.setString(1, names[0]);
				ps.setString(2, names[0]);
			}
			else if (names.length >= 2) {
				ps.setString(1, names[0]);
				ps.setString(2, names[1]);
			}
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				validSearch = true; //else validSearch returns false(default)
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validSearch;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		boolean isUpdated = false;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update customer "
					+ "set first_name=?, last_name=?, email=?"
					+ "where customer_id=?;");
			ps.setString(1, customer.getFirstName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getEmail());
			ps.setInt(4, customer.getID());
			int rowChanged = ps.executeUpdate();
			if (rowChanged > 0) {
				isUpdated = true;
				conn.close();
				ps.close();
				return isUpdated;
			}
			conn.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean deleteCustomer(int Id) {
		return false;
	}
	@Override
	public String customerSearchSQLQuery(String names[]) {
		/* if one word search first OR last
		 * if two words search first AND last
		 * if three or more, disregard names[2, infinity]*/
		String completeStatement = null;
		if (names.length == 1) {
			completeStatement = "SELECT * FROM customer WHERE customer.first_name LIKE "
					+ "CONCAT('%', ?, '%') OR customer.last_name LIKE "
					+ "CONCAT('%', ?, '%');";
		}
		else if (names.length >= 2) {
			completeStatement = "SELECT * FROM customer WHERE customer.first_name LIKE "
					+ "CONCAT('%', ?, '%') AND customer.last_name LIKE "
					+ "CONCAT('%', ?, '%');";
		}
		return completeStatement;
	}

}
