package com.example.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Store;
import com.example.model.DAO.StoreDAO;

@Controller
@RequestMapping ("/store")
public class StoreController {
	@Autowired
	StoreDAO storeDAO;
	
	@RequestMapping(value = "/showInTheMap", method = RequestMethod.GET)
	public String showInTheMap(@RequestParam("value") long orderId,
			Model model){
		try {
			Store store = storeDAO.seachStoreById(orderId);
			String [] gps = store.getGps().split(",");
			model.addAttribute("longitude", gps[0]);
			model.addAttribute("latitude", gps[1]);
			model.addAttribute("address", "ТЕХНОМАРКЕТ " + store.getAddress());
		} catch (SQLException e) {
			System.out.println("SQL Exception into /store/showInMap");
			e.printStackTrace();
			return "errorPage";
		}
		return "storeMaping";
	}
	
	
	
	
	
	

}
