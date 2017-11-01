package com.example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;
import com.example.model.DAO.AdminDAO;
import com.example.model.DAO.OrderDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.StoreDAO;
import com.example.model.exceptions.IlligalAdminActionException;
import com.example.model.exceptions.IlligalUserActionException;
import com.example.model.exceptions.NotAnAdminException;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	AdminDAO adminDAO;
	@Autowired
	OrderDAO orderDAO;

	@RequestMapping(value = "/contacts" , method = RequestMethod.GET)
	public String getContacts(){
		return "contacts";
	}
	
	@RequestMapping(value = "/confirmed" , method = RequestMethod.POST)
	public String confirmedOrder(@RequestParam("value") long orderId, HttpSession session){
		try {
			User user = (User) session.getAttribute("user");
		    try {
				adminDAO.setOrderAsConfirmed(user, orderId, true);
			} catch (NotAnAdminException e) {
				e.printStackTrace();
				return "index";
			} catch (IlligalAdminActionException e) {
				e.printStackTrace();
				return "index";
			}
			  
		} catch (SQLException e) {
			System.out.println("SQL Exception in /admin/confirmed");
			e.printStackTrace();
			return "errorPage";
		} catch (IlligalUserActionException e) {
			System.out.println("SQL Exception in /admin/confirmed");
			e.printStackTrace();
			return "admin_panel";
		}
		return "redirect:/info/infoAdminOrders";
	}
	@RequestMapping(value = "/setPaid" , method = RequestMethod.POST)
	public String paidOrder(@RequestParam("value") long orderId, HttpSession session){
		try {
			User user = (User) session.getAttribute("user");
			try {
				adminDAO.setOrderAsPaid(user, orderId);
			} catch (NotAnAdminException e) {
				System.out.println("Not Admin Exception in /admin/setPaid");
				e.printStackTrace();
				return "index";
			}
		} catch (IlligalAdminActionException e) {
			System.out.println("Exception in admin/setPaid");
			e.printStackTrace();
			return "admin_panel";
		} catch (SQLException e) {
			System.out.println("Exception in admin/setPaid");
			e.printStackTrace();
			return "errorPage";
		}
		return "redirect:/info/infoAdminOrders";
	}
	@RequestMapping(value = "/removeOrder" , method = RequestMethod.POST)
	public String deleteOrder(@RequestParam("value") long orderId, HttpSession session){
		try {
			User user = (User) session.getAttribute("user");
		    try {
				adminDAO.setOrderAsConfirmed(user, orderId, false);
			} catch (NotAnAdminException e) {
				e.printStackTrace();
				return "index";
			} catch (IlligalAdminActionException e) {
				e.printStackTrace();
				return "index";
			}
			  
		} catch (SQLException e) {
			System.out.println("SQL Exception in /admin/confirmed");
			e.printStackTrace();
			return "errorPage";
		} catch (IlligalUserActionException e) {
			System.out.println("SQL Exception in /admin/confirmed");
			e.printStackTrace();
			return "admin_panel";
		}
		return "redirect:/info/infoAdminOrders";
	}
	
	
}
