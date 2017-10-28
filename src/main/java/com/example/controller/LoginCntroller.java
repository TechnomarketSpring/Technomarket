package com.example.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.User;
import com.example.model.DAO.UserDAO;
import com.example.model.exceptions.InvalidCategoryDataException;
import com.example.model.exceptions.InvalidCharacteristicsDataException;

@Controller
public class LoginCntroller {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(HttpSession ses, Model model) {
		ses.removeAttribute("user");
		model.addAttribute("notLog", false);
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, Model model) {
		try {
			boolean exiting = userDAO.existingUser(username, password);
			if (exiting) {
				User user = null;
				try {
					user = userDAO.getUser(username);
				} catch (InvalidCharacteristicsDataException | InvalidCategoryDataException e) {
					System.out.println("Invalid date exceptions");
				}
				user.setAdmin(true);
				session.setAttribute("user", user);
				session.setAttribute("log", true);
				return "index";
			}
		} catch (SQLException e) {
			System.out.println("SQL Exceptions");
		}
		model.addAttribute("invalidUser", "Invalid username or password");
		return "login";

	}

	@RequestMapping(value = "/forgotten", method = RequestMethod.GET)
	public String forgotten() {
		return "forgotten";
	}

	@RequestMapping(value = "/forgotten", method = RequestMethod.POST)
	public String forgottenPassword(Model model, @RequestParam("email") String email) {
		try {
			boolean exist = userDAO.checkIfUserWithSameEmailExist(email);
			if (exist) {
				// Send email to email address:
				return "email_sent";
			} else {
				model.addAttribute("emailError", "Email not valid");
				return "forgotten";
			}
		} catch (SQLException e) {
			// TODO send to errorPage
			System.out.println("Ops SQL Exceptions");
		}
		return "error";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "index";
	}
}
