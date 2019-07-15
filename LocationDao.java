package com.iandonaldson.data;

public interface LocationDao {
	public Location getLocation(int addressID);
	public boolean updateLocation(Location location);
	public boolean deleteLocation(int addressID);
}
