package com.iandonaldson.data;

import java.util.List;

public interface ActorDao {
	//public Actor addActor(Actor actor);
	public Actor getActor(int Id);
	
	public boolean actorExists(Actor actor);
	public boolean addActor(Actor actor);
	public boolean deleteActor(Actor actor);
	public boolean updateActor(Actor actor);
	public boolean validActorNameSearch(String actorName);
	
	public Integer getNewActorID();
	
	public List<Actor> getActorsByName(String actorName);
	public List<Actor> getAllActors();
	public List<Actor> getRemovableActors(); //returns to Actor deletion page

	abstract List<Actor> setActorsForFilm(Film film);
	public List<Actor> getActorsNotAssocWFilm(Film film);
	
	public String actorSearchSQLQuery(String names[]);
}
