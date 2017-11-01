package com.example.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.example.WebInitializer;
import com.example.model.Category;
import com.example.model.Credit;
import com.example.model.Product;
import com.example.model.Store;
import com.example.model.User;
import com.example.model.DAO.AdminDAO;
import com.example.model.DAO.CategoryDAO;
import com.example.model.DAO.ProductDAO;
import com.example.model.DAO.StoreDAO;
import com.example.model.DAO.TradeMarkDAO;
import com.example.model.DAO.UserDAO;
import com.example.model.DBM.DBManager;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidCharacteristicsDataException;
import com.example.model.exceptions.InvalidProductDataException;
import com.example.model.exceptions.InvalidStoreDataException;
import com.example.model.exceptions.NotAnAdminException;

@Component
@RequestMapping(value="/product")
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
	@Autowired
	StoreDAO storeDAO;
	
	@RequestMapping(value = "/insert_product", method = RequestMethod.GET)
	public String prepareRegistarion() {
		return "admin_insert_product";
	}
	
	@RequestMapping(value = "/insert_product", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public String insertNewProduct(Model model, HttpSession session, @RequestParam("productName") String productName,
			@RequestParam("tradeMark") String tradeMark,
			@RequestParam("categoryName") String categoryName,
			@RequestParam("price") BigDecimal price,
			@RequestParam("warranty") int warranty,
			@RequestParam("promoPercent") int promoPercent,
			@RequestParam("image") MultipartFile image) {
		String imageName = null;
		try {
			if(!tradeMarkDAO.tradeMarkExist(tradeMark)){
				tradeMarkDAO.insertTradeMark(tradeMark);
			}
			if(!categoryDAO.categoryExist(categoryName)){
				categoryDAO.insertCategory(categoryName);
			}
		MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
		MimeType type = allTypes.forName(image.getContentType());
		String extention = type.getExtension(); // .extention (dot included)
		String productNumber = productDAO.generateProductNumber();
		imageName = "product" + "-" + productNumber + extention;
		File imageFile = new File(imageName);
		image.transferTo(imageFile);
		Category category = new Category(categoryName);
		Product newProduct = new Product(productName, tradeMark, price, null, category, warranty,
				promoPercent, LocalDate.now(), imageName);
		adminDAO.insertNewProduct(newProduct, (User) session.getAttribute("user"));
		model.addAttribute("added", "New product added");
		model.addAttribute("productId", newProduct.getProductId());
		} catch (IllegalStateException | IOException | MimeTypeException | InvalidCategoryDataException | SQLException | NotAnAdminException | InvalidProductDataException e) {
			e.printStackTrace();
			return "admin_insert_product";
		} 
		return "admin_insert_product";
	}
	
	@RequestMapping(value="/product_pic", method = RequestMethod.GET)
	public void productPic(HttpServletResponse resp, @RequestParam(value = "value") String productId){
		Product p;
		try {
			p = productDAO.searchProductById(productId);
		String url = p.getImageUrl();
		File pic = new File(WebInitializer.LOCATION + url);
		Files.copy(pic.toPath(), resp.getOutputStream());
		resp.getOutputStream().flush();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.GET)
	public String removeProduct(HttpSession session,
			@RequestParam(value = "value") int productId){
		try {
			adminDAO.removeProduct(productId, (User) session.getAttribute("user"));
		} catch (NotAnAdminException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "product_removed";
	}
	
	@RequestMapping(value="/setPromo", method = RequestMethod.POST)
	public String setPromo(Model model, HttpSession session,
			@RequestParam(value = "productId") int productId,
			@RequestParam(value = "promoPercent") int promoPersent){
		try {
			adminDAO.setPromoPercent((User) session.getAttribute("user"), productId, promoPersent);
		} catch (NotAnAdminException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//TODO send email to all subscribers with favourites = this product
		
		model.addAttribute("promoSet", true);
		return "redirect:/info/infoForProduct?value=" + Integer.toString(productId);
	}

	@RequestMapping(value = "/productsByCategory" , method = RequestMethod.GET)
	public String searchProduct(@RequestParam("categoryName") String categoryName, Model model){
		try {
			Set<Product> products = productDAO.searchProductByCategoryName(categoryName);
			model.addAttribute("filtredProducts", products);
			model.addAttribute("categoryName", categoryName);
		} catch (SQLException | InvalidCategoryDataException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "filtred_products";
	}
	@RequestMapping(value = "/compareProduct" , method = RequestMethod.GET)
	public String compare(@RequestParam("compare") String compare,@RequestParam("categoryName")String categoryName, Model model){
		try {
			
			Set<Product> products = productDAO.searchProductByCategoryName(categoryName);
			TreeSet<Product> sortProduct = null;
			if(compare.equals("price")){
			  sortProduct = new TreeSet<Product>((o1 , o2) -> {
				  if(o1.getPrice().compareTo(o2.getPrice())== 0){
					  return 1;
				  }
				  return o1.getPrice().compareTo(o2.getPrice());
			  });
			}
			if(compare.equals("mark")){
			  sortProduct = new TreeSet<Product>((o1, o2) ->{
				  if(o1.getTradeMark().compareTo(o2.getTradeMark()) == 0){
					  return 1;
				  }
				  return o1.getTradeMark().compareTo(o2.getTradeMark());
			  });
			}
			if(compare.equals("type")){
				sortProduct = new TreeSet<Product>((o1, o2) ->{
					if(o1.getPercentPromo() == o2.getPercentPromo()){
						return 1;
					}else{
						if(o1.getPercentPromo() > o2.getPercentPromo()){
							return -1;
						}
					}
					return 1;		
				});
			}
			sortProduct.addAll(products);
			model.addAttribute("filtredProducts", sortProduct);
			model.addAttribute("categoryName", categoryName);
		} catch (SQLException | InvalidCategoryDataException e) {
			e.printStackTrace();
			System.out.println("Error for SQL");
			return "errorPage";
		}
		return "filtred_products";
	}
	
	
	
	
	
	
	
	



	
	
	
	
	
	
	
	
	
	
}