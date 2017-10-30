package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Category;
import com.example.model.Product;
import com.example.model.DBM.DBManager;
import com.example.model.exceptions.InvalidCategoryDataException;
;
@Component
public class CategoryDAO {
    @Autowired
    DBManager DBManager;
	private Connection connection;

	

	

	public void insertProductIntoProductHasCategory(Product p) throws SQLException {
		this.connection = DBManager.getConnections();
		this.connection.setAutoCommit(false);
		try {
			long categoryId = getCategoryId(p.getCategory().getName());
			PreparedStatement ps = this.connection.prepareStatement(
					"INSERT INTO technomarket.product_has_category (category_id, product_id) VALUES (?, ?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, categoryId);
			ps.setLong(2, p.getProductId());
			ps.executeUpdate();
			this.connection.commit();
			ps.close();
		} catch (SQLException e) {
			this.connection.rollback();
			throw new SQLException();
		} finally {
			this.connection.setAutoCommit(true);
		}
	}

	private long getCategoryId(String category) throws SQLException {
		System.out.println("====================================================================================");
		System.out.println("=================================================================" + category);
		Connection con = DBManager.getConnections();
		PreparedStatement ps = con
				.prepareStatement("SELECT category_id FROM technomarket.categories WHERE category_name LIKE ?;");

		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getLong("category_id");
	}

	public Category getProductsCategory(long productId) throws SQLException, InvalidCategoryDataException {
		Connection con = DBManager.getConnections();
		PreparedStatement ps = con.prepareStatement(
				"SELECT category_name FROM technomarket.categories AS c JOIN technomarket.product_has_category AS h ON(c.category_id = h.category_id) JOIN technomarket.product AS p ON(h.product_id = p.product_id) WHERE h.product_id = ?;");
		ps.setLong(1, productId);
		ResultSet rs = ps.executeQuery();
		String name = "";
		while(rs.next()){
			name += rs.getString("category_name");
		}
		Category category = new Category(name);
		return category;
	}
	
	
	public boolean categoryExist(String category) throws SQLException{
		this.connection = DBManager.getConnections();
		PreparedStatement ps = this.connection.prepareStatement("SELECT category_name FROM technomarket.categories WHERE category_name = ?;");
		ps.setString(1, category);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			rs.close();
			return true;
		}else{
			rs.close();
			return false;
		}
	}
	
	public void insertCategory(String category) throws SQLException {
		this.connection = DBManager.getConnections();
		PreparedStatement ps = this.connection.prepareStatement("INSERT INTO technomarket.categories (category_name) VALUES (?)");
		ps.setString(1, category);
		ps.executeUpdate();
	}
	
	public TreeSet<String> getAllInnerCategories() throws SQLException{
		TreeSet<String> innerCategories = new TreeSet<>();
		this.connection = DBManager.getConnections();
		PreparedStatement ps = this.connection.prepareStatement("SELECT category_name FROM technomarket.categories WHERE parent_category_id IS NOT NULL;");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String categoryName = rs.getString("category_name");
			innerCategories.add(categoryName);
		}
		ps.close();
		rs.close();
		return innerCategories;
	}	

}
