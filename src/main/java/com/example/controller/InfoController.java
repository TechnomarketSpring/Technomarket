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
import com.example.model.DAO.UserDAO;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidCharacteristicsDataException;
import com.example.model.exceptions.InvalidStoreDataException;

@Controller
@RequestMapping(value = "/info")
public class InfoController {

	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private StoreDAO storeDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserDAO userDAO;

	// product info gets:

	@RequestMapping(value = "/infoForProduct", method = RequestMethod.GET)
	public String infoForProduct(@RequestParam(value = "value") String productId, HttpSession session, Model model) {
		try {
			// gets the product itself:
			Product product = productDAO.searchProductById(productId);
			model.addAttribute("product", product);

			// finds if product is in stock in any store:
			boolean isProductInStock = storeDAO.isProductInStock(productId);
			model.addAttribute("isProductInStock", isProductInStock);
			
			//gets user, check if he/she is logged and if yes checks if products is in his/her favourites:
			User user = (User) session.getAttribute("user");
			if(user != null){
				boolean isProductInFavourite = userDAO.isPrductInFavourite(String.valueOf(user.getUserId()), productId);
				model.addAttribute("isProductInFavourite", isProductInFavourite);
			}

			//if products is in stock anywhere or user is admin method is searching for stock status per store:
			if (isProductInStock || (user != null && user.getIsAdmin())) {
				// gets the product stock status per store:
				LinkedHashMap<Store, String> statusPerStore = new LinkedHashMap<>();
				HashSet<Store> cities = storeDAO.getAllStores();
				for (Iterator<Store> iterator = cities.iterator(); iterator.hasNext();) {
					Store store = iterator.next();
					StoreDAO.Status status = storeDAO.checkQuantity(store, product);
					String statusImgLink = null;
					if (status == StoreDAO.Status.NO_STATUS) {
						statusImgLink = null;
					} else if (status == StoreDAO.Status.RED_STATUS) {
						statusImgLink = "/img/store_statuses/red.png";
					} else if (status == StoreDAO.Status.YELLOW_STATUS) {
						statusImgLink = "/img/store_statuses/yellow.png";
					} else if (status == StoreDAO.Status.GREEN_STATUS) {
						statusImgLink = "/img/store_statuses/green.png";
					}
					statusPerStore.put(store, statusImgLink);
				}
				model.addAttribute("statusPerStore", statusPerStore);
			}
			
		} catch (SQLException | InvalidStoreDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "productInfo";
	}

	// site conditions info gets:

	@RequestMapping(value = "/infoContacts", method = RequestMethod.GET)
	public String infoContacts() {
		return "contacts";
	}

	@RequestMapping(value = "/infoForShoppingCon", method = RequestMethod.GET)
	public String infoShopping() {
		return "shoppingConditions";
	}

	@RequestMapping(value = "/infoForDelivery", method = RequestMethod.GET)
	public String infoDelivery() {
		return "deliveryInfo";
	}

	@RequestMapping(value = "/infoForOnlinePay", method = RequestMethod.GET)
	public String infoOnlinePay() {
		return "onlinePayInfo";
	}

	@RequestMapping(value = "/infoForTBICredit", method = RequestMethod.GET)
	public String infoTBICredit() {
		return "tbiCreditInfo";
	}

	@RequestMapping(value = "/infoForUniCredit", method = RequestMethod.GET)
	public String infoUniCredit() {
		return "uniCreditInfo";
	}

	// user info gets:

	@RequestMapping(value = "/infoUserProfile", method = RequestMethod.GET)
	public String infoUserProfile() {
		return "user_profile";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String log(HttpSession session) {
		session.invalidate();
		return "/index";
	}

	@RequestMapping(value = "/infoUserOrders", method = RequestMethod.GET)
	public String infoUserOrders(HttpSession sesion, Model model) {

		User user = (User) sesion.getAttribute("user");
		try {
			LinkedHashSet<Order> orders = orderDAO.getOrdersForUser(user.getUserId());
			System.out.println(orders);
			model.addAttribute("orders", orders);
		} catch (SQLException e) {
			System.out.println("SQL Exception into /info/infoUserOrders ");
			e.printStackTrace();
		} catch (InvalidCategoryDataException e) {
			System.out.println("Ivalid data into /info/infoUserOrders");
			e.printStackTrace();
		}
		return "user_orders";
	}

	@RequestMapping(value = "/infoFoCurrentOrder", method = RequestMethod.POST)
	public String infoForCurrentOrder(@RequestParam(value = "value") String orderId, Model model) {
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

	// admin info gets:

	@RequestMapping(value = "/infoAdminPanel", method = RequestMethod.GET)
	public String infoAdminPanel() {
		return "admin_panel";
	}

	@RequestMapping(value = "/infoAdminOrders", method = RequestMethod.GET)

	public String infoAdminOrders(Model model, HttpSession session){
		try {
			HashSet<Order> orders = orderDAO.getOrderWhereIsNotConfirmedAndIsNotPaid();
			model.addAttribute("orders", orders);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception in info/infoAdminPanel");
			return "errorPage";
		}

		return "admin_orders";
	}

	@RequestMapping(value = "/infoAdminBan", method = RequestMethod.GET)
	public String infoAdminBan() {
		return "admin_ban";
	}

	@RequestMapping(value = "/infoAdminCreateAdmin", method = RequestMethod.GET)
	public String infoAdminCreateAdmin() {
		return "admin_create_admin";
	}

	@RequestMapping(value = "/infoAdminInsertProduct", method = RequestMethod.GET)
	public String infoAdminInsertProduct() {
		return "admin_insert_product";
	}

}
