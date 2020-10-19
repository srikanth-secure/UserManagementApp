package com.srikanth.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srikanth.entity.UserAccountEntity;

public interface UserAccountsRepository extends JpaRepository<UserAccountEntity, Serializable> {

	// select * from USER_ACCOUNT where USER_EMAIL=?
	public UserAccountEntity findByUserEmail(String email);

	// select * from USER_ACCOUNT where USER_EMAIL=? and USER_PWD=?
	public UserAccountEntity findByUserEmailAndUserPwd(String email, String TempPwd);

}