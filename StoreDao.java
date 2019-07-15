package com.iandonaldson.data;

import java.sql.SQLException;
import java.util.List;

public interface StoreDao {
	public List<Store> getStoresAndAddresses();
	public List<Store> getStoresForStaff(Staff staff);
	public List<Store> getAllStores();
	public Store getStore(int storeID) throws SQLException;
	public boolean updateStore(Store store);
	public boolean deleteStore(int storeID);
}
