package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Product;
import com.example.model.DAO.CategoryDAO;

@org.springframework.stereotype.Controller
public class WellcomeController {
	@Autowired
	private CategoryDAO categoryDAO;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String intdePAge(HttpSession session) {
		HashMap<Product, Integer> basket = new HashMap<>();
		// setting basket in session:
		session.setAttribute("basket", basket);

		// setting all categories in application scope to be used when
		// visualization the main dropdown menu:
		TreeMap<String, TreeSet<String>> menuCategories = null;
		try {
			menuCategories = categoryDAO.getCategoriesWithParentKeys();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		session.getServletContext().setAttribute("menuCategories", menuCategories);
		
//		session.getServletContext().setAttribute("tv", menuCategories.get("ТЕЛЕВИЗОРИ И АУДИО"));
//		session.getServletContext().setAttribute("menuCategories", menuCategories);
//		session.getServletContext().setAttribute("menuCategories", menuCategories);
//		session.getServletContext().setAttribute("menuCategories", menuCategories);
//		session.getServletContext().setAttribute("menuCategories", menuCategories);
//		session.getServletContext().setAttribute("menuCategories", menuCategories);
//		session.getServletContext().setAttribute("menuCategories", menuCategories);
		return "index";
	}

}
