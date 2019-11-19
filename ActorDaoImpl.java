package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;


public class ActorDaoImpl implements ActorDao {
	public ActorDaoImpl() {
		
	}
	
	
	@Override
	public List<Actor> setActorsForFilm(Film film) {
		List<Actor> actorList = new LinkedList<Actor>();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from actor join film_actor on actor.actor_id = film_actor.actor_id where film_actor.film_id= ?");
			ps.setInt(1, film.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setId(rs.getInt("actor_id"));
				actor.setLastUpdate(rs.getDate("last_update"));
				actorList.add(actor);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}
	
	
	public List<Actor> getActors(PreparedStatement ps) {//helper function
		List<Actor> actors = new LinkedList<Actor>();
		FilmDaoImpl filmDaoImpl = new FilmDaoImpl();
		ActorLNameComparer lnameCompare = new ActorLNameComparer();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setId(rs.getInt("actor_id"));
				actor.setLastUpdate(rs.getDate("last_update"));
				actor.setFilmList(filmDaoImpl.setFilmsForActor(actor));
				actors.add(actor);
			}
			Collections.sort(actors, lnameCompare.lastNameComparator);
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}
	
	
	@Override
	public List<Actor> getAllActors() {
		List<Actor> actorList = new LinkedList<Actor>();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from actor order by ?;");
			ps.setString(1, "last_name");
			actorList = getActors(ps);
			
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

	@Override
	public Actor getActor(int Id) {
		Actor actor = new Actor();
		FilmDaoImpl filmDaoImpl = new FilmDaoImpl();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from actor where actor.actor_id = ?;");
			ps.setInt(1, Id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actor.setId(rs.getInt("actor_id"));
				actor.setLastUpdate(rs.getDate("last_update"));
				actor.setFilmList(filmDaoImpl.setFilmsForActor(actor));
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return actor;
	}
	
	@Override
	public List<Actor> getActorsByName(String actorName) {
		Connection conn = ConnectionFactory.getConnection();
		List<Actor> actorList = new LinkedList<>();
		
		String[] names = actorName.split(" ");
		String completeStatement = actorSearchSQLQuery(names);
		
		try {
			PreparedStatement ps = conn.prepareStatement(completeStatement);
			if (names.length == 1) {
				ps.setString(1, names[0]);
				ps.setString(2, names[0]);
			}
			else if (names.length >= 2) {
				ps.setString(1, names[0]);
				ps.setString(2, names[1]);
			}
			actorList = getActors(ps);
			ps.close();
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return actorList;
	}

	@Override
	public boolean validActorNameSearch(String actorName) {
		boolean validSearch = false;
		Connection conn = ConnectionFactory.getConnection();
		String[] names = actorName.split(" ");
		String completeStatement = actorSearchSQLQuery(names);
		try {
			PreparedStatement ps = conn.prepareStatement(completeStatement);
			if (names.length == 1) {
				ps.setString(1, names[0]);
				ps.setString(2, names[0]);
			}
			else if (names.length >= 2) {
				ps.setString(1, names[0]);
				ps.setString(2, names[1]);
			}
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				validSearch = true;
			}
			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return validSearch;
	}
	@Override
	public boolean updateActor(Actor actor) {
		boolean isUpdated = false;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update actor "
					+ "set first_name=?, last_name=?"
					+ "where actor_id=?;");
			ps.setString(1, actor.getFirstName());
			ps.setString(2, actor.getLastName());
			ps.setInt(3, actor.getId());
			int rowChanged = ps.executeUpdate();
			if (rowChanged > 0) {
				isUpdated = true;
				conn.close();
				ps.close();
				return isUpdated;
			}
			conn.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdated;
	}

	@Override
	public boolean deleteActor(int Id) {
		return false;
	}
	@Override
	public String actorSearchSQLQuery(String names[]) {//helper function
		//TODO if one word search first OR last
		//if two words search first AND last
		//if three words disgregard it and more
		String completeStatement = null;
		if (names.length == 1) { // when one word search query, search by either last OR first
			completeStatement = "SELECT * FROM actor WHERE actor.first_name LIKE "
					+ "CONCAT('%', ?, '%') OR actor.last_name LIKE "
					+ "CONCAT('%', ?, '%');";	
		}
		else if (names.length >= 2) { //search first names with the first word, and last names with the second, disregarding
									  //the rest of the words (if there are more than two)
			completeStatement = "SELECT * FROM actor WHERE actor.first_name LIKE "
					+ "CONCAT('%', ?, '%') AND actor.last_name LIKE "
					+ "CONCAT('%', ?, '%');";
		}
		return completeStatement;
	}

}
