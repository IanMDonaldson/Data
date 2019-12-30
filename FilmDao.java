package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.util.List;

public interface FilmDao {
	public List<Film> setFilmsForActor(Actor actor);//used by actorDaoImpl to set its films
	public List<Film> getAllFilms();
	public List<Film> getFilmsByTitle(String title);
	public Film getFilm(int Id);
	public Integer getNewFilmID();
	
	public boolean updateFilm(Film film);
	public boolean searchFilmByTitle(String title);
	public boolean deleteFilm(int id);
	public boolean filmExists(Film film);
	public boolean addFilm(Film film);
}
