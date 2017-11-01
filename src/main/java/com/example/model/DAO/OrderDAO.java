package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Characteristics;
import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.DBM.DBManager;
import com.example.model.exceptions.IlligalAdminActionException;
import com.example.model.exceptions.IlligalUserActionException;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidCharacteristicsDataException;


@Component
public class OrderDAO {
	//Trqbva da zatrobvim vsichki konekcii i statement i result set vuv creditDAO, CharacterisDAO, CategoryDAO, AdminDAO
	@Autowired
	DBManager DBManager;
	@Autowired
	ProductDAO productDAO;
	private Connection connection;
	//-> select all orders per user_id, fill them in HashSet, select all products per Order, their quantity, and fields it in HashMap of every Order:
	public LinkedHashSet<Order> getOrdersForUser(long long1) throws SQLException, InvalidCharacteristicsDataException, InvalidCategoryDataException {
		LinkedHashSet<Order> orders = new LinkedHashSet<>();
		String query = "SELECT * FROM technomarket.orders WHERE user_id = ?;";
		this.connection = DBManager.getConnections();
		PreparedStatement statment = this.connection.prepareStatement(query);
		statment.setLong(1, long1);
		ResultSet result = statment.executeQuery();
		while (result.next()) {
			Order o = new Order();
			o.setOrderId(result.getLong("order_id"));
			o.setTime(returnDateAndTime(result.getString("date_time")));
			o.setAddress(result.getString("address"));
			o.setPayment(result.getString("payment"));
			o.setNotes(result.getString("notes"));
	
			o.setConfirmed(result.getBoolean("isConfirmed"));
			o.setPaid(result.getBoolean("isPaid"));
			o.setShipingType(null);
			LinkedHashMap<Product, Integer> products = fillAllProductsInOrder(o);
			o.setProducts(products);
			orders.add(o);
		}
		System.out.println(orders.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@2");
		return orders;
	}

	


	private LinkedHashMap<Product, Integer> fillAllProductsInOrder(Order o) throws SQLException, InvalidCharacteristicsDataException, InvalidCategoryDataException {
		LinkedHashMap<Product, Integer> products = new LinkedHashMap<>();
		String query = "SELECT * FROM technomarket.order_has_product WHERE order_id = ?;";
		this.connection = DBManager.getConnections();
		PreparedStatement statment = this.connection.prepareStatement(query);
		statment.setLong(1, o.getOrderId());
		ResultSet result = statment.executeQuery();
		while (result.next()) {
			products.put(productDAO.getProduct(result.getLong("product_id")), result.getInt("quantity"));
		}
		return products;
	}

//	private Order.Shiping getTheRightShipingType(String shipingType) {
//		if(shipingType.equals("HOME_ADDRESS")){
//			return Order.Shiping.HOME_ADDRESS;
//		}
//		
//		if(shipingType.equals("STORE")){
//			return Order.Shiping.STORE;
//		}
//		
//		return null;
//	}

	private LocalDate returnDateAndTime(String dateAndTime) {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDate dateTime = LocalDate.parse(dateAndTime);
		return dateTime;
	}

	//user panel in Orders:
	
	public void insertNewOrder(User u, Order o) throws SQLException{
		Connection con = DBManager.getConnections();
		PreparedStatement ps = con.prepareStatement("INSERT INTO technomarket.orders (user_id, date_time, address, payment, notes, isConfirmed, isPaid, shiping_type, zip, user_phone) VALUES (?,?, ?, ?, ?,?,?, ?,?,?);", Statement.RETURN_GENERATED_KEYS);
		ps.setLong(1, u.getUserId());
		ps.setString(2, o.getTime().toString());
		ps.setString(3, o.getAddress());
		ps.setString(4, o.getPayment());
		ps.setString(5, o.getNotes());
		ps.setBoolean(6, o.getIsConfirmed());
		ps.setBoolean(7, o.getIsPaid());
		ps.setString(8, null);
		ps.setString(9, o.getZip());
		ps.setString(10, o.getUserPhoneNumber());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		o.setOrderId(rs.getLong(1));
		addOrderedProductsToTable(o);
	}
	
	private void addOrderedProductsToTable(Order o) throws SQLException{
		Connection con = DBManager.getConnections();
		for (Iterator<Entry<Product, Integer>> iterator = o.getProducts().entrySet().iterator(); iterator.hasNext();) {
			Entry<Product, Integer> entry = iterator.next();
			PreparedStatement ps = con.prepareStatement("INSERT INTO technomarket.order_has_product (order_id, product_id, quantity) VALUES (?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			ps.setLong(1, o.getOrderId());
			ps.setLong(2, entry.getKey().getProductId());
			ps.setInt(3, entry.getValue());
			ps.executeUpdate();
		}
	}
	
	public void setOrderAsConfirmed(Order o, boolean isConfirmed) throws SQLException, IlligalUserActionException {
		if (o.getIsConfirmed() && isConfirmed) {
			throw new IlligalUserActionException();
		} else if (!o.getIsConfirmed() && !isConfirmed) {
			throw new IlligalUserActionException();
		} else {
			Connection con = DBManager.getConnections();
			PreparedStatement ps = con.prepareStatement("UPDATE technomarket.orders SET isConfirmed = ? WHERE order_id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, isConfirmed);
			ps.setLong(2, o.getOrderId());
			ps.executeUpdate();
		}
		
	}
	
	//admin panel in Orders:

	public void setOrderAsPaid(Order o, boolean isPaid) throws IlligalAdminActionException, SQLException {
		if (o.getIsConfirmed() && isPaid) {
			throw new IlligalAdminActionException();
		} else if (!o.getIsConfirmed() && !isPaid) {
			throw new IlligalAdminActionException();
		} else {
			Connection con = DBManager.getConnections();
			PreparedStatement ps = con.prepareStatement("UPDATE technomarket.orders SET isConfirmed = ? WHERE order_id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, isPaid);
			ps.setLong(2, o.getOrderId());
			ps.executeUpdate();
		}
		
	}
	public Order searchOrderById(String id) throws SQLException{
		Order order = new Order();
		this.connection = DBManager.getConnections();
		PreparedStatement statement = this.connection.prepareStatement("SELECT order_id, users.first_name, users.last_name, date_time, address, payment,notes, isConfirmed, isPaid, zip, user_phone  FROM technomarket.orders JOIN technomarket.users ON(orders.user_id = users.user_id)  WHERE order_id = ?");
		statement.setString(1, id);
		ResultSet result = statement.executeQuery();
		while(result.next()){
		  order.setOrderId(result.getLong(1));
		  order.setUserNames(result.getString(2)+" "+result.getString(3));
		  order.setTime(LocalDate.parse(result.getString(4)));
		  order.setAddress(result.getString(5));
		  order.setPayment(result.getString(6));
		  order.setNotes(result.getString(7));
		  order.setConfirmed(result.getBoolean(8));
		  order.setPaid(result.getBoolean(9));
		  order.setZip(result.getString(10));
		  order.setUserPhoneNumber(result.getString(11));
		}
		statement.close();
		return order;
	}
	public HashSet<Product> getProductFromOrder(String id) throws SQLException{
		HashSet<Product> products = new HashSet<>();
		PreparedStatement st = this.connection.prepareStatement("SELECT trade_marks.trade_mark_name, product.product_name, price, warranty, percent_promo, date_added, product_number, image_url FROM technomarket.product JOIN technomarket.trade_marks ON(trade_marks.trade_mark_id = product.trade_mark_id) JOIN technomarket.order_has_product ON(order_has_product.order_id = ?)");
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		Product pr = null;
		while(rs.next()){
			pr = new Product();
			pr.setTradeMark(rs.getString("trade_marks.trade_mark_name"));
			pr.setName(rs.getString("product.product_name"));
			pr.setPrice(rs.getString("price"));
			pr.setWorranty(rs.getInt("warranty"));
			pr.setPercentPromo(rs.getInt("percent_promo"));
//			pr.setDateAdded(LocalDate.parse("date_added"));
			pr.setProductNumber("product_number");
			pr.setImageUrl("image_url");
			products.add(pr);
		}
		return products;
	}

}
