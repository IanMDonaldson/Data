package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.util.List;

public interface ActorDao {
	public List<Actor> setActorsForFilm(Film film);
	public List<Actor> getAllActors();
	public Actor getActor(int Id);
	public List<Actor> getActorsByName(String actorName);
	public boolean validActorNameSearch(String actorName);
	public boolean updateActor(Actor actor);
	public boolean deleteActor(int Id);
	public String actorSearchSQLQuery(String names[]);
}
