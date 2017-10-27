package com.example.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@org.springframework.stereotype.Controller
public class WellcomeController {
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String intdePAge(){
		return "index";
	}
	

}
