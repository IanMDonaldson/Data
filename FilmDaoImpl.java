package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FilmDaoImpl implements FilmDao {
	
	public FilmDaoImpl() {
		
	}
	@Override
	public List<Film> setFilmsForActor(Actor actor) {
		List<Film> filmList = new LinkedList<Film>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(
					"Select * from film join film_actor on film.film_id = film_actor.film_id where film_actor.actor_id = ?;");
			ps.setInt(1, actor.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("film_id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getNString("description"));
				film.setReleaseDate(rs.getDate("release_year"));
				film.setRating(rs.getString("rating"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				filmList.add(film);
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	//helper function
	public List<Film> getFilms(PreparedStatement ps) {
		List<Film> filmList = new LinkedList<Film>();
		ActorDaoImpl actorDaoImpl = new ActorDaoImpl();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("film_id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getNString("description"));
				film.setReleaseDate(rs.getDate("release_year"));
				film.setRating(rs.getString("rating"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setLength(rs.getInt("length"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setActorList(actorDaoImpl.setActorsForFilm(film));
				filmList.add(film);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filmList;
	}
	
	
	//@Override
//	public List<Film> getFilmsByActor(int actorID) { 
//		String query = "Select * from film join film_actor on film.film_id = film_actor.film_id where film_actor.actor_id = " + Integer.toString(actorID) + ";";
//		return getFilms(query);
//	}
	@Override
	public List<Film> getAllFilms() {
		List<Film> filmList = new LinkedList<Film>();
		FilmDaoImpl filmDaoImpl = new FilmDaoImpl();
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("select * from film");
			
			filmList = filmDaoImpl.getFilms(ps);
			
			ps.close();
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	@Override
	public Film getFilm(int ID) {
		Film film = new Film();
		ActorDaoImpl actorDaoImpl = new ActorDaoImpl();
		try {
			Connection conn = ConnectionFactory.getConnection();
			
			PreparedStatement ps = conn.prepareStatement(
					"Select *"
					+ "from sakila.film where sakila.film.film_id = ?;");
			ps.setString(1, Integer.toString(ID));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseDate(rs.getDate("release_year"));
				film.setLength(rs.getInt("length"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setActorList(actorDaoImpl.setActorsForFilm(film));
			} else {
				film = null;
			}
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return film;
	}
	/*Updates film, used by WebFilm.java class*/
	@Override
	public boolean updateFilm(Film film) {
		boolean isUpdated = false;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update film "
					+ "set title=?, description=?, release_year=?, length=?, rental_rate=?, replacement_cost=? "
					+ "where film_id=?;");
			ps.setString(1, film.getTitle());
			ps.setString(2, film.getDescription());
			ps.setDate(3, film.getReleaseDate());//changed getReleaseDate return type to a java.sql.Date
			ps.setInt(4, film.getLength());
			ps.setDouble(5, film.getRentalRate());
			ps.setDouble(6, film.getReplacementCost());
			ps.setInt(7, film.getId());
			int colChanged = 0;
			colChanged = ps.executeUpdate();
			if (colChanged > 0) {
				isUpdated=true;
				conn.close();

				return isUpdated;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isUpdated;
	}

	@Override
	public boolean deleteFilm(int Id) {
		return false;
	}

}
