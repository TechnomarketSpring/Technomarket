package com.example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.DAO.ProductDAO;
@Controller
@RequestMapping(value = "/info")
public class InfoController {
	
	@Autowired
	ProductDAO productDAO;
	
	//product info gets:
	
	@RequestMapping(value = "/infoForProduct", method = RequestMethod.GET)
	public String infoForProduct(@RequestParam(value = "value") String id, Model model){
		try {
			Product product = productDAO.searchProductById(id);
			model.addAttribute("product", product);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

       return "productInfo";		
	}
	
	//site conditions info gets:

	@RequestMapping(value = "/infoForShoppingCon", method = RequestMethod.GET)
	public String infoShopping(){
       return "shoppingConditions";		
	}
	@RequestMapping(value = "/infoForDelivery", method = RequestMethod.GET)
	public String infoDelivery(){
		return "deliveryInfo";
	}
	@RequestMapping(value = "/infoForOnlinePay", method = RequestMethod.GET)
	public String infoOnlinePay(){
		return "onlinePayInfo";
	}
	@RequestMapping(value = "/infoForTBICredit", method = RequestMethod.GET)
	public String infoTBICredit(){
		return "tbiCreditInfo";
	}
	@RequestMapping(value = "/infoForUniCredit", method = RequestMethod.GET)
	public String infoUniCredit(){
		return "uniCreditInfo";
	}
	
	
	//user info gets:
	
	@RequestMapping(value = "/infoUserProfile", method = RequestMethod.GET)
	public String infoUserProfile(){
		return "user_profile";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String log(HttpSession session){
		session.invalidate();
		return "/index";
	}
	@RequestMapping(value = "/infoUserOrders", method = RequestMethod.GET)
	public String infoUserOrders(HttpSession sesion){
		
		return "user_orders";
	}
	@RequestMapping(value = "/infoUserFavourites", method = RequestMethod.GET)
	public String infoUserFavourites(){
		return "user_favourites";
	}
	@RequestMapping(value = "/infoFoCuurentOrder", method = RequestMethod.GET )
	public String infoForCurrentOrder(@RequestParam("value") Order order,Model model){
		model.addAttribute("orders", order);
		return "pageForCurrentOrders";
	}
	
	//admin info gets:
	
	@RequestMapping(value = "/infoAdminPanel", method = RequestMethod.GET)
	public String infoAdminPanel(){
		return "admin_panel";
	}
	@RequestMapping(value = "/infoAdminOrders", method = RequestMethod.GET)
	public String infoAdminOrders(){
		return "admin_orders";
	}
	@RequestMapping(value = "/infoAdminBan", method = RequestMethod.GET)
	public String infoAdminBan(){
		return "admin_ban";
	}
	@RequestMapping(value = "/infoAdminCreateAdmin", method = RequestMethod.GET)
	public String infoAdminCreateAdmin(){
		return "admin_create_admin";
	}
	@RequestMapping(value = "/infoAdminInsertProduct", method = RequestMethod.GET)
	public String infoAdminInsertProduct(){
		return "admin_insert_product";
	}
	
	
	
}
