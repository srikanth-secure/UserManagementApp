package com.srikanth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srikanth.constants.AppConstants;
import com.srikanth.pojo.UserAccounts;
import com.srikanth.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;

	@ModelAttribute
	public void loadFormData(Model model) {
		UserAccounts userAccObj = new UserAccounts();
		model.addAttribute(AppConstants.USER_ACC, userAccObj);
		Map<Integer, String> countriesMap = userService.loadCountries();
		model.addAttribute("countries", countriesMap);
	}

	@GetMapping("/register")
	public String loadRegForm(Model model) {
		return "registration";
	}

	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isEmailUnique(@RequestParam("email") String email) {
		// String response = "";
		return userService.isUniqueEmail(email) ? AppConstants.UNIQUE : AppConstants.DUPLICATE;
		/*
		 * if (isUnique) { response = "UNIQUE"; } else { response = "DUPLICATE"; }
		 * return response;
		 */
	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam("countryId") Integer countryId) {
		return userService.loadStatesByCountryId(countryId);
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvnt(@RequestParam("stateId") Integer stateId) {
		return userService.loadCitiesByStateId(stateId);
	}

	@PostMapping("/userRegistration")
	public String handleRegisterBtn(UserAccounts userAcc, Model model) {
		boolean isSaved = userService.saveUserAccount(userAcc);

		if (isSaved) {
			model.addAttribute(AppConstants.SUCC_MSG,
					"Your Registration almost finished. Please check your email to unlock your account.");
		} else {
			model.addAttribute(AppConstants.FAIL_MSG, "Registration Failed");
		}
		return AppConstants.REGISTRATION_VIEW_NAME;
	}
}