package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class FilmDaoImpl implements FilmDao {
	private ActorDaoImpl actorDaoImpl;
	public FilmDaoImpl() {
		
	}

	/*Updates film, used by WebFilm.java class*/
	@Override
	public boolean updateFilm(Film film) {
		boolean isUpdated = false;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("update film "
					+ "set title=?, description=?, length=?, rental_rate=?, replacement_cost=? "
					+ "where film_id=?;");
			ps.setString(1, film.getTitle());
			ps.setString(2, film.getDescription());
			//ps.setDate(3, film.getReleaseDate());//changed getReleaseDate return type to a java.sql.Date
			ps.setInt(3, film.getLength());
			ps.setDouble(4, film.getRentalRate());
			ps.setDouble(5, film.getReplacementCost());
			ps.setInt(6, film.getId());
			int rowChanged = 0;
			rowChanged = ps.executeUpdate();
			if (rowChanged > 0) {
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
	public boolean filmExists(String title) {
		boolean filmExists = false;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = filmTitleSQLQuery(title, conn);
		try {
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				filmExists = true;
				rs.close();
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmExists;
	}
	
	@Override
	public boolean deleteFilm(int id) {
		boolean filmDeleted = false;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM film "
					+ "WHERE film_id = ?;");
			ps.setInt(1, id);
			int i = ps.executeUpdate();
			if (i != 0) {
				filmDeleted = true;
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmDeleted;
	}

	@Override
	public boolean addFilm(Film film) {
		boolean isAddSuccessful = false;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("INSERT INTO film(title, description, release_year, language_id, rental_duration, "
					+ "rental_rate, length, replacement_cost, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, film.getTitle());
			ps.setString(2, film.getDescription());
			ps.setDate(3, film.getReleaseYear());
			ps.setInt(4, film.getLanguageID());
			ps.setInt(5, film.getRentalDuration());
			ps.setDouble(6, film.getRentalRate());
			ps.setInt(7, film.getLength());
			ps.setDouble(8, film.getReplacementCost());
			ps.setString(9, film.getRating());
			int rowChanged = ps.executeUpdate();
			if (rowChanged > 0) {
				isAddSuccessful = true;
			}
			ps.close();
			conn.close();

			return isAddSuccessful; //false
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAddSuccessful;
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
				film.setId(rs.getInt("film_id"));
				film.setTitle(rs.getString("title"));
				film.setDescription(rs.getString("description"));
				film.setReleaseYear(rs.getDate("release_year"));
				film.setLength(rs.getInt("length"));
				film.setLanguageID(rs.getInt("language_id"));
				film.setRentalDuration(rs.getInt("rental_duration"));
				film.setRentalRate(rs.getDouble("rental_rate"));
				film.setReplacementCost(rs.getDouble("replacement_cost"));
				film.setActorList(actorDaoImpl.getActorsForFilm(film));
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

	@Override
	public int getNewFilmID() {
		int newFilmID = -1;
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from sakila.film F "
					+ "order by F.film_id desc limit 1;");
			ResultSet rs = ps.executeQuery();
			rs.next();
			newFilmID = rs.getInt("film_id");
			rs.close();
			ps.close();
			conn.close();
			return newFilmID;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newFilmID;
	}

	@Override
	public List<Film> getFilmsForActor(Actor actor) {
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
				film.setReleaseYear(rs.getDate("release_year"));
				film.setRating(rs.getString("rating"));
				film.setLanguageID(rs.getInt("language_id"));
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

	//helper function
	public List<Film> getFilms(PreparedStatement ps) {
		List<Film> filmList = new LinkedList<Film>();
		ActorDaoImpl actorDaoImpl = new ActorDaoImpl();
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Film film = new Film(rs.getString("title"), rs.getString("description"),
						rs.getString("rating"), rs.getInt("film_id"),
						rs.getInt("language_id"), rs.getInt("rental_duration"),
						rs.getInt("length"), rs.getDouble("rental_rate"),
						rs.getDouble("replacement_cost"),
						rs.getDate("release_year"));
				film.setActorList(actorDaoImpl.getActorsForFilm(film));
				filmList.add(film);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return filmList;
	}

	@Override
	public List<Film> getFilmsByTitle(String title) {//checked for validSearch in WebFilm.java/searchFilmGET
		List<Film> filmList = null;
		Connection conn = ConnectionFactory.getConnection();
		PreparedStatement ps = filmTitleSQLQuery(title, conn);
		try {
			filmList = getFilms(ps);
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmList;
	}
	@Override
	public PreparedStatement filmTitleSQLQuery(String title, Connection conn) {//YOU DONT NEED TO DETERMINE WHETHER FILMID EXISTSd
		String completeStatement = "select * from sakila.film " +
				"where film.title LIKE CONCAT('%', ?, '%');";
		PreparedStatement ps = null;
		try {
				ps = conn.prepareStatement(completeStatement);
				ps.setString(1, title);
				return ps;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ps;
	}

	@Override
	public boolean assocActors(Film film, String newlyAssocActorIDs) {
		/*delete already associated actors with delActorAssocFromFilm first, then
		* just iterate through the list of actorID's given by newlyAssocActorIDs adding every film_actor
		* value to the SQL statement. Then setActorList for the film object*/
		List<Actor> newActors = new LinkedList<Actor>();
		StringBuilder statement = new StringBuilder("INSERT INTO film_actor(actor_id, film_id) VALUES ");
	    String[] actorIDs = newlyAssocActorIDs.split("(,)+");
		int filmID = film.getId();
		int j = 1;
		if (delActorAssocFromFilm(film)) {
			for (int i = 0; i < actorIDs.length - 1; i++ ) {
				statement.append("(?, ?), ");
			}
			statement.append("(?, ?);");

			Connection conn = ConnectionFactory.getConnection();
			int rowsAffected;
			try {
				PreparedStatement ps = conn.prepareStatement(statement.toString());
				for (String actorID : actorIDs) {
					ps.setInt(j, Integer.parseInt(actorID));
					j += 1;
					ps.setInt(j, filmID);
					j += 1;
				} //now the actors are associated to the film in the database and now all we have to do is
				//  grab the actors :D
				rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0 ) {
					ActorDaoImpl actorDaoImpl = new ActorDaoImpl();
					List<Actor> actorList = actorDaoImpl.getActorsForFilm(film);
					film.setActorList(actorList);
					ps.close();
					conn.close();
					return true;
				}
			} catch (SQLException e ) {
				e.printStackTrace();
			}

		}
		return false;
	}

	@Override
	public boolean delActorAssocFromFilm(Film film) {
		boolean deleteSuccessful = false;
		List<Actor> actorList = film.getActorList();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM sakila.film_actor " +
					"WHERE film_id = ?;");
			ps.setInt(1, film.getId());
			int rowChanged = ps.executeUpdate();
			if (rowChanged > 0) {
				deleteSuccessful = true;
			}
			ps.close();
			conn.close();
			return deleteSuccessful;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deleteSuccessful;
	}
}