package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
				customerList.add(customer);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new LinkedList<Customer>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("select ? from ?;");
			ps.setString(1, "*");
			ps.setString(2, "customer");
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
			PreparedStatement ps = conn.prepareStatement("select ? from ? where ?=?");
			ps.setString(1, "*");
			ps.setString(2, "customer");
			ps.setString(3, "customer_id");
			ps.setString(4, Integer.toString(Id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				customer.setStoreID(rs.getInt("store_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setCreateDate(rs.getDate("create_date"));
				customer.setLastUpdate(rs.getDate("last_update"));
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
