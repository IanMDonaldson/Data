package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/

import java.util.Date;
import java.util.List;

public class Actor extends Person implements Comparable<Actor> {
	private int id;
	private Date lastUpdate;
	private List<Film> filmList;
	
	public Actor() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public List<Film> getFilmList() {
		return filmList;
	}

	public void setFilmList(List<Film> filmList) {
		this.filmList = filmList;
	}
	

	@Override
	public int compareTo(Actor o) {
		return this.getLastName().compareTo(o.getLastName());
	}
	
	
}
