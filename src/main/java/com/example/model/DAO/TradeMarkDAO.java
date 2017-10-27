package com.example.model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Store;
import com.example.model.DBM.DBManager;
import com.example.model.Store.Address;

@Component
public class TradeMarkDAO {

	@Autowired
	DBManager DBManager;
	
	private Connection connection;
	
	public TreeSet<String> getAllTradeMarks() throws SQLException{
		TreeSet<String> tradeMarks = new TreeSet<>();
		this.connection = DBManager.getConnections();
		PreparedStatement ps = this.connection.prepareStatement("SELECT trade_mark_name FROM technomarket.trade_marks;");
		ps.executeUpdate();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			String tradeMark = rs.getString("trade_mark_name");
			tradeMarks.add(tradeMark);
		}
		ps.close();
		rs.close();
		return tradeMarks;
	}	
}
