package com.srikanth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.srikanth.constants.AppConstants;
import com.srikanth.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * This method is used to load login page
	 * 
	 * @return String
	 */
	@GetMapping(value = { "/", "index" })
	public String index() {
		return AppConstants.INDEX_VIEW_NAME;
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
		String viewName = AppConstants.EMPTY_STR;
		String email = req.getParameter(AppConstants.EMAIL);
		String password = req.getParameter(AppConstants.PAZZWORD);
		String loginCheck = userService.loginCheck(email, password);
		if (loginCheck.equals(AppConstants.VALID)) {
			viewName = AppConstants.DASHBOARD_VIEW_NAME;
		} else {
			viewName = AppConstants.INDEX_VIEW_NAME;
			model.addAttribute(AppConstants.FAIL_MSG, loginCheck);
		}
		return viewName;
	}
}