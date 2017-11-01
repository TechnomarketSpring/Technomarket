package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.StoreDAO;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private ProductDAO adminDAO;

	@RequestMapping(value = "/contacts" , method = RequestMethod.GET)
	public String getContacts(){
		return "contacts";
	}
	
}
