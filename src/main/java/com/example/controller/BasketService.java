package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Product;
import com.example.model.User;
import com.example.model.DAO.AdminDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.exceptions.NotAnAdminException;

@RestController
@RequestMapping("/basket")
public class BasketService {
	
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value="/quantity", method=RequestMethod.POST)
	@ResponseBody
	public void changeQuantity(HttpServletResponse resp, HttpSession session,
			@RequestParam(value="product") String productId,
			@RequestParam(value="quantity") int quantity){
			resp.setStatus(200);
		try {
			HashMap<Product, Integer> basket = (HashMap<Product, Integer>) session.getAttribute("basket");
			Product product = productDAO.searchProductById(productId);
			System.out.println(productId + "==============================" + quantity);
			
			basket.put(product, quantity);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/decrement", method=RequestMethod.POST)
	@ResponseBody
	public void decrementAll(HttpServletResponse resp, HttpSession session){
			resp.setStatus(200);
		HashMap<Product, Integer> basket = (HashMap<Product, Integer>) session.getAttribute("basket");
		for (Iterator<Entry<Product, Integer>> iterator = basket.entrySet().iterator(); iterator.hasNext();) {
			Entry<Product,Integer> entry = iterator.next();
			Product p = entry.getKey();
			Integer i = entry.getValue() - 1;
			basket.put(p, i);
		}
	}
}
