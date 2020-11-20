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

import com.srikanth.constants.AppConstants;
import com.srikanth.entity.CitiesMasterEntity;
import com.srikanth.entity.CountryMasterEntity;
import com.srikanth.entity.StatesMasterEntity;
import com.srikanth.entity.UserAccountEntity;
import com.srikanth.pojo.UserAccounts;
import com.srikanth.props.AppProperties;
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

	@Autowired
	private AppProperties appProps;

	@Override
	public String loginCheck(String email, String pwd) {
		UserAccountEntity userAccountEntity = userAccRepo.findByUserEmailAndUserPwd(email, pwd);
		if (userAccountEntity == null) {
			return AppConstants.INVALID_DETAILS;
		} else if (userAccountEntity.getAccStatus().equals(AppConstants.LOCKED)) {
			String accLockMsg = appProps.getMessages().get(AppConstants.ACC_LOCK_MSG);
			return accLockMsg;
		} else {
			return AppConstants.VALID;
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
		String AlphaNumericString = AppConstants.A2Z + AppConstants.NO_0_TO_9 + AppConstants.SMALL_A2Z;
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

		userAccount.setAccStatus(AppConstants.LOCKED);

		userAccount.setUserPwd(generateTempPwd());

		UserAccountEntity entity = new UserAccountEntity();
		BeanUtils.copyProperties(userAccount, entity);
		UserAccountEntity savedEntity = userAccRepo.save(entity);

		if (savedEntity.getUserId() != null) {
			String to = userAccount.getUserEmail();
			String subject = AppConstants.REG_SUCCESS;
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

		String fileName = AppConstants.REGISTRATION_MAIL_BODY_FILE;
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, "");
			Stream<String> lines = Files.lines(path);
			// String replaceLines = user.getUserPwd()
			// + "<a href=\'http://localhost:9090/unlockAcc?email=sri@yahoo.com\'>
			// Click here to unlockhere";

			replacedLines = lines
					.map(line -> line.replace("{FNAME}", user.getFirstName()).replace("{LNAME}", user.getLastName())
							.replace("{TEMP-PWD}", user.getUserPwd()).replace("{EMAIL}", user.getUserEmail()))
					.collect(Collectors.toList());

			mailBody = String.join(AppConstants.EMPTY_STR, replacedLines);
			// mailBody = String.join("", replaceLines);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}

	@Override
	public String getRecoverPwdEmailBody(UserAccounts userAccount) {
		String fileName = AppConstants.RECOVERY_PWD_MAIL_BODY_FILE;
		List<String> replacedLines = null;
		String mailBody = null;
		try {
			Path path = Paths.get(fileName, AppConstants.EMPTY_STR);
			Stream<String> lines = Files.lines(path);
			replacedLines = lines
					.map(line -> line.replace("{FNAME}", userAccount.getFirstName())
							.replace("{LNAME}", userAccount.getLastName()).replace("{PWD}", userAccount.getUserPwd()))
					.collect(Collectors.toList());

			mailBody = String.join(AppConstants.EMPTY_STR, replacedLines);
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
		entity.setAccStatus(AppConstants.UNLOCKED);
		entity.setUserPwd(password);
		UserAccountEntity savedEntity = userAccRepo.save(entity);
		return savedEntity.getUserId() != null ? true : false;
	}

	@Override
	public String recoverPassword(String email) {
		UserAccountEntity entity = userAccRepo.findByUserEmail(email);
		if (entity != null) {
			UserAccounts acc = new UserAccounts();
			BeanUtils.copyProperties(entity, acc);
			String body = getRecoverPwdEmailBody(acc);
			String to = acc.getUserEmail();
			String subject = AppConstants.PAZZWORD_RECOVERY;
			sendPwdToEmail(to, subject, body);
			return AppConstants.SUCCESS;
		} else {
			return AppConstants.FAIL;
		}
	}

	@Override
	public String sendPwdToEmail(String to, String subject, String body) {
		boolean sendEmail = emailUtils.sendEmail(to, subject, body);
		if (sendEmail) {
			return AppConstants.EMAIL_SENT;
		}
		return AppConstants.EMAIL_SENT_FAIL;
	}
}