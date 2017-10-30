package com.example.controller;


import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;


@org.springframework.stereotype.Controller
public class WellcomeController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String intdePAge(HttpSession session){
		if(session.getAttribute("user") == null){
			session.setAttribute("user", new User());
		}
		return "index";
	}
	

}
