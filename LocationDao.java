package com.iandonaldson.data;

import java.util.List;

public interface LocationDao {
	public List<Location> getAllLocations();
	public Location getLocation(int addressID);
	public boolean updateLocation(Location location);
	public boolean deleteLocation(int addressID);
}
