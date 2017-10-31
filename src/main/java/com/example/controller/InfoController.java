package com.example.controller;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
import com.example.model.Store;
import com.example.model.User;
import com.example.model.DAO.OrderDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.StoreDAO;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidCharacteristicsDataException;
import com.example.model.exceptions.InvalidStoreDataException;
@Controller
@RequestMapping(value = "/info")
public class InfoController {
	
	@Autowired
	ProductDAO productDAO;
	@Autowired
	StoreDAO storeDAO;
	@Autowired
	OrderDAO orderDAO;
	
	//product info gets:
	
	@RequestMapping(value = "/infoForProduct", method = RequestMethod.GET)
	public String infoForProduct(@RequestParam(value = "value") String id, Model model){
		try {
			System.out.println(id);
			Product product = productDAO.searchProductById(id);
			model.addAttribute("product", product);
			
			LinkedHashMap<Store, String> statusPerStore = new LinkedHashMap<>();
			HashSet<Store> cities = storeDAO.getAllStores();
			for (Iterator<Store> iterator = cities.iterator(); iterator.hasNext();) {
				Store store = iterator.next();
				StoreDAO.Status status = storeDAO.checkQuantity(store, product);
				String statusImgLink = null;
				if(status == StoreDAO.Status.NO_STATUS){
					statusImgLink = null;
				}else if(status == StoreDAO.Status.RED_STATUS){
					statusImgLink = "/img/store_statuses/red.png";
				}else if(status == StoreDAO.Status.YELLOW_STATUS){
					statusImgLink = "/img/store_statuses/yellow.png";
				}else if(status == StoreDAO.Status.GREEN_STATUS){
					statusImgLink = "/img/store_statuses/green.png";
				}
				statusPerStore.put(store, statusImgLink);
				System.out.println(statusImgLink);
			}
			model.addAttribute("statusPerStore", statusPerStore);
		} catch (SQLException | InvalidStoreDataException e) {
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
	public String infoUserOrders(HttpSession sesion, Model model){
		
		User user = (User)sesion.getAttribute("user");
		try {
			LinkedHashSet<Order> orders = orderDAO.getOrdersForUser(user.getUserId());
			System.out.println(orders);
			model.addAttribute("orders", orders);
		} catch (SQLException e) {
			System.out.println("SQL Exception into /info/infoUserOrders ");
			e.printStackTrace();
		} catch (InvalidCharacteristicsDataException | InvalidCategoryDataException e) {
			System.out.println("Ivalid data into /info/infoUserOrders");
			e.printStackTrace();
		} 
		return "user_orders";
	}
	@RequestMapping(value = "/infoUserFavourites", method = RequestMethod.GET)
	public String infoUserFavourites(){
		return "user_favourites";
	}
	@RequestMapping(value = "/infoFoCurrentOrder", method = RequestMethod.POST )
	public String infoForCurrentOrder(@RequestParam("value") String orderId,Model model){
		try {
			Order order = orderDAO.searchOrderById(orderId);
			HashSet<Product> product = orderDAO.getProductFromOrder(orderId);
			model.addAttribute("order", order);
			model.addAttribute("products", product);
		} catch (SQLException e) {
			System.out.println("SQL EXception is InfoControlle/inForCurrenrOrder");
			e.printStackTrace();
			return "errorPage";
		}
		
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
