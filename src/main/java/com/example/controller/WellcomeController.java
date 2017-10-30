package com.example.controller;


import java.util.HashMap;
import java.util.LinkedHashMap;

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
		HashMap<Product, Integer> basket = new LinkedHashMap<>();
		session.setAttribute("basket", basket);

		return "index";
	
	}
	

}
