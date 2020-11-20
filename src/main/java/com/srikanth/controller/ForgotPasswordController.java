package com.srikanth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.srikanth.constants.AppConstants;
import com.srikanth.props.AppProperties;
import com.srikanth.service.UserService;

@Controller
public class ForgotPasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private AppProperties appProperties;

	/**
	 * This method is used to load forgot password form
	 * 
	 * @return String
	 */
	@GetMapping("/forgotPwd")
	public String loadForm() {
		return AppConstants.FORGOT_PWD_VIEW_NAME;
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
		String email = req.getParameter(AppConstants.EMAIL);
		String status = userService.recoverPassword(email);
		// System.out.pri ntln(email);
		if (status.equals(AppConstants.SUCCESS)) {
			String succMsg = appProperties.getMessages().get(AppConstants.RECOVER_PWD_SUCC);
			model.addAttribute(AppConstants.SUCC_MSG, succMsg);
		} else {
			String failMsg = appProperties.getMessages().get(AppConstants.INVALID_EMAIL);
			model.addAttribute(AppConstants.FAIL_MSG, failMsg);
		}

		return AppConstants.FORGOT_PWD_VIEW_NAME;
	}
}