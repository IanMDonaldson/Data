package com.iandonaldson.data;

/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FilmDaoImpl implements FilmDao {
	
	public FilmDaoImpl() {
		
	}
	
	public List<Film> setFilmsForActor(Actor actor) {
		List<Film> filmList = new LinkedList<Film>();
		String query = "Select * from film join film_actor on film.film_id = film_actor.film_id where film_actor.actor_id = " + Integer.toString(actor.getId()) + ";";
		try {
			Connection conn = ConnectionFactory.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
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
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	//helper function
	public List<Film> getFilms(String query) {
		List<Film> filmList = new LinkedList<Film>();
		ActorDaoImpl actorDaoImpl = new ActorDaoImpl();
		try {
			Connection conn = ConnectionFactory.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
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
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return filmList;
	}
	
	
	@Override //used by actorDaoImpl to set its films
	public List<Film> getFilmsByActor(int actorID) { 
		String query = "Select * from film join film_actor on film.film_id = film_actor.film_id where film_actor.actor_id = " + Integer.toString(actorID) + ";";
		return getFilms(query);
	}
	@Override
	public List<Film> getAllFilms() {
		List<Film> filmList = new LinkedList<Film>();
		try {
			Connection conn = ConnectionFactory.getConnection();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from film");
			while (rs.next()) {
				Film film = new Film();
				film.setId(rs.getInt("film_id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseDate(rs.getDate("release_year"));
				film.setLength(rs.getInt("length"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				// [TODO set actor list for each film]
				filmList.add(film);
			}
			conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}

	@Override
	public Film getFilm(int Id) {
		Film film = new Film();
		
		Connection conn = ConnectionFactory.getConnection();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select * from sakila.film where film_id = " + Id);
			if (rs.next()) {
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseDate(rs.getDate("release_year"));
				film.setLength(rs.getInt("length"));
				film.setLanguageId(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
			} 
			else {
				film = null;
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return film;
	}

	@Override
	public boolean updateFilm(Film film) {
		return false;
	}

	@Override
	public boolean deleteFilm(int Id) {
		return false;
	}

}