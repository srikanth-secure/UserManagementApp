package com.srikanth.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srikanth.entity.CitiesMasterEntity;
import com.srikanth.entity.CountryMasterEntity;
import com.srikanth.entity.StatesMasterEntity;
import com.srikanth.entity.UserAccountEntity;
import com.srikanth.pojo.UserAccounts;
import com.srikanth.repo.CitiesMasterRepository;
import com.srikanth.repo.CountryMasterRepository;
import com.srikanth.repo.StatesMasterRepository;
import com.srikanth.repo.UserAccountsRepository;
import com.srikanth.util.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAccountsRepository userAccRepo;

	@Autowired
	private CountryMasterRepository countryRepo;

	@Autowired
	private StatesMasterRepository statesRepo;

	@Autowired
	private CitiesMasterRepository citiesRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccountEntity userAccountEntity = userAccRepo.findByUserEmailAndUserPwd(email, pwd);
		if (userAccountEntity == null) {
			return "Invalid Details";
		} else if (userAccountEntity.getAccStatus().equals("LOCKED")) {
			return "Your account is locked. Please check your email and unlock it.";
		} else {
			return "VALID";
		}
	}

	@Override
	public Map<Integer, String> loadCountries() {
		Map<Integer, String> countryMap = new HashMap<>();
		List<CountryMasterEntity> entitiesList = countryRepo.findAll();
		entitiesList.forEach(entity -> {
			countryMap.put(entity.getCountryId(), entity.getCountryName());
		});
		return countryMap;
	}

	@Override
	public Map<Integer, String> loadStatesByCountryId(Integer countryId) {
		Map<Integer, String> stateMap = new HashMap<>();
		List<StatesMasterEntity> entitiesList = statesRepo.findByCountryId(countryId);
		entitiesList.forEach(entity -> {
			stateMap.put(entity.getStateId(), entity.getStateName());
		});
		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCitiesByStateId(Integer stateId) {
		Map<Integer, String> cityMap = new HashMap<>();
		List<CitiesMasterEntity> entitiesList = citiesRepo.findByStateId(stateId);
		entitiesList.forEach(entity -> {
			cityMap.put(entity.getCityId(), entity.getCityName());
		});
		return cityMap;
	}

	@Override
	public boolean isUniqueEmail(String email) {
		UserAccountEntity userAccEntity = userAccRepo.findByUserEmail(email);
		return userAccEntity != null ? false : true;
	}

	@Override
	public String generateTempPwd() {
		// chose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());
			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

	@Override
	public boolean saveUserAccount(UserAccounts userAccount) {

		userAccount.setAccStatus("LOCKED");

		userAccount.setUserPwd(generateTempPwd());

		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(userAccount, entity);
		UserAccountEntity savedEntity = userAccRepo.save(entity);

		if (savedEntity.getUserId() != null) {
			String to = userAccount.getUserEmail();
			String subject = "Registration Successfull";
			String body = getRegSuccMailBody(userAccount);
			sendRegSuccEmail(to, subject, body);
			return true;
		}
		return false;
		// return savedEntity.getUserId() != null ? true : false;
		/*
		 * Integer userId = savedEntity.getUserId(); if (userId != null) { return true;
		 * } else { return false; }
		 */
	}

	@Override
	public String getRegSuccMailBody(UserAccounts user) {

		String fileName = "Mail Body Template.txt";
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			String replaceLines = user.getUserPwd()
					+ "<a href=\'http://localhost:9090/unlockAcc?email=sri@yahoo.com\'>Click here to unlockhere";
			/*
			 * replacedLines = (List<String>) lines .map(line -> line .replace("{FNAME}",
			 * user.getFirstName()) .replace("{LNAME}", user.getLastName())
			 * .replace("{TEMP-PWD}", user.getUserPwd()) .replace("{EMAIL}",
			 * user.getUserEmail()) .collect(Collectors.toList()) );
			 */

			// mailBody = String.join("", replacedLines);
			mailBody = String.join("", replaceLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public boolean sendRegSuccEmail(String to, String subject, String body) {
		return emailUtils.sendEmail(to, subject, body);
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		UserAccountEntity entity = userAccRepo.findByUserEmailAndUserPwd(email, tempPwd);
		return entity != null ? true : false;
	}

	@Override
	public boolean unlockAccount(String email, String password) {
		UserAccountEntity entity = userAccRepo.findByUserEmail(email);
		entity.setAccStatus("UNLOCKED");
		entity.setUserPwd(password);
		UserAccountEntity savedEntity = userAccRepo.save(entity);
		return savedEntity.getUserId() != null ? true : false;
	}

	@Override
	public String recoverPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccounts userAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String sendPwdToEmail(String email, String subject, String body) {
		// TODO Auto-generated method stub
		return null;
	}

}
