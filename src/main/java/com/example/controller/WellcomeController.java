package com.example.controller;


import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.Product;
import com.example.model.User;


@org.springframework.stereotype.Controller
public class WellcomeController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String intdePAge(HttpSession session){
<<<<<<< HEAD
		HashMap<Product, Integer> basket = new HashMap<>();
		session.setAttribute("basket", basket);
=======
		
		
>>>>>>> 44b929142e15748c72b3afed968b27cc7d7da8fe
		return "index";
	
	}
	

}
