package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocationDaoImpl implements LocationDao {
	public LocationDaoImpl() {
		
	}
	
	public Location getLocationFromDB(String query) {
		// TODO make able to use PREPAREDsTATMENT as function parameters
		Location location = new Location();
		Connection conn = ConnectionFactory.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			location.setAddress(rs.getString("address"));
			location.setAddressID(rs.getInt("address_id"));
			location.setCity(rs.getString("city"));
			location.setCityID(rs.getInt("city_id"));
			location.setDistrict(rs.getString("district"));
			location.setPhoneNumber(rs.getString("phone"));
			location.setPostalCode(rs.getInt("postal_code"));
			location.setCountryID(rs.getInt("country_id"));
			conn.close();
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}
	

	@Override
	public Location getLocation(int addressID) {
		// TODO make query a preparedStatement, and set up the connection here passing in PREPAREDsTATEmENT to getLocationFromDB
		String query = "SELECT address.address_id,address.address,city.city,address.district,address.postal_code,address.phone,address.city_id,city.country_id\n" + 
				"FROM address join city on address.city_id = city.city_id\n" + 
				"where address.address_id = " + Integer.toString(addressID) + ";";
		return getLocationFromDB(query);
	}

	@Override
	public boolean updateLocation(Location location) {
		return false;
	}

	@Override
	public boolean deleteLocation(int addressID) {
		return false;
	}

}
