package com.srikanth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srikanth.constants.AppConstants;
import com.srikanth.pojo.UnlockAccount;
import com.srikanth.service.UserService;

@Controller
public class UnlockAccountController {

	@Autowired
	private UserService userService;

	/**
	 * This method is used load unlock-account form
	 * 
	 * @param email
	 * @param model
	 * @return String
	 */
	@GetMapping("/unlockAcc")

	public String loadUnlockAccForm(@RequestParam(AppConstants.EMAIL) String email, Model model) {

		UnlockAccount account = new UnlockAccount();
		account.setEmail(email);
		model.addAttribute(AppConstants.USER_ACC, account);
		return AppConstants.UNLOCK_ACC_VIEW_NAME;
	}

	/**
	 * This method is used to handle unlock-account form submission
	 * 
	 * @param unlockAcc
	 * @param model
	 * @return String
	 */
	@PostMapping("/unlockAccount")
	public String handleSubmitBtn(@ModelAttribute(AppConstants.USER_ACC) UnlockAccount unlockAcc, Model model) {
		boolean isValid = userService.isTempPwdValid(unlockAcc.getEmail(), unlockAcc.getTempPwd());
		if (isValid) {
			userService.unlockAccount(unlockAcc.getEmail(), unlockAcc.getNewPwd());
			model.addAttribute(AppConstants.SUCC_MSG,
					"Your Account Unlocked.<a href=\"index\">Click here to login</a>");
		} else {
			model.addAttribute(AppConstants.FAIL_MSG, "Enter Correct Temp Password");
		}
		return AppConstants.UNLOCK_ACC_VIEW_NAME;
	}
}