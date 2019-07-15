package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.util.List;

public interface FilmDao {
	public List<Film> getFilmsByActor(int actorID);//used by actorDaoImpl to set its films
	public List<Film> getAllFilms();
	public Film getFilm(int Id);
	public boolean updateFilm(Film film);
	public boolean deleteFilm(int Id);
}
