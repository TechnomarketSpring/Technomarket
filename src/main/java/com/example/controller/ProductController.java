package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TreeSet;

import javax.servlet.http.HttpSession;

import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebInitializer;
import com.example.model.Category;
import com.example.model.Credit;
import com.example.model.Product;
import com.example.model.User;
import com.example.model.DAO.AdminDAO;
import com.example.model.DAO.CategoryDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.TradeMarkDAO;
import com.example.model.DAO.UserDAO;
import com.example.model.DBM.DBManager;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidProductDataException;
import com.example.model.exceptions.NotAnAdminException;

@Component
public class ProductController {

	
	@Autowired
	UserDAO userDAO;
	@Autowired
	TradeMarkDAO tradeMarkDAO;
	@Autowired
	CategoryDAO categoryDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	AdminDAO adminDAO;
	
	@RequestMapping(value = "/insert_product", method = RequestMethod.GET)
	public String prepareRegistarion(Model model) {
		TreeSet<String> tradeMarks = null;
		TreeSet<String> innerCategories = null;
		try {
			tradeMarks = tradeMarkDAO.getAllTradeMarks();
			innerCategories = categoryDAO.getAllInnerCategories();
		} catch (SQLException e) {
			// TODO error page
			e.printStackTrace();
		}
		model.addAttribute(tradeMarks);
		model.addAllAttributes(innerCategories);
		return "admin_insert_product";
	}
	
	@RequestMapping(value = "/insert_product", method = RequestMethod.POST)
	public String insertNewProduct(Model model, HttpSession session, @RequestParam("productName") String productName,
			@RequestParam("tradeMark") String tradeMark,
			@RequestParam("category") String categoryName,
			@RequestParam("price") BigDecimal price,
			@RequestParam("warranty") int warranty,
			@RequestParam("promoPercent") int promoPercent,
			@RequestParam("image") MultipartFile image) {

		String imageName = null;
		try {
		MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
		MimeType type = allTypes.forName(image.getContentType());
		String extention = type.getExtension(); // .extention (dot included)
		String productNumber = productDAO.generateProductNumber();
		imageName = "product" + "-" + productNumber + extention;
		File imageFile = new File(WebInitializer.LOCATION + imageName);
		image.transferTo(imageFile);
		Category category = new Category(categoryName);
		Product newProduct = new Product(productName, tradeMark, price, null, category, warranty,
				promoPercent, LocalDate.now(), imageName);
		adminDAO.insertNewProduct(newProduct, (User) session.getAttribute("user"));
		model.addAttribute("added", "New product added");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MimeTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidCategoryDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProductDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotAnAdminException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "admin_insert_product";
	}
	
	
	
	
//			@RequestMapping(value="daiSnimka/{id}", method = RequestMethod.GET)
//			public void daiSnimka(@RequestParam("id") Integer productId){
//				Product p = Dao.getProductById(productId);
//				String url = p.getImageUrl();//krasi.jpg / stefi.gif / homer-lala.png
//				File f = new File(WebInitializer.LOCATION + File.separator + url);
//			}

//			@RequestMapping(value="daiSnimka", method = RequestMethod.GET)
//			public void daiSnimka(HttpServletResponse resp){
//				File f = new File(WebInitializer.LOCATION + File.separator + "homer-end-is-near.jpg");
//				try {
//					Files.copy(f.toPath(), resp.getOutputStream());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}

		
		
		
		
		
		
		
		
//		
//		try {
//			boolean exist = userDAO.checkIfUserWithSameEmailExist(email);
//			if (exist) {
//				// Send email to email address:
//				return "email_sent";
//			} else {
//				model.addAttribute("emailError", "Email not valid");
//				return "forgotten";
//			}
//		} catch (SQLException e) {
//			// TODO send to errorPage
//			System.out.println("Ops SQL Exceptions");
//		}
//		return "error";
//	}
	
}