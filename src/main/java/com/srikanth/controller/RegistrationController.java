package com.srikanth.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srikanth.pojo.UserAccounts;

@Controller
public class RegistrationController {

	@GetMapping("/register")
	public String loadRegForm(Model model) {
		// TODO: We should write logic here
		return "registration";
	}

	@GetMapping("/uniqueMailCheck")
	public @ResponseBody String isEmailUnique(@RequestParam("email") String email) {
		String response = "";
		// TODO:We should write logic here
		return response;
	}

	@GetMapping("/countryChange")
	public @ResponseBody Map<Integer, String> handleCountryChangeEvnt(@RequestParam("countryId") Integer countryId) {
		Map<Integer, String> statesMap = null;
		// TODO: We should write logic here
		return statesMap;
	}

	@GetMapping("/stateChange")
	public @ResponseBody Map<Integer, String> handleStateChangeEvnt(@RequestParam("stateId") Integer stateId) {
		Map<Integer, String> citiesMap = null;
		// TODO: We should write logic here
		return citiesMap;
	}

	@PostMapping("/userRegistration")
	public String handleRegisterBtn(UserAccounts userAcc, Model model) {
		// TODO: We should write logic here
		return "registration";
	}

}