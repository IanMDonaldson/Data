package com.iandonaldson.data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class LocationDaoImpl implements LocationDao {
	public LocationDaoImpl() {
		
	}
	
	public Location getLocationFromDB(PreparedStatement ps) {
		Location location = new Location();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) { //without while, gives error that it is before the actual table returned
				location.setAddress(rs.getString("address"));
				location.setAddressID(rs.getInt("address_id"));
				location.setCity(rs.getString("city"));
				location.setCityID(rs.getInt("city_id"));
				location.setDistrict(rs.getString("district"));
				location.setPhoneNumber(rs.getString("phone"));
				location.setPostalCode(rs.getInt("postal_code"));
				location.setCountryID(rs.getInt("country_id"));
				location.setCountry(rs.getString("country"));
				location.setLastUpdate(rs.getDate("last_update"));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
	}
	
	@Override
	public List<Location> getAllLocations() {
		List<Location> locationList = new LinkedList<Location>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("select *"
					+ "from address right join city on address.city_id=city.city_id "
					+ "inner join country on city.country_id=country.country_id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Location location = new Location();
				location.setAddress(rs.getString("address"));
				location.setAddressID(rs.getInt("address_id"));
				location.setCity(rs.getString("city"));
				location.setCityID(rs.getInt("city_id"));
				location.setDistrict(rs.getString("district"));
				location.setPhoneNumber(rs.getString("phone"));
				location.setPostalCode(rs.getInt("postal_code"));
				location.setCountryID(rs.getInt("country_id"));
				location.setCountry(rs.getString("country"));
				location.setLastUpdate(rs.getDate("last_update"));
				locationList.add(location);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return locationList;
	}

	@Override
	public Location getLocation(int addressID) {
		Location location = new Location();
		/*String query = "SELECT address.address_id,address.address,city.city,address.district,address.postal_code,country.country,address.phone,address.city_id,city.country_id,city.last_update\n" + 
				"FROM address right join city on address.city_id = city.city_id inner join country on city.country_id=country.country_id\n" + 
				"where address.address_id = " + Integer.toString(addressID) + ";";*/
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT address.address_id,address.address,city.city,address.district,address.postal_code,country.country,address.phone,address.city_id,city.country_id,city.last_update\n" + 
					"FROM address right join city on address.city_id = city.city_id inner join country on city.country_id=country.country_id\n" + 
					"WHERE address.address_id = ?;");
			ps.setInt(1, addressID);
			location = getLocationFromDB(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return location;
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
