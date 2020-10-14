package com.srikanth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	/**
	 * This method is used to load login page
	 * 
	 * @return String
	 */
	@GetMapping("/")
	public String index() {
		return "index";
	}

	/**
	 * This method is used handle sign-in btn request
	 * 
	 * @param req
	 * @param model
	 * @return String
	 */
	@PostMapping("/signin")
	public String handleSignInBtn(HttpServletRequest req, Model model) {
		String viewName = "";
		// TODO: We should write logic here
		return viewName;
	}

}