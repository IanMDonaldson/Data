package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LanguageDaoImpl implements LanguageDao {

	@Override
	public Language setLanguageForFilm(Film film) {//used by FilmDaoImpl to set language for film
		Language language = new Language();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT sakila.language.language_id, sakila.language.name, sakila.language.last_update\n" + 
					"from language join film on language.language_id = film.language_id\n" + 
					"where film.film_id = ?;");
			ps.setInt(1, film.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				language.setLanguageID(rs.getInt("language_id"));
				language.setName(rs.getString("name"));
				language.setLastUpdate(rs.getDate("last_update"));
			}
			else {
				conn.close();
				ps.close();
				rs.close();
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}

	@Override
	public boolean updateLanguage() {
		return false;
	}

	@Override
	public boolean deleteLanguage() {
		return false;
	}

}
