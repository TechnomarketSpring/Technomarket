package com.example.controller;

import static org.mockito.Matchers.endsWith;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Product;
import com.example.model.User;
import com.example.model.DAO.ProductDAO;

@Controller
@RequestMapping("/buyController")
public class BuyController {
@Autowired
ProductDAO productDAO;



	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String addInTheBasket(@RequestParam(value = "value") String id, HttpSession session, Model model) {
		System.out.println("7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777");
		if(session.getAttribute("user") == null){
			session.setAttribute("user", new User());
		}
		User user = (User) session.getAttribute("user");
		try {
			Product product = productDAO.searchProductById(id);
			if(!user.getBasket().containsKey(product)){
			   user.getBasket().put(product, 1);
			}else{
				Integer number = user.getBasket().get(product)+1;
				user.getBasket().put(product, number);
			}
			
			for(Iterator<Entry<Product, Integer>> products = user.getBasket().entrySet().iterator(); products.hasNext();){
				Entry<Product, Integer> entry = products.next();
				System.out.println(entry.getKey());
				System.out.println(entry.getValue());
			}
			model.addAttribute("userBasket", user.getBasket());
		} catch (SQLException e) {
			e.printStackTrace();
			return "errorPage";
		}
		return "basket";
	}
	     
	@RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
	public String makeOrder(HttpSession session, Model model){
		if(session.getAttribute("log") == null){
			return "login";
		}
		model.addAttribute("basket", ((User)session.getAttribute("user")).getBasket());
		return "makeOrder";
		
	}
	
	

}
