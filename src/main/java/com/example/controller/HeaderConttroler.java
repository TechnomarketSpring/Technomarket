package com.example.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Product;
import com.example.model.Store;
import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.StoreDAO;
import com.example.model.exceptions.InvalidStoreDataException;


@Controller
@RequestMapping("/header")
public class HeaderConttroler {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	StoreDAO storeDAO;
	
	@RequestMapping(value = "/contacts" , method = RequestMethod.GET)
	public String getContacts(){
		return "contacts";
	}
	@RequestMapping(value = "/home",  method = RequestMethod.GET)
	public String getHome(Model model){
		try {
			Set<Product> product = productDAO.searchProductWithCategoryHome();
			model.addAttribute("filtredProducts", product);
		} catch (SQLException e) {
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "filtred_products";
	}
	
	
	@RequestMapping(value = "/apple", method = RequestMethod.GET)
	public String getAppleProduct(Model model){
		try {
			Set<Product> product = productDAO.getAppleProduct();
			model.addAttribute("filtredProducts", product);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "filtred_products";
	}
	
	@RequestMapping(value = "/promo", method = RequestMethod.GET)
	public String getProductWithPromoPrice(Model model){
		try {
			Set<Product> product = productDAO.getPromoProduct();
			model.addAttribute("filtredProducts", product);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "filtred_products";
		
	}
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public String services(){
		return "services";
	}
	@RequestMapping(value = "/stores", method = RequestMethod.GET)
	public String store(Model model){
		try {
		  List<String> allStores = storeDAO.getAllCities();
		  model.addAttribute("allNameOfStores", allStores);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "stores";
	}
	
	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public String cities(@RequestParam(value = "value") String cityName, Model model){
		try {
			Set<Store> stores = storeDAO.getStoresPerCity(cityName);
			model.addAttribute("stores", stores);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		} catch (InvalidStoreDataException e) {
			return "errorPage";
		}
		
		return "pageForCities";
	}
	
	
	@RequestMapping(value = "/search" , method = RequestMethod.GET)
	public String searchProduct(@RequestParam("searched_text") String searched_text, Model model){
		try {
			Set<Product> products = productDAO.searchProductByName(searched_text);
			
			model.addAttribute("filtredProducts", products);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "filtred_products";
	}
	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String getLoginPage(){
		return "login";
	}
	@RequestMapping(value = "/register" , method = RequestMethod.GET)
	public String getRegisterPage(){
		return "register";
	}
    
}