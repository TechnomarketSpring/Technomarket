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
import com.example.model.DAO.StoreDAO;
import com.example.model.DAO.UserDAO;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidCharacteristicsDataException;

@Controller
@RequestMapping("/buyController")
public class BuyController {
	@Autowired
	private ProductDAO productDAO;
	@Autowired
	private OrderDAO orderDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private StoreDAO storeDAO;

	@RequestMapping(value = "/buy", method = RequestMethod.POST)
	public String addInTheBasket(@RequestParam(value = "value") String productId, HttpSession session) {

		boolean isProductInStock = false;
		try {
			isProductInStock = storeDAO.isProductInStock(productId);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return "errorPage";
		}
		if (!isProductInStock) {
			return "redirect:/info/infoForProduct?value=" + productId;
		}

		if (session.getAttribute("basket") == null) {
			HashMap<Product, Integer> basket = new HashMap<>();
			session.setAttribute("basket", basket);
		}

		HashMap<Product, Integer> basket = (HashMap<Product, Integer>) session.getAttribute("basket");

		try {
			Product product = productDAO.searchProductById(productId);
			if (!basket.containsKey(product)) {
				basket.put(product, 1);
			} else {
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
	public String goToBasket() {
		return "basket";
	}

	@RequestMapping(value = "/removeProduct", method = RequestMethod.POST)
	public String removeProduct(@RequestParam(value = "value") long id, HttpSession session) {
		HashMap<Product, Integer> product = (HashMap<Product, Integer>) session.getAttribute("basket");
		Product pro = null;
		try {
			pro = productDAO.getProduct(id);

		} catch (SQLException e) {
			System.out.println("SQL exception in buyController/removeProduct");
			e.printStackTrace();
			return "errorPage";
		} catch (InvalidCategoryDataException e) {
			System.out.println(
					"InvalidCharacteristicsDataException or InvalidCategoryDataException in buyController/removeProduct");
			e.printStackTrace();
			return "errorPage";
		}
		for (Iterator<Entry<Product, Integer>> it = product.entrySet().iterator(); it.hasNext();) {
			Entry<Product, Integer> entry = it.next();
			if (entry.getKey().getProductId() == id) {
				it.remove();
			}
		}
		return "basket";
	}

	@RequestMapping(value = "/makeOrder", method = RequestMethod.GET)
	public String makeOrder(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			session.setAttribute("inBasket", true);
			model.addAttribute("logInPls", true);
			return "login";
		}
		model.addAttribute("date", LocalDate.now());
		HashMap<Product, Integer> product = (HashMap<Product, Integer>) session.getAttribute("basket");
		double price = 0.0;
		for (Iterator<Entry<Product, Integer>> it = product.entrySet().iterator(); it.hasNext();) {
			Entry<Product, Integer> entry = it.next();
			price += (entry.getKey().getPrice().doubleValue() * entry.getValue())
					- (((entry.getKey().getPrice().doubleValue() * entry.getValue()) * entry.getKey().getPercentPromo())
							/ 100);
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
			HttpSession session) {
		Order order = new Order();
		StringBuffer sb = new StringBuffer();
		sb.append(street.trim());
		sb.append(", ");
		sb.append(number.trim());
		sb.append(", ");
		sb.append((block == null ? "" : block.trim()));
		sb.append(", ");
		sb.append((entrace == null ? "" : entrace.trim()));
		sb.append(", ");
		sb.append((floor == null ? "" : floor.trim()));
		sb.append(", ");
		sb.append((aparment == null ? "" : aparment.trim()));
		sb.append(".");
		order.setAddress(sb.toString());
		order.setUserNames(name.trim());
		order.setUserPhoneNumber(telNumber.trim());
		order.setZip(postCode.trim());
		order.setNotes(notes.trim());
		order.setPrice(price.trim());

		HashMap<Product, Integer> basket = (HashMap<Product, Integer>) session.getAttribute("basket");
		order.setProducts(basket);
		order.setTime(LocalDate.now());
		order.setConfirmed(false);
		order.setPayment(payment.trim());
		order.setPaid(false);
		try {
			User user = (User) session.getAttribute("user");
			orderDAO.insertNewOrder(user, order);

		} catch (SQLException e) {
			System.out.println("SQL Exception in BuyController");
			e.printStackTrace();
			return "errorPage";
		}

		((HashMap<Product, Integer>) session.getAttribute("basket")).clear();
		return "confurt";
	}

}
