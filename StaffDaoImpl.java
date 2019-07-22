package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class StaffDaoImpl implements StaffDao {
	public StaffDaoImpl() {
		
	}
	
	public List<Staff> setStaffForStore(Store store) {
		// TODO use preparedStatment
		List<Staff> staffList = new LinkedList<Staff>();
		LocationDaoImpl locationDaoImpl = new LocationDaoImpl();
		String query = "SELECT staff.staff_id, staff.first_name,staff.last_name,staff.address_id,staff.email,store.store_id,staff.username,staff.password,staff.last_update,store.manager_staff_id\n" + 
				"FROM staff join store on staff.store_id = staff.store_id\n" + 
				"WHERE staff.store_id = " + Integer.toString(store.getStoreID()) + ";";
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT staff.staff_id, staff.first_name,staff.last_name,staff.address_id,staff.email,store.store_id,staff.username,staff.password,staff.last_update,store.manager_staff_id\n" + 
				"FROM staff join store on staff.store_id = staff.store_id\n" + 
				"WHERE staff.store_id = ?;"); 
			ps.setInt(1, store.getStoreID());
			ResultSet rs = ps.executeQuery(query);
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
				staff.setLocation(locationDaoImpl.getLocation(staff.getAddressID()));
				staffList.add(staff);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;
	}
	
	public List<Staff> getStaff(PreparedStatement ps) {
		// TODO have function take in PREPAREDsTATMENT parameter
		List<Staff> staffList = new LinkedList<Staff>();
		StoreDaoImpl storeDaoImpl = new StoreDaoImpl();
		LocationDaoImpl locationDaoImpl = new LocationDaoImpl();
		try {
			ResultSet rs = ps.executeQuery();
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
				staff.setLocation(locationDaoImpl.getLocation(staff.getAddressID()));
				staffList.add(staff);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;
	}
	@Override
	public List<Staff> getStaffAtStore(int storeID) {
		List<Staff> staffList = new LinkedList<Staff>();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT staff.staff_id, staff.first_name,staff.last_name,staff.address_id,staff.email,store.store_id,staff.username,staff.password,staff.last_update,store.manager_staff_id\n" + 
					"FROM staff join store on staff.store_id = staff.store_id\n" + 
					"WHERE staff.store_id = store.store_id;");
			staffList = getStaff(ps);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;
	}

	@Override
	public List<Staff> getAllStaff() {
		List<Staff> staffList = new LinkedList<Staff>();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from sakila.staff");
			staffList = getStaff(ps);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return staffList;

	}

	@Override
	public Staff getStaff(int managerStaffID) {
		// TODO implemnet for website
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
