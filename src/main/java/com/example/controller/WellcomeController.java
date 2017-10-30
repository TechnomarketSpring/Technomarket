package com.example.controller;

import java.sql.SQLException;
import java.util.HashMap;
<<<<<<< HEAD
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
=======
import java.util.LinkedHashMap;
>>>>>>> 3b0e9a91f685ea7f6b409bd08decc948bf14b476

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
		// setting basket in session:
		if(session.getAttribute("basket") == null){
			   LinkedHashMap<Product, Integer> basket = new LinkedHashMap<Product, Integer>();
			   session.setAttribute("basket", basket);
			}

		// setting all categories in application scope to be used when
		// visualization the main dropdown menu:
		TreeMap<String, TreeSet<String>> menuCategories = null;
		try {
			menuCategories = categoryDAO.getCategoriesWithParentKeys();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		session.getServletContext().setAttribute("menuCategories", menuCategories);

		return "index";
	}

}
