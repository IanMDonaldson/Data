package com.iandonaldson.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public Category setCategoryForFilm(Film film) {
		Category category = new Category();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select category.category_id,category.name,category.last_update\n" + 
					"from category left join film_category on category.category_id = film_category.category_id\n" + 
					"where film_category.film_id = ?;");
			ps.setInt(1, film.getId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				category.setCategoryID(rs.getInt("category_id"));
				category.setName(rs.getString("name"));
				category.setLastUpdate(rs.getDate("last_update"));
			}
			else {
				conn.close();
				ps.close();
				rs.close();
				
				System.out.println("setCategoryForFilm(film film)'s ifelse(rs.next()) thingy returned false");//TODO ERROR MESSAGE DELETE MAYBE
				return null;
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public List<Category> getAllCategories() {
		
		//TODO might not need, delete later?
		
		List<Category> categoryList = new LinkedList<Category>();
		Connection conn = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from category");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryID(rs.getInt("category_id"));
				category.setName(rs.getString("name"));
				category.setLastUpdate(rs.getDate("last_update"));
				categoryList.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

	@Override
	public boolean updateCategory(int categoryID) {
		return false;
	}

	@Override
	public boolean deleteCategory(int categoryID) {
		return false;
	}

}
