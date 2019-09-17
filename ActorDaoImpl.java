package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;


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
	
	
	public List<Actor> getActors(PreparedStatement ps) {
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
	public boolean updateActor(Actor actor) {
		return false;
	}

	@Override
	public boolean deleteActor(int Id) {
		return false;
	}


}
