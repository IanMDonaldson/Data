package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
	public StaffDaoImpl() {
		
	}
	
	public List<Staff> setStaffForStore(Store store) {
		// TODO use preparedStatment
		List<Staff> staffList = new LinkedList<Staff>();
		String query = "SELECT staff.staff_id, staff.first_name,staff.last_name,staff.address_id,staff.email,store.store_id,staff.username,staff.password,staff.last_update,store.manager_staff_id\n" + 
				"FROM staff join store on staff.store_id = staff.store_id\n" + 
				"WHERE staff.store_id = " + Integer.toString(store.getStoreID()) + ";";
		try {
			Connection conn = ConnectionFactory.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Staff staff = new Staff();
				staff.setAddressID(rs.getInt("address_id"));
				staff.setStoreID(rs.getInt("staff_id"));
				staff.setEmail(rs.getString("email"));
				staff.setFirstName(rs.getString("first_name"));
				staff.setLastName(rs.getString("last_name"));
				staff.setStaffID(rs.getInt("staff_id"));
				staff.setPassword(rs.getString("password"));
				staff.setUsername(rs.getString("username"));
				staffList.add(staff);
			}
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;
	}
	
	public List<Staff> getStaff(String query) {
		// TODO have function take in PREPAREDsTATMENT parameter
		List<Staff> staffList = new LinkedList<Staff>();
		StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
		Connection conn = ConnectionFactory.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Staff staff = new Staff();
				staff.setAddressID(rs.getInt("address_id"));
				staff.setStoreID(rs.getInt("staff_id"));
				staff.setEmail(rs.getString("email"));
				staff.setFirstName(rs.getString("first_name"));
				staff.setLastName(rs.getString("last_name"));
				staff.setStaffID(rs.getInt("staff_id"));
				staff.setPassword(rs.getString("password"));
				staff.setUsername(rs.getString("username"));
				staff.setWorksAt(storeDaoImpl.getStoresForStaff(staff));
				staffList.add(staff);
			}
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;
	}
	@Override
	public List<Staff> getStaffAtStore(int storeID) {
		String query = "SELECT staff.staff_id, staff.first_name,staff.last_name,staff.address_id,staff.email,store.store_id,staff.username,staff.password,staff.last_update,store.manager_staff_id\n" + 
				"FROM staff join store on staff.store_id = staff.store_id\n" + 
				"WHERE staff.store_id = store.store_id;";
		return getStaff(query);
	}

	@Override
	public List<Staff> getAllStaff() {
		// TODO use preparedStatement
		String query = "select * from sakila.staff";
		return getStaff(query);
	}

	@Override
	public Staff getStaff(int managerStaffID) {
		// TODO implement for Website
		return null;
	}

	@Override
	public boolean updateStaff(Staff staffMember) {
		return false;
	}

	@Override
	public boolean deleteStaff(int managerStaffID) {
		return false;
	}

}
