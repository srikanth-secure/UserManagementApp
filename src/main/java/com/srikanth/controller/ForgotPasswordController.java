package com.srikanth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ForgotPasswordController {

	/**
	 * This method is used to load forgot password form
	 * 
	 * @return String
	 */
	@GetMapping("/forgotPwd")
	public String loadForm() {
		return "forgotPwd";
	}

	/**
	 * This is method is used to handle forgot password screen submit btn
	 * 
	 * @param req
	 * @param model
	 * @return String
	 */
	@PostMapping("/forgotPwd")
	public String handleForgotPwdSubmtBtn(HttpServletRequest req, Model model) {
		// TODO: We should write logic here
		return "forgotPwd";
	}
}