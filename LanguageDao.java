package com.iandonaldson.data;

public interface LanguageDao {
	public Language setLanguageForFilm(Film film);
	public boolean updateLanguage();
	public boolean deleteLanguage();
}
