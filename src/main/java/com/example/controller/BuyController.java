package com.example.controller;

import static org.mockito.Matchers.doubleThat;
import static org.mockito.Matchers.endsWith;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.DAO.OrderDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.UserDAO;

@Controller
@RequestMapping("/buyController")
public class BuyController {
@Autowired
ProductDAO productDAO;
@Autowired
OrderDAO orderDAO;
@Autowired
UserDAO userDAO;



	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String addInTheBasket(@RequestParam(value = "value") String id, HttpSession session) {
		
		if(session.getAttribute("basket") == null){
			HashMap<Product, Integer> basket = new HashMap<>();
			session.setAttribute("basket",basket);
		}
		HashMap<Product, Integer> basket = (HashMap<Product, Integer>) session.getAttribute("basket");
		try {
			Product product = productDAO.searchProductById(id);
			if(!basket.containsKey(product)){
			   basket.put(product, 1);
			}else{
				Integer number = basket.get(product) + 1;
				basket.put(product, number);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "errorPage";
		}
		return "basket";
	}
	
	
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String goToBasket(){
		return "basket";
	}
	     
	@RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
	public String makeOrder(HttpSession session, Model model){
		if(session.getAttribute("user") == null){
			return "login";
		}
		model.addAttribute("date", LocalDate.now());
		HashMap<Product, Integer> product = (HashMap<Product, Integer>) session.getAttribute("basket");
		double price = 0.0;
		for(Iterator<Entry<Product, Integer>> it = product.entrySet().iterator(); it.hasNext();){
			Entry<Product, Integer> entry = it.next();
			price+= (entry.getKey().getPrice().doubleValue() * entry.getValue()) - (((entry.getKey().getPrice().doubleValue() * entry.getValue())*entry.getKey().getPercentPromo())/100);
		}
		model.addAttribute("price", price);
		return "makeOrder";
	}
	@RequestMapping(value = "/addOrder", method = RequestMethod.POST)
	public String addOrder(@RequestParam("firstAndLastName") String name,
			@RequestParam("telNumber") String telNumber,
			@RequestParam("town") String town,
			@RequestParam("postCode") String postCode,
			@RequestParam("street") String street,
			@RequestParam("number") String number,
			@RequestParam("block") String block,
			@RequestParam("entrace") String entrace,
			@RequestParam("floor") String floor,
			@RequestParam("aparment") String aparment,
			@RequestParam("notes") String notes,
			@RequestParam("price") String price,
			@RequestParam("payment") String payment,
			HttpSession session
			){
		Order order = new Order();
		order.setAddress(""+street+","+number+", "+(block == null? "":block)+", "+(entrace == null ? "":entrace)+", "+(floor == null? "":floor)+", "+(aparment == null ?"":aparment)+"");
		order.setUserNames(name);
		order.setUserPhoneNumber(telNumber);
		order.setZip(postCode);
		order.setNotes(notes);
		order.setPrice(price);
		
		HashMap<Product, Integer> basket = (HashMap<Product, Integer>) session.getAttribute("basket");
		order.setProducts(basket);
		order.setTime(LocalDate.now());
		order.setConfirmed(false);
		order.setPayment(payment);
		order.setPaid(false);
		try {
			User user = (User) session.getAttribute("user");
			orderDAO.insertNewOrder(user, order);
			
		} catch (SQLException e) {
			System.out.println("SQL Exception in BuyController");
			e.printStackTrace();
			return "errorPage";
		}
		
		((HashMap<Product, Integer>)session.getAttribute("basket")).clear();
		return "confurt";
	}
	
	

}
