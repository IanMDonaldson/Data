package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.sql.PreparedStatement;

public class StoreDaoImpl implements StoreDao {
	public StoreDaoImpl() {
		
	}
	public List<Store> getStoresForStaff(Staff staff) {
		List<Store> storeList = new LinkedList<Store>();
		LocationDaoImpl locationDaoImpl = new LocationDaoImpl();
		/*String query = "SELECT store.staff_id, staff.first_name,staff.last_name/,staff.address_id/,staff.email/,"
				+ "store.store_id/,staff.username,staff.password/,staff.last_update,store.manager_staff_id\n" + 
				"FROM staff join store on staff.store_id = store.store_id\n" + 
				"WHERE store.store_id = " + Integer.toString(staff.getStoreID()) + ";";*/
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?\n" + 
					"FROM ? join ? on ? = ?\n" + 
					"WHERE ? = ?;");
			ps.setString(1, "store.staff_id");
			ps.setString(2, "staff.first_name");
			ps.setString(3, "staff.last_name");
			ps.setString(4, "staff.address_id");
			ps.setString(5, "staff.email");
			ps.setString(6, "store.store_id");
			ps.setString(7, "staff.username");
			ps.setString(8, "staff.password");
			ps.setString(9, "staff.last_update");
			ps.setString(10, "store.manager_staff_id");
			ps.setString(11, "staff");
			ps.setString(12, "store");
			ps.setString(13, "staff.store_id");
			ps.setString(14, "store.store_id");
			ps.setString(15, "store.store_id");
			ps.setString(16, Integer.toString(staff.getStoreID()));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store store = new Store();
				store.setAddressID(rs.getInt("address_id"));
				store.setManagerStaffID(rs.getInt("manager_staff_id"));
				store.setStoreID(rs.getInt("store_id"));
				store.setLastUpdate(rs.getDate("last_update"));
				store.setLocation(locationDaoImpl.getLocation(store.getAddressID()));
				storeList.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Store> getStores(PreparedStatement ps) {
		List<Store> storeList = new LinkedList<Store>();
		StaffDaoImpl staffDaoImpl = new StaffDaoImpl();
		LocationDaoImpl locationDaoImpl = new LocationDaoImpl();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Store store = new Store();
				store.setAddressID(rs.getInt("address_id"));
				store.setManagerStaffID(rs.getInt("manager_staff_id"));
				store.setStoreID(rs.getInt("store_id"));
				store.setLastUpdate(rs.getDate("last_update"));
				store.setStaffList(staffDaoImpl.getStaffAtStore(store.getStoreID()));
				store.setLocation(locationDaoImpl.getLocation(store.getAddressID()));
				storeList.add(store);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return storeList;
	}
	
	@Override
	public List<Store> getStoresAndAddresses() {
		List<Store> storeList = new LinkedList<Store>();
		/*String query = "select store.store_id,store.address_id,store.manager_staff_id,store.last_update,address.address,"
				+ "address.district,address.postal_code,city.city\n" + 
				"from store join address on address.address_id = store.address_id AND address join city on address.city_id = city.city_id\n" + 
				"where store.address_id = address.address_id AND address.city_id = city.city_id;";*/
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(
				"select ?, ?, ?, ?, ?, ?, ?, ?\n" + 
				"from ? join ? on ? = ? AND ? join ? on ? = ?\n" + 
				"where ? = ? AND ? = ?;");
			ps.setString(1, "store.store_id");
			ps.setString(2, "store.address_id");
			ps.setString(3, "store.manager_staff_id");
			ps.setString(4, "store.last_update");
			ps.setString(5, "address.address");
			ps.setString(6, "address.district");
			ps.setString(7, "address.postal_code");
			ps.setString(8, "city.city");
			ps.setString(9, "store");
			ps.setString(10, "address");
			ps.setString(11, "address.address_id");
			ps.setString(12, "store.address_id");
			ps.setString(13, "address");
			ps.setString(14, "city");
			ps.setString(15, "address.city_id");
			ps.setString(16, "city.city_id");
			ps.setString(17, "store.address_id");
			ps.setString(18, "address.address_id");
			ps.setString(19, "address.city_id");
			ps.setString(20, "city.city_id");
			storeList = getStores(ps);
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return storeList;
	}
	
	/*@Override
	public List<Store> getStoresEmployingStaff(int staffID) {
		String query = "SELECT staff.staff_id, staff.first_name,staff.last_name,staff.address_id,staff.email,store.store_id,staff.username,staff.password,staff.last_update,store.manager_staff_id\n" + 
				"FROM staff join store on staff.store_id = staff.store_id\n" + 
				"WHERE staff.store_id = store.store_id;";
		return getStores(query);
	}*/

	@Override
	public List<Store> getAllStores() {
		List<Store> storeList = new LinkedList<Store>();
		PreparedStatement ps;
		try {
			Connection conn = ConnectionFactory.getConnection();
			ps = conn.prepareStatement("select ? from ?;");
			ps.setString(1, "*");
			ps.setString(2, "store");
			
			storeList = getStores(ps);
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return storeList;
	}

	@Override
	public Store getStore(int storeID) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = conn.prepareStatement("select ? from ? where ? = ?");
		ps.setString(1, "*");
		ps.setString(2, "store");
		ps.setString(3, "store_id");
		ps.setString(4, Integer.toString(storeID));
		ResultSet rs = ps.executeQuery();
		
		Store store = new Store();
		store.setAddressID(rs.getInt("address"));
		store.setLastUpdate(rs.getDate("last_update"));
		store.setManagerStaffID(rs.getInt("manager_staff_id"));
		store.setStoreID(storeID);
		conn.close();
		ps.close();
		rs.close();
		return store;
	}

	@Override
	public boolean updateStore(Store store) {
		return false;
	}

	@Override
	public boolean deleteStore(int storeID) {
		return false;
	}

}
