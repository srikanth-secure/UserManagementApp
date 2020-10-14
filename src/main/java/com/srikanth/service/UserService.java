package com.srikanth.service;

import java.util.Map;

import com.srikanth.pojo.UserAccounts;

public interface UserService {

	public String loginCheck(String email, String pwd);

	public Map<Integer, String> loadCountries();

	public Map<Integer, String> loadStatesByCountryId(Integer countryId);

	public Map<Integer, String> loadCitiesByStateId(Integer stateId);

	public boolean isUniqueEmail(String email);

	public String generateTempPwd();

	public boolean saveUserAccount(UserAccounts userAccount);

	public String getRegSuccMailBody(UserAccounts userAccount);

	public boolean sendRegSuccEmail(String to, String subject, String body);

	public boolean isTempPwdValid(String email, String tempPwd);

	public boolean unlockAccount(String email, String password);

	public String recoverPassword(String email);

	public String getRecoverPwdEmailBody(UserAccounts userAccount);

	public String sendPwdToEmail(String email, String subject, String body);
}
