package com.iandonaldson.data;
/* a) list all the stores and their addresses (that’s going to require a Join)
 * b) list the stores and their staff.
 * That’s going to require a Join AND using an inherited class. 
 * 1 – List all movies and actors
 * 2 – List all stores and addresses
 * 3 – List all stores and staff.*/
import java.sql.Date;
import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private Date releaseDate;
	private int languageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private List<Actor> actorList;
	private Category category;
	private Language language;
	
	public Film() {
		
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getReleaseDate() {
		return releaseDate;
	}



	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}



	public int getLanguageId() {
		return languageId;
	}



	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}



	public int getRentalDuration() {
		return rentalDuration;
	}



	public void setRentalDuration(int rentalDuration) {
		this.rentalDuration = rentalDuration;
	}



	public double getRentalRate() {
		return rentalRate;
	}



	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public double getReplacementCost() {
		return replacementCost;
	}



	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}



	public List<Actor> getActorList() {
		return actorList;
	}



	public void setActorList(List<Actor> actorList) {
		this.actorList = actorList;
	}



	public String getRating() {
		return rating;
	}



	public void setRating(String rating) {
		this.rating = rating;
	}



	public Category getCategory() {
		return category;
	}



	public void setCategory(Category category) {
		this.category = category;
	}



	public Language getLanguage() {
		return language;
	}



	public void setLanguage(Language language) {
		this.language = language;
	}
}

