package com.iandonaldson.data;

import java.util.Comparator;

public class ActorLNameComparer implements Comparable<Actor> {
	public int compare(Actor actor1, Actor actor2) {
		
		return actor1.getLastName().compareTo(actor2.getLastName());
	}

	public Comparator<Actor> lastNameComparator = new Comparator<Actor>() {
		public int compare(Actor actor1, Actor actor2) {
			return actor1.getLastName().compareTo(actor2.getLastName());
		}
	};
	@Override
	public int compareTo(Actor o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
