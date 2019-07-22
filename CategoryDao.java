package com.iandonaldson.data;

import java.util.List;

public interface CategoryDao {
	public Category setCategoryForFilm(Film film);
	public List<Category> getAllCategories();
	public boolean updateCategory(int categoryID);
	public boolean deleteCategory(int categoryID);
}
