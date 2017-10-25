package com.example.controller;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.model.DAO.UserDAO;
import com.example.model.exceptions.EmailAlreadyInUseException;
import com.example.model.exceptions.InvalidUserDataException;


@Controller
public class RegisterController {

	@Autowired
	UserDAO userDAO;
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public String products(HttpServletResponse responce, HttpServletRequest request) {
		if(!request.getParameter("password").equals(request.getParameter("password1"))){
			request.setAttribute("invalidPassword", "Passwords are not the same ");
			return "register";
		}
		if(request.getParameter("submit") == null){
			request.setAttribute("unAccepted", "UnAccepted condition");
			return "register";
		}
		User user = new User(request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("email"),
				request.getParameter("password"),
				request.getParameter("gender"),
				LocalDate.parse(request.getParameter("bday")),
				request.getParameter("abonat") == null? false: true, false, false);
		try {
			userDAO.insertUser(user);
			request.getSession().setAttribute("user", user);
			request.getRequestDispatcher("index.jsp").forward(request, responce);
		} catch (SQLException e) {
			request.getRequestDispatcher("errorPage.jsp").forward(request, responce);
			e.printStackTrace();
			System.out.println("SQL Exception");
		} catch (EmailAlreadyInUseException e) {
			request.setAttribute("invalidEmailAddres", "Email addres is exist");
			request.getRequestDispatcher("view/register.jsp").forward(request, responce);
			
		}
	} catch (InvalidUserDataException e) {
		request.setAttribute("invalidDateЕrror", "Invalid date for user");
		request.getRequestDispatcher("view/register.jsp").forward(request, responce);
	}	
		
		
		
		
		
		
		Product p = new Product();
		model.addAttribute(p);
		System.out.println(request.getAttribute("test"));
		
		return "products";
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			if(!request.getParameter("password").equals(request.getParameter("password1"))){
				request.setAttribute("invalidPassword", "Passwords are not the same ");
				request.getRequestDispatcher("view/register.jsp").forward(request, response);
				return;
			}
			if(request.getParameter("submit") == null){
				request.setAttribute("unAccepted", "UnAccepted condition");
				request.getRequestDispatcher("view/register.jsp").forward(request, response);
				return;
			}
			User user = new User(request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("email"),
					request.getParameter("password"),
					request.getParameter("gender"),
					LocalDate.parse(request.getParameter("bday")),
					request.getParameter("abonat") == null? false: true, false, false);
			try {
				UserDAO.getInstance().insertUser(user);
				request.getSession().setAttribute("user", user);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} catch (SQLException e) {
				request.getRequestDispatcher("errorPage.jsp").forward(request, response);
				e.printStackTrace();
				System.out.println("SQL Exception");
			} catch (EmailAlreadyInUseException e) {
				request.setAttribute("invalidEmailAddres", "Email addres is exist");
				request.getRequestDispatcher("view/register.jsp").forward(request, response);
				
			}
		} catch (InvalidUserDataException e) {
			request.setAttribute("invalidDateЕrror", "Invalid date for user");
			request.getRequestDispatcher("view/register.jsp").forward(request, response);
			
		}
		
		
	}
}
