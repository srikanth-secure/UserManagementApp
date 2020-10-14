package com.srikanth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.srikanth.pojo.UnlockAccount;

@Controller
public class UnlockAccountController {

	/**
	 * This method is used load unlock-account form
	 * @param email
	 * @param model
	 * @return String
	 */
	@GetMapping("/loadUnlockAccForm")
	public String loadUnlockAccForm(@RequestParam("email") String email, Model model) {
		// TODO: We should write logic here
		return "unlockAcc";
	}

	/**
	 * This method is used to handle unlock-account form submission
	 * @param unlockAcc
	 * @param model
	 * @return String
	 */
	@PostMapping("/unlockAcc")
	public String handleSubmitBtn(UnlockAccount unlockAcc, Model model) {
		// TODO: We should write logic here
		return "unlockAcc";
	}

}