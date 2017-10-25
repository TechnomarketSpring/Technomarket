package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			long categoryId = getCategoryId(p.getCategory());
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
			this.connection.close();
		}
	}

	private long getCategoryId(Category category) throws SQLException {
		Connection con = DBManager.getConnections();
		PreparedStatement ps = con
				.prepareStatement("SELECT category_id FROM technomarket.categories WHERE category_name LIKE '?';");
		ps.setString(1, category.getName());
		ResultSet rs = ps.executeQuery();
		rs.next();
		return rs.getLong("category_id");
	}

	public Category getProductsCategory(long productId) throws SQLException, InvalidCategoryDataException {
		Connection con = DBManager.getConnections();
		PreparedStatement ps = con.prepareStatement(
				"SELECT category_name FROM technomarket.categories AS c JOIN technomarket.order_has_product AS h ON(c.category_id = h.category_id) JOIN technomarket.product AS p ON(h.product_id = p.product_id) WHERE h.product_id = ?;");
		ps.setLong(1, productId);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String name = rs.getString("category_name");
		Category category = new Category(name);
		return category;
	}

}
